package com.wwdy.auth.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDateTime;

/**
 * @author wwdy
 * @date 2022/2/21 18:02
 */
@Configuration
public class JacksonSerializerConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new JacksonSerializer.LocalDateTimeJsonSerializer());
        timeModule.addDeserializer(LocalDateTime.class, new JacksonSerializer.LocalDateTimeJsonDeserializer());
        objectMapper.registerModule(timeModule);
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new JacksonSerializerModifier()));
        objectMapper.getSerializerProvider().setNullValueSerializer(new JacksonSerializer.NullObjectJsonSerializer());
        return objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(ObjectMapper objectMapper){
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

}
