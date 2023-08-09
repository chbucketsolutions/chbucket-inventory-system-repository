package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.model.product.Product;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.repository.ProductBrandRepository;
import chbucket.inventory.systemrepositoryservice.repository.ProductSubCategoryRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserAccountRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserProductServiceImplTest {

    @Mock
    UserProductRepository userProductRepository;

    @Mock
    ProductSubCategoryRepository productSubCategoryRepository;

    @Mock
    UserAccountRepository userAccountRepository;

    @Mock
    ProductBrandRepository productBrandRepository;

    @InjectMocks
    UserProductServiceImpl userProductService;

    private Product product;
    private ProductDto productDto;
    private UserAccount userAccount;
    private UserAccountProductDto userAccountProductDto;
    private SubCategoryProductDto subCategoryProductDto;
    private ParentCategory parentCategory;
    private SubCategory subCategory;
    private Brand brand;
    private BrandDto brandDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

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
    }

    @Test
    void saveUserProductSuccess() {

        when(productSubCategoryRepository.findById(productDto.getCategory().getId())).thenReturn(Optional.of(subCategory));
        when(productBrandRepository.findById(productDto.getBrand().getId())).thenReturn(Optional.of(brand));
        when(userAccountRepository.findById(productDto.getUserOwner().getId())).thenReturn(Optional.of(userAccount));

        when(userProductRepository.save(Mockito.any(Product.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        ProductDto savedProductDto = userProductService.saveProduct(productDto);

        assertNotNull(savedProductDto);
        assertEquals(productDto.getId(), savedProductDto.getId());
        assertEquals(productDto.getProductName(), savedProductDto.getProductName());
        assertEquals(productDto.getCategory().getCategoryName(), savedProductDto.getCategory().getCategoryName());
        assertEquals(productDto.getBrand().getBrandName(), savedProductDto.getBrand().getBrandName());
        assertEquals(productDto.getUserOwner().getUsername(), savedProductDto.getUserOwner().getUsername());
    }

    @Test
    void saveUserProductReturnNull() {
        when(productSubCategoryRepository.findById(productDto.getCategory().getId())).thenReturn(Optional.empty());
        when(productBrandRepository.findById(productDto.getBrand().getId())).thenReturn(Optional.empty());
        when(userAccountRepository.findById(productDto.getUserOwner().getId())).thenReturn(Optional.empty());

        ProductDto savedProductDto = userProductService.saveProduct(productDto);

        assertNull(savedProductDto);
    }

    @Test
    void updateUserProductSuccess() {

        when(productSubCategoryRepository.findById(productDto.getCategory().getId())).thenReturn(Optional.of(subCategory));
        when(productBrandRepository.findById(productDto.getBrand().getId())).thenReturn(Optional.of(brand));
        when(userAccountRepository.findById(productDto.getUserOwner().getId())).thenReturn(Optional.of(userAccount));
        when(userProductRepository.findById(productDto.getId())).thenReturn(Optional.of(product));

        when(userProductRepository.save(Mockito.any(Product.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        ProductDto updateProductDto = userProductService.updateProduct(productDto);

        assertNotNull(updateProductDto);
        assertEquals(productDto.getId(), updateProductDto.getId());
        assertEquals(productDto.getProductName(), updateProductDto.getProductName());
        assertEquals(productDto.getCategory().getCategoryName(), updateProductDto.getCategory().getCategoryName());
        assertEquals(productDto.getBrand().getBrandName(), updateProductDto.getBrand().getBrandName());
        assertEquals(productDto.getUserOwner().getUsername(), updateProductDto.getUserOwner().getUsername());
    }

    @Test
    void updateProductSubCategoryReturnNull() {
        when(productSubCategoryRepository.findById(productDto.getCategory().getId())).thenReturn(Optional.empty());
        when(productBrandRepository.findById(productDto.getBrand().getId())).thenReturn(Optional.empty());
        when(userAccountRepository.findById(productDto.getUserOwner().getId())).thenReturn(Optional.empty());
        when(userProductRepository.findById(productDto.getId())).thenReturn(Optional.of(product));

        ProductDto updateProductDto = userProductService.updateProduct(productDto);

        assertNull(updateProductDto);
    }

    @Test
    void retrieveUserProductByProductNameSuccess() {
        String productName = "Lenovo Laptop";

        when(userProductRepository.findByProductName(productName)).thenReturn(Optional.of(product));

        ProductDto findProductDtoByName = userProductService.findProductByName(productName);

        assertNotNull(findProductDtoByName);
        assertEquals(productDto.getId(), findProductDtoByName.getId());
        assertEquals(productDto.getProductName(), findProductDtoByName.getProductName());
        assertEquals(productDto.getCategory().getCategoryName(), findProductDtoByName.getCategory().getCategoryName());
        assertEquals(productDto.getBrand().getBrandName(), findProductDtoByName.getBrand().getBrandName());
        assertEquals(productDto.getUserOwner().getUsername(), findProductDtoByName.getUserOwner().getUsername());
    }

    @Test
    void retrieveProductSubCategoryBySubCategoryReturnsNull() {
        String productName = "Lenovo Laptop";

        when(userProductRepository.findByProductName(productName)).thenReturn(Optional.empty());

        ProductDto findProductDtoByName = userProductService.findProductByName(productName);

        // Assert that the result is null
        assertNull(findProductDtoByName);
    }

    @Test
    void retrieveAllUserProductSuccess() {

        when(userProductRepository.findAll()).thenReturn(List.of(product));

        List<ProductDto> productDtoList = userProductService.findAllProduct();

        assertNotNull(productDtoList);
        assertEquals(productDtoList.get(0).getId(), productDto.getId());
        assertEquals(productDtoList.get(0).getProductName(), productDto.getProductName());
        assertEquals(productDtoList.get(0).getCategory().getCategoryName(), productDto.getCategory().getCategoryName());
        assertEquals(productDtoList.get(0).getBrand().getBrandName(), productDto.getBrand().getBrandName());
        assertEquals(productDtoList.get(0).getUserOwner().getUsername(), productDto.getUserOwner().getUsername());
    }

    @Test
    void deleteUserProductSuccess() {

        when(userProductRepository.findById(productDto.getId())).thenReturn(Optional.of(product));

        doNothing().when(userProductRepository).delete(product);

        String deleteProduct = userProductService.deleteProductById("1L");

        verify(userProductRepository, times(1)).delete(product);
        assertNotNull(deleteProduct);
        assertEquals("productId: " + "1L" + " was deleted successfully.", deleteProduct);
    }

    @Test
    void deleteUserProductFailed() {

        when(userProductRepository.findById(productDto.getId())).thenReturn(Optional.empty());

        doNothing().when(userProductRepository).delete(product);

        String deleteProduct = userProductService.deleteProductById("1L");

        verify(userProductRepository, times(0)).delete(product);
        assertNotNull(deleteProduct);
        assertEquals("productId: " + "1L" + " deletion failed.", deleteProduct);
    }



}