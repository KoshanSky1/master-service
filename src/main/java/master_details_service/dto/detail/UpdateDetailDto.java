package master_details_service.dto.detail;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UpdateDetailDto(
        @Pattern(regexp = "^(?!\\s*$).+", message = "Name can not be empty")
        String name,
        @Positive(message = "Amount must be positive")
        Long amount
) {
}