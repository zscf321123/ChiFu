package framework.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import framework.model.Festival;

public interface FestivalRepository extends JpaRepository<Festival,String> {
	List<Festival> findAllByOrderByFestivalDay();
}