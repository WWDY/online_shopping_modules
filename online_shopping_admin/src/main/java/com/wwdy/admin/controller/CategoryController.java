package com.wwdy.admin.controller;

import com.wwdy.admin.pojo.Category;
import com.wwdy.admin.pojo.update.CategoryUpdate;
import com.wwdy.admin.pojo.vo.CategoryVO;
import com.wwdy.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/25 13:11
 */
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 添加标签
     * @param category 标签信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addCategory(@Valid @RequestBody Category category){
        int res = categoryService.addCategory(category);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 通过id查找标签
     *
     * @param id id
     * @return ResultVO<Category>
     */
    @GetMapping("/{id}")
    public ResultVO<Category> getCategoryById(@PathVariable("id") Integer id){
        Category category = categoryService.selectCategoryById(id);
        return ResultUtil.success(category);
    }

    /**
     * 更新标签
     * @param update 更新内容
     * @return ResultVO<String>
     */
    @PatchMapping("/")
    public ResultVO<String> updateCategory(@Valid @RequestBody CategoryUpdate update) {
        int res = categoryService.updateCategory(update);
        if (res > 0) {
            return ResultUtil.success("更新成功");
        }
        return ResultUtil.error("更新失败");
    }

    /**
     * 通过id删除标签
     * @param id id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> delCategory(@PathVariable("id") Integer id){
        int res = categoryService.delCategory(id);
        if (res > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error("删除失败");
    }

    /**
     * 获取所有分类标签（树形）
     * @return ResultVO<List<CategoryVO>>
     */
    @GetMapping("/tree")
    public ResultVO<List<CategoryVO>> getCategories(){
        List<CategoryVO> categories = categoryService.getCategories();
        return ResultUtil.success(categories);
    }

    /**
     * 获取根标签分类
     * @return ResultVO<List<CategoryVO>>
     */
    @GetMapping("/root")
    public ResultVO<List<CategoryVO>> getRootCategories(){
        List<CategoryVO> categories = categoryService.getRootCategories();
        return ResultUtil.success(categories);
    }
}
