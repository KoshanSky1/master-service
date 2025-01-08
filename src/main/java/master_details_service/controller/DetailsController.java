package master_details_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import master_details_service.dto.detail.DetailDto;
import master_details_service.dto.detail.NewDetailDto;
import master_details_service.dto.detail.UpdateDetailDto;
import master_details_service.mapper.DetailMapper;
import master_details_service.model.Detail;
import master_details_service.service.DetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("details")
public class DetailsController {

    private final DetailService service;
    private final DetailMapper mapper;

    @GetMapping("/{masterId}")
    public String getAllDetailsByMasterId(@PathVariable("masterId") Long masterId, Model model) {
        log.info("---START GET ALL DETAILS BY MASTER ID ENDPOINT---");
        List<DetailDto> allDetailByMasterId = mapper.toDtoList(service.getAllDetailByMasterId(masterId));
        model.addAttribute("allDetails", allDetailByMasterId);
        model.addAttribute("masterId", masterId);
        return "details";
    }

    @GetMapping("/get/{detailId}")
    public String getDetailById(@PathVariable("detailId") Long detailId, Model model) {
        log.info("---START GET DETAIL BY ID ENDPOINT---");
        Detail detail = service.getDetailByDetailId(detailId);
        DetailDto detailDto = mapper.toDetailDto(detail);
        model.addAttribute("detail", detail);
        model.addAttribute("masterId", detail.getMaster().getId());
        return "edit-detail";
    }

    @PostMapping("/{masterId}")
    public String createDetail(@PathVariable("masterId") Long masterId, NewDetailDto dto, Model model) {
        log.info("---START CREATE DETAIL ENDPOINT---");
        service.createDetail(masterId, mapper.toDetailFromNewDetailDto(dto));
        List<DetailDto> allDetailByMasterId = mapper.toDtoList(service.getAllDetailByMasterId(masterId));
        model.addAttribute("allDetails", allDetailByMasterId);
        model.addAttribute("masterId", masterId);
        return "details";
    }

    @PostMapping("/edit/{detailId}")
    public String updateDetail(@PathVariable("detailId") Long detailId, UpdateDetailDto dto, Model model) {
        log.info("---START UPDATE DETAIL ENDPOINT---");
        Detail detail = service.updateDetail(detailId, dto);
        DetailDto detailDto = mapper.toDetailDto(detail);
        model.addAttribute("detail", detail);
        model.addAttribute("masterId", detail.getMaster().getId());
        return "edit-detail";
    }

    @PostMapping("/{masterId}/delete")
    public String deleteDetail(@PathVariable("masterId") Long masterId, @RequestParam("detailId") Long detailId,
                               Model model) {
        log.info("---START DELETE DETAIL ENDPOINT---");
        service.deleteDetailById(detailId);
        List<DetailDto> allDetailByMasterId = mapper.toDtoList(service.getAllDetailByMasterId(masterId));
        model.addAttribute("allDetails", allDetailByMasterId);
        model.addAttribute("masterId", masterId);
        return "details";
    }

}