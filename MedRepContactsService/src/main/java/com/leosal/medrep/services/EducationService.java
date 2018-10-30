package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.dto.EducTypeDTO;

public interface EducationService {
	public EducTypeDTO findById(Integer id);
	public List<EducTypeDTO> findAll();
	public EducTypeDTO saveOrUpdate(EducTypeDTO entity);
	public void remove(EducTypeDTO entity);
}
