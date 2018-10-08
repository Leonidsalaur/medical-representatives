package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.entity.MedrepUser;

public interface UserService {
	List<MedrepUser> findAll();
	MedrepUser findById(Long userId);
	MedrepUser saveOrUpdate(MedrepUser user);
	boolean remove(Long userId);
}
