package com.leosal.medrep.services;

import com.leosal.medrep.dto.MedRepUserDTO;

public interface UserService {
	public MedRepUserDTO findByLogin(String login);
}
