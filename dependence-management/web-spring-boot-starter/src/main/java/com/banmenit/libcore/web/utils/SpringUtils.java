package com.banmenit.libcore.web.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 * 
 * @author thinglinks
 */
@Component
public final class SpringUtils implements  ApplicationContextAware
{
    public static void main(String[] args) {
        SpringUtils.getBean("ss");
    }
    /** Spring应用上下文环境 */
    private static ApplicationContext applicationContext;

    private final static String APP_NAME_PROP_KEY = "spring.application.name";

    private static final String LOCAL = "local";
    private static final String DEV = "dev";
    private static final String TEST = "test";
    private static final String PROD = "prod";


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     *
     */
    @SuppressWarnings("unchecked")
    public static Object getBean(String name)
    {
        Object o = null;
        try {
            o = getApplicationContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
        }
        return o;
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @return
     * @throws BeansException
     *
     */
    public static <T> T getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name)
    {
        return getApplicationContext().containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     *
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return getApplicationContext().isSingleton(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     *
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return getApplicationContext().getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     *
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException
    {
        return getApplicationContext().getAliases(name);
    }

    /**
     * 获取aop代理对象
     * 
     * @param invoker
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker)
    {
        return (T) AopContext.currentProxy();
    }

    /**
     * 获取spring.profiles.active
     */
    public static String getActiveProfile() {
        String[] activeProfiles = getEnvironment().getActiveProfiles();
        if (activeProfiles == null || activeProfiles.length == 0) {
            return null;
        }
        return getEnvironment().getActiveProfiles()[0];
    }

    /**
     * 本地环境
     *
     * @return
     */
    public static boolean isLocal() {
        return StrUtil.equals(getActiveProfile(), LOCAL);
    }

    /**
     * dev 环境
     *
     * @return
     */
    public static boolean isDev() {
        return StrUtil.equals(getActiveProfile(), DEV);
    }

    /**
     * test 环境
     *
     * @return
     */
    public static boolean isTest() {
        return StrUtil.equals(getActiveProfile(), TEST);
    }

    /**
     * 生产环境
     *
     * @return
     */
    public static boolean isProd() {
        return StrUtil.equals(getActiveProfile(), PROD);
    }

    /**
     * 获取应用名称
     *
     * @return
     */
    public static String getApplicationName() {
        return getEnvironment().getProperty(APP_NAME_PROP_KEY);
    }

    private static Environment getEnvironment() {
        return getApplicationContext().getEnvironment();
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取spring bean 属性值为空的 属性名称
     *
     * @param source
     * @return: {@link String[]}
     * @throws:
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        if (pds != null) {
            return Stream.of(pds).map(pd -> pd.getName())
                    .filter(name -> src.getPropertyValue(name) == null)
                    .distinct().toArray(String[]::new);
        }
        return new String[]{};
    }
}
