package com.beta.v2_0_0.replyservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static String reverseUsingXOR(String message) {
        char[] charArray = message.toCharArray();
        int low = 0, high = charArray.length - 1;
        while (low < high) {
            charArray[low] = (char) (charArray[low] ^ charArray[high]);
            charArray[high] = (char) (charArray[low] ^ charArray[high]);
            charArray[low] = (char) (charArray[low] ^ charArray[high]);
            low++;
            high--;
        }
        return String.valueOf(charArray);
    }

    public static String reverseUsingSwap(String message) {
        char[] charArray = message.toCharArray();
        int begin = 0, end = charArray.length - 1;
        char temp;
        while (end > begin) {
            temp = charArray[begin];
            charArray[begin] = charArray[end];
            charArray[end] = temp;
            end--;
            begin++;
        }
        return String.valueOf(charArray);
    }
}
