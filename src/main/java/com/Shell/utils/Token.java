package com.Shell.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@PermitAll()
@Path("/login")
public class Token {

	public static byte[] result; 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	
	public static void generateKey()
	{
		Key key = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");  
			keyGenerator.init(new SecureRandom());  
			SecretKey secretKey= keyGenerator.generateKey();  
			//转换key秘钥  
			DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(secretKey.getEncoded());  
			SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
			key = secretKeyFactory.generateSecret(deSedeKeySpec);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		String keyencode= HexBin.encode(key.getEncoded());
		try
		{
			DAOFactory.getIKeyDAOInstance().setKey(keyencode);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public static String authenticateUser(User user)
	{
		Key key = null;
		try {
			key = receiveSecret(DAOFactory.getIKeyDAOInstance().getKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(keyencode);
		String jwt = "";
		Date expiry = getExpiryDate(30 * 24 * 60);
		if (authenticate(user))
		{
			String group = null;
			try {
				user = DAOFactory.getIUserDAOInstance().findUserById(user.getUid());
				group = DAOFactory.getIGroupDAOInstance().findGroupById(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid())).getName();
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(group);
			jwt = getJWTString(user.getName(), group, expiry, key);
		}
		return jwt;
	}
	
	private static Date getExpiryDate(int minutes)
	{
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minutes);
		
		return calendar.getTime();
	}
	
	private static boolean authenticate(User user)
	{
		boolean isFind = false;
		try
		{
			isFind = DAOFactory.getIUserDAOInstance().findUser(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isFind;
	}
	
	public static String getJWTString(String name, String group, Date expires, Key key)
	{	
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String jwt = Jwts.builder()
				.setIssuer("Jersey-Security-Basic")
				.setSubject(name)
				.setAudience(group)
				.setExpiration(expires)
				.setIssuedAt(new Date())
				.setId("1")
				.signWith(signatureAlgorithm, key)
				.compact();
		return jwt;
	}
	
    public static Key receiveSecret(String keystr){  
        Key key = null;
    	try {           
         //2.通过读取到的key  获取到key秘钥
            byte[] keybyte= HexBin.decode(keystr); 
            DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(keybyte);  
            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
            key = secretKeyFactory.generateSecret(deSedeKeySpec); //获取到key秘钥  
             //System.out.println(key);
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }  
    	return key;
    }

//    public static void main(String[] args)
//	{
//    	//generateKey();
//    	String jsw = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKZXJzZXktU2VjdXJpdHktQmFzaWMiLCJzdWIiOiJtb2x0cmVzIiwiYXVkIjoidXNlciIsImV4cCI6MTUwMzIzMTI5MSwiaWF0IjoxNTAwNjM5MjkxLCJqdGkiOiIxIn0.xk_TMyKlTHUu_NlrYEIN4j-vWWuV-BnJoPuqtgPPU2U";
//			User user = new User("moltres", "admin", "123456");
//			
//			
//			try {
//				Key key = receiveSecret(DAOFactory.getIKeyDAOInstance().getKey());
//				//System.out.println(authenticateUser(user, key));
//				System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jsw).getBody().getSubject());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
}
