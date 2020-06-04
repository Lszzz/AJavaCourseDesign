package vehicleleasing.algorithm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64.*;
/**
 * @author lszzz
 * @create 2020/6/3
 */
public class DesCryptology{


	/**
	 * DES���ܽ��� DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��
	 */
	public DesCryptology()
	{
	}

//		// ����
//		public static void main(String args[]) throws UnsupportedEncodingException {
//			// ����������
//			String str = "lszzz";
//			// ���룬����Ҫ��8�ı���
//			String password = "12343213";
//			String res=Des.encrypt(str, password);
//			System.out.println(res);
//			//System.out.println(s1+" BASE "+s2);
//			// ֱ�ӽ��������ݽ���;
//			try
//			{
//				String decryResult = Des.decrypt(res, password);
//				System.out.println("after��"+decryResult);
//			} catch (Exception e1)
//			{
//				e1.printStackTrace();
//			}
//
//		}


	/**
	 * ����
	 *
	 * @param data
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	//dataΪ���ݣ�password����Կ
	public static String encrypt(String data, String password)
	{
		try
		{
			//��ȡ����
			byte []datasource=data.getBytes();
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// ���ڣ���ȡ���ݲ�����
			// ��ʽִ�м��ܲ���
			System.out.println(new String(cipher.doFinal(datasource)));
			return jdkBase64String(cipher.doFinal(datasource));
		} catch (Throwable e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//ʹ��base64��������ʹ����������ʱ��DES���ص��ֽڸ�ʽ��ƥ������⣻
	public static String jdkBase64String(byte[] key)
	{
		return Base64.getEncoder().encodeToString(key);
	}

	public static byte[] jdkBase64Decoder(String str)
	{
		return Base64.getDecoder().decode(str);
	}

	/**
	 * ����
	 *
	 * @param Src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static String decrypt(String Src, String password) throws Exception
	{
		//��ȡbase64���ֽ�����
		byte []src=jdkBase64Decoder(Src);
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom random = new SecureRandom();
		// ����һ��DESKeySpec����
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// ����һ���ܳ׹���
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// ��DESKeySpec����ת����SecretKey����
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// ������ʼ���ܲ���
		System.out.println(new String(cipher.doFinal(src))+"  ����");
		return new String(cipher.doFinal(src));
	}
}

