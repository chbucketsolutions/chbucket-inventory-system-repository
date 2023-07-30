package chbucket.inventory.systemrepositoryservice.service;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto saveBrand (BrandDto brandDto);
    BrandDto updateBrand(BrandDto brandDto);
    BrandDto findBrandByName(String brandName);
    List<BrandDto> findAllBrand();
    String deleteBrandById(String id);
}
