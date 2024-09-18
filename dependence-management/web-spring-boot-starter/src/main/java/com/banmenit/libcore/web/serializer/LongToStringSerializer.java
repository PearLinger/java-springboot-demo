package com.banmenit.libcore.web.serializer;//package com.banmenit.libcore.web.serializer;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//
//import java.io.IOException;
//
///**
// * 对于超过指定大小的long类型，序列化为字符串
// **/
//public class LongToStringSerializer extends JsonSerializer<Long> {
//
//    public final static LongToStringSerializer instance = new LongToStringSerializer();
//
//    /**
//     * 最大允许的long值，采用前端的Number.MAX_SAFE_INTEGER
//     */
//    public static final long MAX_SAFE_INTEGER = 9007199254740991L;
//
//    @Override
//    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        if (value == null) {
//            gen.writeNull();
//        }
//        else if(value > MAX_SAFE_INTEGER || value < -MAX_SAFE_INTEGER) {
//            gen.writeString(value.toString());
//        }
//        else {
//            gen.writeNumber(value);
//        }
//    }
//}
//
