package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.response.ResponseDto;
import chbucket.inventory.systemrepositoryservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("repository/brand/v1")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @PostMapping("/save")
    public ResponseDto<BrandDto> productBrandSave(@RequestBody BrandDto brandDto){
        return new ResponseDto (brandService.saveBrand(brandDto));
    }

    @PutMapping("/update")
    public ResponseDto<BrandDto> productBrandUpdate(@RequestBody BrandDto brandDto){
        return new ResponseDto (brandService.updateBrand(brandDto));
    }

    @GetMapping("/find/{brandName}")
    public ResponseDto<BrandDto> findProductBrandByName(@PathVariable ("brandName") String brandName){
        return new ResponseDto (brandService.findBrandByName(brandName));
    }

    @GetMapping("/find/allBrand")
    public ResponseDto<List<BrandDto>> findProductAllBrand(){
        return new ResponseDto (brandService.findAllBrand());
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseDto<String> productBrandDeleteById(@PathVariable("brandId") String brandId){

        return new ResponseDto (brandService.deleteBrandById(brandId));
    }
}
