package framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.dao.FestivalRepository;
import framework.model.Festival;
import framework.service.FestivalService;

@Service
public class FestivalServiceImpl implements FestivalService {
	@Autowired
	FestivalRepository festivalRepository;

	@Override
	public List<Festival> findAllByOrderByFestivalDay() {
		return festivalRepository.findAllByOrderByFestivalDay();
	}

	@Override
	public void save(Festival festical) {
		festivalRepository.save(festical);
	}

}