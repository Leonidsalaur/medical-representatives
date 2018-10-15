package com.leosal.medrep.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leosal.medrep.entity.Institution;

@Service
public class InstitutionsServiceImpl implements InstitutionsService {

	@Override
	public Institution findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Institution> findAll() {
		System.out.println("Looking for all existing Institutions");
		return null;
	}

	@Override
	public Institution saveOrUpdate(Institution entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Institution entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Institution> findByRepLogin(String login) {
		System.out.println("Looking for Institutions for rep login: " + login);
		return null;
	}

}
