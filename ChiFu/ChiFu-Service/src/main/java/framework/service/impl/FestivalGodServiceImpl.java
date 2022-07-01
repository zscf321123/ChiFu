package framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.dao.FestivalGodRepository;
import framework.dao.FestivalRepository;
import framework.model.Festival;
import framework.model.FestivalGod;
import framework.service.FestivalGodService;
import framework.service.FestivalService;

@Service
public class FestivalGodServiceImpl implements FestivalGodService {
	@Autowired
	FestivalGodRepository festivalGodRepository;

	@Override
	public List<FestivalGod> findAll() {
		return festivalGodRepository.findAll();
	}

	@Override
	public void save(FestivalGod festivalGod) {
		festivalGodRepository.save(festivalGod);
	}

	@Override
	public List<FestivalGod> findByFestivalId(int festivalId) {
		return festivalGodRepository.findByIdFestivalId(festivalId);
	}


}