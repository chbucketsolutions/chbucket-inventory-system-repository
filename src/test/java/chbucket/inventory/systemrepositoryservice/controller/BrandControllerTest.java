package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.BrandDto;
import chbucket.inventory.systemrepositoryservice.service.BrandService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandControllerTest {

    private MockMvc mockMvc;

    private BrandService brandService;

    private BrandDto brandDto;

    @BeforeEach
    void setUp(){
        brandService = mock(BrandService.class);
        BrandController brandController = new BrandController(brandService);
        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();

        brandDto = new BrandDto();
        brandDto.setId("1");
        brandDto.setBrandName("Lenovo");
    }

    @Test
    void brandSaveSuccess() throws Exception{
        when(brandService.saveBrand(any(BrandDto.class))).thenReturn(brandDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/brand/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandName\":\"Lenovo\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brandName").value("Lenovo"));

        // Verify that the service method was called
        verify(brandService, times(1)).saveBrand(any(BrandDto.class));
    }

    @Test
    void brandSaveIsBadRequest() throws Exception{
        when(brandService.saveBrand(any(BrandDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/brand/v1/save")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(brandService, times(0)).saveBrand(any(BrandDto.class));
    }

    @Test
    void brandUpdateSuccess() throws Exception{
        when(brandService.updateBrand(any(BrandDto.class))).thenReturn(brandDto);

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/brand/v1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"brandName\":\"Lenovo\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brandName").value("Lenovo"));

        // Verify that the service method was called
        verify(brandService, times(1)).updateBrand(any(BrandDto.class));
    }

    @Test
    void brandUpdateIsBadRequest() throws Exception{
        when(brandService.updateBrand(any(BrandDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP PUT request
        mockMvc.perform(put("/repository/brand/v1/update")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(brandService, times(0)).updateBrand(any(BrandDto.class));
    }

    @Test
    void brandFindByNameSuccess() throws Exception{
        when(brandService.findBrandByName(anyString())).thenReturn(brandDto);

        // Perform the HTTP GET request
        mockMvc.perform(get("/repository/brand/v1/find/Lenovo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brandName").value("Lenovo"));

        // Verify that the service method was called
        verify(brandService, times(1)).findBrandByName(anyString());
    }

    @Test
    void brandFindByNameIsBadRequest() throws Exception{
        String brandName = "errorBrandName";
        when(brandService.findBrandByName(eq(brandName))).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)); // Return null to simulate bad request

        mockMvc.perform(get("/repository/brand/v1/find/{brandName}", brandName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(brandService, times(1)).findBrandByName(eq(brandName));
    }

    @Test
    void brandFindAllBrandSuccess() throws Exception{
        List<BrandDto> brandDtos = Arrays.asList(brandDto);

        when(brandService.findAllBrand()).thenReturn(brandDtos);

        mockMvc.perform(get("/repository/brand/v1/find/allBrand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(brandDtos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].brandName").value(brandDtos.get(0).getBrandName()));

        verify(brandService, times(1)).findAllBrand();
    }

    @Test
    void brandFindAllBrandIsBadRequest() throws Exception{

        when(brandService.findAllBrand()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/repository/brand/v1/find/allBrand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(brandService, times(1)).findAllBrand();
    }

    @Test
    void brandDeleteSuccess() throws Exception{

        when(brandService.deleteBrandById(anyString())).thenReturn("brandId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully.");

        // Perform the HTTP GET request
        mockMvc.perform(delete("/repository/brand/v1/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .value("brandId: ee0518c3-ca4a-4a34-b72f-0671e03ee16d was deleted successfully."));

        // Verify that the service method was called
        verify(brandService, times(1)).deleteBrandById(anyString());
    }

    @Test
    void brandDeleteIsBadRequest() throws Exception{
        when(brandService.deleteBrandById(anyString())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        // Perform the HTTP POST request
        mockMvc.perform(delete("/repository/brand/v1/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(brandService, times(1)).deleteBrandById(anyString());
    }

}