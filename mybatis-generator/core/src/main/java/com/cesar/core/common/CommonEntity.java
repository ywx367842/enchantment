package com.cesar.core.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class CommonEntity {
    @TableId
    private String id;
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedDate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
