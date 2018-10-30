package com.leosal.medrep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.leosal.medrep.entity.Contact;

public interface ContactDAO extends CrudRepository<Contact, Integer> {
	
	@Query("SELECT p FROM Contact p "
			+ "INNER JOIN p.institutions i "
			+ "WHERE i.region.user.login=:login")
	public List<Contact> findByUserLogin(@Param("login") String userLogin);

}
