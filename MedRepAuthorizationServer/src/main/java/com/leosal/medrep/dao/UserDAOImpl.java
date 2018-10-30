//package com.leosal.medrep.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Repository;
//
//import com.leosal.medrep.entity.MedrepUser;
//
//@Repository
//public class UserDAOImpl implements UserDAO {
//	@PersistenceContext
//	EntityManager entityManager;
//
//	@Override
//	public MedrepUser findById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MedrepUser> findAll() {
//		Query q = entityManager.createQuery("SELECT p FROM Person p");
//		return q.getResultList();
//	}
//
//	@Override
//	public MedrepUser saveOrUpdate(MedrepUser entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void remove(MedrepUser entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//
//}
