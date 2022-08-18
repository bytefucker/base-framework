package com.moyu.framework.utils.encrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * aes加解密工具
 */
public final class AESUtil {

  private static final String KEY_ALGORITHM = "AES";

  /**
   * 默认的加密算法
   */
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
  private static final Charset CHARSET = StandardCharsets.UTF_8;

  private AESUtil() {
  }

  /**
   * AES 加密操作
   *
   * @param content  待加密内容
   * @param password 加密密码
   * @return 返回Base64转码后的加密数据
   */
  public static String encrypt(String content, String password)
      throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
    // 创建密码器
    Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
    byte[] byteContent = content.getBytes(CHARSET);

    // 初始化为加密模式的密码器
    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
    byte[] result = cipher.doFinal(byteContent);
    return Base64.encodeBase64String(result);

  }

  /**
   * AES 解密操作
   *
   * @param content
   * @param password
   * @return
   */
  public static String decrypt(String content, String password)
      throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

    //实例化
    Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

    //使用密钥初始化，设置为解密模式
    cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

    //执行操作
    byte[] result = cipher.doFinal(Base64.decodeBase64(content));

    return new String(result, CHARSET);

  }

  /**
   * 生成加密秘钥
   *
   * @return
   */
  private static SecretKeySpec getSecretKey(final String password)
      throws NoSuchAlgorithmException {
    if (StringUtils.isBlank(password)) {
      throw new IllegalArgumentException("password is null");
    }
    byte[] passwordBytes = password.getBytes(CHARSET);
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest(passwordBytes);
    return new SecretKeySpec(digest, KEY_ALGORITHM);

  }

}
