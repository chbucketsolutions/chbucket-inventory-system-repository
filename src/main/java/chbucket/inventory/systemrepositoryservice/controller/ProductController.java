package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.response.ResponseDto;
import chbucket.inventory.systemrepositoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("repository/product/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseDto<ProductDto> userProductSave(@RequestBody ProductDto productDto){
        return new ResponseDto (productService.saveProduct(productDto));
    }

    @PutMapping("/update")
    public ResponseDto<ProductDto> userProductUpdate(@RequestBody ProductDto productDto){
        return new ResponseDto (productService.updateProduct(productDto));
    }

    @GetMapping("/find/{productName}")
    public ResponseDto<ProductDto> findUserProductByName(@PathVariable ("productName") String productName){
        return new ResponseDto (productService.findProductByName(productName));
    }

    @GetMapping("/find/allUserProduct")
    public ResponseDto<List<ProductDto>> findAllUserProduct(){
        return new ResponseDto (productService.findAllProduct());
    }

    @DeleteMapping("/delete/{userProductId}")
    public ResponseDto<String> userProductDeleteById(@PathVariable("userProductId") String productId){

        return new ResponseDto (productService.deleteProductById(productId));
    }

}
