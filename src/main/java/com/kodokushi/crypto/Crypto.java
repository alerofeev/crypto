package com.kodokushi.crypto;

import com.kodokushi.crypto.algorithm.Aes;
import com.kodokushi.crypto.algorithm.Xor;

// import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public record Crypto(String algorithm, String mode, ArrayList<Byte> message, byte[] key) implements Xor, Aes {

    private static final int KEY_LENGTH = 16;

    public static final String AES_128_ECB = "aes128ecb";
    public static final String GOST = "gost";
    public static final String XOR = "xor";

    public void mTransform() {
        switch (algorithm) {
            case AES_128_ECB -> {
                // TODO: add AES encrypt-decrypt
            }
            case GOST -> { }
            case XOR -> Xor.mEncryptDecrypt(message, key);
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