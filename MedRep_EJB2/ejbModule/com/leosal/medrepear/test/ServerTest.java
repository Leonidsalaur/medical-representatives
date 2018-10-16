package com.leosal.medrepear.test;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leosal.medrepear.eao.MedrepEAO;
import com.leosal.medrepear.entity.Person;

@Stateless
public class ServerTest {
	@EJB
	MedrepEAO eao;
	
	private static final String LOGIN="diana.salaurd";
	private static final String PASSWORD = "123456";
	private static Person user;

	public ServerTest() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void login(){
		user = eao.getUser(LOGIN, PASSWORD);
	}
	
	@Test
	public void test1(){
		List<Person> list = eao.allPersons();
		Assert.assertTrue(list!=null && list.size()>0);
	}
	
	

}
