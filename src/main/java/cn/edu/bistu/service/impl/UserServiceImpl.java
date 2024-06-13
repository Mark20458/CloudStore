package cn.edu.bistu.service.impl;

import cn.edu.bistu.entity.User;
import cn.edu.bistu.mail.MailMsg;
import cn.edu.bistu.mapper.UserMapper;
import cn.edu.bistu.service.UserService;
import cn.edu.bistu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MailMsg mailMsg;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user, String code) {
        if (!user.isValid()) {
            return false;
        }
        if (!mailMsg.verify(user.getEmail(), code)) {
            return false;
        }
        if (userMapper.getUserByEmail(user.getEmail()) == null) {
            // user中loginPassword和masterPassword已经hash过了
            return userMapper.insertUser(user) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean login(User user) {
        if (!StringUtil.isEmail(user.getEmail()) ||
                StringUtil.isEmpty(user.getHLoginPassword()) ||
                StringUtil.isEmpty(user.getHMasterPassword())
        )
            return false;
        User userData = userMapper.getUserByEmail(user.getEmail());
        if (userData == null) {
            return false;
        } else {
            String hLoginPassword = StringUtil.hash(user.getHLoginPassword() + userData.getLoginPasswordSalt());
            String hMasterPassword = StringUtil.hash(user.getHMasterPassword() + userData.getMasterPasswordSalt());
            return hLoginPassword.equals(userData.getHLoginPassword()) && hMasterPassword.equals(userData.getHMasterPassword());
        }
    }
}
