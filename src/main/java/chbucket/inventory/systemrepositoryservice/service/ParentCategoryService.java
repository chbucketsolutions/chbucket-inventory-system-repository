package chbucket.inventory.systemrepositoryservice.service;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;

import java.util.List;

public interface ParentCategoryService {
    ParentCategoryDto saveParentCategory (ParentCategoryDto parentCategoryDto);
    ParentCategoryDto updateParentCategory(ParentCategoryDto parentCategoryDto);
    ParentCategoryDto findParentCategoryByName(String parentCategoryName);
    List<ParentCategoryDto> findAllParentCategory();
    String deleteParentCategoryById(String id);
}
