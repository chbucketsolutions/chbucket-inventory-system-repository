package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.ParentCategoryDto;
import chbucket.inventory.systemrepositoryservice.mapper.ProductParentCategoryMapper;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import chbucket.inventory.systemrepositoryservice.repository.ProductParentCategoryRepository;
import chbucket.inventory.systemrepositoryservice.service.ParentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentCategoryServiceImpl implements ParentCategoryService {

    private final ProductParentCategoryRepository parentCategoryRepository;
    private Optional<ParentCategory> isParentCategoryExist;

    @Autowired
    public ParentCategoryServiceImpl(ProductParentCategoryRepository parentCategoryRepository){
        this.parentCategoryRepository = parentCategoryRepository;
    }


    @Override
    public ParentCategoryDto saveParentCategory(ParentCategoryDto parentCategoryDto) {
        ParentCategory saveParentCategory = parentCategoryRepository.save(ProductParentCategoryMapper.of()
                .setParentCategoryDto(parentCategoryDto).mapParentCategoryDtoToParentCategory());

        parentCategoryDto.setId(ProductParentCategoryMapper.of().setParentCategory(saveParentCategory)
                .mapParentCategoryToParentCategoryDto().getId());

        return parentCategoryDto;
    }

    @Override
    public ParentCategoryDto updateParentCategory(ParentCategoryDto parentCategoryDto) {
        isParentCategoryExist = parentCategoryRepository.findById(parentCategoryDto.getId());

        if(isParentCategoryExist.isPresent()){
            parentCategoryRepository.save(ProductParentCategoryMapper.of()
                .setParentCategory(isParentCategoryExist.get())
                .setParentCategoryDto(parentCategoryDto)
                .mapParentCategoryDtoToParentCategory());
            return parentCategoryDto;
        }
        return null;
    }

    @Override
    public ParentCategoryDto findParentCategoryByName(String parentCategoryName) {
        isParentCategoryExist = parentCategoryRepository.findByParentCategoryName(parentCategoryName);

        if(isParentCategoryExist.isPresent()){
            return ProductParentCategoryMapper.of()
                    .setParentCategory(isParentCategoryExist.get())
                    .mapParentCategoryToParentCategoryDto();
        }

        return null;
    }

    @Override
    public List<ParentCategoryDto> findAllParentCategory() {
        List<ParentCategory> parentCategoryList = parentCategoryRepository.findAll();
        List<ParentCategoryDto> parentCategoryDtoList = parentCategoryList.stream().map(parentCategory ->
                ProductParentCategoryMapper.of().setParentCategory(parentCategory).mapParentCategoryToParentCategoryDto())
                .collect(Collectors.toList());
        return parentCategoryDtoList;
    }

    @Override
    public String deleteParentCategoryById(String id) {
        isParentCategoryExist = parentCategoryRepository.findById(id);

        if(isParentCategoryExist.isPresent()){
            parentCategoryRepository.delete(isParentCategoryExist.get());
            return "parentCategoryId: " + id + " was deleted successfully.";
        }
        return "parentCategoryId: " + id + " deletion failed.";
    }
}
