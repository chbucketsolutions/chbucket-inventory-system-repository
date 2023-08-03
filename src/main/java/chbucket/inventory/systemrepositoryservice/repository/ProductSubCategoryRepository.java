package chbucket.inventory.systemrepositoryservice.repository;

import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSubCategoryRepository extends JpaRepository<SubCategory,String> {
    Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}
