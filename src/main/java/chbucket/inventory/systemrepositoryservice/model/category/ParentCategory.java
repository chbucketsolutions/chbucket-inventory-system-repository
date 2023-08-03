package chbucket.inventory.systemrepositoryservice.model.category;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_parentcategory")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class ParentCategory extends StandardEntity {
    @Column(name = "parentcategory_name")
    private String parentCategoryName;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<SubCategory> subCategory;

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }
}
