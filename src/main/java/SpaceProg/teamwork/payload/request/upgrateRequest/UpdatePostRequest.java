package SpaceProg.teamwork.payload.request.upgrateRequest;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class UpdatePostRequest {

    @NotBlank
    private String header;

    @NotBlank
    private String text;

    @NotNull
    private Timestamp sendingTime;


}
