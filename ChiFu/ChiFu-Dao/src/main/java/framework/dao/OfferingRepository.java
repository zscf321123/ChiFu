package framework.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import framework.model.Offering;

public interface OfferingRepository extends JpaRepository<Offering,String> {
	List<Offering> findByIdGodId(int godId);
}