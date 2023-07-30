package chbucket.inventory.systemrepositoryservice.repository;

import chbucket.inventory.systemrepositoryservice.model.product.Product;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProductRepository extends JpaRepository<Product,String> {
    Optional<UserAccount> findByUserAccount(UserAccount userAccount);
}
