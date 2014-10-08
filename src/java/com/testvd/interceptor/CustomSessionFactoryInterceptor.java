package com.testvd.interceptor;

import org.hibernate.EmptyInterceptor;

/**
 * Common interceptor for all sessions created using sessionfactory. Need to override custom methods of 
 * @link{org.hibernate.Interceptor} for custom purposes such as audit,etc.
 * @author vdandu
 *
 */
public class CustomSessionFactoryInterceptor extends EmptyInterceptor{

}
