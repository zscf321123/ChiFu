package framework.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import framework.model.Festival;
import framework.model.FestivalGod;
import framework.model.Offering;

@Mapper
public interface OfferingMapper {
	@Mapping(source = "offeringId", target = "id.offeringId")
	@Mapping(source = "godId", target = "id.godId")
	Offering offeringDtoToOffering(OfferingDto offeringDto);

	@Mapping(source = "id.offeringId", target = "offeringId")
	@Mapping(source = "id.godId", target = "godId")
	OfferingDto offeringToOfferingDto(Offering offering);
	
	List<Offering> offeringList(List<OfferingDto> offeringDtos);
}
