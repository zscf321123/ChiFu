package framework.service;

import java.util.List;

import framework.model.Festival;


public interface FestivalService {
	public List<Festival> findAllByOrderByFestivalDay();
	public void save(Festival festical);
}
