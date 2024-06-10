package cn.edu.bistu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("backup")
public class Backup {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("e_mail")
    private String e_mail;

    @TableField("cTime")
    private LocalDateTime create_time;

    @TableField("data")
    private String backup;
}
