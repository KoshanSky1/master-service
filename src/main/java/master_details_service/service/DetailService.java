package master_details_service.service;

import master_details_service.dto.detail.UpdateDetailDto;
import master_details_service.model.Detail;

import java.util.List;

public interface DetailService {
    Detail createDetail(Long masterId, Detail detail);

    Detail updateDetail(Long detailId, UpdateDetailDto updateDetailDto);

    Detail getDetailByDetailId(Long detailId);

    List<Detail> getAllDetailByMasterId(Long masterId);

    void deleteDetailById(Long detailId);
}
