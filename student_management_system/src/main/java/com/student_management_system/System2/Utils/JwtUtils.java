package com.student_management_system.System2.Utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

// 这里不需要@Component吧 都是静态方法
// 话说早了
public class JwtUtils {

    private static final String signKey = "U3R1ZGVudE1hbmFnZW1lbnRTeXN0ZW1PZlN5c3RlbTIyMDI1"; // 密钥
    // 虽然但是我看到说在配置里面写jwt.secret=xxx的 后面研究一下

    // 生成JWT令牌
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey) // 签名算法
                .setExpiration(new Date(System.currentTimeMillis()+600000)) // 设置有效期为10min
                .compact();
    }

    // 解析JWT令牌
    // 这里看起来不是很需要一个返回值 因为采用了解析异常会报错这种方式 所以我改成void类型
//    public static Claims parseJWT(String jwt) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(signKey)
//                .parseClaimsJws(jwt)
//                .getBody(); // 得到自定义部分
//        return claims;
//    }
    public static void parseJWT(String jwt) {
        Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody(); // 得到自定义部分
    }

    // 从JWT令牌中获取用户ID
    public static int getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Integer.class); // ?
    }

    // 从JWT令牌中获取用户姓名
    public static String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("name", String.class); // ?
    }

    // 从JWT令牌中获取用户角色
    public static String getUserRoleFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class); // ?
    }

}
