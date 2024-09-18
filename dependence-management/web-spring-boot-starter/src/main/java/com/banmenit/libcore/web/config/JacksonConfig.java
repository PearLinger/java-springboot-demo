package com.banmenit.libcore.web.config;//package com.banmenit.libcore.web.config;
//
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author yangyi
// * @version v 1.0
// * @date 2022/12/5 8:55
// */
//@Configuration
//public class JacksonConfig {
//
//    /**
//     * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
//     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
//     */
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
//                .serializerByType(Long.class, LongToStringSerializer.instance)
//                .serializerByType(Long.TYPE, LongToStringSerializer.instance);
//    }
//}
//
