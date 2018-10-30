package com.leosal.medrep.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leosal.medrep.dto.MedRepUserDTO;
import com.leosal.medrep.enums.RoleType;

@Service
public class MedRepUserDetailService implements UserDetailsService {
	
	@Value("${medrep.oauth2.rest.username}")
	private String internalUser;
	
	@Value("${medrep.oauth2.rest.password}")
	private String internalPassword;
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MedRepUserDTO user = findUserByName(username);

		if(user != null) {
			
			return User.withUsername(username)
					.password(new BCryptPasswordEncoder().encode(user.getPassword()))
					.roles(user.getRoles().toArray(new String[user.getRoles().size()]))
					.build();
		} else {
			throw new UsernameNotFoundException(username + " not found");
		}
		
	}
	
	private MedRepUserDTO findUserByName(String username) {
		MedRepUserDTO user = null;
		
		if(internalUser.equals(username)) {
			user = new MedRepUserDTO();
			user.setFirstName("Name " + username);
			user.setId(-1);;
			user.setLogin(username);
			user.setPassword(internalPassword);
			user.setRoles(new HashSet<String>(Arrays.asList(RoleType.ADMIN.toString())));
		} else {
			user = userService.findByLogin(username);
		}

		
		return user;
	}

}
