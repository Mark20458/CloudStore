package cn.edu.bistu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;
import java.util.Map;

public class TokenUtil {

    // 签名 用于加密
    private static final String SIGNATURE = "GONGZ,YOU ARE SO HANDSOME!";

    // 过期时间 10天 10 * 24 * 60 * 60 * 1000
    private static final long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000;


    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            builder.withClaim(k, v);
        }
        builder.withExpiresAt(expirationDate);
        return builder.sign(Algorithm.HMAC256(SIGNATURE));
    }


    /**
     * SignatureVerificationException 无效签名
     * TokenExpiredException token过期
     * AlgorithmMismatchException token算法不一致
     * 其他的Exception都可以认为是token失效
     *
     * @param token 需要验证的token
     */
    public static void verify(String token) throws
            SignatureVerificationException, TokenExpiredException, AlgorithmMismatchException {
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }
}
