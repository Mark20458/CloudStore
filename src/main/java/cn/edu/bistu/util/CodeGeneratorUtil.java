package cn.edu.bistu.util;


public class CodeGeneratorUtil {

    public static String generatorCode(int length) {
        StringBuilder code = new StringBuilder();
        while (length-- > 0) {
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }
}
