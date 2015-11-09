package com.zyl.centre.common.utils;

import java.util.UUID;


public class UUIDValueGenerator {
    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }
 
    public static String generateValue(String param) {
        return UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString();
    }
}