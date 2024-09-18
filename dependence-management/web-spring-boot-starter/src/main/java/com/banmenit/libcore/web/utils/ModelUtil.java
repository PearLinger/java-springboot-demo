package com.banmenit.libcore.web.utils;

import com.banmenit.libcore.common.api.ResultCode;
import com.banmenit.libcore.common.exception.ApiException;
import com.banmenit.libcore.common.utils.AuthorizationDataUtil;
import com.banmenit.libcore.common.utils.JsonUtils;
import com.banmenit.libcore.common.utils.SnowflakeIdFactory;
import com.banmenit.libcore.web.model.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 基本模型数据填充工具类
 * 依赖BeanUtils.copyProperties实现不适合对象内有对象的!!!
 *
 * @author tangsq
 * @date 2022/12/12
 */
@Slf4j
public class ModelUtil {

    /**
     * 有重复风险, 暂时不用管
     */
    private static final SnowflakeIdFactory SNOW_FLAKE_ID_FACTORY
            = new SnowflakeIdFactory((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() % 1024), 0);


    /**
     * 新增dto转实体，并填充新增基本属性值
     *
     * @param s     源对象
     * @param clazz 目标对象
     * @return {@link T}
     */
    public static <T extends BaseModel<T>, S> T addDto2Entity(S s, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(s, t);
            //设置新增属性值
            addBaseFieldValue(t);
            return t;
        } catch (Exception e) {
            log.error("dto转实体类发生异常,{}:{},{}", JsonUtils.toJson(s), clazz.getDeclaredFields(), e.getMessage());
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
    }

    /**
     * 更新dto转实体，并填充新增基本属性值
     *
     * @param s     源对象
     * @param clazz 目标对像
     * @return {@link T}
     */
    public static <T extends BaseModel<T>, S> T updateDto2Entity(S s, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(s, t);
            //设置更新属性值
            updateBaseFieldValue(t);
            return t;
        } catch (Exception e) {
            log.error("dto转实体类发生异常,{}:{},{}", JsonUtils.toJson(s), clazz.getDeclaredFields(), e.getMessage());
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
    }

    /**
     * 对象转换
     *
     * @param s     源对象
     * @param clazz 目标对象
     * @return {@link T}
     */
    public static <S, T> T transferObj(S s, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(s, t);
            return t;
        } catch (Exception e) {
            log.error("类转换发生异常,{}:{},{}", JsonUtils.toJson(s), clazz.getDeclaredFields(), e.getMessage());
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
    }


    /**
     * 新增设置基本字段值
     *
     * @param baseModel
     */
    public static void addBaseFieldValue(BaseModel baseModel) {
        baseModel.setId(SNOW_FLAKE_ID_FACTORY.nextId());
        baseModel.setCreationTime(System.currentTimeMillis());
        baseModel.setLastModificationTime(System.currentTimeMillis());
        //使用AuthorizationDataUtil方便多线程更改值
        baseModel.setCreatorUserId(AuthorizationDataUtil.getRequestData().getSub());
        baseModel.setLastModifierUserId(AuthorizationDataUtil.getRequestData().getSub());
    }

    /**
     * 修改设置基本属性
     *
     * @param baseModel
     */
    public static void updateBaseFieldValue(BaseModel baseModel) {
        baseModel.setLastModificationTime(System.currentTimeMillis());
        baseModel.setLastModifierUserId(AuthorizationDataUtil.getRequestData().getSub());
    }

}
