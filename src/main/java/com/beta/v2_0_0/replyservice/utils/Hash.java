package com.beta.v2_0_0.replyservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Hash {

    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";

    public static String md5(String txt) {
        return Hash.getHash(txt, MD5);
    }

    public static String sha1(String txt) {
        return Hash.getHash(txt, SHA1);
    }

    private static String getHash(String txt, String hashType) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            byte[] byteArray = messageDigest.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b: byteArray) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
