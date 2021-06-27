package com.kodokushi.crypto;

import com.kodokushi.crypto.algorithm.Aes;
import com.kodokushi.crypto.algorithm.Xor;

// import java.security.SecureRandom;
import java.util.Random;

public record Crypto(String algorithmName) implements Xor, Aes {

    private static final int KEY_LENGTH = 16;

    public void mEncrypt(char[] message, char[] key) {
        switch (this.algorithmName) {
            case "AES" -> {
                for (int i = 0; i < message.length; i += 16) {
                    Aes.mEncrypt(message, key, i);
                }
            }
            case "XOR" -> Xor.mEncryptDecrypt(message, key);
        }
    }

    public void mDecrypt(char[] message, char[] key) {
        switch (this.algorithmName) {
            case "AES" -> {
                for (int i = 0; i < message.length; i += 16) {
                    Aes.mDecrypt(message, key, i);
                }
            }
            case "XOR" -> Xor.mEncryptDecrypt(message, key);
        }
    }

/*
    /**
     * Генерирует криптографически безопасные значения для ключа
     * @return char[]
     *
    public char[] mSetSecureKey() {
        byte[] tempKey = new byte[KEY_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(tempKey);
        char[] key = new char[KEY_LENGTH];
        for (int i = 0; i < KEY_LENGTH; i++) {
            key[i] = (char) tempKey[i];
        }
        return key;
    }
*/

    public static char[] mSetKey() {
        Random random = new Random();
        char[] key = new char[KEY_LENGTH];
        for (int i = 0; i < KEY_LENGTH; i++) {
            key[i] = (char) random.nextInt(256);
        }
        return key;
    }

    public static char[] mPadMessage(char[] message) {
        if (message.length % 16 != 0) {
            char[] paddedMessage = new char[16 * (message.length / 16 + 1)];
            System.arraycopy(message, 0, paddedMessage, 0, message.length);
            for (int i = message.length; i < paddedMessage.length; i++) {
                paddedMessage[i] = 0x00;
            }
            return paddedMessage;
        }
        return message;
    }
}