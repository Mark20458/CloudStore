package cn.edu.bistu.cloudstore.dao;

import cn.edu.bistu.cloudstore.model.Backup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BackupDao extends BaseMapper<Backup> {
}
