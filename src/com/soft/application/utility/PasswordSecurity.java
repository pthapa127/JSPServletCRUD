package com.soft.application.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;


//this class will be responsible for hashing the password input by user 
public class PasswordSecurity {

	
	
	//this method will secure password
	//parameters will be the user input password 
	public static String securePassword(String passToHash,byte[] salt){
		String genPassword="";  //generated password
		
		try{
			MessageDigest md=MessageDigest.getInstance("MD5");  //using md5 to hash password
			md.update(passToHash.getBytes());
			byte []bytes=md.digest();
			
			StringBuilder stringBuilder=new StringBuilder();
			for(int i=0;i<bytes.length;i++){
				stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
			}
			genPassword=stringBuilder.toString();
		}catch(NoSuchAlgorithmException ex){
			ex.printStackTrace();
		}
		return genPassword;
	}
	
	//this method will also help to secure the password using secure random that generates the random number
	//secure random provides cryptographically strong generated number
	public static byte[] getSaltForPasswordSecure(){
		SecureRandom sr;
		try{
			sr=SecureRandom.getInstance("SHA1PRNG","SUN");
			byte[] salt=new byte[16];
			sr.nextBytes(salt);
			return salt;
		}catch(NoSuchAlgorithmException | NoSuchProviderException ex){
			ex.printStackTrace();
		}
		return null;
	}
}
