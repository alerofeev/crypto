package com.kodokushi.crypto;

import java.security.SecureRandom;

public class KeyGenerator {

    public static char[] mCreateKey(int keySize) {
        char[] key;
        switch (keySize) {
            case 128 -> key = new char[16];
            case 192 -> key = new char[24];
            case 256 -> key = new char[32];
            default -> throw new IllegalStateException("Unexpected value: " + keySize);
        }
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < key.length; i++) {
            key[i] = (char) secureRandom.nextInt(256);
        }
        return key;
    }
}
