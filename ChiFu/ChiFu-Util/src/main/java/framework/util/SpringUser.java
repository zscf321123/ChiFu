package framework.util;
import framework.model.CfUserM;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SpringUser implements UserDetails {
	private static final long serialVersionUID = 1L;
	private CfUserM cfUserM;

	public SpringUser(CfUserM cfUserM) {
		this.cfUserM = cfUserM;
	}

	public int getId() {
		return cfUserM.getUserId();
	}

	public String getName() {
		return cfUserM.getUserName();
	}
	
	public String getRole() {
		return cfUserM.getType();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(cfUserM.getType()));
	}

	@Override
	public String getPassword() {
		return cfUserM.getPassword();
	}

	@Override
	public String getUsername() {
		return cfUserM.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public CfUserM getPrincipal() {
        return this.cfUserM;
    }
}