package chbucket.inventory.systemrepositoryservice.model.brand;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_brand")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class Brand extends StandardEntity{
    @Column(name = "brand_name")
    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
