package com.xzy.guava.eventbus.simpledemo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class StaticFieldTest {
    /**
     * jackson json
     */
    private static final ObjectMapper jacksonMapper = new ObjectMapper();

    static {
        jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        jacksonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        System.out.println("static code field");
    }

    public static void test() {
        System.out.println("test");
        System.out.println(jacksonMapper.getDeserializationConfig().getDeserializationFeatures());
    }
}
