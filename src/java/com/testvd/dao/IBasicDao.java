package com.testvd.dao;

import com.testvd.model.DomainObject;

public interface IBasicDao<T extends DomainObject> {
	
	void save(T object);
	
	void delete(T object);
	
	void delete(long id,Class<? extends T> persistentClass);
	
	T find(long id, Class<? extends T> persistentClass);
	
	void update(T object);

}

