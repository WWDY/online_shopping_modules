package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 获取对应根目录的所有三级分类
     * @param id 父id
     * @return ResultVO<List<Integer>>
     */
    @GetMapping("/api/category/third/{id}")
    ResultVO<List<Integer>> getAllThreadCategoriesByRootId(@PathVariable("id") Integer id);

    /**
     * 获取所有分类标签（树形）
     * @return ResultVO<List<CategoryVO>>
     */
    @GetMapping("/api/category/tree")
    ResultVO<List<Category>> getTreeCategories();
}
