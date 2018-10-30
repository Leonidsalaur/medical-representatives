package com.leosal.medrep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leosal.medrep.dao.UserDAO;
import com.leosal.medrep.dto.MedRepUserDTO;
import com.leosal.medrep.entity.MedrepUser;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	public MedRepUserDTO findByLogin(String login) {
		MedrepUser user = userDAO.findByLogin(login);
		
		if(user != null) {
			return new MedRepUserDTO(user);
		} else {
			return null;
		}
	}

}
