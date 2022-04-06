package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.Category;
import org.springframework.web.bind.annotation.GetMapping;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/6 10:44
 */
public interface CategoryService {

    /**
     * 获取根标签分类
     * @return ResultVO<List<CategoryVO>>
     */
    @GetMapping("/api/category/root")
    ResultVO<List<Category>> getRootCategories();
}
