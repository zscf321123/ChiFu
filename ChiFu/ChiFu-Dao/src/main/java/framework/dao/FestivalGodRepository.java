package framework.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import framework.model.FestivalGod;

public interface FestivalGodRepository extends JpaRepository<FestivalGod,String> {
	List<FestivalGod> findByIdFestivalId(int festivalId);
}