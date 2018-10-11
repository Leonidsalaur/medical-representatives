package com.leosal.medrep.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leosal.medrep.entity.MedrepUser;

@Service
public class UserServiceMockImpl implements UserService {

	@Override
	public List<MedrepUser> findAll() {
		List<MedrepUser> users = new ArrayList<MedrepUser>();
		
		for(int i = 1; i <= 5; i++) {
			MedrepUser user = new MedrepUser();
			user.setId((long) i);
			user.setFirstName("Name" + i);
			user.setLogin("login" + i);
			user.setPassword("pass" + i);
			
			users.add(user);
		}
		
		return users;
	}

	@Override
	public MedrepUser findById(Long userId) {
		MedrepUser user = new MedrepUser();
		user.setId((long) userId);
		user.setFirstName("Name" + userId);
		user.setLogin("login" + userId);
		user.setPassword("pass" + userId);
		
		return user;
	}

	@Override
	public MedrepUser saveOrUpdate(MedrepUser user) {
		return user;
	}

	@Override
	public void remove(MedrepUser entity) {
		// TODO Auto-generated method stub
		
	}

}
