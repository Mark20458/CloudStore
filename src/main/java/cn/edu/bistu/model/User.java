package cn.edu.bistu.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    /**
     * 用户id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户邮箱，用来注册账号
     */
    @TableField("e_mail")
    private String e_mail;

    @TableField("hash_password")
    private String hash_password;

    @TableField("login_salt")
    private String login_salt;

    @TableField("master_password")
    private String master_password;

    @TableField("crypt_salt")
    private String crypt_salt;

    @TableField("create_time")
    private LocalDateTime create_time;
}
