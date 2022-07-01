package framework.service;

import java.util.List;

import framework.model.Festival;
import framework.model.Offering;


public interface OfferingService {
	public List<Offering> findAll();
	public void save(Offering offering);
	public List<Offering> findByGodId(int offeringId);
}
