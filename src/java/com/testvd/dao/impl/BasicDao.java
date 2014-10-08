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
	
	public void save(T object, Organization org)
	{
		getSession().save(object);
	}
	
	public void delete(Organization org, T object)
	{
		getSession().delete(object);
	}
	
	public void delete(long id, Organization org, Class<? extends T> persistentClass)
	{
		getSession().delete(find(id,org,persistentClass));
	}
	
	public T find(long id, Organization org, Class<? extends T> persistentClass)
	{
		return (T) getSession().get(persistentClass, new Long(id));
	}
	
	public void update(T object, Organization org)
	{
		getSession().update(object);
	}
	
	public Collection<T> findAll(Organization org)
	{
		return null;
	}
	
}
