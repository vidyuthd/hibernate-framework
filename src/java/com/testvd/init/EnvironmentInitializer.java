package com.testvd.init;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Version;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.testvd.interceptor.CustomSessionFactoryInterceptor;

/**
 * Provides access to configuration info passed in <tt>Properties</tt> objects.
 * <br><br>
 * Hibernate has two property scopes:
 * <ul>
 * <li><b>Factory-level</b> properties may be passed to the <tt>SessionFactory</tt> when it
 * instantiated. Each instance might have different property values. If no
 * properties are specified, the factory calls <tt>Environment.getProperties()</tt>.
 * <li><b>System-level</b> properties are shared by all factory instances and are always
 * determined by the <tt>Environment</tt> properties.
 * </ul>
 * The only system-level properties are
 * <ul>
 * <li><tt>hibernate.jdbc.use_streams_for_binary</tt>
 * <li><tt>hibernate.cglib.use_reflection_optimizer</tt>
 * </ul>
 * <tt>Environment</tt> properties are populated by calling <tt>System.getProperties()</tt>
 * and then from a resource named <tt>/hibernate.properties</tt> if it exists. System
 * properties override properties specified in <tt>hibernate.properties</tt>.<br>
 * <br>
 * The <tt>SessionFactory</tt> is controlled by the following properties.
 * Properties may be either be <tt>System</tt> properties, properties
 * defined in a resource named <tt>/hibernate.properties</tt> or an instance of
 * <tt>java.util.Properties</tt> passed to
 * <tt>Configuration.build()</tt><br>
 * <br>
 * <table>
 * <tr><td><b>property</b></td><td><b>meaning</b></td></tr>
 * <tr>
 *   <td><tt>hibernate.dialect</tt></td>
 *   <td>classname of <tt>org.hibernate.dialect.Dialect</tt> subclass</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.connection.provider_class</tt></td>
 *   <td>classname of <tt>ConnectionProvider</tt>
 *   subclass (if not specified hueristics are used)</td>
 * </tr>
 * <tr><td><tt>hibernate.connection.username</tt></td><td>database username</td></tr>
 * <tr><td><tt>hibernate.connection.password</tt></td><td>database password</td></tr>
 * <tr>
 *   <td><tt>hibernate.connection.url</tt></td>
 *   <td>JDBC URL (when using <tt>java.sql.DriverManager</tt>)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.connection.driver_class</tt></td>
 *   <td>classname of JDBC driver</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.connection.isolation</tt></td>
 *   <td>JDBC transaction isolation level (only when using
 *     <tt>java.sql.DriverManager</tt>)
 *   </td>
 * </tr>
 *   <td><tt>hibernate.connection.pool_size</tt></td>
 *   <td>the maximum size of the connection pool (only when using
 *     <tt>java.sql.DriverManager</tt>)
 *   </td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.connection.datasource</tt></td>
 *   <td>databasource JNDI name (when using <tt>javax.sql.Datasource</tt>)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jndi.url</tt></td><td>JNDI <tt>InitialContext</tt> URL</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jndi.class</tt></td><td>JNDI <tt>InitialContext</tt> classname</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.max_fetch_depth</tt></td>
 *   <td>maximum depth of outer join fetching</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jdbc.batch_size</tt></td>
 *   <td>enable use of JDBC2 batch API for drivers which support it</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jdbc.fetch_size</tt></td>
 *   <td>set the JDBC fetch size</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jdbc.use_scrollable_resultset</tt></td>
 *   <td>enable use of JDBC2 scrollable resultsets (you only need this specify
 *   this property when using user supplied connections)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.jdbc.use_getGeneratedKeys</tt></td>
 *   <td>enable use of JDBC3 PreparedStatement.getGeneratedKeys() to retrieve
 *   natively generated keys after insert. Requires JDBC3+ driver and JRE1.4+</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.hbm2ddl.auto</tt></td>
 *   <td>enable auto DDL export</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.default_schema</tt></td>
 *   <td>use given schema name for unqualified tables (always optional)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.default_catalog</tt></td>
 *   <td>use given catalog name for unqualified tables (always optional)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.session_factory_name</tt></td>
 *   <td>If set, the factory attempts to bind this name to itself in the
 *   JNDI context. This name is also used to support cross JVM <tt>
 *   Session</tt> (de)serialization.</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.transaction.jta.platform</tt></td>
 *   <td>classname of <tt>org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform</tt>
 *   implementor</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.transaction.factory_class</tt></td>
 *   <td>the factory to use for instantiating <tt>Transaction</tt>s.
 *   (Defaults to <tt>JdbcTransactionFactory</tt>.)</td>
 * </tr>
 * <tr>
 *   <td><tt>hibernate.query.substitutions</tt></td><td>query language token substitutions</td>
 * </tr>
 * </table>
 *
 * @see org.hibernate.SessionFactory
 * The above text borrowed from  org.hibernate.cfg.Environment.java. so moving some properties from cfg xml to 
 * @author vdandu
 */
public final class EnvironmentInitializer 
{
	static
	{
		Version.logVersion();
			
		try
		{
			Configuration cfg = new Configuration().configure();
			cfg.setInterceptor(new CustomSessionFactoryInterceptor()); 
			hibernateSessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().build());
		}
		catch(Throwable ex)
		{
			ex.printStackTrace(System.out);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	protected static Session getSession() throws HibernateException
	{
		Session session = (Session)threadSession.get();
		
		// Open a new session if thread has none 
		try
		{
			if(session == null)
			{
				session = hibernateSessionFactory.openSession();
				threadSession.set(session);
			}
		}
		catch(HibernateException hbe)
		{
			throw new HibernateException(hbe);
		}
		
		return session;
	}
	
	protected static void closeSession() throws HibernateException
	{
		try
		{
			Session session = (Session) threadSession.get();
			threadSession.set(null);
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		catch(HibernateException hbe)
		{
			throw new HibernateException(hbe);
		}
	}
	
	protected static void beginTransaction()
	{
		Transaction tx = (Transaction) threadTransaction.get();
		
		// Open a new transaction if thread has none
		try
		{
			if(tx == null)
			{
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		}
		catch(HibernateException hbe)
		{
			throw new HibernateException(hbe);
		}
		
	}
	
	protected static void commitTransaction()
	{
		Transaction tx = (Transaction) threadTransaction.get();
		
		// Open a new transaction if thread has none
		try
		{
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack() )
			{
				tx.commit();
				threadTransaction.set(null);
			}
		}
		catch(HibernateException hbe)
		{
			throw new HibernateException(hbe);
		}
		
	}
	
	protected static void rollBackTransaction()
	{
		Transaction tx = (Transaction) threadTransaction.get();
		
		// Open a new transaction if thread has none
		try
		{
			threadTransaction.set(null);
			if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
			{
				tx.rollback();
			}
		}
		catch(HibernateException hbe)
		{
			throw new HibernateException(hbe);
		}
		finally
		{
			closeSession();
		}
		
	}
		
	/**
	 * shuts down session factory
	 * @throws HibernateException
	 */
	public static void shutdown() throws HibernateException
	{
		hibernateSessionFactory.close();
	}
	
	private static final SessionFactory hibernateSessionFactory;	
	
	private static final ThreadLocal threadSession = new ThreadLocal();
	
	private static final ThreadLocal threadTransaction = new ThreadLocal();
}
