package test.net.naijatek.myalumni.modules;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class SessionManager{
	
	private static SessionFactory sessionFactory;
	private static SessionManager singleton;
	
	private SessionManager(){}
	
	public SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			try{
				InputStream is = getClass().getResourceAsStream("/spring.properties");
				Properties props = new Properties();
				props.load(is);
				Configuration c = new Configuration();
				
				c.setProperty(Environment.USER, props.getProperty("username"))
				.setProperty(Environment.PASS, props.getProperty("password"))
				.setProperty(Environment.URL, props.getProperty("url"))
				.setProperty(Environment.DIALECT, props.getProperty("hibernate.dialect"))
				.setProperty(Environment.DRIVER, props.getProperty("driver"));
				
				sessionFactory = c.configure().buildSessionFactory();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}
	
	public static synchronized SessionManager getInstance(){
		if(singleton == null){
			singleton = new SessionManager();
		}
		return singleton;			
	}
}
