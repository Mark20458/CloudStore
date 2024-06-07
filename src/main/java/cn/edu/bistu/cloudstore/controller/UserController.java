package cn.edu.bistu.cloudstore.controller;


import cn.edu.bistu.cloudstore.common.CODE;
import cn.edu.bistu.cloudstore.common.Result;
import cn.edu.bistu.cloudstore.dao.UserDao;
import cn.edu.bistu.cloudstore.mail.MailMsg;
import cn.edu.bistu.cloudstore.model.User;
import cn.edu.bistu.cloudstore.util.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private MailMsg mailMsg;

    @Autowired
    private UserDao userDao;

    /**
     * e_mail
     * hash_password
     * master_password
     * 登录成功返回加密使用的盐和token
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getE_mail, user.getE_mail());
        User item = userDao.selectOne(wrapper);
        System.out.println("/login #user = " + user);
        if (item != null &&
                Objects.equals(hash(user.getHash_password() + item.getLogin_salt()), item.getHash_password()) &&
                Objects.equals(hash(user.getMaster_password() + item.getCrypt_salt()), item.getMaster_password())
        ) {
            return Result.OK().message("登录成功").data(new String[]{getToken(user), item.getCrypt_salt()});
        } else {
            return Result.ERR().message("邮箱或密码错误");
        }
    }

    /**
     * e_mail
     * hash_password
     * login_salt
     * master_password
     * crypt_salt
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getE_mail, user.getE_mail());
        Long count = userDao.selectCount(wrapper);
        System.out.println("/register #user = " + user);
        if (count == 0) {
            userDao.insert(user);
            // return new Result(CODE.OK, "注册成功", getToken(user));
            return Result.OK().message("注册成功").data(getToken(user));
        } else {
            // return new Result(CODE.ERR, "该邮箱已经注册，请勿重复注册！");
            return Result.ERR().message("该邮箱已经注册，请勿重复注册！");
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


    @RequestMapping("/tokenExpired")
    public Result error() {
        return new Result(CODE.Token_Expired, "token过期，请重新登录");
    }

    private String getToken(User user) {
        HashMap<String, String> map = new HashMap<>();
        map.put("e_mail", user.getE_mail());
        return TokenUtil.getToken(map);
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

    public static void main(String[] args) {
        System.out.println(hash("123456kll3i7DzX@a_ek6-J"));
    }
}
