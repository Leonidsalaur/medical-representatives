package com.leosal.medrep.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leosal.medrep.entity.MedrepUser;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	EntityManager entityManager;

	@Override
	public MedrepUser findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedrepUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedrepUser saveOrUpdate(MedrepUser entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MedrepUser entity) {
		// TODO Auto-generated method stub
		
	}

	

}
