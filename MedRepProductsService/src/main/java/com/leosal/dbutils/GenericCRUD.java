package com.leosal.dbutils;
import java.util.List;

public interface GenericCRUD <T extends DBEntity> {
	public T findById(Long id);
	public List<T> findAll();
	public T saveOrUpdate(T entity);
	public void remove(T entity);
}
