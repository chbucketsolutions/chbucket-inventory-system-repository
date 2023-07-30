package chbucket.inventory.systemrepositoryservice.dto.product;

import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
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
    private UserAccountDto userOwner;
    private SubCategoryDto categoryName;
    private Boolean isActive;

}
