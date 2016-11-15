package com.metelsos.common.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
* <pre>
* com.metelsos.common.aes
*   |_ AesUtil.java
* </pre>
* 
* Desc : ��ȣȭ ��ƿ Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 2:52:11
* @Version :
 */
public class AesUtil {
	/** ��ȣȭ Ű 16�ڸ� */
	private String key = "fe8025947de7cd71";

    /**
     * 
     * Desc : 16���� ���ڿ��� ����Ʈ �迭�� ��ȯ�Ѵ�.
     * @Method Name : hexToByteArray
     * @param hex - 16���� ���ڿ�
     * @return byte[]
     */
    public byte[] hexToByteArray(String hex) {
    	
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        
        return ba;
    }

    /**
     * 
     * Desc : unsigned byte(����Ʈ) �迭�� 16���� ���ڿ��� �ٲ۴�.
     * @Method Name : byteArrayToHex
     * @param ba - unsigned byte[]
     * @return 16���� ���ڿ�
     */
    public String byteArrayToHex(byte[] ba) {
    	
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        
        return sb.toString();
    }

    /**
     * 
     * Desc : AES ����� ��ȣȭ
     * @Method Name : encrypt
     * @param message - message ��ȣȭ ��� ���ڿ�
     * @return String ��ȣȭ �� ���ڿ�
     * @throws Exception
     */
    public String encrypt(String message) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(message.getBytes());
        
        return byteArrayToHex(encrypted);
    }
    
    /**
     * 
     * Desc : AES ����� ��ȣȭ
     * @Method Name : decrypt
     * @param encrypted - message ��ȣȭ ��� ���ڿ�
     * @return String ��ȣȭ �� ���ڿ�
     * @throws Exception
     */
    public String decrypt(String encrypted) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        
        byte[] original = cipher.doFinal(hexToByteArray(encrypted));
        
        String originalString = new String(original);
        
        return originalString;
    }
}
