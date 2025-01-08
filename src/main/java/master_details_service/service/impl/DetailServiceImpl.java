package master_details_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import master_details_service.dto.detail.UpdateDetailDto;
import master_details_service.exception.EntityNotFoundException;
import master_details_service.exception.NameAlreadyExistsException;
import master_details_service.mapper.DetailMapper;
import master_details_service.model.Detail;
import master_details_service.model.Master;
import master_details_service.repository.DetailRepository;
import master_details_service.service.DetailService;
import master_details_service.service.LogService;
import master_details_service.service.MasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;
    private final LogService logService;
    private final MasterService masterService;
    private final DetailMapper detailMapper;

    @Override
    @Transactional
    public Detail createDetail(Long masterId, Detail detail) {
        Master master = masterService.getMasterByMasterId(masterId);
        checkIfTheNameIsAvailable(master, detail.getName());
        detail.setMaster(master);
        Detail detailSaved = detailRepository.save(detail);
        Long newMasterAmount = recalculateMasterAmount(masterId, detail.getAmount(), "ADD");
        master.setAmount(newMasterAmount);
        log.info("Added a new detail to the master with id=" + masterId);
        return detailSaved;
    }

    @Override
    @Transactional
    public Detail updateDetail(Long detailId, UpdateDetailDto updateDetailDto) {
        Detail detail = getDetailById(detailId);
        String oldName = detail.getName();
        Master master = masterService.getMasterByMasterId(detail.getMaster().getId());
        Long newMasterAmount;
        if (updateDetailDto.name() != null) {
            checkIfTheNameIsAvailable(master, updateDetailDto.name());
        }
        if (updateDetailDto.amount() != null) {
            if (detail.getAmount() > updateDetailDto.amount()) {
                newMasterAmount = recalculateMasterAmount(master.getId(),
                        detail.getAmount() - updateDetailDto.amount(), "DECREASE");
            } else {
                newMasterAmount = recalculateMasterAmount(master.getId(),
                        updateDetailDto.amount() - detail.getAmount(), "INCREASE");
            }
            master.setAmount(newMasterAmount);
        }
        detailMapper.updateDetail(updateDetailDto, detail);
        if (updateDetailDto.name().isEmpty()) {
            detail.setName(oldName);
        }
        Detail updatedDetail = detailRepository.save(detail);
        log.info("Updated detail with id=" + detailId);
        return updatedDetail;
    }

    @Override
    public Detail getDetailByDetailId(Long detailId) {
        Detail detail = getDetailById(detailId);
        log.info("Detail with id=" + detailId + " found");
        return detail;
    }

    @Override
    public List<Detail> getAllDetailByMasterId(Long masterId) {
        Master master = masterService.getMasterByMasterId(masterId);
        final List<Detail> details = detailRepository.findAllByMaster(master);
        log.info("A list of details for the master with id=" + masterId + " has been generated");
        return details;
    }

    @Override
    @Transactional
    public void deleteDetailById(Long detailId) {
        Detail detail = getDetailById(detailId);
        Master master = masterService.getMasterByMasterId(detail.getMaster().getId());
        Long newMasterAmount = recalculateMasterAmount(master.getId(), detail.getAmount(), "DELETE");
        master.setAmount(newMasterAmount);
        detailRepository.deleteById(detailId);
        log.info("Detail with id=" + detailId + "was deleted");
    }

    private Detail getDetailById(Long detailId) {
        return detailRepository.findById(detailId).orElseThrow(()
                -> new EntityNotFoundException("Detail with id=" + detailId + " was not found"));
    }

    private void checkIfTheNameIsAvailable(Master master, String name) {
        if (detailRepository.existsByMasterAndName(master, name)) {
            logService.saveLogMessage("Trying to re-add а detail with name=" + name + " to thr master with id: "
                    + master.getId());
            throw new NameAlreadyExistsException("This name " + name + " already exists in the master with id="
                    + master.getId());
        }
    }

    private Long recalculateMasterAmount(Long masterId, Long detailAmount, String option) {
        Master master = masterService.getMasterByMasterId(masterId);
        Long oldMasterAmount = master.getAmount();
        return switch (option) {
            case "ADD", "INCREASE" -> oldMasterAmount + detailAmount;
            case "DELETE", "DECREASE" -> oldMasterAmount - detailAmount;
            default -> oldMasterAmount;
        };
    }
}