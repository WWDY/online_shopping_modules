package com.wwdy.front.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/4/5 10:35
 */
@TableName(value ="cart")
@Data
public class Cart implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Integer shopId;

    /**
     * 逻辑删除字段
     */
    private Integer deleted;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /**
     * 收藏标识
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}