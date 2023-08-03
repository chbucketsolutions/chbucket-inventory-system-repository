package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.SubCategoryDto;
import chbucket.inventory.systemrepositoryservice.mapper.ProductParentCategoryMapper;
import chbucket.inventory.systemrepositoryservice.mapper.ProductSubCategoryMapper;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.repository.ProductParentCategoryRepository;
import chbucket.inventory.systemrepositoryservice.repository.ProductSubCategoryRepository;
import chbucket.inventory.systemrepositoryservice.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final ProductSubCategoryRepository subCategoryRepository;
    private final ProductParentCategoryRepository parentCategoryRepository;
    private Optional<SubCategory> isSubCategoryExist;
    private Optional<ParentCategory> isParentCategoryExist;


    @Autowired
    public SubCategoryServiceImpl(ProductSubCategoryRepository subCategoryRepository, ProductParentCategoryRepository parentCategoryRepository){
        this.subCategoryRepository = subCategoryRepository;
        this.parentCategoryRepository = parentCategoryRepository;
    }

    @Override
    public SubCategoryDto saveSubCategory(SubCategoryDto subCategoryDto) {

        isParentCategoryExist = parentCategoryRepository.findById(subCategoryDto.getParentCategory().getId());

        if(isParentCategoryExist.isPresent()){

            SubCategory saveSubCategory = subCategoryRepository.save(ProductSubCategoryMapper.of()
                    .setSubCategoryDto(subCategoryDto)
                    .setParentCategory(isParentCategoryExist.get())
                    .mapSubCategoryDtoToSubCategory());

            subCategoryDto.setId(ProductSubCategoryMapper.of()
                    .setSubCategory(saveSubCategory)
                    .mapSubCategoryToSubCategoryDto().getId());

            return subCategoryDto;
        }

        return null;
    }

    @Override
    public SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto) {
        isParentCategoryExist = parentCategoryRepository.findByParentCategoryName(subCategoryDto.getParentCategory().getParentCategoryName());
        isSubCategoryExist = subCategoryRepository.findById(subCategoryDto.getId());


        if(isSubCategoryExist.isPresent() && isParentCategoryExist.isPresent()){

            subCategoryRepository.save(ProductSubCategoryMapper.of()
                .setSubCategory(isSubCategoryExist.get())
                .setSubCategoryDto(subCategoryDto)
                .setParentCategory(isParentCategoryExist.get())
                .mapSubCategoryDtoToSubCategory());

            return subCategoryDto;
        }
        return null;
    }

    @Override
    public SubCategoryDto findSubCategoryByName(String subCategoryName) {
        isSubCategoryExist = subCategoryRepository.findBySubCategoryName(subCategoryName);

        if(isSubCategoryExist.isPresent()){
            return ProductSubCategoryMapper.of()
                    .setSubCategory(isSubCategoryExist.get())

                    .setParentCategoryDto(ProductParentCategoryMapper.of()
                            .setParentCategory(isSubCategoryExist.get().getParentCategory())
                            .mapParentCategoryToParentCategoryDto())

                    .mapSubCategoryToSubCategoryDto();
        }

        return null;
    }

    @Override
    public List<SubCategoryDto> findAllSubCategory() {
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();

        List<SubCategoryDto> subCategoryDtoList = subCategoryList.stream().map(subCategory -> ProductSubCategoryMapper.of()
                .setSubCategory(subCategory)
                .setParentCategoryDto(ProductParentCategoryMapper.of()
                        .setParentCategory(subCategory.getParentCategory())
                        .mapParentCategoryToParentCategoryDto())
                .mapSubCategoryToSubCategoryDto())
                .collect(Collectors.toList());

        return subCategoryDtoList;
    }

    @Override
    public String deleteSubCategoryById(String id) {
        isSubCategoryExist = subCategoryRepository.findById(id);

        if(isSubCategoryExist.isPresent()){
            subCategoryRepository.delete(isSubCategoryExist.get());
            return "subCategoryId: " + id + " was deleted successfully.";
        }
        return "subCategoryId: " + id + " deletion failed.";
    }
}
