package com.cesar.mphigh.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName User
 * @Description: TODO
 * @Author movit
 * @Date 2019/9/29
 * @Version V1.0
 **/
@Data
//@TableName("t_user")
//@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    //主键
    @TableId(type= IdType.ID_WORKER_STR)
    private String id;
    //姓名
//    @TableField(condition = SqlCondition.LIKE)
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //直属上级
    private Long managerId;
    //创建时间
//    private LocalDateTime createTime;
    private Date createTime;

    //备注
//    @TableField(exist = false)
//    private String remark;
}
