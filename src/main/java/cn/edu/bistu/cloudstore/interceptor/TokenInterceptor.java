package cn.edu.bistu.cloudstore.interceptor;

import cn.edu.bistu.cloudstore.util.TokenUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            TokenUtil.verify(token);
            return true;
        } catch (Exception e) {
            if (e instanceof TokenExpiredException) {
                // token过期
                System.out.println("token过期");
                return true;
            }
            return false;
        }
    }
}
