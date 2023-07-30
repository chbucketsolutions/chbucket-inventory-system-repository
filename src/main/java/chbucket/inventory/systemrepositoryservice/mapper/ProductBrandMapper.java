package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;

public class ProductBrandMapper {
    private BrandDto brandDto;
    private Brand brand;

    private ProductBrandMapper(){
    }

    public static ProductBrandMapper of(){
        return new ProductBrandMapper();
    }

    public  ProductBrandMapper setBrandDto(BrandDto brandDto){
        this.brandDto = brandDto;
        return this;
    }

    public  ProductBrandMapper setBrand(Brand brand){
        this.brand = brand;
        return this;
    }

    public Brand mapBrandDtoToBrand(){
        if(brand == null){
            brand = new Brand();
        }
        brand.setBrandName(brandDto.getBrandName());
        return brand;
    }

    public BrandDto mapBrandToBrandDto(){
        if(brandDto == null){
            brandDto = new BrandDto();
            brandDto.setId(brand.getId());
        }
        brandDto.setBrandName(brand.getBrandName());

        return brandDto;
    }
}
