package com.leosal.medrep;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leosal.medrep.entity.MedrepUser;

@Service
public class MedRepUserDetailService implements UserDetailsService {
	
	@Value("${medrep.oauth2.rest.username}")
	private String internalUser;
	
	@Value("${medrep.oauth2.rest.password}")
	private String internalPassword;
	
//	
//	public MedRepUserDetailService(String internalUser, String internalPassword) {
//		super();
//		
//		this.internalUser = internalUser;
//		this.internalPassword = internalPassword;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MedrepUser user = findUserByName(username);

		if(user != null) {
			return User.withUsername(username)
					.password(new BCryptPasswordEncoder().encode(user.getPassword()))
					.roles(user.getRoles().toArray(new String[user.getRoles().size()]))
					.build();
		} else {
			throw new UsernameNotFoundException(username + " not found");
		}
		
	}
	
	private MedrepUser findUserByName(String username) {
		MedrepUser user = null;
		
		if("leonid".equals(username)) {
			user = new MedrepUser();
			user.setFirstName("Name " + username);
			user.setId(1l);
			user.setLogin(username);
			user.setPassword("pass1");
			user.setRoles(Arrays.asList("USER", "ADMIN"));
		} else if("ilia".equals(username)) {
			user = new MedrepUser();
			user.setFirstName("Name " + username);
			user.setId(2l);
			user.setLogin(username);
			user.setPassword("pass2");
			user.setRoles(Arrays.asList("USER", "OPERATOR"));
		} else if(internalUser.equals(username)) {
			user = new MedrepUser();
			user.setFirstName("Name " + username);
			user.setId(3l);
			user.setLogin(username);
			user.setPassword(internalPassword);
			user.setRoles(Arrays.asList("ADMIN"));
		}

		
		return user;
	}

}
