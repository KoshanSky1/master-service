package master_details_service.dto.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NewMasterDto (
        @NotBlank(message = "Number can not be blank")
        Integer number,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @NotNull(message = "Date cannot be null")
        LocalDate date,
        @NotBlank(message = "Description can not be blank")
        String description
) {
}