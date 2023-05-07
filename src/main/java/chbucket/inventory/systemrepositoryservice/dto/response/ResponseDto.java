package chbucket.inventory.systemrepositoryservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
public class ResponseDto<T> {

    private String status;
    private String message;
    private T data;

    public ResponseDto() {
        this("ok","Default Empty Object Response", null);
    }

    public ResponseDto(T data) {
        this("ok", "Default Success Response", data);
    }

}
