package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.util.DBHelper;

import org.joda.time.DateTime;

import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

public class SystemTasksHibernateDaoTest extends BaseDAOTestCase {

	public SystemTasksHibernateDaoTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		init();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSystemDatabaseBackup() {
		DateTime dtime = new DateTime(new Date());
		String dateStr = dtime.getMonthOfYear() + "_" + dtime.getDayOfMonth() + "_" + dtime.getYear() + "_" + dtime.getHourOfDay() + "_" + dtime.getMinuteOfHour() + "_" + dtime.getSecondOfMinute(); 

        String dbUser = "myalumniuser";  
        String dbPswd = "myalumnipswd";
        String dbIpAddress = "localhost";
        String dbName = "myalumnidb";
        String dbPort = "3306";
        String dbDialet = DBHelper.DB_TYPE_MYSQL;
        String dbPath = "C:\\MyHome\\database\\mysql-5.0.45-win32\\bin\\";
        
		String dumpPath = "C:\\MyHome\\database\\backup\\" + dateStr + ".sql";
		
		
		try{
			sysDao.systemDatabaseBackup(dbName, dbDialet, dbUser, dbPswd, dbIpAddress, dbPort, dumpPath, dbPath);
		}
	    catch(MyAlumniBaseException ex){
	    	System.out.println(ex.getMessage());
	    	ex.printStackTrace();
	    } 
	}

}
