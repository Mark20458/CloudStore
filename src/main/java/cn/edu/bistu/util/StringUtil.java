package cn.edu.bistu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * @param email 检查数据
     * @return 是否为合法邮箱
     */
    public static boolean isEmail(String email) {
        if (StringUtil.isEmpty(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
            return pattern.matcher(email).matches();
        }
    }

    /**
     * @return 检查str是否为null、""、"     "
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.isEmpty();
        }
    }

    public static String hash(String msg) {
        byte[] digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256").digest(msg.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(digest);
    }
}
