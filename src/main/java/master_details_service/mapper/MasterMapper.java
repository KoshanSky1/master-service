package master_details_service.mapper;

import master_details_service.dto.master.MasterDto;
import master_details_service.dto.master.NewMasterDto;
import master_details_service.dto.master.UpdateMasterDto;
import master_details_service.model.Master;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface MasterMapper {
    Master toMasterFromNewMasterDto(NewMasterDto newMasterDto);

    MasterDto toMasterDto(Master master);

    List<MasterDto> toDtoList(List<Master> masters);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateMaster(UpdateMasterDto updateMasterDto, @MappingTarget Master masterToUpdate);
}
