package master_details_service.mapper;

import master_details_service.dto.detail.DetailDto;
import master_details_service.dto.detail.NewDetailDto;
import master_details_service.dto.detail.UpdateDetailDto;
import master_details_service.model.Detail;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface DetailMapper {
    Detail toDetailFromNewDetailDto(NewDetailDto newDetailDto);

    DetailDto toDetailDto(Detail detail);

    List<DetailDto> toDtoList(List<Detail> details);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateDetail(UpdateDetailDto updateDetailDto, @MappingTarget Detail detailToUpdate);
}
