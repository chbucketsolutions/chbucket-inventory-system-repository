package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSubCategoryMapperTest {
    private SubCategory subCategory;
    private SubCategoryDto subCategoryDto;
    private ParentCategory parentCategory;
    private ParentCategoryDto parentCategoryDto;
    private ProductSubCategoryMapper productSubCategoryMapper;

    @BeforeEach
    void setUp(){
        parentCategory = new ParentCategory();
        parentCategory.setId("1L");
        parentCategory.setParentCategoryName("Electronics");

        parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId("1L");
        parentCategoryDto.setParentCategoryName("Electronics");

        subCategory = new SubCategory();
        subCategory.setId("1L");
        subCategory.setParentCategory(parentCategory);
        subCategory.setSubCategoryName("Laptop");

        subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId("1L");
        subCategoryDto.setCategoryName("Laptop");
        subCategoryDto.setParentCategory(parentCategoryDto);

        productSubCategoryMapper = ProductSubCategoryMapper.of();
    }

    @Test
    void testMapSubCategoryDtoToSubCategory(){
        SubCategory result = productSubCategoryMapper
                .setParentCategory(parentCategory).setSubCategoryDto(subCategoryDto)
                .mapSubCategoryDtoToSubCategory();

        assertEquals(subCategory.getSubCategoryName(),result.getSubCategoryName());
        assertEquals(subCategory.getParentCategory().getParentCategoryName(), result.getParentCategory().getParentCategoryName());
    }

    @Test
    void testMapSubCategoryToSubCategoryDto(){
        SubCategoryDto result = productSubCategoryMapper
                .setParentCategoryDto(parentCategoryDto).setSubCategory(subCategory)
                .mapSubCategoryToSubCategoryDto();

        assertEquals(subCategoryDto.getId(),result.getId());
        assertEquals(subCategoryDto.getParentCategory().getId(),result.getParentCategory().getId());
        assertEquals(subCategoryDto.getCategoryName(),result.getCategoryName());
        assertEquals(subCategoryDto.getParentCategory().getParentCategoryName(), result.getParentCategory().getParentCategoryName());
    }

    @Test
    void testMapParentCategoryDtoToParentCategoryWithNullParentCategory(){
        SubCategory result = productSubCategoryMapper
                .setParentCategory(buildNullParentCategory()).setSubCategoryDto(buildNullSubCategoryDto())
                .mapSubCategoryDtoToSubCategory();

        assertNull(result.getSubCategoryName());
        assertNull(result.getParentCategory().getParentCategoryName());
    }

    @Test
    void testMapSubCategoryToSubCategoryDtoWithNullSubCategoryDto(){
        SubCategoryDto result = productSubCategoryMapper
                .setParentCategoryDto(buildNullParentCategoryDto()).setSubCategory(buildNullSubCategory())
                .mapSubCategoryToSubCategoryDto();

        assertNull(result.getId());
        assertNull(result.getParentCategory().getId());
        assertNull(result.getCategoryName());
        assertNull(result.getParentCategory().getParentCategoryName());
    }

    SubCategory buildNullSubCategory(){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(null);
        subCategory.setSubCategoryName(null);
        subCategory.setParentCategory(buildNullParentCategory());
        return subCategory;
    }

    SubCategoryDto buildNullSubCategoryDto(){
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId(null);
        subCategoryDto.setCategoryName(null);
        subCategoryDto.setParentCategory(buildNullParentCategoryDto());
        return subCategoryDto;
    }

    ParentCategory buildNullParentCategory(){
        ParentCategory parentCategory = new ParentCategory();
        parentCategory.setId(null);
        parentCategory.setParentCategoryName(null);
        return parentCategory;
    }

    ParentCategoryDto buildNullParentCategoryDto(){
        ParentCategoryDto parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId(null);
        parentCategoryDto.setParentCategoryName(null);
        return parentCategoryDto;
    }


}