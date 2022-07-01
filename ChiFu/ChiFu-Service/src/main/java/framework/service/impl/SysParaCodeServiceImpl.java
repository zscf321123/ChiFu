package framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import framework.dao.FestivalGodRepository;
import framework.dao.FestivalRepository;
import framework.dao.SysParaCodeRepository;
import framework.model.Festival;
import framework.model.FestivalGod;
import framework.model.SysParaCode;
import framework.service.FestivalGodService;
import framework.service.FestivalService;
import framework.service.SysParaCodeService;

@Service
public class SysParaCodeServiceImpl implements SysParaCodeService {
	@Autowired
	SysParaCodeRepository sysParaCodeRepository;

	@Override
	public List<SysParaCode> findByIdSysIdAndIdParaId(String sysId, String paraId) {
		return sysParaCodeRepository.findByIdSysIdAndIdParaId(sysId, paraId);
	}

	@Override
	public String findByIdSysIdAndIdParaIdAndIdParaCode(String sysId, String paraId, String paraCode) {
		Optional<SysParaCode> sys=sysParaCodeRepository.findByIdSysIdAndIdParaIdAndIdParaCode(sysId,paraId,paraCode);
		if(sys.isPresent()) {
			return sys.get().getParaValue();
		}else {
			return "";			
		}
	}

}