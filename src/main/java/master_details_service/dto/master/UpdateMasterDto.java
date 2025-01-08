package master_details_service.dto.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateMasterDto(
        Integer number,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDate date,
        @Pattern(regexp = "^(?!\\s*$).+", message = "Description can not be empty")
        String description
) {
}