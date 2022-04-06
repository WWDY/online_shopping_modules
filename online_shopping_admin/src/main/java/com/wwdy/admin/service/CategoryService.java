package com.wwdy.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.admin.pojo.Category;
import com.wwdy.admin.pojo.update.CategoryUpdate;
import com.wwdy.admin.pojo.vo.CategoryVO;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/25 13:02
 */
public interface CategoryService extends IService<Category> {
    /**
     * 添加标签
     * @param category 标签信息
     * @return int
     */
    int addCategory(Category category);

    /**
     * 通过id查找标签
     * @param id id
     * @return Category
     */
    Category selectCategoryById(int id);


    /**
     * 更新标签
     * @param update 更新信息
     * @return int
     */
    int updateCategory(CategoryUpdate update);

    /**
     * 通过id删除标签
     * @param id id
     * @return int
     */
    int delCategory(int id);

    /**
     * 获取所有标签
     * @return List<CategoryVO>
     */
    List<CategoryVO> getCategories();

    /**
     * 获取根标签分类
     * @return List<CategoryVO>
     */
    List<CategoryVO> getRootCategories();
}
