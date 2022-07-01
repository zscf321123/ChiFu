package framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.dao.FestivalGodRepository;
import framework.dao.FestivalRepository;
import framework.dao.OfferingRepository;
import framework.dao.SysParaCodeRepository;
import framework.model.Festival;
import framework.model.FestivalGod;
import framework.model.Offering;

import framework.service.OfferingService;

@Service
public class OfferingServiceImpl implements OfferingService {
	@Autowired
	OfferingRepository offeringRepository;

	@Override
	public List<Offering> findAll() {
		return offeringRepository.findAll();
	}

	@Override
	public void save(Offering offering) {
		offeringRepository.save(offering);
	}

	@Override
	public List<Offering> findByGodId(int godId) {
		// TODO Auto-generated method stub
		return offeringRepository.findByIdGodId(godId);
	}

}