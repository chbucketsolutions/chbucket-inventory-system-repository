package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.repository.ProductParentCategoryRepository;
import chbucket.inventory.systemrepositoryservice.repository.ProductSubCategoryRepository;
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
import static org.mockito.Mockito.times;

class SubCategoryServiceImplTest {

    @Mock
    ProductParentCategoryRepository parentCategoryRepository;

    @Mock
    ProductSubCategoryRepository productSubCategoryRepository;

    @InjectMocks
    SubCategoryServiceImpl subCategoryService;

    private ParentCategoryDto parentCategoryDto;
    private ParentCategory parentCategory;

    private SubCategoryDto subCategoryDto;
    private SubCategory subCategory;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

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
    }

    @Test
    void saveProductSubCategorySuccess() {

        when(parentCategoryRepository.findById(subCategoryDto.getParentCategory().getId())).thenReturn(Optional.of(parentCategory));
        when(productSubCategoryRepository.save(Mockito.any(SubCategory.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        SubCategoryDto savedProductSubCategoryDto = subCategoryService.saveSubCategory(subCategoryDto);

        assertNotNull(savedProductSubCategoryDto);
        assertEquals(subCategoryDto.getId(), savedProductSubCategoryDto.getId());
        assertEquals(subCategoryDto.getCategoryName(), savedProductSubCategoryDto.getCategoryName());
        assertEquals(subCategoryDto.getParentCategory().getParentCategoryName(), savedProductSubCategoryDto.getParentCategory().getParentCategoryName());
    }

    @Test
    void saveProductSubCategoryReturnNull() {
        when(parentCategoryRepository.findById(subCategoryDto.getParentCategory().getId())).thenReturn(Optional.empty());

        SubCategoryDto saveProductSubCategoryDto = subCategoryService.saveSubCategory(subCategoryDto);

        assertNull(saveProductSubCategoryDto);
    }

    @Test
    void updateProductSubCategorySuccess() {

        when(parentCategoryRepository.findByParentCategoryName(subCategoryDto.getParentCategory().getParentCategoryName())).thenReturn(Optional.of(parentCategory));
        when(productSubCategoryRepository.findById(subCategoryDto.getId())).thenReturn(Optional.of(subCategory));

        when(productSubCategoryRepository.save(Mockito.any(SubCategory.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        SubCategoryDto updateProductSubCategoryDto = subCategoryService.updateSubCategory(subCategoryDto);

        assertNotNull(updateProductSubCategoryDto);
        assertEquals(subCategoryDto.getId(), updateProductSubCategoryDto.getId());
        assertEquals(subCategoryDto.getCategoryName(), updateProductSubCategoryDto.getCategoryName());
        assertEquals(subCategoryDto.getParentCategory().getParentCategoryName(), updateProductSubCategoryDto.getParentCategory().getParentCategoryName());
    }

    @Test
    void updateProductSubCategoryReturnNull() {
        when(parentCategoryRepository.findByParentCategoryName(subCategoryDto.getParentCategory().getParentCategoryName())).thenReturn(Optional.empty());
        when(productSubCategoryRepository.findById(subCategoryDto.getId())).thenReturn(Optional.empty());

        SubCategoryDto updateProductSubCategoryDto = subCategoryService.updateSubCategory(subCategoryDto);

        assertNull(updateProductSubCategoryDto);
    }

    @Test
    void retrieveSubCategoryBySubCategoryNameSuccess() {
        String subCategoryName = "Laptop";

        when(productSubCategoryRepository.findBySubCategoryName(subCategoryName)).thenReturn(Optional.of(subCategory));

        SubCategoryDto findSubCategoryDtoByName = subCategoryService.findSubCategoryByName(subCategoryName);

        assertNotNull(findSubCategoryDtoByName);
        assertEquals(subCategoryDto.getId(), findSubCategoryDtoByName.getId());
        assertEquals(subCategoryDto.getCategoryName(), findSubCategoryDtoByName.getCategoryName());
        assertEquals(subCategoryDto.getParentCategory().getParentCategoryName(), findSubCategoryDtoByName.getParentCategory().getParentCategoryName());
    }

    @Test
    void retrieveProductSubCategoryBySubCategoryReturnsNull() {
        // Set up the mock behavior for the userAccountRepository
        when(productSubCategoryRepository.findBySubCategoryName(anyString())).thenReturn(Optional.empty());

        // Call the method under test
        SubCategoryDto findSubCategoryDtoByName = subCategoryService.findSubCategoryByName("Laptop");

        // Assert that the result is null
        assertNull(findSubCategoryDtoByName);
    }

    @Test
    void retrieveAllProductSubCategorySuccess() {

        when(productSubCategoryRepository.findAll()).thenReturn(List.of(subCategory));

        List<SubCategoryDto> subCategoryDtoList = subCategoryService.findAllSubCategory();

        assertNotNull(subCategoryDtoList);
        assertEquals(subCategoryDtoList.get(0).getId(), subCategoryDto.getId());
        assertEquals(subCategoryDtoList.get(0).getCategoryName(), subCategoryDto.getCategoryName());
        assertEquals(subCategoryDtoList.get(0).getParentCategory().getParentCategoryName(), subCategoryDto.getParentCategory().getParentCategoryName());
    }

    @Test
    void deleteProductSubCategorySuccess() {

        when(productSubCategoryRepository.findById(subCategoryDto.getId())).thenReturn(Optional.of(subCategory));

        doNothing().when(productSubCategoryRepository).delete(subCategory);

        String deleteSubCategory = subCategoryService.deleteSubCategoryById("1L");

        verify(productSubCategoryRepository, times(1)).delete(subCategory);
        assertNotNull(deleteSubCategory);
        assertEquals("subCategoryId: " + "1L" + " was deleted successfully.", deleteSubCategory);
    }

    @Test
    void deleteProductSubCategoryFailed() {

        when(productSubCategoryRepository.findById(subCategoryDto.getId())).thenReturn(Optional.empty());

        doNothing().when(productSubCategoryRepository).delete(subCategory);

        String deleteSubCategory = subCategoryService.deleteSubCategoryById("1L");

        verify(productSubCategoryRepository, times(0)).delete(subCategory);
        assertNotNull(deleteSubCategory);
        assertEquals("subCategoryId: " + "1L" + " deletion failed.", deleteSubCategory);
    }


}