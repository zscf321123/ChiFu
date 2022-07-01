package framework.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import framework.model.FestivalGod;

@Mapper
public interface FestivalGodMapper {
	@Mapping(source = "festivalId", target = "id.festivalId")
	@Mapping(source = "godId", target = "id.godId")
	@Mapping(source = "godName", target = "id.godName")
	FestivalGod festivalGodDtoToFestivalGod(FestivalGodDto festivalGodDto);

	@Mapping(source = "id.festivalId", target = "festivalId")
	@Mapping(source = "id.godId", target = "godId")
	@Mapping(source = "id.godName", target = "godName")
	FestivalGodDto festivalGodToFestivalGodDto(FestivalGod festivalGod);

	List<FestivalGod> festivalGodList(List<FestivalGodDto> festivalGodDtos);
}
