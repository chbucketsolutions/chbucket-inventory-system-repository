package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.repository.ProductParentCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ParentCategoryServiceImplTest {
    @Mock
    ProductParentCategoryRepository parentCategoryRepository;

    @InjectMocks
    ParentCategoryServiceImpl parentCategoryService;

    private ParentCategoryDto parentCategoryDto;
    private ParentCategory parentCategory;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        parentCategory = new ParentCategory();
        parentCategory.setId("1L");
        parentCategory.setParentCategoryName("Electronics");

        parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId("1L");
        parentCategoryDto.setParentCategoryName("Electronics");
    }

    @Test
    void saveProductParentCategorySuccess() {

        when(parentCategoryRepository.save(Mockito.any(ParentCategory.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        ParentCategoryDto savedProductParentCategoryDto = parentCategoryService.saveParentCategory(parentCategoryDto);

        assertNotNull(savedProductParentCategoryDto);
        assertEquals(parentCategoryDto.getId(), savedProductParentCategoryDto.getId());
        assertEquals(parentCategoryDto.getParentCategoryName(), savedProductParentCategoryDto.getParentCategoryName());
    }

    @Test
    void updateProductParentCategorySuccess() {

        when(parentCategoryRepository.findById(parentCategoryDto.getId())).thenReturn(Optional.of(parentCategory));

        when(parentCategoryRepository.save(Mockito.any(ParentCategory.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        ParentCategoryDto updateProductParentCategoryDto = parentCategoryService.updateParentCategory(parentCategoryDto);

        assertNotNull(updateProductParentCategoryDto);
        assertEquals(parentCategoryDto.getId(), updateProductParentCategoryDto.getId());
        assertEquals(parentCategoryDto.getParentCategoryName(), updateProductParentCategoryDto.getParentCategoryName());
    }

    @Test
    void updateProductParentCategoryReturnNull() {

        when(parentCategoryRepository.findById(anyString())).thenReturn(Optional.empty());

        ParentCategoryDto updateProductParentCategoryDto = parentCategoryService.updateParentCategory(parentCategoryDto);

        assertNull(updateProductParentCategoryDto);
    }

    @Test
    void retrieveParentCategoryByParentCategoryNameSuccess() {
        String parentCategoryName = "Electronics";

        when(parentCategoryRepository.findByParentCategoryName(parentCategoryName)).thenReturn(Optional.of(parentCategory));

        ParentCategoryDto findParentCategoryDtoByName = parentCategoryService.findParentCategoryByName(parentCategoryName);

        assertNotNull(findParentCategoryDtoByName);
        assertEquals(parentCategoryName, findParentCategoryDtoByName.getParentCategoryName());
        assertEquals(parentCategoryDto.getId(), findParentCategoryDtoByName.getId());
    }

    @Test
    void retrieveProductParentCategoryByParentCategoryReturnsNull() {
        // Set up the mock behavior for the userAccountRepository
        when(parentCategoryRepository.findByParentCategoryName(anyString())).thenReturn(Optional.empty());

        // Call the method under test
        ParentCategoryDto findParentCategoryDtoByName = parentCategoryService.findParentCategoryByName("Electronics");

        // Assert that the result is null
        assertNull(findParentCategoryDtoByName);
    }

    @Test
    void retrieveAllProductParentCategorySuccess() {

        when(parentCategoryRepository.findAll()).thenReturn(List.of(parentCategory));

        List<ParentCategoryDto> parentCategoryDtoList = parentCategoryService.findAllParentCategory();

        assertNotNull(parentCategoryDtoList);
        assertEquals(parentCategoryDtoList.get(0).getId(), parentCategory.getId());
        assertEquals(parentCategoryDtoList.get(0).getParentCategoryName(), parentCategory.getParentCategoryName());
    }

    @Test
    void deleteProductParentCategorySuccess() {

        when(parentCategoryRepository.findById(parentCategoryDto.getId())).thenReturn(Optional.of(parentCategory));

        doNothing().when(parentCategoryRepository).delete(parentCategory);

        String deleteParentCategory = parentCategoryService.deleteParentCategoryById("1L");

        verify(parentCategoryRepository, times(1)).delete(parentCategory);
        assertNotNull(deleteParentCategory);
        assertEquals("parentCategoryId: " + "1L" + " was deleted successfully.", deleteParentCategory);
    }

    @Test
    void deleteProductParentCategoryFailed() {

        when(parentCategoryRepository.findById(parentCategoryDto.getId())).thenReturn(Optional.empty());

        doNothing().when(parentCategoryRepository).delete(parentCategory);

        String deleteParentCategory = parentCategoryService.deleteParentCategoryById("1L");

        verify(parentCategoryRepository, times(0)).delete(parentCategory);
        assertNotNull(deleteParentCategory);
        assertEquals("parentCategoryId: " + "1L" + " deletion failed.", deleteParentCategory);
    }

}