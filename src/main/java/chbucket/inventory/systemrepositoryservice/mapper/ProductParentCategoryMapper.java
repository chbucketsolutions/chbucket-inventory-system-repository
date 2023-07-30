package chbucket.inventory.systemrepositoryservice.mapper;
import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;

public class ProductParentCategoryMapper {
    private ParentCategoryDto parentCategoryDto;
    private ParentCategory parentCategory;

    private ProductParentCategoryMapper(){
    }

    public static ProductParentCategoryMapper of(){
        return new ProductParentCategoryMapper();
    }

    public ProductParentCategoryMapper setParentCategoryDto(ParentCategoryDto parentCategoryDto){
        this.parentCategoryDto = parentCategoryDto;
        return this;
    }

    public  ProductParentCategoryMapper setParentCategory(ParentCategory parentCategory){
        this.parentCategory = parentCategory;
        return this;
    }

    public ParentCategory mapParentCategoryDtoToParentCategory(){
        if(parentCategory == null){
            parentCategory = new ParentCategory();
        }
        parentCategory.setParentCategoryName(parentCategoryDto.getParentCategoryName());
        return parentCategory;
    }

    public ParentCategoryDto mapParentCategoryToParentCategoryDto(){
        if(parentCategoryDto == null){
            parentCategoryDto = new ParentCategoryDto();
            parentCategoryDto.setId(parentCategory.getId());
        }
        parentCategoryDto.setParentCategoryName(parentCategory.getParentCategoryName());

        return parentCategoryDto;
    }
}
