package cn.xuyangl.onlineshopping.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description jwt 自动登录工具类
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 14:20
 */
public class JwtToken {


    public static String createToken(String sellerId, String userName, String type) {
        Map<String, Object> header = new HashMap<>(16);
        header.put("type", "jwt");
        header.put("alg", "HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("sellerId", sellerId)
                .withClaim("userName", userName)
                .withClaim("type", type)
                .sign(Algorithm.HMAC256("secret"));
    }

    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaims();
    }
}
