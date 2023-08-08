package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.repository.ProductBrandRepository;
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

class BrandServiceImplTest {

    @Mock
    ProductBrandRepository productBrandRepository;

    @InjectMocks
    BrandServiceImpl brandService;

    private BrandDto brandDto;
    private Brand brand;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        brandDto = new BrandDto();
        brandDto.setId("1");
        brandDto.setBrandName("Lenovo");

        brand = new Brand();
        brand.setId("1");
        brand.setBrandName("Lenovo");
    }

    @Test
    void saveProductBrandSuccess() {

        when(productBrandRepository.save(Mockito.any(Brand.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        BrandDto savedProductBrandDto = brandService.saveBrand(brandDto);

        assertNotNull(savedProductBrandDto);
        assertEquals(brandDto.getId(), savedProductBrandDto.getId());
        assertEquals(brandDto.getBrandName(), savedProductBrandDto.getBrandName());
    }

    @Test
    void updateProductBrandSuccess() {

        when(productBrandRepository.findById(brandDto.getId())).thenReturn(Optional.of(brand));

        when(productBrandRepository.save(Mockito.any(Brand.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        BrandDto updateProductBrandDto = brandService.updateBrand(brandDto);

        assertNotNull(updateProductBrandDto);
        assertEquals(brandDto.getId(), updateProductBrandDto.getId());
        assertEquals(brandDto.getBrandName(), updateProductBrandDto.getBrandName());
    }

    @Test
    void updateProductBrandReturnNull() {

        when(productBrandRepository.findById(anyString())).thenReturn(Optional.empty());

        BrandDto updateProductBrandDto = brandService.updateBrand(brandDto);

        assertNull(updateProductBrandDto);
    }

    @Test
    void retrieveBrandByBrandNameSuccess() {
        String brandName = "Lenovo";

        when(productBrandRepository.findByBrandName(brandName)).thenReturn(Optional.of(brand));

        BrandDto findBrandDtoByName = brandService.findBrandByName(brandName);

        assertNotNull(findBrandDtoByName);
        assertEquals(brandName, findBrandDtoByName.getBrandName());
        assertEquals(brandDto.getId(), findBrandDtoByName.getId());
    }

    @Test
    void retrieveProductBrandByBrandNameReturnsNull() {
        // Set up the mock behavior for the userAccountRepository
        when(productBrandRepository.findByBrandName(anyString())).thenReturn(Optional.empty());

        // Call the method under test
        BrandDto result = brandService.findBrandByName("Lenovo");

        // Assert that the result is null
        assertNull(result);
    }

    @Test
    void retrieveAllProductBrandSuccess() {

        when(productBrandRepository.findAll()).thenReturn(List.of(brand));

        List<BrandDto> brandDtoList = brandService.findAllBrand();

        assertNotNull(brandDtoList);
        assertEquals(brandDtoList.get(0).getId(), brand.getId());
        assertEquals(brandDtoList.get(0).getBrandName(), brand.getBrandName());
    }

    @Test
    void deleteProductBrandSuccess() {

        when(productBrandRepository.findById(brandDto.getId())).thenReturn(Optional.of(brand));

        doNothing().when(productBrandRepository).delete(brand);

        String deleteBrand = brandService.deleteBrandById("1");

        verify(productBrandRepository, times(1)).delete(brand);
        assertNotNull(deleteBrand);
        assertEquals("brandId: " + "1" + " was deleted successfully.", deleteBrand);
    }

    @Test
    void deleteProductBrandFailed() {

        when(productBrandRepository.findById(anyString())).thenReturn(Optional.empty());

        String deleteBrand = brandService.deleteBrandById("1");

        verify(productBrandRepository, times(0)).delete(brand);
        assertNotNull(deleteBrand);
        assertEquals("brandId: " + "1" + " deletion failed.", deleteBrand);
    }




}