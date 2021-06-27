package com.kodokushi.crypto.algorithm;

import java.util.ArrayList;

public interface Xor {

    static void mEncryptDecrypt(ArrayList<Byte> message, byte[] key) {
        for (int i = 0; i < message.size(); i++) {
            message.set(i, (byte) (message.get(i) ^ key[i % key.length]));
        }
    }
}

