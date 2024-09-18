package com.banmenit.libcore.common.api;

import com.banmenit.libcore.common.context.GlobalInstanceContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangsq
 */
@Slf4j
@JacksonStdImpl
public class GlobalizationSerializer extends ToStringSerializerBase {


    /**
     * <p>
     * Note: usually you should NOT create new instances, but instead use
     * {@link #INSTANCE} which is stateless and fully thread-safe. However,
     * there are cases where constructor is needed; for example,
     * when using explicit serializer annotations like
     * {@link com.fasterxml.jackson.databind.annotation.JsonSerialize#using}.
     */
    public GlobalizationSerializer() {
        super(Object.class);
    }


    /**
     * Singleton instance to use.
     */
    public final static GlobalizationSerializer INSTANCE = new GlobalizationSerializer();


    /**
     * Sometimes it may actually make sense to retain actual handled type.
     */
    public GlobalizationSerializer(Class<?> handledType) {
        super(handledType);
    }

    @Override
    public final String valueToString(Object value) {
        try {
            if (value == null) {
                return null;
            }
            UpdateResultAware updateResultAware = GlobalInstanceContext.getInstance().getUpdateResultAware();
            if (updateResultAware == null || !(value instanceof String)) {
                // 只能对string进行国际化，不然直接返回对象toString方法
                return value.toString();
            }
            return updateResultAware.updateResult((String) value);
        } catch (Exception e) {
            log.error("全球化发生异常", e);
        }
        return value.toString();
    }
}