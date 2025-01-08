package master_details_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import master_details_service.dto.master.MasterDto;
import master_details_service.dto.master.NewMasterDto;
import master_details_service.dto.master.UpdateMasterDto;
import master_details_service.mapper.MasterMapper;
import master_details_service.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("masters")
public class MasterController {

    private final MasterService service;
    private final MasterMapper mapper;

    @GetMapping
    public String getAllMasters(Model model) {
        log.info("---START GET ALL MASTERS ENDPOINT---");
        List<MasterDto> allMasters = mapper.toDtoList(service.getAllMasters());
        model.addAttribute("allMasters", allMasters);
        return "masters";
    }

    @GetMapping("/{masterId}")
    public String getMasterById(@PathVariable("masterId") Long masterId, Model model) {
        log.info("---START GET MASTER BY ID ENDPOINT---");
        MasterDto master = mapper.toMasterDto(service.getMasterByMasterId(masterId));
        model.addAttribute("master", master);
        return "edit-master";
    }

    @PostMapping
    public String createMaster(NewMasterDto dto, Model model) {
        log.info("---START CREATE MASTER ENDPOINT---");
        service.createMaster(mapper.toMasterFromNewMasterDto(dto));
        List<MasterDto> allMasters = mapper.toDtoList(service.getAllMasters());
        model.addAttribute("allMasters", allMasters);
        return "masters";
    }

    @PostMapping("/edit/{masterId}")
    public String updateMaster(@PathVariable("masterId") Long masterId, UpdateMasterDto dto, Model model) {
        log.info("---START UPDATE MASTER ENDPOINT---");
        MasterDto updatedMaster = mapper.toMasterDto(service.updateMaster(masterId, dto));
        model.addAttribute("master", updatedMaster);
        return "edit-master";
    }

    @PostMapping("/delete")
    public String deleteMaster(@RequestParam("masterId") long id, Model model) {
        log.info("---START DELETE MASTER ENDPOINT---");
        service.deleteMasterById(id);
        List<MasterDto> allMasters = mapper.toDtoList(service.getAllMasters());
        model.addAttribute("allMasters", allMasters);
        return "masters";
    }

}