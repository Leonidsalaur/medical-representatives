package com.leosal.medrep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.leosal.medrep.entity.MedrepUser;

public interface UserDAO extends CrudRepository<MedrepUser, Integer> {
	
	@Query("SELECT u FROM MedrepUser u WHERE u.login IS NOT NULL")
	public List<MedrepUser> findUsers();
	
	@Query("SELECT u FROM MedrepUser u WHERE u.login=:login")
	public MedrepUser findByLogin(@Param("login") String login);

}
