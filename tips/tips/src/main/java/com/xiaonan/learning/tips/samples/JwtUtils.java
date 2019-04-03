package com.xiaonan.learning.tips.samples;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtils {

	public static String generateToken (String value1, String value2, String value3, long validForMillis, String secretKey) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long currentMillis = System.currentTimeMillis();
		Date current = new Date(currentMillis);
		JwtBuilder builder = null;
		try {
			long expiryMillis = currentMillis + validForMillis;
			Date expiry = new Date(expiryMillis);
			builder = Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT")
					.setIssuedAt(current).setExpiration(expiry).claim("field1", value1).claim("field2", value2).claim("field3", value3)
					.signWith(signatureAlgorithm, secretKey.getBytes("UTF-8"));
			
		} catch (UnsupportedEncodingException ex) {
			// log the exception
		}
		
		String token = builder.compact();
		return token;
	}
	
	public static boolean validateToken (String key, String token) {
		boolean isValid = false;
		try {
			Jwts.parser().setSigningKey(key.getBytes("UTF-8")).parseClaimsJws(token);
			isValid = true;
		} catch (SignatureException ex) {
			//log signature not validated
		} catch (Exception ex) {
			//log other exception
		}
		return isValid;
	}
}
