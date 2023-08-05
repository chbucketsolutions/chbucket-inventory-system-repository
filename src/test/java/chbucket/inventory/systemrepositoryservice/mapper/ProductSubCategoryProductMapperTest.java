package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSubCategoryProductMapperTest {

    private SubCategory subCategory;
    private SubCategoryProductDto subCategoryProductDto;
    private ParentCategory parentCategory;
    private ProductSubCategoryProductMapper productSubCategoryProductMapper;

    @BeforeEach
    void setUp(){
        parentCategory = new ParentCategory();
        parentCategory.setId("1L");
        parentCategory.setParentCategoryName("Electronics");

        subCategory = new SubCategory();
        subCategory.setId("1L");
        subCategory.setSubCategoryName("Laptop");
        subCategory.setParentCategory(parentCategory);

        subCategoryProductDto = new SubCategoryProductDto();
        subCategoryProductDto.setId("1L");
        subCategoryProductDto.setCategoryName("Laptop");

        productSubCategoryProductMapper = ProductSubCategoryProductMapper.of();
    }

    @Test
    void testMapSubCategoryProductDtoToSubCategory(){
        SubCategory result = productSubCategoryProductMapper.setSubCategoryProductDto(subCategoryProductDto)
                .mapSubCategoryDtoToSubCategory();

        assertEquals(subCategoryProductDto.getCategoryName(), result.getSubCategoryName());
    }

    @Test
    void testMapSubCategoryProductToSubCategoryProductDto(){
        SubCategoryProductDto result = productSubCategoryProductMapper.setSubCategory(subCategory)
                .mapSubCategoryToSubCategoryDto();

        assertEquals(subCategory.getId(), result.getId());
        assertEquals(subCategory.getSubCategoryName(), result.getCategoryName());
        assertNotNull(subCategory.getParentCategory());
    }

    @Test
    void testMapSubCategoryProductDtoToSubCategoryWithNullSubCategory(){
        SubCategory result = productSubCategoryProductMapper.setSubCategoryProductDto(buildNullSubCategoryProductDto())
                .mapSubCategoryDtoToSubCategory();

        assertNull(result.getSubCategoryName());
    }

    @Test
    void testMapSubCategoryProductToSubCategoryProductDtoWithNullSubCategoryProductDto(){
        SubCategoryProductDto result = productSubCategoryProductMapper.setSubCategory(buildNullSubCategory())
                .mapSubCategoryToSubCategoryDto();

        assertNull(result.getId());
        assertNull(result.getCategoryName());
        assertNull(buildNullSubCategory().getParentCategory().getId());
        assertNull(buildNullSubCategory().getParentCategory().getParentCategoryName());
    }

    ParentCategory buildNullParentCategory(){
        ParentCategory parentCategory = new ParentCategory();
        parentCategory.setId(null);
        parentCategory.setParentCategoryName(null);
        return parentCategory;
    }

    SubCategory buildNullSubCategory(){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(null);
        subCategory.setSubCategoryName(null);
        subCategory.setParentCategory(buildNullParentCategory());
        return subCategory;
    }

    SubCategoryProductDto buildNullSubCategoryProductDto(){
        SubCategoryProductDto subCategoryProductDto = new SubCategoryProductDto();
        subCategoryProductDto.setId(null);
        subCategoryProductDto.setCategoryName(null);
        return subCategoryProductDto;
    }

}