package com.kodokushi.crypto.algorithm;

public interface Xor {

    static void mEncryptDecrypt(char[] message, char[] key) {
        for (int i = 0; i < message.length; i++) {
            message[i] ^= key[i % key.length];
        }
    }
}

