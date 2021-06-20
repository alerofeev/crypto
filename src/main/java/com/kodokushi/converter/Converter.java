package com.kodokushi.converter;

import java.util.ArrayList;

public interface Converter {

    /**
     * Перезаписывает массив символов в строку
     * @param charArray массив символов
     * @return String
     */
    static String mCharArrayToString(char[] charArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : charArray) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * Перезаписывает строку в массив символов
     * @param string строка
     * @return char[]
     */
    static char[] mStringToCharArray(String string) {
        char[] charArray = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            charArray[i] = string.charAt(i);
        }
        return charArray;
    }

    /**
     * Конвертирует массив символов в строку с символами в шестнадцатиричном представлении
     * @param charArray массив символов
     * @return String
     */
    static String mCharArrayToHexString(char[] charArray) {
        StringBuilder hexString = new StringBuilder();
        for (int c : charArray) {
            hexString.append(Integer.toHexString(c)).append(" ");
        }
        return hexString.toString();
    }

    /**
     * Конвертирует строку с символами в шестнадцатиричном представлении в массив символов
     * @param hexString строка
     * @return char[]
     */
    static char[] mHexStringToCharArray(String hexString) {
        ArrayList<Character> tempCharArray = new ArrayList<>();
        StringBuilder tempHex = new StringBuilder("");
        for (int i = 0; i < hexString.length(); i++) {
            if (hexString.charAt(i) != ' ') {
                tempHex.append(hexString.charAt(i));
                if (hexString.charAt(i + 1) == ' ' || i + 1 == hexString.length()) {
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
