package com.leosal.medrep.services;

import java.util.List;

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.Institution;

public interface InstitutionsService extends GenericCRUD<Institution> {
	List<Institution> findByRepLogin(String login);
}
