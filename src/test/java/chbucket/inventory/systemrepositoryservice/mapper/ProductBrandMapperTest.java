package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductBrandMapperTest {

    private Brand brand;

    private BrandDto brandDto;

    private ProductBrandMapper productBrandMapper;

    @BeforeEach
    void setUp(){
        brandDto = new BrandDto();
        brandDto.setId("1");
        brandDto.setBrandName("Lenovo");

        brand = new Brand();
        brand.setId("1");
        brand.setBrandName("Lenovo");

        productBrandMapper = ProductBrandMapper.of();
    }

    @Test
    void testMapBrandDtotoBrand(){
        Brand result = productBrandMapper.setBrandDto(brandDto).mapBrandDtoToBrand();

        assertEquals(brand.getBrandName(), result.getBrandName());
    }

    @Test
    void testMapBrandtoBrandDto(){
        BrandDto result = productBrandMapper.setBrand(brand).mapBrandToBrandDto();

        assertEquals(brandDto.getId(), result.getId());
        assertEquals(brandDto.getBrandName(), result.getBrandName());
    }

    @Test
    void testMapBrandDtotoBrandWithNullBrand(){
        Brand result = productBrandMapper.setBrandDto(buildNullBrandDto()).mapBrandDtoToBrand();

        assertNull(result.getId());
        assertNull(result.getBrandName());
    }

    @Test
    void testMapBrandtoBrandDtoWithNullBrandDto(){
        BrandDto result = productBrandMapper.setBrand(buildNullBrand()).mapBrandToBrandDto();

        assertNull(result.getId());
        assertNull(result.getBrandName());
    }

    Brand buildNullBrand(){
        Brand brand = new Brand();
        brand.setId(null);
        brand.setBrandName(null);
        return brand;
    }

    BrandDto buildNullBrandDto(){
        BrandDto brandDto = new BrandDto();
        brandDto.setId(null);
        brandDto.setBrandName(null);
        return brandDto;
    }

}