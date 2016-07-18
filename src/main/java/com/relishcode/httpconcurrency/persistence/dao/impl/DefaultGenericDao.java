package com.relishcode.httpconcurrency.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.relishcode.httpconcurrency.persistence.dao.GenericDao;

public abstract class DefaultGenericDao<E, PK> implements GenericDao<E, PK> {	

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<E> entityClass;
	
	public DefaultGenericDao () {}
	
	public DefaultGenericDao (Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	
	@Override
	public E read(PK id) {
		return entityManager.find(entityClass, id);
	}
	
	
	@Override
	public List<E> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(entityClass);
		Root<E> entity = cq.from(entityClass);
		cq.select(entity);
		return entityManager.createQuery(cq).getResultList();		
	}
	
	@Override
	public E update(E entity) {
		return entityManager.merge(entity);
	}	
}
