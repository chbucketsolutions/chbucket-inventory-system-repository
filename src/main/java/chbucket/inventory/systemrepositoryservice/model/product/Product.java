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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "name")
    private String name;

    @Column(name = "isActive")
    private Boolean isActive;

}
