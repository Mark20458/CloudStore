package cn.edu.bistu.entity;

import cn.edu.bistu.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "hLoginPassword")
    private String hLoginPassword;

    @JsonProperty(value = "loginPasswordSalt")
    private String loginPasswordSalt;

    @JsonProperty(value = "hMasterPassword")
    private String hMasterPassword;

    @JsonProperty(value = "masterPasswordSalt")
    private String masterPasswordSalt;

    @JsonProperty(value = "createTime")
    private LocalDateTime createTime;

    /**
     * @return 用户信息是否合法
     */
    public boolean isValid() {
        return StringUtil.isEmail(email) && !(StringUtil.isEmpty(hLoginPassword) ||
                StringUtil.isEmpty(loginPasswordSalt) ||
                StringUtil.isEmpty(hMasterPassword) ||
                StringUtil.isEmpty(masterPasswordSalt)
        );
    }
}
