package com.testvd.dao.impl;

import java.util.Collection;

import com.testvd.dao.IBasicDao;
import com.testvd.model.DomainObject;
import com.testvd.model.Organization;

import static com.testvd.init.HibernateUtil.getSession;

/**
 * Basic Dao which should be extended by all the daos used for basic CRUD operations and generic methods
 * @author vdandu
 *
 */
public class BasicDao<T extends DomainObject> implements IBasicDao<T>{
	
	public void save(T object)
	{
		getSession().save(object);
	}
	
	public void delete(T object)
	{
		getSession().delete(object);
	}
	
	public void delete(long id, Class<? extends T> persistentClass)
	{
		getSession().delete(find(id,persistentClass));
	}
	
	public T find(long id, Class<? extends T> persistentClass)
	{
		return (T) getSession().get(persistentClass, new Long(id));
	}
	
	public void update(T object)
	{
		getSession().update(object);
	}
	
	public Collection<T> findAll(Organization org)
	{
		return null;
	}
	
}

