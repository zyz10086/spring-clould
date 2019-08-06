package com.spring.study;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wangxia
 * @date 2019/8/6 16:35
 * @Description:
 */
public class Md5Utils {

    /**
     * 加盐方式生成md5校验码
     *
     * @param password
     * @return
     */
    public static String md5Password(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : result) {
                //与运算
                int number = b & 0xff;
                //加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(str);
            }
            // 标准的md5加密后的结果
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String MD5(String key) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = key.getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //使用指定的字节生成摘要
            digest.update(btInput);
            // 获得密文
            byte[] md=digest.digest();
            // 获得密文
            char[] str=new char[md.length*2];
            int k=0;
            for(int i=0;i<md.length;i++){
                byte byte0=md[i];
                str[k++]=hexDigits[byte0>>>4&0xf];
                str[k++]=hexDigits[byte0&0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";

    //使用第三方依赖
    public static String md5(String text) throws Exception{
//        String encodeStr= Base64.getEncoder().encodeToString(DigestUtils.md5Digest((text+slat).getBytes()));
        String encodeStr=DigestUtils.md5DigestAsHex((text+slat).getBytes());
        System.out.println(encodeStr);
        return encodeStr;
    }

    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) throws Exception {
        encrypt("你好啊");
    }

}
