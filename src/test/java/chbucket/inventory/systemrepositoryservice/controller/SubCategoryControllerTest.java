package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.service.SubCategoryService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SubCategoryControllerTest {
    private MockMvc mockMvc;
    private SubCategoryDto subCategoryDto;
    private ParentCategoryDto parentCategoryDto;
    private SubCategoryService subCategoryService;
    private String requestBody;

    @BeforeEach
    void setUp() throws Exception{

        subCategoryService = mock(SubCategoryService.class);
        SubCategoryController subCategoryController = new SubCategoryController(subCategoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(subCategoryController).build();

        parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId("1L");
        parentCategoryDto.setParentCategoryName("Electronics");

        subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId("1L");
        subCategoryDto.setCategoryName("Laptop");
        subCategoryDto.setParentCategory(parentCategoryDto);

        ObjectMapper objectMapper = new ObjectMapper();
        requestBody = objectMapper.writeValueAsString(subCategoryDto);
    }

    @Test
    void subCategorySaveSuccess() throws Exception{
        when(subCategoryService.saveSubCategory(any(SubCategoryDto.class))).thenReturn(subCategoryDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/sub/category/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(subCategoryService, times(1)).saveSubCategory(any(SubCategoryDto.class));
    }

    @Test
    void subCategorySaveIsBadRequest() throws Exception{
        when(subCategoryService.saveSubCategory(any(SubCategoryDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/sub/category/v1/save")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(subCategoryService, times(0)).saveSubCategory(any(SubCategoryDto.class));
    }

    @Test
    void subCategoryUpdateSuccess() throws Exception{
        when(subCategoryService.updateSubCategory(any(SubCategoryDto.class))).thenReturn(subCategoryDto);

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/sub/category/v1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(subCategoryService, times(1)).updateSubCategory(any(SubCategoryDto.class));
    }

    @Test
    void subCategoryUpdateIsBadRequest() throws Exception{
        when(subCategoryService.updateSubCategory(any(SubCategoryDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/sub/category/v1/update")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(subCategoryService, times(0)).updateSubCategory(any(SubCategoryDto.class));
    }

    @Test
    void subCategoryFindByNameSuccess() throws Exception{
        when(subCategoryService.findSubCategoryByName(anyString())).thenReturn(subCategoryDto);

        // Perform the HTTP GET request
        mockMvc.perform(get("/repository/sub/category/v1/find/Laptop")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.categoryName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.id").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategory.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(subCategoryService, times(1)).findSubCategoryByName(anyString());
    }

    @Test
    void subCategoryFindByNameIsBadRequest() throws Exception{
        String subCategoryName = "errorBrandName";
        when(subCategoryService.findSubCategoryByName(eq(subCategoryName))).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)); // Return null to simulate bad request

        mockMvc.perform(get("/repository/sub/category/v1/find/{brandName}", subCategoryName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(subCategoryService, times(1)).findSubCategoryByName(eq(subCategoryName));
    }

    @Test
    void subCategoryFindAllSubCategorySuccess() throws Exception{
        List<SubCategoryDto> subCategoryDtos = Arrays.asList(subCategoryDto);

        when(subCategoryService.findAllSubCategory()).thenReturn(subCategoryDtos);

        mockMvc.perform(get("/repository/sub/category/v1/find/allSubCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(subCategoryDtos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].categoryName").value(subCategoryDtos.get(0).getCategoryName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].parentCategory.id").value(subCategoryDtos.get(0).getParentCategory().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].parentCategory.parentCategoryName").value(subCategoryDtos.get(0).getParentCategory().getParentCategoryName()));

        verify(subCategoryService, times(1)).findAllSubCategory();
    }

    @Test
    void subCategoryFindAllSubCategoryIsBadRequest() throws Exception{

        when(subCategoryService.findAllSubCategory()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/repository/sub/category/v1/find/allSubCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(subCategoryService, times(1)).findAllSubCategory();
    }

    @Test
    void subCategoryDeleteSuccess() throws Exception{

        when(subCategoryService.deleteSubCategoryById(anyString())).thenReturn("subCategoryId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully.");

        // Perform the HTTP GET request
        mockMvc.perform(delete("/repository/sub/category/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .value("subCategoryId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully."));

        // Verify that the service method was called
        verify(subCategoryService, times(1)).deleteSubCategoryById(anyString());
    }

    @Test
    void subCategoryDeleteIsBadRequest() throws Exception{
        when(subCategoryService.deleteSubCategoryById(anyString())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        // Perform the HTTP POST request
        mockMvc.perform(delete("/repository/sub/category/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(subCategoryService, times(1)).deleteSubCategoryById(anyString());
    }


}