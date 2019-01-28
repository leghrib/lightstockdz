package CRUD;



import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

public class NewHibernateUtil {

	public static final SessionFactory sessionFactory;
	private static Statistics stats;

	static {
		
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
			
			
			
			//Stat
			 stats = sessionFactory.getStatistics();
			stats.setStatisticsEnabled(true);
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionfactory() {
		System.out.println("-------  NBR SESSION OPENED : "+stats.getSessionOpenCount()+" ---------------------------------------");
		System.out.println("-------  NBR SESSION Closed : "+stats.getSessionCloseCount()+" ---------------------------------------");
		return sessionFactory;
	}

}
