package cn.edu.bistu.mapper;

import cn.edu.bistu.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    User getUserByEmail(String email);
}
