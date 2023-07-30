package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.dto.response.ResponseDto;
import chbucket.inventory.systemrepositoryservice.service.ParentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("repository/parent/category/v1")
public class ParentCategoryController {
    private final ParentCategoryService parentCategoryService;

    @Autowired
    public ParentCategoryController(ParentCategoryService parentCategory){
        this.parentCategoryService = parentCategory;
    }

    @PostMapping("/save")
    public ResponseDto<ParentCategoryDto> productParentCategorySave(@RequestBody ParentCategoryDto parentCategoryDto){
        return new ResponseDto (parentCategoryService.saveParentCategory(parentCategoryDto));
    }

    @PutMapping("/update")
    public ResponseDto<ParentCategoryDto> productParentCategoryUpdate(@RequestBody ParentCategoryDto parentCategoryDto){
        return new ResponseDto (parentCategoryService.updateParentCategory(parentCategoryDto));
    }

    @GetMapping("/find/{parentCategoryName}")
    public ResponseDto<ParentCategoryDto> findProductParentCategoryByName(@PathVariable ("parentCategoryName")
                                                                                      String parentCategoryName){
        return new ResponseDto (parentCategoryService.findParentCategoryByName(parentCategoryName));
    }

    @GetMapping("/find/allParentCategory")
    public ResponseDto<List<ParentCategoryDto>> findProductAllParentCategory(){
        return new ResponseDto (parentCategoryService.findAllParentCategory());
    }

    @DeleteMapping("/delete/{parentCategoryId}")
    public ResponseDto<String> productParentCategoryDeleteById(@PathVariable("parentCategoryId") String parentCategoryId){

        return new ResponseDto (parentCategoryService.deleteParentCategoryById(parentCategoryId));
    }
}
