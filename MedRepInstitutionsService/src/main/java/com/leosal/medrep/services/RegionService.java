package com.leosal.medrep.services;

import java.util.List;

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.Region;

public interface RegionService extends GenericCRUD<Region> {

	List<Region> findByRepLogin(String login);

}
