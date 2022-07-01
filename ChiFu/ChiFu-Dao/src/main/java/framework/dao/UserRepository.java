package framework.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import framework.model.CfUserM;

public interface UserRepository extends JpaRepository<CfUserM,String> {
	Optional<CfUserM> findByEmail(String email);
}