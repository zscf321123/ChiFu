package framework.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import framework.map.FestivalDto;
import framework.map.FestivalGodDto;
import framework.map.FestivalGodMapper;
import framework.filter.R;
import framework.map.FestivalMapper;
import framework.map.OfferingDto;
import framework.map.OfferingMapper;
import framework.map.ProductMDto;
import framework.map.ProductMMapper;
import framework.model.Festival;
import framework.model.FestivalGod;
import framework.model.Offering;
import framework.service.CompanyInfoService;
import framework.service.FestivalGodService;
import framework.service.FestivalService;
import framework.service.OfferingService;
import framework.service.ProductMService;
import framework.service.SeqNoService;
import framework.service.SysParaCodeService;
import framework.util.LunarCalendar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(value = "http://localhost:8080")
@RestController
@Api(value = "取得所有節慶相關資料")
@RequestMapping("/festival/")
public class FestivalController {
	@Autowired
	FestivalService festivalService;
	@Autowired
	FestivalGodService festivalGodService;
	@Autowired
	OfferingService offeringService;
	@Autowired
	ProductMService productMService;
	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	SysParaCodeService sysParaCodeService;
	@Autowired
	SeqNoService seqNoService;
	@Autowired
	FestivalMapper festivalMapper;
	@Autowired
	FestivalGodMapper festivalGodMapper;
	@Autowired
	OfferingMapper offeringMapper;
	@Autowired
	ProductMMapper productMMapper;

	@ApiOperation(value = "取得所有節慶資料", notes = "取得所有節慶資料")
	@ApiResponses({ @ApiResponse(code = 200, message = "執行成功") })
	@GetMapping("festivalList")
	public R list() {
		try {
			LunarCalendar n = new LunarCalendar();
			List<FestivalDto> festivalDtoList = festivalService.findAllByOrderByFestivalDay().stream().map(s -> festivalMapper.festivalToFestivalDto(s)).collect(Collectors.toList());
			for (FestivalDto f : festivalDtoList) {
				List<FestivalGodDto> festivalGodDtos = new ArrayList<FestivalGodDto>();
				List<ProductMDto> productMDtos = new ArrayList<ProductMDto>();
				festivalGodDtos.addAll(festivalGodService.findByFestivalId(f.getFestivalId()).stream().map(s -> festivalGodMapper.festivalGodToFestivalGodDto(s)).collect(Collectors.toList()));
				for (FestivalGodDto fg : festivalGodDtos) {
					List<OfferingDto> offeringDtos = new ArrayList<OfferingDto>();
					offeringDtos.addAll(offeringService.findByGodId(fg.getGodId()).stream().map(s -> offeringMapper.offeringToOfferingDto(s)).collect(Collectors.toList()));
					for (OfferingDto oft : offeringDtos) {
						String offeringName = sysParaCodeService.findByIdSysIdAndIdParaIdAndIdParaCode("PD", "Offering", oft.getOfferingId());
						oft.setOfferingName(offeringName);
						oft.setGodId(fg.getGodId());
					}
					fg.setOffering(offeringDtos);
				}
				String paraValue = sysParaCodeService.findByIdSysIdAndIdParaIdAndIdParaCode("FV", "Offering", String.valueOf(f.getFestivalId()));
				List<Integer> items = paraValue.length() > 0 ? Arrays.asList(paraValue.split("\\|")).stream().map(Integer::parseInt).collect(Collectors.toList()) : null;
				if (items != null)
					productMDtos.addAll(productMService.findByProductIdIn(items).stream().map(s -> productMMapper.productMToProductMDto(s)).collect(Collectors.toList()));
				for (ProductMDto p:productMDtos) {
					p.setCompanyName(companyInfoService.findByComId(p.getComId()).getName());
				}
				f.setProductM(productMDtos);
				f.setFestivalGod(festivalGodDtos);
			}

			return R.isOk().data(festivalDtoList);
		} catch (Exception e) {
			return R.isFail(e);
		}
	}

	@ApiOperation(value = "儲存節慶資料", notes = "儲存節慶資料")
	@ApiResponses({ @ApiResponse(code = 200, message = "執行成功") })
	@PostMapping(value = "saveFestival", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public R save(@RequestBody FestivalDto festivalDto) {
		try {
			int i = 0;
			Date dt = new Date();
			String aplDate = new SimpleDateFormat("YYYYMMdd").format(dt);
			int seqF = seqNoService.findByModuleNo("festival");
			List<FestivalGod> festivalGodList = festivalGodMapper.festivalGodList(festivalDto.getFestivalGod());
			for (FestivalGod fg : festivalGodList) {
				int seqG = seqNoService.findByModuleNo("godId");
				List<Offering> offeringList = offeringMapper.offeringList(festivalDto.getFestivalGod().get(i).getOffering());
				for (Offering of : offeringList) {
					of.setCreDate(aplDate);
					of.setUpdDate(aplDate);
					of.getId().setGodId(seqG);
					offeringService.save(of);
					System.out.println("of:" + of.toString());
				}
				fg.getId().setFestivalId(seqF);
				fg.getId().setGodId(seqG);
				fg.setCreDate(aplDate);
				fg.setUpdDate(aplDate);
				festivalGodService.save(fg);
				i += 1;
			}

			Festival festival = festivalMapper.festivalDtoToFestival(festivalDto);
			festival.setFestivalId(seqF);
			festival.setCreDate(aplDate);
			festival.setUpdDate(aplDate);
			festivalService.save(festival);
			return R.isOk().data("執行成功");
		} catch (Exception e) {
			return R.isFail(e);
		}
	}

}