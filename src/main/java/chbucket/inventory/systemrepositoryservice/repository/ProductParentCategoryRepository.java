package chbucket.inventory.systemrepositoryservice.repository;

import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductParentCategoryRepository extends JpaRepository<ParentCategory,String> {
    Optional<ParentCategory> findByParentCategoryName(String parentCategoryName);
}
