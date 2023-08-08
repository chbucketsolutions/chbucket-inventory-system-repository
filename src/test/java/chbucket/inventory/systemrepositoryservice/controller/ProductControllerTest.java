package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryProductDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {
    private MockMvc mockMvc;
    private UserAccountProductDto userAccountProductDto;
    private SubCategoryProductDto subCategoryProductDto;
    private BrandDto brandDto;
    private ProductDto productDto;
    private ProductService productService;
    private String requestBody;

    @BeforeEach
    void setUp() throws Exception{
        productService = mock(ProductService.class);
        ProductController productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        userAccountProductDto = new UserAccountProductDto();
        userAccountProductDto.setId("1L");
        userAccountProductDto.setUsername("testuser");
        userAccountProductDto.setFullName("Test User");

        subCategoryProductDto = new SubCategoryProductDto();
        subCategoryProductDto.setId("1L");
        subCategoryProductDto.setCategoryName("Laptop");

        brandDto = new BrandDto();
        brandDto.setId("1");
        brandDto.setBrandName("Lenovo");

        productDto = new ProductDto();
        productDto.setId("1L");
        productDto.setProductName("Lenovo Laptop");
        productDto.setUserOwner(userAccountProductDto);
        productDto.setCategory(subCategoryProductDto);
        productDto.setBrand(brandDto);
        productDto.setIsActive(true);

        ObjectMapper objectMapper = new ObjectMapper();
        requestBody = objectMapper.writeValueAsString(productDto);
    }

    @Test
    void productSaveSuccess() throws Exception{
        when(productService.saveProduct(any(ProductDto.class))).thenReturn(productDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/product/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.productName").value("Lenovo Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.fullName").value("Test User"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.brandName").value("Lenovo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.isActive").value(true));

        // Verify that the service method was called
        verify(productService, times(1)).saveProduct(any(ProductDto.class));
    }

    @Test
    void productSaveIsBadRequest() throws Exception{
        when(productService.saveProduct(any(ProductDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/product/v1/save")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(productService, times(0)).saveProduct(any(ProductDto.class));
    }

    @Test
    void productUpdateSuccess() throws Exception{
        when(productService.updateProduct(any(ProductDto.class))).thenReturn(productDto);

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/product/v1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.productName").value("Lenovo Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.fullName").value("Test User"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.brandName").value("Lenovo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.isActive").value(true));

        // Verify that the service method was called
        verify(productService, times(1)).updateProduct(any(ProductDto.class));
    }

    @Test
    void productUpdateIsBadRequest() throws Exception{
        when(productService.updateProduct(any(ProductDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/product/v1/update")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(productService, times(0)).updateProduct(any(ProductDto.class));
    }

    @Test
    void productFindByNameSuccess() throws Exception{
        when(productService.findProductByName(anyString())).thenReturn(productDto);

        // Perform the HTTP GET request
        mockMvc.perform(get("/repository/product/v1/find/Lenovo Laptop")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.productName").value("Lenovo Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userOwner.fullName").value("Test User"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.brandName").value("Lenovo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.isActive").value(true));

        // Verify that the service method was called
        verify(productService, times(1)).findProductByName(anyString());
    }

    @Test
    void productFindByNameIsBadRequest() throws Exception{
        String productName = "Lenovo Laptop";
        when(productService.findProductByName(eq(productName))).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)); // Return null to simulate bad request

        mockMvc.perform(get("/repository/product/v1/find/{productName}", productName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(productService, times(1)).findProductByName(eq(productName));
    }

    @Test
    void productFindAllProductSuccess() throws Exception{
        List<ProductDto> productDtos = Arrays.asList(productDto);

        when(productService.findAllProduct()).thenReturn(productDtos);

        mockMvc.perform(get("/repository/product/v1/find/allUserProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(productDtos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].productName").value(productDtos.get(0).getProductName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].userOwner.id").value(productDtos.get(0).getUserOwner().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].userOwner.username").value(productDtos.get(0).getUserOwner().getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].userOwner.fullName").value(productDtos.get(0).getUserOwner().getFullName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category.id").value(productDtos.get(0).getCategory().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category.categoryName").value(productDtos.get(0).getCategory().getCategoryName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].brand.id").value(productDtos.get(0).getBrand().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].brand.brandName").value(productDtos.get(0).getBrand().getBrandName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].isActive").value(productDtos.get(0).getIsActive()));

        verify(productService, times(1)).findAllProduct();
    }

    @Test
    void productFindAllProductIsBadRequest() throws Exception{

        when(productService.findAllProduct()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/repository/product/v1/find/allUserProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(productService, times(1)).findAllProduct();
    }

    @Test
    void productDeleteSuccess() throws Exception{

        when(productService.deleteProductById(anyString())).thenReturn("productId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully.");

        // Perform the HTTP GET request
        mockMvc.perform(delete("/repository/product/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .value("productId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully."));

        // Verify that the service method was called
        verify(productService, times(1)).deleteProductById(anyString());
    }

    @Test
    void productDeleteIsBadRequest() throws Exception{
        when(productService.deleteProductById(anyString())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        // Perform the HTTP POST request
        mockMvc.perform(delete("/repository/product/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(productService, times(1)).deleteProductById(anyString());
    }

}