package com.leosal.medrep.services;

import java.util.ArrayList;
import java.util.List;

import com.leosal.medrep.dto.MedRepUserDTO;
import com.leosal.medrep.entity.MedrepUser;

//@Service
public class UserServiceMockImpl implements UserService {

	public List<MedRepUserDTO> findAll() {
		List<MedRepUserDTO> users = new ArrayList<MedRepUserDTO>();
		
		for(int i = 1; i <= 5; i++) {
			MedRepUserDTO user = new MedRepUserDTO();
			user.setId((int) i);
			user.setFirstName("Name" + i);
			user.setLogin("login" + i);
			user.setPassword("pass" + i);
			
			users.add(user);
		}
		
		return users;
	}

	public MedrepUser findById(Long userId) {
		MedrepUser user = new MedrepUser();
		user.setId(userId.intValue());
		user.setFirstname("Name" + userId);
		user.setLogin("login" + userId);
		user.setPassword("pass" + userId);
		
		return user;
	}

	public MedrepUser saveOrUpdate(MedrepUser user) {
		return user;
	}

	public void remove(MedrepUser entity) {
		// TODO Auto-generated method stub
		
	}

}
