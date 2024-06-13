package cn.edu.bistu.controller;


import cn.edu.bistu.common.Result;
import cn.edu.bistu.entity.User;
import cn.edu.bistu.mail.MailMsg;
import cn.edu.bistu.service.RedisService;
import cn.edu.bistu.service.UserService;
import cn.edu.bistu.util.TokenUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private MailMsg mailMsg;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    public static final String TOKEN = "token";

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (userService.login(user)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("email", user.getEmail());
            String token = TokenUtil.getToken(map);
            redisService.hSet(user.getEmail(), TOKEN, token);
            return Result.OK().message("登录成功").data(token);
        } else {
            return Result.ERR().message("登录失败");
        }
    }

    @GetMapping("/verify_code/{email}")
    public Result getCode(@PathVariable String email) {
        try {
            if (mailMsg.mail(email)) {
                return Result.OK().message("验证码已发送");
            } else {
                return Result.ERR().message("验证码发送失败");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
