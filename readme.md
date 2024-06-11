### 该项目是[KeySpace](https://github.com/Mark20458/KeySpace)的后端

可以通过ngrok工具，将CloudStore项目代理到公网上，然后设置KeySpace下Api类BASE_URL变量为自己的基地址

| 接口                   | 类型   | 含义          |
|----------------------|------|-------------|
| /login               | POST | 登录          |
| /register            | POST | 注册          |
| /verify_code/{email} | GET  | 给email发送验证码 |
| /tokenExpired        | NONE | token过期     |
| /backup              | POST | 备份数据        |
| /list/{e_mail}       | GET  | 返回备份数据      |

需要先安装Redis和MySQL，修改application.yml中的相应配置

在MySQL中创建数据库keyspace
```SQL
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL COMMENT '主键',
  `e_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户注册邮箱（接收验证码）',
  `hash_login_password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'hash后的登录密码',
  `login_password_salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码的盐',
  `hash_master_password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'hash后的主密码',
  `master_password_salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主密码的盐',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```
