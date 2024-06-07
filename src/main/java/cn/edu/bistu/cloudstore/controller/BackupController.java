package cn.edu.bistu.cloudstore.controller;

import cn.edu.bistu.cloudstore.common.CODE;
import cn.edu.bistu.cloudstore.common.Result;
import cn.edu.bistu.cloudstore.dao.BackupDao;
import cn.edu.bistu.cloudstore.mail.MailMsg;
import cn.edu.bistu.cloudstore.model.Backup;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BackupController {
    @Autowired
    private BackupDao backupDao;

    @PostMapping("/backup")
    public Result upload(@RequestBody Backup backup) {
        backup.setId(null);
        backup.setCreate_time(null);
        int i = backupDao.insert(backup);
        if (i > 0) {
            return Result.OK().message("备份成功");
        } else {
            return Result.ERR().message("备份失败");
        }
    }

    @GetMapping("/list/{e_mail}")
    public Result getList(@PathVariable String e_mail) {
        LambdaQueryWrapper<Backup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Backup::getE_mail, e_mail);
        List<Backup> list = backupDao.selectList(wrapper);
        return Result.OK().message("查询成功").data(list);
    }
}
