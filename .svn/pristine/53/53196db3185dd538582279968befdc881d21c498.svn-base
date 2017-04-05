package com.jyu.sati.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author louis.tsang
 */
public class MD5 {

    private static final String HEX_NUMS_STR = "0123456789ABCDEF";

    /**
     * �?16进制字符串转换成字节数组
     * 
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    /**
     * 将指定byte数组转换�?16进制字符�?
     * 
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 将username password 继续加盐 然后转换�?16进制字符�?
     * 
     * @param md5code
     * @param name
     * @return
     */
    public static String encode(String md5code, String name) {
        // 创建消息摘要对象
        MessageDigest md;
        byte[] digest = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(name.getBytes());
            md.update(md5code.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteToHexString(digest);
    }

    // 不加盐处�?
    public static String encode(String code) {
        MessageDigest md;
        byte[] digest = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(code.getBytes());
            digest = md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteToHexString(digest);
    }

    /**
     * 比较继续加盐处理的username,password是否正确
     * 
     * @param md5code
     * @param name
     * @param dbmd5code
     * @return
     */
    public static boolean validateUser(String md5code, String name,
            String dbmd5code) {
        byte[] md5codebyte = hexStringToByte(encode(md5code, name));
        byte[] dbmd5codebyte = hexStringToByte(dbmd5code);
        if (Arrays.equals(md5codebyte, dbmd5codebyte))
            return true;
        return false;
    }

    /**
     * 加盐：形式MD5（MD5（A�?+MD5（B））�?
     * 
     * @param code
     * @return
     */
    public static String encodeDouble(String stringA, String stringB) {
        return encode(encode(stringA) + encode(stringB));
    }

 
    /**
     * 获取文件的MD5�?
     * 
     * @param inputStream
     * @return String
     */
    public static String calcFileHash(InputStream inputStream) {
        String result = null;
        if (inputStream == null) {
            return null;
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return result;
        }
        byte buffer[] = new byte[8192];
        int len;
        try {
            while ((len = inputStream.read(buffer, 0, 8192)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        result = bigInt.toString(16).toUpperCase();
        return result;
    }


}
