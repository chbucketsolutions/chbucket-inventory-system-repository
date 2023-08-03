package chbucket.inventory.systemrepositoryservice.model.brand;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;
import chbucket.inventory.systemrepositoryservice.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_brand")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class Brand extends StandardEntity{
    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private List<Product> product;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
