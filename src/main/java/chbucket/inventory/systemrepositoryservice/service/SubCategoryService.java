package chbucket.inventory.systemrepositoryservice.service;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {
    SubCategoryDto saveSubCategory (SubCategoryDto subCategoryDto);
    SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto);
    SubCategoryDto findSubCategoryByName(String subCategoryName);
    List<SubCategoryDto> findAllSubCategory();
    String deleteSubCategoryById(String id);
}
