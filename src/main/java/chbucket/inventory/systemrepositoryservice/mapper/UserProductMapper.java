package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.model.product.Product;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;

public class UserProductMapper {
    private Product product;
    private ProductDto productDto;
    private UserAccount userAccount;
    private UserAccountProductDto userAccountProductDto;
    private SubCategoryProductDto subCategoryProductDto;
    private SubCategory subCategory;
    private Brand brand;
    private BrandDto brandDto;

    private UserProductMapper(){
    }

    public static UserProductMapper of(){
        return new UserProductMapper();
    }

    public UserProductMapper setProduct(Product product){
        this.product = product;

        return this;
    }

    public UserProductMapper setProductDto(ProductDto productDto){
        this.productDto = productDto;

        return this;
    }

    public UserProductMapper setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        return this;
    }

    public UserProductMapper setUserAccountProductDto(UserAccountProductDto userAccountProductDto){
        this.userAccountProductDto = userAccountProductDto;
        return this;
    }

    public UserProductMapper setSubCategoryProductDto(SubCategoryProductDto subCategoryProductDto){
        this.subCategoryProductDto = subCategoryProductDto;

        return this;
    }

    public UserProductMapper setSubCategory(SubCategory subCategory){
        this.subCategory = subCategory;

        return this;
    }

    public UserProductMapper setBrandDto(BrandDto brandDto){
        this.brandDto = brandDto;

        return this;
    }

    public UserProductMapper setBrand(Brand brand){
        this.brand = brand;

        return this;
    }

    public Product mapProductDtoToProduct(){
        if(product == null){
            product = new Product();
        }
        product.setProductName(productDto.getProductName());
        product.setUserAccount(userAccount);
        product.setSubCategory(subCategory);
        product.setBrand(brand);
        product.setActive(productDto.getIsActive());

        return product;
    }

    public ProductDto mapProductToProductDto(){
        if(productDto == null){
            productDto = new ProductDto();
            productDto.setId(product.getId());
        }
        productDto.setProductName(product.getProductName());
        productDto.setUserOwner(userAccountProductDto);
        productDto.setCategory(subCategoryProductDto);
        productDto.setBrand(brandDto);
        productDto.setIsActive(product.getActive());

        return productDto;
    }

}
