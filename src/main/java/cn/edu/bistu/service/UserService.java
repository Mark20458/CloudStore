package cn.edu.bistu.service;

import cn.edu.bistu.entity.User;

public interface UserService {
    /**
     * 注册
     *
     * @param user 注册用户数据
     * @param code 验证码
     * @return 是否成功
     */
    boolean register(User user, String code);

    /**
     * 登录
     * @param user 登录用户身份信息
     * @return 是否可以的公路
     */
    boolean login(User user);
}
