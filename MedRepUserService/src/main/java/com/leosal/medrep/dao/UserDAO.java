package com.leosal.medrep.dao;

import org.springframework.data.repository.CrudRepository;

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.MedrepUser;

public interface UserDAO extends GenericCRUD<MedrepUser> {

}
