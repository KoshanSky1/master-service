package master_details_service.service;

import master_details_service.dto.master.UpdateMasterDto;
import master_details_service.model.Master;

import java.util.List;

public interface MasterService {
    Master createMaster(Master master);

    Master updateMaster(Long masterId, UpdateMasterDto updateMasterDto);

    Master getMasterByMasterId(Long masterId);

    List<Master> getAllMasters();

    void deleteMasterById(Long masterId);
}