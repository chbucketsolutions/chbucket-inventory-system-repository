package chbucket.inventory.systemrepositoryservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategoryDto {
    private String id;
    private String parentCategoryName;
}
