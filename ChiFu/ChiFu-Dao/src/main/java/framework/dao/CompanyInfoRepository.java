package framework.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import framework.model.CompanyInfo;
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
	
	Optional<CompanyInfo> findByComId(int comId);
}