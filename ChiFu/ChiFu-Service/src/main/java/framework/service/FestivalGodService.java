package framework.service;

import java.util.List;

import framework.model.Festival;
import framework.model.FestivalGod;


public interface FestivalGodService {
	public List<FestivalGod> findAll();
	public List<FestivalGod> findByFestivalId(int festivalId);
	public void save(FestivalGod festivalGod);
}
