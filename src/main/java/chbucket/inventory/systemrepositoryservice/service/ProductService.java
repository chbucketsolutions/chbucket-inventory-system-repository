package chbucket.inventory.systemrepositoryservice.service;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct (ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto findProductByName(String productName);
    List<ProductDto> findAllProduct();
    String deleteProductById(String id);
}
