package framework.util.jwt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import framework.dao.UserRepository;
import framework.model.CfUserM;
import framework.util.SpringUser;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public List<CfUserM> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<CfUserM> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveCfUserM(CfUserM cfUserM) {
		cfUserM.setPassword(bCryptPasswordEncoder.encode(cfUserM.getPassword()));
		System.out.println(cfUserM.toString());
		userRepository.save(cfUserM);
	}

	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        //username指的是在登入時, 由前端用戶在登入畫面所輸入的帳號資料, 所以這段會在登入驗證時啟動,
        //讀出是否有該帳號資料, 若查到就回傳做後續驗證比對
		CfUserM cfUserM = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new NullPointerException()).get();
        System.out.println("loadUserByUserEmail , cfUserM:"+cfUserM);
        if (cfUserM == null) throw new UsernameNotFoundException("user email:"+email);
        return new SpringUser(cfUserM);
	}

}