package com.face.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;

import java.security.Security;
import java.util.Arrays;

public class Encryption {
    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_NAME = "AES/CBC/PKCS7Padding";

    private Key key;
    private Cipher cipher;

    private static final byte[] DEFAULT_IV = { 0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35,
                                               0x30, 0x36, 0x30, 0x37, 0x30, 0x38 };

    private byte[] keyBytes = null;
    private byte[] ivBytes = null;
    private byte[] decryptedData;

    public Encryption() {}

    public Encryption(byte[] keyBytes, byte[] ivBytes) {
        this.keyBytes = keyBytes;
        this.ivBytes = ivBytes;
    }

    void setIvBytes(byte[] ivBytes) {
        this.ivBytes = ivBytes;
    }

    void setKeyBytes(byte[] keyBytes) {
        this.keyBytes = keyBytes;
    }

    private void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte)0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp; // 重新修改传入的keyBytes
        }

        Security.addProvider(new BouncyCastleProvider());

        // 转化为Java的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            cipher = Cipher.getInstance(ALGORITHM_NAME, "BC");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }  catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 加密算法
     * @param content : 待加密的明文
     * @param keyBytes : 密钥
     * @param ivBytes : 传入默认对称解密算法初始向量
     * @return 返回加密结果
     */
    public byte[] encrypt(byte[] content, byte[] keyBytes, byte[] ivBytes) {
        byte[] encryptedData = null;
        init(keyBytes);
        try {
            if (ivBytes != null)
                cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));
            else
                cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(DEFAULT_IV));
            encryptedData = cipher.doFinal(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encryptedData;
    }

    /**
     * 加密方法（采用默认对称解密算法初始向量）
     * @param content : 待加密的明文
     * @param keyBytes : 加密密钥
     * @return : 加密结果
     */
    public byte[] encrypt(byte[] content, byte[] keyBytes) {
        if (ivBytes == null)  return encrypt(content, keyBytes, DEFAULT_IV);
        else return encrypt(content, keyBytes, ivBytes);
    }

    /**
     * 加密算法
     * @param content : 待加密的明文，采用默认对称解密算法初始向量，密钥由构造函数传入（如果没有密钥，则返回空）
     * @return : 加密结果
     */
    public byte[] encrypt(byte[] content) {
        if (this.keyBytes == null) return null;
        if (ivBytes == null) return encrypt(content, this.keyBytes, DEFAULT_IV);
        else return encrypt(content, this.keyBytes, this.ivBytes);
    }

    /**
     * 解密算法
     * @param encryptedData : 被加密的数据
     * @param keyBytes : 传入密钥
     * @param ivBytes : 传入默认对称解密算法初始向量
     * @return 解密结果
     */
    public byte[] decrypt(byte[] encryptedData, byte[] keyBytes, byte[] ivBytes) {
        byte[] decryptedData = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));
            decryptedData = decryptedData;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return decryptedData;
    }

    /**
     * 解密算法（采用默认对称解密算法初始向量）
    * @param encryptedData : 被加密的数据
     * @param keyBytes : 传入密钥
     * @return 解密结果
     */
    public byte[] decrypt(byte[] encryptedData, byte[] keyBytes) {
        return decrypt(encryptedData, keyBytes, DEFAULT_IV);
    }

    /**
     * 解密算法
     * @param encryptedData : 被加密的数据，采用默认对称解密算法初始向量，密钥由构造函数传入（如果没有密钥，则返回空）
     * @return : 解密结果
     */
    public byte[] decrypt(byte[] encryptedData) {
        if (this.keyBytes == null)  return null;
        return decrypt(encryptedData, this.keyBytes, DEFAULT_IV);
    }
}
