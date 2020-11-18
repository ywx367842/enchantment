package com.cesar.core.common;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
	private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
	public static void main(String[] args) throws Exception {
		//生成公钥和私钥
		genKeyPair();
		//加密字符串
		long curTime = System.currentTimeMillis();
		String message = curTime+"|df723820";
		System.out.println("随机生成的公钥为:" + keyMap.get(0));
		System.out.println("随机生成的私钥为:" + keyMap.get(1));
		String messageEn = encrypt(message,keyMap.get(0));
		System.out.println(message + "\t加密后的字符串为:" + messageEn);
//		messageEn = "qwCDfeg8fb3jQKZrKXhBmUtJccwrRD0g4Z31ZsQ/2L15aQJ2iZjLSkq+9oOLIw8ffr7HoQ9b16wUSeAV5kfs6w7E7VlJ1WtvBy8P4wBwemaBnEq6ckFWk0rkccXeZS/p2t6tkvZ5aAv2mUO/cAP7i9RBqPgpzxeF4FhXTUGzIQw=";
		String messageDe = decrypt(messageEn,keyMap.get(1));
		System.out.println("还原后的字符串为:" + messageDe);
	}

	/** 
	 * 随机生成密钥对 
	 * @throws NoSuchAlgorithmException 
	 */  
	public static void genKeyPair() throws NoSuchAlgorithmException {  
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
		// 初始化密钥对生成器，密钥大小为96-1024位  
		keyPairGen.initialize(1024,new SecureRandom());  
		// 生成一个密钥对，保存在keyPair中  
		KeyPair keyPair = keyPairGen.generateKeyPair();  
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥  
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥  
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		// 得到私钥字符串  
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));  
		// 将公钥和私钥保存到Map
//		keyMap.put(0,publicKeyString);  //0表示公钥
//		keyMap.put(1,privateKeyString);  //1表示私钥

		//公钥
		String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDAkBPqPKUVeFRlYWw86MeuQMBLCWrFJr5rtEKs30CsJXZx+e7lEQUyUN/zCr9DoWRrCwiKCkgciiaXHxBXgTaX5tb9WJ8Ea4rkoRqnssFNn5cPOMLlUThIkvQ/XHJLaWq9lvgV8WAGV4FaoGvB5olFuz3SI3v0WZoRn32fCCtpQIDAQAB";
		//私钥
		String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIMCQE+o8pRV4VGVhbDzox65AwEsJasUmvmu0QqzfQKwldnH57uURBTJQ3/MKv0OhZGsLCIoKSByKJpcfEFeBNpfm1v1YnwRriuShGqeywU2flw84wuVROEiS9D9ccktpar2W+BXxYAZXgVqga8HmiUW7PdIje/RZmhGffZ8IK2lAgMBAAECgYAWzoCFnaLFQgIcoswIVxGxmoG0ZYwVMVwdVVqc40xC+JzNsehcll4XxEXU7KUbMrj7+B4iwB0Qwe7hmHIlKwnq/m7byqSESY2J7YFcEn88qJ20wYR+ANgR4Oh/Q/s6ccvVwuyCUywGtI2N6QRfYDWH4ACt+1DNIz2PbCqhhrJmLQJBALnNg27bl5ZUvgDWcMNEuRukHVwyS1K0oXHc1n6BYWAFNTzubWMwhf+rJ99n51V1aPNJ2JxvBe3c3iBkwmkarvcCQQC0gSstasGcZ7Akk9g/exjWHY1zuU+REun5kwh8mCsEWQxb4CvPcD99TArJ8cN24r3a4R4WzENCl9zoaf0XwnVDAkEAlp3YO5da7IxJcdhjlIczHwsMmd5TXPn8HZcNcYNj8jiGjZwhpFSDa1vNeLZ7urTwnLdyFm2Ret6eWdgUGKt+xQJBAKG+1Vf/P7rNVxOPv8kynxDOroAyX9AZ6LuRO5uaJ4Hf4ak6eUeKx/mY+Fn5YDPZ/lJyQyoeo2668mL5BqgRwk0CQCvDYb+0z47bA9OowV14Aikn7djRtbOMbG5q9DTa3ADsqHZ+Zuh597t8cFwkVAWOTnLASfoXB1mcB7XZJniXI6g=";
		keyMap.put(0,PUBLIC_KEY);  //0表示公钥
		keyMap.put(1,PRIVATE_KEY);  //1表示私钥
	}  
	/** 
	 * RSA公钥加密 
	 *  
	 * @param str 
	 *            加密字符串
	 * @param publicKey 
	 *            公钥 
	 * @return 密文 
	 * @throws Exception 
	 *             加密过程中的异常信息 
	 */  
	public static String encrypt( String str, String publicKey ) throws Exception{
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/** 
	 * RSA私钥解密
	 *  
	 * @param str 
	 *            加密字符串
	 * @param privateKey 
	 *            私钥 
	 * @return 铭文
	 * @throws Exception 
	 *             解密过程中的异常信息 
	 */  
	public static String decrypt(String str, String privateKey) throws Exception{
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);  
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));  
		//RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}

}