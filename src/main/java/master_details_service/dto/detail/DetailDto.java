package master_details_service.dto.detail;

import lombok.Builder;

@Builder
public record DetailDto(
        Long id,
        Long masterId,
        String name,
        Long amount
) {
}