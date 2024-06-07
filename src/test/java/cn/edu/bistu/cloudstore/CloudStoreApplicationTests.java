package cn.edu.bistu.cloudstore;

import cn.edu.bistu.cloudstore.dao.BackupDao;
import cn.edu.bistu.cloudstore.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudStoreApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BackupDao backupDao;

    @Test
    void contextLoads() {
        System.out.println(userDao.selectList(null));
    }


    @Test
    void test() {
        System.out.println(backupDao.selectList(null));
    }
}
