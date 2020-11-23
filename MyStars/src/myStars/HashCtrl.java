package myStars;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCtrl {
	
	public static String hashPassword(String password) {
		String passwordToHash = password;
		String hashedpw = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <bytes.length; i++) {
				sb.append(Integer.toString((bytes[i]&0xff)+0x100,32).substring(1));
			}
		hashedpw = sb.toString();
//		System.out.println(hashedpw);
		return hashedpw;
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
