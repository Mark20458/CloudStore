package cn.edu.bistu;

import cn.edu.bistu.dao.BackupDao;
import cn.edu.bistu.dao.UserDao;
import cn.edu.bistu.mail.MailMsg;
import cn.edu.bistu.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudStoreApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BackupDao backupDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailMsg mailMsg;

    @Test
    void contextLoads() {
        System.out.println(userDao.selectList(null));
    }


    @Test
    void test() {
        System.out.println(backupDao.selectList(null));
    }

    @Test
    void RedisService() {
        redisService.set("gongz@qq.com", "aasdjopposd", 200);
        redisService.hSet("gongz", "token", "123456");
    }

    @Test
    void testP() {
        System.out.println(mailMsg.verify("2146160669@qq.com", "970587"));
    }
}
