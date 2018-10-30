package com.leosal.medrep.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public MedrepUser findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MedRepUserDTO> findAll() {
		List<MedrepUser> daoList = userDAO.findUsers();
		
		List<MedRepUserDTO> result = new ArrayList<MedRepUserDTO>();
		
		daoList.forEach(user -> result.add(new MedRepUserDTO(user)));
		
		return result;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MedrepUser saveOrUpdate(MedrepUser entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void remove(MedrepUser entity) {
		// TODO Auto-generated method stub

	}

}
