package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.response.ResponseDto;
import chbucket.inventory.systemrepositoryservice.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("repository/sub/category/v1")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService){
        this.subCategoryService = subCategoryService;
    }

    @PostMapping("/save")
    public ResponseDto<SubCategoryDto> productSubCategorySave(@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseDto (subCategoryService.saveSubCategory(subCategoryDto));
    }

    @PutMapping("/update")
    public ResponseDto<SubCategoryDto> productSubCategoryUpdate(@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseDto (subCategoryService.updateSubCategory(subCategoryDto));
    }

    @GetMapping("/find/{subCategoryName}")
    public ResponseDto<SubCategoryDto> findProductSubCategoryByName(@PathVariable ("subCategoryName")
                                                                                  String subCategoryName){
        return new ResponseDto (subCategoryService.findSubCategoryByName(subCategoryName));
    }

    @GetMapping("/find/allSubCategory")
    public ResponseDto<List<SubCategoryDto>> findProductAllSubCategory(){
        return new ResponseDto (subCategoryService.findAllSubCategory());
    }

    @DeleteMapping("/delete/{subCategoryId}")
    public ResponseDto<String> productSubCategoryDeleteById(@PathVariable("subCategoryId") String subCategoryId){

        return new ResponseDto (subCategoryService.deleteSubCategoryById(subCategoryId));
    }
}
