package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.model.product.Product;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProductMapperTest {

    private Product product;
    private ProductDto productDto;
    private UserAccount userAccount;
    private UserAccountProductDto userAccountProductDto;
    private SubCategoryProductDto subCategoryProductDto;
    private ParentCategory parentCategory;
    private SubCategory subCategory;
    private Brand brand;
    private BrandDto brandDto;
    private UserProductMapper userProductMapper;

    @BeforeEach
    void setUp(){

        userAccount = new UserAccount();
        userAccount.setId("1L");
        userAccount.setUsername("testuser");
        userAccount.setPassword("testpassword");
        userAccount.setEmail("test@example.com");
        userAccount.setContactNumber("1234567890");
        userAccount.setAddress("Test Address");
        userAccount.setFullName("Test User");

        userAccountProductDto = new UserAccountProductDto();
        userAccountProductDto.setId("1L");
        userAccountProductDto.setUsername("testuser");
        userAccountProductDto.setFullName("Test User");

        parentCategory = new ParentCategory();
        parentCategory.setId("1L");
        parentCategory.setParentCategoryName("Electronics");

        subCategory = new SubCategory();
        subCategory.setId("1L");
        subCategory.setSubCategoryName("Laptop");
        subCategory.setParentCategory(parentCategory);

        subCategoryProductDto = new SubCategoryProductDto();
        subCategoryProductDto.setId("1L");
        subCategoryProductDto.setCategoryName("Laptop");

        brandDto = new BrandDto();
        brandDto.setId("1");
        brandDto.setBrandName("Lenovo");

        brand = new Brand();
        brand.setId("1");
        brand.setBrandName("Lenovo");

        product = new Product();
        product.setId("1L");
        product.setProductName("Lenovo Laptop");
        product.setUserAccount(userAccount);
        product.setSubCategory(subCategory);
        product.setBrand(brand);
        product.setActive(true);

        productDto = new ProductDto();
        productDto.setId("1L");
        productDto.setProductName("Lenovo Laptop");
        productDto.setUserOwner(userAccountProductDto);
        productDto.setCategory(subCategoryProductDto);
        productDto.setBrand(brandDto);
        productDto.setIsActive(true);

        userProductMapper = UserProductMapper.of();
    }

    @Test
    void testMapProductDtoToProduct(){
        Product result = userProductMapper.setUserAccount(userAccount).setSubCategory(subCategory).setBrand(brand)
                .setProductDto(productDto).mapProductDtoToProduct();

        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getSubCategory().getSubCategoryName(), result.getSubCategory().getSubCategoryName());
        assertEquals(product.getBrand().getBrandName(), result.getBrand().getBrandName());
        assertEquals(product.getUserAccount().getUsername(), result.getUserAccount().getUsername());
        assertEquals(product.getActive(), result.getActive());
    }

    @Test
    void testMapProductToProductDto(){
        ProductDto result = userProductMapper
                .setUserAccountProductDto(userAccountProductDto)
                .setSubCategoryProductDto(subCategoryProductDto)
                .setBrandDto(brandDto)
                .setProduct(product).mapProductToProductDto();

        assertEquals(productDto.getId(), result.getId());
        assertEquals(productDto.getProductName(), result.getProductName());
        assertEquals(productDto.getCategory().getCategoryName(), result.getCategory().getCategoryName());
        assertEquals(productDto.getBrand().getBrandName(), result.getBrand().getBrandName());
        assertEquals(productDto.getUserOwner().getUsername(), result.getUserOwner().getUsername());
        assertEquals(productDto.getIsActive(), result.getIsActive());
    }

    @Test
    void testMapProductDtoToProductWithNullProduct(){
        Product result = userProductMapper.setUserAccount(buildNullUserAccount())
                .setSubCategory(buildNullSubCategory())
                .setBrand(buildNullBrand())
                .setProductDto(buildNullProductDto())
                .mapProductDtoToProduct();

        assertNull(result.getProductName());
        assertNull(result.getSubCategory().getSubCategoryName());
        assertNull(result.getBrand().getBrandName());
        assertNull(result.getUserAccount().getUsername());
        assertNull(result.getActive());
    }

    @Test
    void testMapProductToProductDtoWithNullProductDto(){
        ProductDto result = userProductMapper
                .setUserAccountProductDto(buildNullUserAccountProductDto())
                .setSubCategoryProductDto(buildNullSubCategoryProductDto())
                .setBrandDto(buildNullBrandDto())
                .setProduct(buildNullProduct()).mapProductToProductDto();

        assertNull(result.getId());
        assertNull(result.getProductName());
        assertNull(result.getCategory().getCategoryName());
        assertNull(result.getBrand().getBrandName());
        assertNull(result.getUserOwner().getUsername());
        assertNull(result.getIsActive());
    }


    UserAccount buildNullUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setId(null);
        userAccount.setUsername(null);
        userAccount.setPassword(null);
        userAccount.setEmail(null);
        userAccount.setContactNumber(null);
        userAccount.setAddress(null);
        userAccount.setFullName(null);

        return userAccount;
    }

    UserAccountProductDto buildNullUserAccountProductDto(){
        UserAccountProductDto userAccountProductDto = new UserAccountProductDto();

        userAccountProductDto.setId(null);
        userAccountProductDto.setUsername(null);
        userAccountProductDto.setFullName(null);

        return userAccountProductDto;
    }

    ParentCategory buildNullParentCategory(){
        ParentCategory parentCategory = new ParentCategory();
        parentCategory.setId(null);
        parentCategory.setParentCategoryName(null);
        return parentCategory;
    }

    SubCategory buildNullSubCategory(){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(null);
        subCategory.setSubCategoryName(null);
        subCategory.setParentCategory(buildNullParentCategory());
        return subCategory;
    }

    SubCategoryProductDto buildNullSubCategoryProductDto(){
        SubCategoryProductDto subCategoryProductDto = new SubCategoryProductDto();
        subCategoryProductDto.setId(null);
        subCategoryProductDto.setCategoryName(null);
        return subCategoryProductDto;
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

    Product buildNullProduct(){
        product = new Product();
        product.setId(null);
        product.setProductName(null);
        product.setUserAccount(buildNullUserAccount());
        product.setSubCategory(buildNullSubCategory());
        product.setBrand(buildNullBrand());
        product.setActive(null);

        return product;
    }

    ProductDto buildNullProductDto(){
        productDto = new ProductDto();
        productDto.setId(null);
        productDto.setProductName(null);
        productDto.setUserOwner(buildNullUserAccountProductDto());
        productDto.setCategory(buildNullSubCategoryProductDto());
        productDto.setBrand(buildNullBrandDto());
        productDto.setIsActive(null);

        return productDto;
    }

}