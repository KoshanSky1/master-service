package master_details_service.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String error,
        Integer status
) {
}