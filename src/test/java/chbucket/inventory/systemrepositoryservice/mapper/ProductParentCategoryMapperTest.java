package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductParentCategoryMapperTest {
    private ParentCategory parentCategory;
    private ParentCategoryDto parentCategoryDto;
    private ProductParentCategoryMapper productParentCategoryMapper;

    @BeforeEach
    void setUp(){
        parentCategory = new ParentCategory();
        parentCategory.setId("1L");
        parentCategory.setParentCategoryName("Electronics");

        parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId("1L");
        parentCategoryDto.setParentCategoryName("Electronics");

        productParentCategoryMapper = ProductParentCategoryMapper.of();
    }

    @Test
    void testMapParentCategoryDtoToParentCategory(){
        ParentCategory result = productParentCategoryMapper.setParentCategoryDto(parentCategoryDto)
                .mapParentCategoryDtoToParentCategory();

        assertEquals(parentCategory.getParentCategoryName(), result.getParentCategoryName());
    }

    @Test
    void testMapParentCategoryToParentCategoryDto(){
        ParentCategoryDto result = productParentCategoryMapper.setParentCategory(parentCategory)
                .mapParentCategoryToParentCategoryDto();

        assertEquals(parentCategoryDto.getId(), result.getId());
        assertEquals(parentCategoryDto.getParentCategoryName(), result.getParentCategoryName());
    }

    @Test
    void testMapParentCategoryDtoToParentCategoryWithNullParentCategory(){
        ParentCategory result = productParentCategoryMapper.setParentCategoryDto(buildNullParentCategoryDto())
                .mapParentCategoryDtoToParentCategory();

        assertNull(result.getParentCategoryName());
    }

    @Test
    void testMapParentCategoryToParentCategoryDtoWithNullParentCategoryDto(){
        ParentCategoryDto result = productParentCategoryMapper.setParentCategory(buildNullParentCategory())
                .mapParentCategoryToParentCategoryDto();

        assertNull(result.getId());
        assertNull(result.getParentCategoryName());
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