package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.mapper.ProductBrandMapper;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.repository.ProductBrandRepository;
import chbucket.inventory.systemrepositoryservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final ProductBrandRepository brandRepository;
    private Optional<Brand> isBrandExist;
    private List<Brand> brandList;

    @Autowired
    public BrandServiceImpl(ProductBrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }


    @Override
    public BrandDto saveBrand(BrandDto brandDto) {
        Brand saveBrand = brandRepository.save(ProductBrandMapper.of().setBrandDto(brandDto).mapBrandDtoToBrand());
        brandDto.setId(ProductBrandMapper.of().setBrand(saveBrand).mapBrandToBrandDto().getId());
        return brandDto;
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        isBrandExist = brandRepository.findById(brandDto.getId());

        if(isBrandExist.isPresent()){
            Brand brandSave = brandRepository.save(ProductBrandMapper.of()
                    .setBrand(isBrandExist.get())
                    .setBrandDto(brandDto)
                    .mapBrandDtoToBrand());
            return brandDto;
        }
        return null;
    }

    @Override
    public BrandDto findBrandByName(String brandName) {
        isBrandExist = brandRepository.findByBrandName(brandName);

        if(isBrandExist.isPresent()){
            return ProductBrandMapper.of().setBrand(isBrandExist.get()).mapBrandToBrandDto();
        }

        return null;
    }

    @Override
    public List<BrandDto> findAllBrand() {
        List<Brand> brandList = brandRepository.findAll();
        List<BrandDto> brandDtoList = brandList.stream().map(brand -> ProductBrandMapper.of().setBrand(brand).mapBrandToBrandDto()).collect(Collectors.toList());
        return brandDtoList;
    }

    @Override
    public String deleteBrandById(String id) {
        isBrandExist = brandRepository.findById(id);

        if(isBrandExist.isPresent()){
            brandRepository.delete(isBrandExist.get());
            return "brandId: " + id + " was deleted successfully.";
        }
        return "brandId: " + id + " deletion failed.";
    }
}
