package com.relishcode.httpconcurrency.persistence.dao;

import java.util.List;

public interface GenericDao<E, PK> {	
	public E create(E entity);
	public E read(PK id);
	public E update(E entity);
	public List<E> findAll();
}
