package chbucket.inventory.systemrepositoryservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String contactNumber;
    private String address;
}
