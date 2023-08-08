package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.service.ParentCategoryService;
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

class ParentCategoryControllerTest {
    private MockMvc mockMvc;

    private ParentCategoryDto parentCategoryDto;

    private ParentCategoryService parentCategoryService;

    @BeforeEach
    void setUp(){
        parentCategoryService = mock(ParentCategoryService.class);
        ParentCategoryController parentCategoryController = new ParentCategoryController(parentCategoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(parentCategoryController).build();

        parentCategoryDto = new ParentCategoryDto();
        parentCategoryDto.setId("1");
        parentCategoryDto.setParentCategoryName("Electronics");
    }

    @Test
    void parentCategorySaveSuccess() throws Exception{
        when(parentCategoryService.saveParentCategory(any(ParentCategoryDto.class))).thenReturn(parentCategoryDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/parent/category/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"parentCategoryName\":\"Electronics\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(parentCategoryService, times(1)).saveParentCategory(any(ParentCategoryDto.class));
    }

    @Test
    void parentCategorySaveIsBadRequest() throws Exception{
        when(parentCategoryService.saveParentCategory(any(ParentCategoryDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/parent/category/v1/save")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(parentCategoryService, times(0)).saveParentCategory(any(ParentCategoryDto.class));
    }

    @Test
    void parentCategoryUpdateSuccess() throws Exception{
        when(parentCategoryService.updateParentCategory(any(ParentCategoryDto.class))).thenReturn(parentCategoryDto);

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/parent/category/v1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"parentCategoryName\":\"Electronics\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(parentCategoryService, times(1)).updateParentCategory(any(ParentCategoryDto.class));
    }

    @Test
    void parentCategoryUpdateIsBadRequest() throws Exception{
        when(parentCategoryService.updateParentCategory(any(ParentCategoryDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/parent/category/v1/update")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(parentCategoryService, times(0)).updateParentCategory(any(ParentCategoryDto.class));
    }

    @Test
    void parentCategoryFindByNameSuccess() throws Exception{
        when(parentCategoryService.findParentCategoryByName(anyString())).thenReturn(parentCategoryDto);

        // Perform the HTTP GET request
        mockMvc.perform(get("/repository/parent/category/v1/find/Electronics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.parentCategoryName").value("Electronics"));

        // Verify that the service method was called
        verify(parentCategoryService, times(1)).findParentCategoryByName(anyString());
    }

    @Test
    void parentCategoryFindByNameIsBadRequest() throws Exception{
        String parentCategoryName = "errorBrandName";
        when(parentCategoryService.findParentCategoryByName(eq(parentCategoryName))).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)); // Return null to simulate bad request

        mockMvc.perform(get("/repository/parent/category/v1/find/{parentCategoryName}", parentCategoryName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(parentCategoryService, times(1)).findParentCategoryByName(eq(parentCategoryName));
    }

    @Test
    void parentCategoryFindAllBrandSuccess() throws Exception{
        List<ParentCategoryDto> parentCategoryDtos = Arrays.asList(parentCategoryDto);

        when(parentCategoryService.findAllParentCategory()).thenReturn(parentCategoryDtos);

        mockMvc.perform(get("/repository/parent/category/v1/find/allParentCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(parentCategoryDtos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].parentCategoryName").value(parentCategoryDtos.get(0).getParentCategoryName()));

        verify(parentCategoryService, times(1)).findAllParentCategory();
    }

    @Test
    void parentCategoryFindAllBrandIsBadRequest() throws Exception{

        when(parentCategoryService.findAllParentCategory()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/repository/parent/category/v1/find/allParentCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(parentCategoryService, times(1)).findAllParentCategory();
    }

    @Test
    void parentCategoryDeleteSuccess() throws Exception{

        when(parentCategoryService.deleteParentCategoryById(anyString())).thenReturn("parentCategoryId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully.");

        // Perform the HTTP DELETE request
        mockMvc.perform(delete("/repository/parent/category/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .value("parentCategoryId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully."));

        // Verify that the service method was called
        verify(parentCategoryService, times(1)).deleteParentCategoryById(anyString());
    }

    @Test
    void parentCategoryDeleteIsBadRequest() throws Exception{
        when(parentCategoryService.deleteParentCategoryById(anyString())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        // Perform the HTTP DELETE request
        mockMvc.perform(delete("/repository/parent/category/v1/delete/ee0518c3-ca4a-4a34-b72f-0671e03ee16d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(parentCategoryService, times(1)).deleteParentCategoryById(anyString());
    }


}