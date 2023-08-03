package chbucket.inventory.systemrepositoryservice.model.product;

import chbucket.inventory.systemrepositoryservice.model.brand.Brand;
import chbucket.inventory.systemrepositoryservice.model.category.SubCategory;
import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_product")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class Product extends StandardEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "is_active")
    private Boolean isActive;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
