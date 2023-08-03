package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;

public class ProductSubCategoryProductMapper {
    private SubCategoryProductDto subCategoryProductDto;
    private SubCategory subCategory;

    private ProductSubCategoryProductMapper(){
    }

    public static ProductSubCategoryProductMapper of(){
        return new ProductSubCategoryProductMapper();
    }

    public ProductSubCategoryProductMapper setSubCategoryProductDto(SubCategoryProductDto subCategoryDto){
        this.subCategoryProductDto = subCategoryDto;
        return this;
    }

    public  ProductSubCategoryProductMapper setSubCategory(SubCategory subCategory){
        this.subCategory = subCategory;
        return this;
    }


    public SubCategory mapSubCategoryDtoToSubCategory(){
        if(subCategory == null){
            subCategory = new SubCategory();
        }
        subCategory.setSubCategoryName(subCategoryProductDto.getCategoryName());

        return subCategory;
    }

    public SubCategoryProductDto mapSubCategoryToSubCategoryDto(){
        if(subCategoryProductDto == null){
            subCategoryProductDto = new SubCategoryProductDto();
            subCategoryProductDto.setId(subCategory.getId());
        }
        subCategoryProductDto.setCategoryName(subCategory.getSubCategoryName());

        return subCategoryProductDto;
    }
}
