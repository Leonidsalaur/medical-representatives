package com.leosal.medrep.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leosal.medrep.entity.Advert;

@Service
public class AdvertsServiceMockImpl implements AdvertsService {

	@Override
	public Advert findById(Long id) {
		return new Advert(id, "Advert " + id, id);
	}

	@Override
	public List<Advert> findAll() {
		List<Advert> result = new ArrayList<Advert>();
		
		for(long i = 0; i < 10; i++) {
			result.add(new Advert(i, "Advert " + i, i));
		}
		
		return result;
	}

	@Override
	public Advert saveOrUpdate(Advert entity) {
		
		if(entity.getId() == null) {
			entity.setId((long) (Math.random() * 10000));
		}
		
		return entity;
	}

	@Override
	public void remove( Advert entity) {

	}

}
