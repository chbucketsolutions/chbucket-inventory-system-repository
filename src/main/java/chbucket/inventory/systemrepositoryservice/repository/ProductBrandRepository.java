package chbucket.inventory.systemrepositoryservice.repository;

import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBrandRepository extends JpaRepository<Brand,String> {
    Optional<Brand> findByBrandName(String brandName);
}
