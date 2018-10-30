package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.dto.UniversityDTO;

public interface UniversityService {
	public UniversityDTO findById(Integer id);
	public List<UniversityDTO> findAll();
	public UniversityDTO saveOrUpdate(UniversityDTO entity);
	public void remove(UniversityDTO entity);
}
