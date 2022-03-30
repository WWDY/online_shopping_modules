package com.wwdy.admin.pojo.update;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  wwdy
 * @date  2022/3/25 13:00
 */
@Data
public class CategoryUpdate implements Serializable {

    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父标签
     */
    private Integer parentId;

}