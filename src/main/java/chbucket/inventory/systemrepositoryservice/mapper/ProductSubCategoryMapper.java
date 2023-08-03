package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;

public class ProductSubCategoryMapper {
    private SubCategoryDto subCategoryDto;
    private SubCategory subCategory;
    private ParentCategoryDto parentCategoryDto;
    private ParentCategory parentCategory;

    private ProductSubCategoryMapper(){
    }

    public static ProductSubCategoryMapper of(){
        return new ProductSubCategoryMapper();
    }

    public ProductSubCategoryMapper setSubCategoryDto(SubCategoryDto subCategoryDto){
        this.subCategoryDto = subCategoryDto;
        return this;
    }

    public  ProductSubCategoryMapper setSubCategory(SubCategory subCategory){
        this.subCategory = subCategory;
        return this;
    }

    public ProductSubCategoryMapper setParentCategoryDto(ParentCategoryDto parentCategoryDto){
        this.parentCategoryDto = parentCategoryDto;
        return this;
    }

    public ProductSubCategoryMapper setParentCategory(ParentCategory parentCategory){
        this.parentCategory = parentCategory;
        return this;
    }


    public SubCategory mapSubCategoryDtoToSubCategory(){
        if(subCategory == null){
            subCategory = new SubCategory();
        }
        subCategory.setSubCategoryName(subCategoryDto.getCategoryName());
        subCategory.setParentCategory(parentCategory);

        return subCategory;
    }

    public SubCategoryDto mapSubCategoryToSubCategoryDto(){
        if(subCategoryDto == null){
            subCategoryDto = new SubCategoryDto();
            subCategoryDto.setId(subCategory.getId());
        }
        subCategoryDto.setCategoryName(subCategory.getSubCategoryName());
        subCategoryDto.setParentCategory(parentCategoryDto);

        return subCategoryDto;
    }
}
