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
