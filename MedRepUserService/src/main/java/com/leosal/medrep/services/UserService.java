package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.dto.MedRepUserDTO;
import com.leosal.medrep.entity.MedrepUser;
import com.leosal.medrep.entity.Person;

public interface UserService {
	public MedrepUser findById(Long id);

	public List<MedRepUserDTO> findAll();

	public MedrepUser saveOrUpdate(MedrepUser entity);

	public void remove(MedrepUser entity);
}
