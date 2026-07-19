package com.amreshmaurya.payflow.util;

import java.security.SecureRandom;
import java.util.Base64;

public final class ApiKeyGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private ApiKeyGenerator() {
    }

    public static String generateApiKey() {
        byte[] bytes = new byte[32];   // 256-bit
        RANDOM.nextBytes(bytes);

        return "pk_" + Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }

    public static String generateSecretKey() {
        byte[] bytes = new byte[64];   // 512-bit
        RANDOM.nextBytes(bytes);

        return "sk_" + Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
