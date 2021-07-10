package com.kodokushi.crypto.algorithm;

public class Gost {

    public Gost() { }

    public static char[] mPadMessage(char[] message) {
        char[] paddedMessage = new char[8 * (message.length / 8 + 1)];
        System.arraycopy(message, 0, paddedMessage, 0, message.length);
        for (int i = message.length; i < paddedMessage.length; i++) {
            paddedMessage[i] = 0x00;
        }
        return paddedMessage;
    }
}
