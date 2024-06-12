package cn.edu.bistu.entity;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String email;
    private String hLoginPassword;
    private String loginPasswordSalt;
    private String hMasterPassword;
    private String masterPasswordSalt;

    private LocalDateTime createTime;
}
