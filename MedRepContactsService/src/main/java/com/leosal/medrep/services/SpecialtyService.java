package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.dto.SpecialtyDTO;

public interface SpecialtyService {
	public SpecialtyDTO findById(Integer id);
	public List<SpecialtyDTO> findAll();
	public SpecialtyDTO saveOrUpdate(SpecialtyDTO entity);
	public void remove(SpecialtyDTO entity);

}
