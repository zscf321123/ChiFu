package framework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import framework.model.SeqNo;

public interface SeqNoRepository extends JpaRepository<SeqNo,String> {
	Optional<SeqNo> findByModuleNo(String moduleNo);
}