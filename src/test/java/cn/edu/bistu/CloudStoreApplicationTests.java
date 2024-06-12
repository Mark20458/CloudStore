package cn.edu.bistu;

import cn.edu.bistu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudStoreApplicationTests {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testUserMapper() {
        System.out.println(userMapper.getUserByEmail("2"));
    }
}
