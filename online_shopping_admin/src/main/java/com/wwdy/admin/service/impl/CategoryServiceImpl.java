package com.wwdy.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.admin.converter.CategoryConverter;
import com.wwdy.admin.exception.NotFoundRecordException;
import com.wwdy.admin.mapper.CategoryMapper;
import com.wwdy.admin.pojo.Category;
import com.wwdy.admin.pojo.update.CategoryUpdate;
import com.wwdy.admin.pojo.vo.CategoryVO;
import com.wwdy.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author  wwdy
 * @date  2022/3/25 13:02
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    private final CategoryConverter categoryConverter;

    /**
     * 添加标签
     * @param category 标签信息
     * @return int
     */
    @Override
    public int addCategory(Category category) {
        return baseMapper.insert(category);
    }

    /**
     * 通过id查找标签
     * @param id id
     * @return Category
     */
    @Override
    public Category selectCategoryById(int id) {
        Category category = baseMapper.selectById(id);
        if(Optional.ofNullable(category).isPresent()){
            return category;
        }
        throw new NotFoundRecordException("轮播图不存在");
    }

    /**
     * 更新标签
     * @param update 更新信息
     * @return int
     */
    @Override
    public int updateCategory(CategoryUpdate update) {
        Category convert = categoryConverter.convert(update);
        return baseMapper.updateById(convert);
    }

    /**
     * 通过id删除标签
     * @param id id
     * @return int
     */
    @Override
    public int delCategory(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 获取所有标签
     * @return List<CategoryVO>
     */
    @Override
    public List<CategoryVO> getCategories() {
        List<Category> categories = list();
        List<CategoryVO> categoriesVO = categoryConverter.convert(categories);
        return categoriesVO.stream()
                .filter(categoryVO -> categoryVO.getParentId() == 0)
                .peek(categoryVO -> categoryVO.setChildren(setChildrenCategories(categoryVO, categoriesVO)))
                .collect(Collectors.toList());
    }

    /**
     * 找出所有的子标签
     * @param current 当前标签
     * @param all 所有标签
     * @return List<CategoryVO>
     */
    protected List<CategoryVO> setChildrenCategories(CategoryVO current, List<CategoryVO> all){
        return all.stream()
                .filter(categoryVO -> categoryVO.getParentId().equals(current.getId()))
                .peek(categoryVO -> categoryVO.setChildren(setChildrenCategories(categoryVO, all)))
                .collect(Collectors.toList());
    }
}




