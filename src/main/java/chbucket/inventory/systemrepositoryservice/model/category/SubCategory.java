package chbucket.inventory.systemrepositoryservice.model.category;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_subcategory")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class SubCategory extends StandardEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentcategory_id")
    private ParentCategory parentCategory;

    @Column(name = "subcategory_name")
    private String subCategoryName;
}
