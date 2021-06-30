package com.kodokushi.crypto;

import com.kodokushi.crypto.algorithm.Aes;
import com.kodokushi.crypto.algorithm.Xor;

// import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public record Crypto(String algorithm, String mode, ArrayList<Byte> message, byte[] key) implements Xor, Aes {

    private static final int KEY_LENGTH = 16;

    public void mTransform() {
        switch (this.algorithm) {
            case "AES-128 ECB" -> {
                if (this.message.size() % 16 != 0) {
                    mPadMessage();
                }
                if (mode.equals("enc")) {
                    for (int i = 0; i < this.message.size(); i += 16) {
                        Aes.mEncrypt(message, key, i);
                    }
                } else {
                    for (int i = 0; i < this.message.size(); i += 16) {
                        Aes.mDecrypt(message, key, i);
                    }
                }
            }
            case "GOST" -> { }
            case "XOR" -> Xor.mEncryptDecrypt(this.message, this.key);
        }
    }

/*
    public byte[] mCreateSecureKey() {
        byte[] tempKey = new byte[KEY_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(tempKey);
        return key;
    }
*/

    public static byte[] mCreateKey() {
        Random random = new Random();
        byte[] key = new byte[KEY_LENGTH];
        for (int i = 0; i < KEY_LENGTH; i++) {
            key[i] = (byte) random.nextInt(256);
        }
        return key;
    }

    private void mPadMessage() {
        for (int i = this.message.size(); i < 16 * (this.message.size() / 16 + 1); i++) {
            this.message.add((byte) 0x00);
        }
    }
}