package cn.edu.bistu.controller;


import cn.edu.bistu.common.Result;
import cn.edu.bistu.mail.MailMsg;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
public class UserController {
    @Autowired
    private MailMsg mailMsg;


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

    private static String hash(String msg) {
        byte[] digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256").digest(msg.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(digest);
    }

}
