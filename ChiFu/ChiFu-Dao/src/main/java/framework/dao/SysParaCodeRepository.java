package framework.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import framework.model.SysParaCode;

public interface SysParaCodeRepository extends JpaRepository<SysParaCode, String> {
	List<SysParaCode> findByIdSysIdAndIdParaId(String sysId, String paraId);

	Optional<SysParaCode> findByIdSysIdAndIdParaIdAndIdParaCode(String sysId, String paraId, String paraCode);

}