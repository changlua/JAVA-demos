package com.changlu.springsecuritydemo05jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "changlu";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
        testToken();
    }

    public static void test() throws Exception {
        final String token = createJWT("changlu");
        System.out.println(token);
        //校验token
        final Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMzk3ZGYyODIwNzg0OGMxOWI1ZDgwN2I0YTI2YWRhMyIsInN1YiI6ImNoYW5nbHUiLCJpc3MiOiJzZyIsImlhdCI6MTY0ODE3ODc4MSwiZXhwIjoxNjQ4MTgyMzgxfQ.PUAv37t5dguAFKBgChudo_lzKixS6LU2tRYmzMPETjA");
        //claims包含五个键值，getSubject就是获取key为sub，也就是我们在createJWT传入的内容
        System.out.println("claims：" + claims);//结果：claims：{jti=f397df28207848c19b5d807b4a26ada3, sub=changlu, iss=sg, iat=1648178781, exp=1648182381}
        System.out.println("subject：" + claims.getSubject());// subject：changlu
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYWM2ZDVhZi1mNjVlLTQ0MDAtYjcxMi0zYWEwOGIyOTIwYjQiLCJzdWIiOiJzZyIsImlzcyI6InNnIiwiaWF0IjoxNjM4MTA2NzEyLCJleHAiOjE2MzgxMTAzMTJ9.JVsSbkP94wuczb4QryQbAke3ysBDIL5ou8fWsbt_ebg";
//        Claims claims = parseJWT(token);
//        System.out.println(claims);
    }


    public static void testToken() throws Exception {
        System.out.println(parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwYTRhMTdjNjVmZmI0MjliYjZjODJlZDMyNjM4ODI4NSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY0ODE5MzIwMSwiZXhwIjoxNjQ4MTk2ODAxfQ.M5xKYLYNOu-bnU7FUGYnx5hsBc-wSAtNOnkSxYhCT1I").getSubject());
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}