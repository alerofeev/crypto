package com.kodokushi.converter;

import java.util.ArrayList;

public interface Converter {

    static char[] mStringToCharArray(String string) {
        char[] charArray = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            charArray[i] = string.charAt(i);
        }
        return charArray;
    }

    static String mCharArrayToString(char[] charArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : charArray) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    static String mCharArrayToHexString(char[] charArray) {
        StringBuilder hexString = new StringBuilder();
        for (int c : charArray) {
            if (Integer.toHexString(c).length() == 1) {
                hexString.append("0").append(Integer.toHexString(c)).append(" ");
            } else {
                hexString.append(Integer.toHexString(c)).append(" ");
            }
        }
        return hexString.toString();
    }

    static char[] mHexStringToCharArray(String hexString) {
        ArrayList<Character> tempCharArray = new ArrayList<>();
        StringBuilder tempHex = new StringBuilder();
        for (int i = 0; i < hexString.length(); i++) {
            if (hexString.charAt(i) != ' ') {
                tempHex.append(hexString.charAt(i));
                if (hexString.charAt(i + 1) == ' ') {
                    tempCharArray.add((char) Long.parseLong(tempHex.toString(), 16));
                    tempHex.delete(0, tempHex.length());
                }
            }
        }
        char[] charArray = new char[tempCharArray.size()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = tempCharArray.get(i);
        }
        return charArray;
    }
}
