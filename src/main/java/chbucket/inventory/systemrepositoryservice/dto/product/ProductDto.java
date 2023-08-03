package chbucket.inventory.systemrepositoryservice.dto.product;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String productName;
    private UserAccountProductDto userOwner;
    private SubCategoryProductDto category;
    private BrandDto brand;
    private Boolean isActive;
}
