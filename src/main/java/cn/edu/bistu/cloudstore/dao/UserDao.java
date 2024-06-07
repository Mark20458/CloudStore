package cn.edu.bistu.cloudstore.dao;

import cn.edu.bistu.cloudstore.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
