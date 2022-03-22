package com.wwdy.admin.config;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.Collection;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/2/21 19:59
 */
public class JacksonSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        beanProperties.forEach(writer -> {
            if (isArrayType(writer)) {
                writer.assignNullSerializer(new JacksonSerializer.NullArrayJsonSerializer());
            }
            if (isStringType(writer)) {
                writer.assignNullSerializer(new JacksonSerializer.NullStringJsonSerializer());
            }
            if (isNumberType(writer)) {
                writer.assignNullSerializer(new JacksonSerializer.NullNumberJsonSerializer());
            }
            if (isBooleanType(writer)) {
                writer.assignNullSerializer(new JacksonSerializer.NullBooleanJsonSerializer());
            }
        });
        return super.changeProperties(config, beanDesc, beanProperties);
    }

    /**
     * 是否是数组
     * @param writer BeanPropertyWriter
     * @return boolean
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是String
     * @param writer BeanPropertyWriter
     * @return boolean
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是数值类型
     * @param writer BeanPropertyWriter
     * @return boolean
     */
    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是boolean
     * @param writer BeanPropertyWriter
     * @return boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }

}
