package com.cesar.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cesar.core.common.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cesar
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cms_clause")
public class Clause extends CommonEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 条款名称
     */
    private String clauseName;

    /**
     * 分类id
     */
    private String classifyId;

    /**
     * 条款内容
     */
    private String clauseContent;

    /**
     * 标签
     */
    private String label;

    /**
     * 是用量
     */
    private Integer num;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String state;

    /**
     * 流程id
     */
    private String procInstId;

    /**
     * 编码
     */
    private String code;

    /**
     * 版本
     */
    private Integer fversion;


}
