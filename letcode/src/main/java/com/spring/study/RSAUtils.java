package com.spring.study;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
/**
 * 参考网址:https://blog.csdn.net/kikajack/article/details/80703894
 * Java RSA 加密工具类
 * PS:RSA加密对明文的长度有所限制，规定需加密的明文最大长度=密钥长度-11（单位是字节，即byte），
 * 所以在加密和解密的过程中需要分块进行。而密钥默认是1024位，即1024位/8位-11=128-11=117字节。
 * 所以默认加密前的明文最大长度117字节，解密密文最大长度为128字。那么为啥两者相差11字节呢？
 * 是因为RSA加密使用到了填充模式（padding），即内容不足117字节时会自动填满，用到填充模式自
 * 然会占用一定的字节，而且这部分字节也是参与加密的。
 */
public class RSAUtils {
    /**
     * 密钥长度 与原文长度对应 以及越长速度越慢  以bit为单位
     * 可加密明文长度：  key_size/8-7
     */
    private final static int KEY_SIZE = 1024;
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //base64加密
        //RSA 加密或签名后的结果是不可读的二进制，使用时经常会转为 BASE64 码再传输。
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //0表示公钥
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
    }

    /**
     * 用私钥对信息生成数字签名
     *1.对消息字符串的散列值（Message digest，用 MD5、SHA256 等算法求得的长度较短且固定的字符串）
     * 2.使用 RSA 的私钥计算签名（实际上仍然是加密消息）后，得到一个签名字符串
     * @param data       待签名数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 针对同一个待签数据，只要公钥和私钥不变，生成的签名是一致的
     * 校验数字签名
     *
     * @param data      待签名数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }
    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        //加密
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        System.out.println("未进行base64编码:"+new String(cipher.doFinal(str.getBytes("UTF-8"))));
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str);
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        //解密
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
    public static void main(String[] args) throws Exception {
        long temp = System.currentTimeMillis();
        //生成公钥和私钥
        genKeyPair();
        //加密字符串
        System.out.println("公钥:" + keyMap.get(0));
        System.out.println("私钥:" + keyMap.get(1));
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        String message = "RSA测试ABCD~!@#$";
        System.out.println("原文:" + message);
        temp = System.currentTimeMillis();
        String messageEn = encrypt(message, keyMap.get(0));
        System.out.println("密文:" + messageEn);
        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        temp = System.currentTimeMillis();
        String messageDe = decrypt(messageEn, keyMap.get(1));
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");

//        String message = "RSA测试ABCD~!@#$";
//        String tempSign=sign(message.getBytes(),privateKey);
//        System.out.println("签名:"+tempSign);
//        System.out.println("验签结果:"+verify(message.getBytes(),publicKey,tempSign));
//        System.out.println("原文:" + message);
//        String messageEn = encrypt(message, publicKey);
//        System.out.println("密文:" + messageEn);
//        String messageDe = decrypt(messageEn, privateKey);
//        System.out.println("解密:" + messageDe);
    }

    static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTrcnbN1Aqn9Q1ptvsBvUfAMDVcYrNeQXUHmSJn79wMCD+XAgJJnMhZFhO7uo1rFwP66laDR3+KR5xIx9MC1CxtwGw4gKnnxZLrLDiOIw0ZzQ+4ktLh3rmsteZyCWF6JlPLDWCSTK/IIXGxUWog8Z3kq4xUS0zxq/be6sq3RNT/QIDAQAB";
    static String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANOtyds3UCqf1DWm2+wG9R8AwNVxis15BdQeZImfv3AwIP5cCAkmcyFkWE7u6jWsXA/rqVoNHf4pHnEjH0wLULG3AbDiAqefFkussOI4jDRnND7iS0uHeuay15nIJYXomU8sNYJJMr8ghcbFRaiDxneSrjFRLTPGr9t7qyrdE1P9AgMBAAECgYEAwhDuo2AOy0VFFPspXTbzu7zBDe7OxppR0asbmWEv7fbyCMDvTRRHMTICFDvN1e9zZ9UyqjCkcsPX+PmuU9V74AYhAkvD2WuOmXyxgv5vhmndjPJcUp0kCbosyJLavIJhM2S5Vcoh4+Zv4MUJ4JYP+9UdQXLVpRmt1uZ2yJrg0jECQQD8Vm8hyePN4g0S/Qmg1B8XbuiuDaLzyAGwFkqjJSnnuLRUvURkUhGfDNC+PXNYcdXFUWswFFpSMC9pXrTDIrGTAkEA1sBJLaCeZMtxoSvp+f6EfjITLNJjDZg+lA0nrMjNc9/711SLHgYG5NVAUCsjdkhNUPq3O8XeR/QOFVeYIPSeLwJAMGX7psVsVh7XVx8Fa72DwSDarBede5iPClEcO7JzGSjKHb63kW5URudUWki7COm8kL4PCwe8Uys+zL0Heq361QJBAJfmItzXQ7JgBNvPyAxAb08j9FQ77Dxtd7YZJ/sbfdd8kxJYhp6R/MZX4MnT+Z1svuOMs5bf+r5CbsPB1aVZ4ZkCQQCCOghIk32fznC/0cb7PZbmOuPKld6NfRuLdptAHgXfBIpNau61xfj21H2tJ24HuLQiJmr/ZXi52V/AbY5pEKAI";


}
