package chbucket.inventory.systemrepositoryservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionDto {
    private String id;
    private String userAccountId;
    private String token;
}
