package framework.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.dao.CompanyInfoRepository;
import framework.model.CompanyInfo;

import framework.service.CompanyInfoService;
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	@Autowired
	CompanyInfoRepository companyInfoRepository ;

	@Override
	public CompanyInfo findByComId(int comId) {
		Optional<CompanyInfo> companyInfOptional = companyInfoRepository.findByComId(comId);
		return companyInfOptional.isPresent()?companyInfOptional.get():null;
		}


}