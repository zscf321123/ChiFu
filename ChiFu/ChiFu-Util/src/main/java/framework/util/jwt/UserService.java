package framework.util.jwt;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import framework.model.CfUserM;


public interface UserService extends UserDetailsService {
	public List<CfUserM> findAll();
	public Optional<CfUserM> findByEmail(String email);
	public void saveCfUserM(CfUserM cfUserM);
}
