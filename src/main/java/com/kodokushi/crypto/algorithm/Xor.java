package com.kodokushi.crypto.algorithm;

public interface Xor {

    /**
     * Шифрует/дешифрует сообщение посредством побитовой операции XOR
     * @param message сообщение
     * @param key ключ
     */
    static void mEncryptDecrypt(char[] message, char[] key) {
        for (int i = 0; i < message.length; i++) {
            message[i] ^= key[i % key.length];
        }
    }
}

