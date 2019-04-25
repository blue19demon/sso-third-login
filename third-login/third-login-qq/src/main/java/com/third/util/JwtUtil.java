package com.third.util;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	private static final String JWT_KEY = "jwt_key";
	public static SecretKeySpec generateKey() {
		byte[] encodeKey=Base64Utils.encode(JWT_KEY.getBytes());
		SecretKeySpec signingKey = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
		return signingKey;
	}
	
	public static String createJWT(String uuid,String content,long expireTime) {
		 //签名算法，选择SHA-256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //获取当前系统时间
        long nowTimeMillis = System.currentTimeMillis();
        Date now = new Date(nowTimeMillis);
        //使用HmacSHA256签名算法生成一个HS256的签名秘钥Key
        SecretKeySpec signingKey = generateKey();
        JwtBuilder builder = Jwts.builder()
                .claim("uuid", uuid)
                .setSubject(content)
                //Signature
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expireTime >= 0) {
            long expMillis = nowTimeMillis + expireTime;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate).setNotBefore(now);
        }
        return builder.compact();
	}
	
	public static Claims parseJWT(String jsonWebToken) {
        Claims claims = null;
        try {
            if (!StringUtils.isEmpty(jsonWebToken)) {
                //解析jwt
            	byte[] encodeKey=Base64Utils.encode(JWT_KEY.getBytes());
                claims = Jwts.parser().setSigningKey(encodeKey)
                        .parseClaimsJws(jsonWebToken).getBody();
            }else {
                log.warn("[JWTHelper]-json web token 为空");
            }
        } catch (Exception e) {
        	log.error("[JWTHelper]-JWT解析异常：可能因为token已经超时或非法token");
        }
        return claims;
    }

}
