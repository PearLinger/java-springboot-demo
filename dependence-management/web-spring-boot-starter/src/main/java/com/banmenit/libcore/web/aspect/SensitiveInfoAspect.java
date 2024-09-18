package com.banmenit.libcore.web.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.banmenit.libcore.web.annotation.Sensitive;
import com.banmenit.libcore.web.annotation.SensitiveInfo;
import com.banmenit.libcore.web.annotation.SensitiveInput;
import com.banmenit.libcore.web.config.SecureConfig;
import com.banmenit.libcore.web.model.SensitiveType;
import com.banmenit.libcore.web.utils.SecureUtils;
import com.banmenit.libcore.web.utils.SensitiveInfoUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Aspect
@Component
class SensitiveInfoAspect {
    @Autowired
    private SecureConfig secureConfig;

    @Pointcut("@annotation(com.banmenit.libcore.web.annotation.Sensitive)||@annotation(com.banmenit.libcore.web.annotation.SensitiveInput)")
    public void sensitive() {

    }

    @Around("sensitive()")
    public Object sensitiveInfoHandle(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        //脱敏数据 入参加密处理
        SensitiveInput sensitiveInput = AnnotatedElementUtils.findMergedAnnotation(method, SensitiveInput.class);
        if (sensitiveInput != null) {
            Class<?>[] targetClass = sensitiveInput.targetClass();
            Class<?>[] ignoreClass = sensitiveInput.ignoreClass();
            for (Object arg : jp.getArgs()) {
                sensitiveFieldHandle(targetClass, ignoreClass, arg, true);
            }
        }
        // 调用切点方法
        Object result = jp.proceed();
        if (null == result) {
            return result;
        }
        //脱敏数据 出参解密 脱敏处理
        Sensitive sensitive = AnnotatedElementUtils.findMergedAnnotation(method, Sensitive.class);
        if (null == sensitive) {
            return result;
        }
        Class<?>[] targetOutClass = sensitive.targetClass();
        Class<?>[] ignoreOutClass = sensitive.ignoreClass();
        sensitiveFieldHandle(targetOutClass, ignoreOutClass, result, false);

        return result;
    }

    private void sensitiveFieldHandle(Class[] targetClass, Class[] ignoreClass, Object data, boolean isInput) throws IllegalAccessException {
        if (null == data) {
            return;
        }
        Class clazz = data.getClass();
        if (clazz == Field.class) {
            clazz = ((Field) data).getType();
        }
        //是否是集合
        if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) data;
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                sensitiveFieldHandle(targetClass, ignoreClass, iterator.next(), isInput);
            }
        } else if (clazz.isArray()) {
            //是否是数组
            Object[] array = (Object[]) data;
            for (Object o : array) {
                sensitiveFieldHandle(targetClass, ignoreClass, o, isInput);
            }
        } else {
            //校验目标对象
            if (targetClass != null && targetClass.length > 0 && !isInClass(targetClass, clazz)) {
                return;
            }
            //校验忽略对象
            if (ignoreClass != null && ignoreClass.length > 0 && isInClass(ignoreClass, clazz)) {
                return;
            }
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (null == field.get(data)) {
                    continue;
                }
                if (Collection.class.isAssignableFrom(field.getType())) {
                    //处理集合类型
                    Object filedData = field.get(data);
                    Collection collection = (Collection) filedData;
                    SensitiveInfo sensitiveInfo = field.getAnnotation(SensitiveInfo.class);
                    if (sensitiveInfo != null && isStringCollection(collection)) {
                        Iterator iterator = collection.iterator();
                        List<String> newList = new ArrayList<>();
                        while (iterator.hasNext()) {
                            String value = handlerData((String) iterator.next(), isInput, sensitiveInfo);
                            newList.add(value);
                        }
                        collection.clear();
                        collection.addAll(newList);
                        field.set(data, collection);
                    } else if (isBeanCollection(collection)) {
                        sensitiveFieldHandle(targetClass, ignoreClass, field.get(data), isInput);
                    }
                } else if (field.getType().isArray()) {
                    //处理数组类型
                    Object[] array = (Object[]) field.get(data);
                    SensitiveInfo sensitiveInfo = field.getAnnotation(SensitiveInfo.class);
                    if (sensitiveInfo != null && isStringArray(array)) {
                        for (int i = 0; i < array.length; i++) {
                            String value = handlerData((String) array[i], isInput, sensitiveInfo);
                            array[i] = value;
                        }
                        field.set(data, array);
                    } else {
                        sensitiveFieldHandle(targetClass, ignoreClass, field.get(data), isInput);
                    }
                } else {
                    SensitiveInfo sensitiveInfo = field.getAnnotation(SensitiveInfo.class);
                    if (String.class != field.getType() || sensitiveInfo == null) {
                        continue;
                    }
                    String value = (String) field.get(data);
                    value = handlerData(value, isInput, sensitiveInfo);
                    field.set(data, value);
                }
            }
        }
    }

    private boolean isBeanCollection(Collection collection) {
        if (CollectionUtil.isEmpty(collection)) {
            return false;
        }
        Object data = collection.iterator().next();
        if (data instanceof String || data instanceof Long || data instanceof Short || data instanceof Integer
                || data instanceof Float || data instanceof Double || data instanceof Character || data instanceof Byte || data instanceof Boolean) {
            return false;
        }
        return true;
    }

    private boolean isStringArray(Object[] array) {
        if (ArrayUtil.isEmpty(array)) {
            return false;
        }
        if (array[0] instanceof String) {
            return true;
        }
        return false;
    }

    private String handlerData(String value, boolean isInput, SensitiveInfo sensitiveInfo) {
        if (isInput) {
            //编码处理
            if (sensitiveInfo.encode()) {
                if (StrUtil.isNotEmpty(sensitiveInfo.encodeKey())) {
                    value = SecureUtils.encode(value, sensitiveInfo.encodeKey());
                } else {
                    value = SecureUtils.encode(value, secureConfig.getSensitiveAesKey());

                }
            }
        } else {
            //解码处理
            if (sensitiveInfo.decode()) {
                if (StrUtil.isNotEmpty(sensitiveInfo.decodeKey())) {
                    value = SecureUtils.decode(value, sensitiveInfo.decodeKey());
                } else {
                    value = SecureUtils.decode(value, secureConfig.getSensitiveAesKey());

                }
            }
            //数据脱敏处理 todo 在此添加新的数据脱敏种类
            SensitiveType type = sensitiveInfo.type();
            switch (type) {
                case USER_PHONE:
                    value = SensitiveInfoUtil.phone(value);
                    break;
                case ID_CARD:
                    value = SensitiveInfoUtil.idCard(value);
                    break;
                case NONE:
                    break;
            }
        }
        return value;
    }

    private boolean isStringCollection(Collection collection) {
        if (CollectionUtil.isEmpty(collection)) {
            return false;
        }
        Iterator iterator = collection.iterator();
        if (iterator.next() instanceof String) {
            return true;
        }
        return false;
    }

    private boolean isInClass(Class[] classes, Class calzz) {
        for (Class aClass : classes) {
            if (aClass == calzz) {
                return true;
            }
        }
        return false;
    }
}