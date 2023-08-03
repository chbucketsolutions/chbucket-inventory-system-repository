package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.product.ProductDto;
import chbucket.inventory.systemrepositoryservice.mapper.ProductBrandMapper;
import chbucket.inventory.systemrepositoryservice.mapper.ProductSubCategoryProductMapper;
import chbucket.inventory.systemrepositoryservice.mapper.UserAccountProductMapper;
import chbucket.inventory.systemrepositoryservice.mapper.UserProductMapper;
import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.model.product.Product;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.repository.ProductBrandRepository;
import chbucket.inventory.systemrepositoryservice.repository.ProductSubCategoryRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserAccountRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserProductRepository;
import chbucket.inventory.systemrepositoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProductServiceImpl implements ProductService {

    private final UserProductRepository userProductRepository;
    private final ProductSubCategoryRepository productSubCategoryRepository;
    private final UserAccountRepository userAccountRepository;
    private final ProductBrandRepository productBrandRepository;
    private Optional<UserAccount> isUserExist;
    private Optional<SubCategory> isSubCategoryExist;
    private Optional<Brand> isBrandExist;
    private Optional<Product>isProductExist;

    @Autowired
    public UserProductServiceImpl(UserProductRepository userProductRepository, ProductBrandRepository productBrandRepository,
                                  ProductSubCategoryRepository productSubCategoryRepository,
                                  UserAccountRepository userAccountRepository){

        this.userProductRepository = userProductRepository;
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productBrandRepository = productBrandRepository;
        this.userAccountRepository = userAccountRepository;

    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        isUserExist = userAccountRepository.findById(productDto.getUserOwner().getId());
        isBrandExist = productBrandRepository.findById(productDto.getBrand().getId());
        isSubCategoryExist = productSubCategoryRepository.findById(productDto.getCategory().getId());

        if(isUserExist.isPresent() && isBrandExist.isPresent() && isSubCategoryExist.isPresent()){
            Product productSave = userProductRepository.save(UserProductMapper.of()
                    .setProductDto(productDto)
                    .setUserAccount(isUserExist.get())
                    .setSubCategory(isSubCategoryExist.get())
                    .setBrand(isBrandExist.get())
                    .mapProductDtoToProduct());

            productDto.setId(UserProductMapper.of().setProduct(productSave)
                    .mapProductToProductDto().getId());

            return productDto;
        }

        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        isUserExist = userAccountRepository.findById(productDto.getUserOwner().getId());
        isBrandExist = productBrandRepository.findById(productDto.getBrand().getId());
        isSubCategoryExist = productSubCategoryRepository.findById(productDto.getCategory().getId());
        isProductExist = userProductRepository.findById(productDto.getId());

        if(isProductExist.isPresent() && isUserExist.isPresent() && isBrandExist.isPresent() && isSubCategoryExist.isPresent()){
            Product productSave = userProductRepository.save(UserProductMapper.of()
                    .setProductDto(productDto)
                    .setProduct(isProductExist.get())
                    .setUserAccount(isUserExist.get())
                    .setSubCategory(isSubCategoryExist.get())
                    .setBrand(isBrandExist.get())
                    .mapProductDtoToProduct());

            return productDto;
        }

        return null;
    }

    @Override
    public ProductDto findProductByName(String productName) {

        isProductExist = userProductRepository.findByProductName(productName);

        if(isProductExist.isPresent()){
            return UserProductMapper.of()
                    .setProduct(isProductExist.get())

                    .setUserAccountProductDto(UserAccountProductMapper
                            .of()
                            .setUserAccountProduct(isProductExist.get().getUserAccount())
                            .mapUserAccountToUserDto())

                    .setSubCategoryProductDto(ProductSubCategoryProductMapper
                            .of()
                            .setSubCategory(isProductExist.get().getSubCategory())
                            .mapSubCategoryToSubCategoryDto())

                    .setBrandDto(ProductBrandMapper
                            .of()
                            .setBrand(isProductExist.get().getBrand())
                            .mapBrandToBrandDto())

                    .mapProductToProductDto();
        }

        return null;
    }

    @Override
    public List<ProductDto> findAllProduct() {
        List<Product> productList = userProductRepository.findAll();

        List<ProductDto> productDtoList = productList.stream().map(product -> UserProductMapper.of()
                .setProduct(product)
                .setUserAccountProductDto(UserAccountProductMapper
                        .of()
                        .setUserAccountProduct(product.getUserAccount())
                        .mapUserAccountToUserDto())

                .setSubCategoryProductDto(ProductSubCategoryProductMapper
                        .of()
                        .setSubCategory(product.getSubCategory())
                        .mapSubCategoryToSubCategoryDto())

                .setBrandDto(ProductBrandMapper
                        .of()
                        .setBrand(product.getBrand())
                        .mapBrandToBrandDto())
                .mapProductToProductDto())
                .collect(Collectors.toList());

        return productDtoList;
    }

    @Override
    public String deleteProductById(String id) {
        isProductExist = userProductRepository.findById(id);

        if(isProductExist.isPresent()){
            userProductRepository.delete(isProductExist.get());
            return "productId: " + id + " was deleted successfully.";
        }

        return "productId: " + id + " deletion failed.";
    }
}
