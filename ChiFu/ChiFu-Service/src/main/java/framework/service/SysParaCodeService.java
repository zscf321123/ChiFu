package framework.service;

import java.util.List;

import com.google.common.base.Optional;

import framework.model.SysParaCode;

public interface SysParaCodeService {
	public List<SysParaCode> findByIdSysIdAndIdParaId(String sysId,String paraId);

	public String findByIdSysIdAndIdParaIdAndIdParaCode(String sysId, String paraId, String paraCode);
}
