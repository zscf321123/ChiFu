package framework.map;

import org.mapstruct.Mapper;

import framework.model.Festival;

@Mapper
public interface FestivalMapper {
	Festival festivalDtoToFestival (FestivalDto festival);
	
	FestivalDto festivalToFestivalDto (Festival festival);

}
