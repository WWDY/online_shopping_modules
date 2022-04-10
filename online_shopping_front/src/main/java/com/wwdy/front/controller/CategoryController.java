package com.wwdy.front.controller;

import com.wwdy.front.feign.AdminClient;
import com.wwdy.front.feign.pojo.dto.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/6 10:46
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final AdminClient adminClient;


    /**
     * 获取根标签分类
     * @return ResultVO<List<Category>>
     */
    @GetMapping("/root")
    public ResultVO<List<Category>> getRootCategories(){
        return adminClient.getRootCategories();
    }


    /**
     * 获取所有分类标签（树形）
     * @return ResultVO<List<CategoryVO>>
     */
    @GetMapping("/tree")
    public ResultVO<List<Category>> getCategoriesTree(){
        return adminClient.getTreeCategories();
    }
}
