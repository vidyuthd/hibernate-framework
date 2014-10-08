package com.testvd.init;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Class exposing Utility methods
 * @author vdandu
 *
 */
public class HibernateUtil {
	
	public static Session getSession()
	{
		try
		{
			Session hibernateSession =  EnvironmentInitializer.getSession();
			return hibernateSession;
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
		return null;
	}
	
	public void closeSession()
	{
		try
		{
			EnvironmentInitializer.closeSession();
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
	}
	
	public void beginTransaction()
	{
		try
		{
			EnvironmentInitializer.beginTransaction();
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
	}
	
	public void commitTransaction()
	{
		try
		{
			EnvironmentInitializer.commitTransaction();
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
	}
	
	public void rollbackTransaction()
	{
		try
		{
			EnvironmentInitializer.rollBackTransaction();
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
	}
	
	public void shutDown()
	{
		try
		{
			EnvironmentInitializer.shutdown();
		}
		catch(HibernateException hbe)
		{
			// add logger.error
		}
	}
}
