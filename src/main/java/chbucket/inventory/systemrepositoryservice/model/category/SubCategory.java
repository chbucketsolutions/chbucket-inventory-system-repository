package chbucket.inventory.systemrepositoryservice.model.category;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;
import chbucket.inventory.systemrepositoryservice.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_subcategory")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class SubCategory extends StandardEntity {
    @ManyToOne
    @JoinColumn(name = "parentcategory_id")
    private ParentCategory parentCategory;

    @Column(name = "subcategory_name")
    private String subCategoryName;

    @OneToMany(mappedBy = "subCategory")
    private List<Product> product;

    public ParentCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
