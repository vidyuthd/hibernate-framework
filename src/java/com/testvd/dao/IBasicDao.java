package com.testvd.dao;

import com.testvd.model.DomainObject;
import com.testvd.model.Organization;

public interface IBasicDao<T extends DomainObject> {
	
	void save(T object,Organization org);
	
	void delete(Organization org, T object);
	
	void delete(long id,Organization org, Class<? extends T> persistentClass);
	
	T find(long id,Organization org, Class<? extends T> persistentClass);
	
	void update(T object,Organization org);

}
