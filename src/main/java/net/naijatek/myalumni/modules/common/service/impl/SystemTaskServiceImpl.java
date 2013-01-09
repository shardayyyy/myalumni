/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni MUST remain 
 * intact in the scripts and in the outputted HTML.
 * The "powered by" text/logo with a link back to
 * http://www.naijatek.com in 
 * the footer of the pages MUST remain visible when the pages
 * are viewed on the internet or intranet.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Support can be obtained from support forums at:
 * http://www.naijatek.com/myalumni/forum
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at naijatek com
 *
 * <p>Title: MyAlumni </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.modules.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.naijatek.myalumni.framework.extensions.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.EmailExceptionVO;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemTasksDao;
import net.naijatek.myalumni.modules.common.service.ISystemTaskService;
import net.naijatek.myalumni.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
  *
  * @author Folashade Adeyosoye
  * @version 1.0
  */
@Component
public class SystemTaskServiceImpl implements ISystemTaskService {

    @Autowired
    private SystemTasksDao systemTasksDao;

    /**
     * Dao Injection
     * @param systemTasksDao
     */
/*    public SystemTaskServiceImpl(SystemTasksDao systemTasksDao) {
        this.systemTasksDao = systemTasksDao;
    }*/

    public void logEmailException(EmailExceptionVO email) {
        systemTasksDao.logEmailException(email);
    }

    public void deleteLogEmailException(EmailExceptionVO email) {
        systemTasksDao.deleteLogEmailException(email);
    }


    public List getAllEmailExceptions() {
        return systemTasksDao.getAllEmailExceptions();
    }

    public EmailExceptionVO getEmailException(String id) {
        return systemTasksDao.getEmailException(id);
    }

    public void systemDatabaseBackup(String dumpPath)throws MyAlumniBaseException {
    	//  spring.properties  properties file.
        Properties prop = new Properties();
        String dbUser = new String();
        String dbPswd = new String();
        String dbIpAddress = new String();
        String dbName = new String();
        String dbPort = new String();
        String dbDialet = new String();
        String dbPath = new String();
        InputStream is = null;
        
        try {
        	 is = getClass().getResourceAsStream("/spring.properties");
            prop.load(is);
            dbUser = prop.getProperty("username");  
            dbPswd = prop.getProperty("password");
            dbIpAddress = prop.getProperty("dbip");
            dbName = prop.getProperty("dbname");
            dbPort = prop.getProperty("dbport");
            dbDialet = prop.getProperty("hibernate.dialect");
            dbPath = prop.getProperty("dbpath");
            
            if (!(new File(dbPath).exists())){
            	throw new MyAlumniBaseException("myalumni.errorcode.00005");
            }
            
            if (dbDialet != null && dbDialet.endsWith(DBHelper.DIALET_MYSQL)){
            	dbDialet = DBHelper.DB_TYPE_MYSQL;
            }
            else if (dbDialet != null && dbDialet.endsWith(DBHelper.DIALET_DB2)){
            	dbDialet = DBHelper.DB_TYPE_DB2;
            }
            else if (dbDialet != null && dbDialet.endsWith(DBHelper.DIALET_ORACLE)){
            	dbDialet = DBHelper.DB_TYPE_ORACLE;
            }
            else if (dbDialet != null && dbDialet.endsWith(DBHelper.DIALET_POSTGRESQL)){
            	dbDialet = DBHelper.DB_TYPE_POSTGRESQL;
            }            
            else if (dbDialet != null && dbDialet.endsWith(DBHelper.DIALET_SYBASE)){
            	dbDialet = DBHelper.DB_TYPE_SYBASE;
            }            
        } catch (IOException e) {
        	//logger.error(e.getMessage());
        	throw new MyAlumniBaseException("myalumni.errorcode.00003");    //Unable to read spring properties file.
        }
        finally {
            try {
              if (is != null) {
                is.close();
              }
            }
            catch (IOException exception) {
              // ignored
            }
        }
        systemTasksDao.systemDatabaseBackup( dbName,  dbDialet,  dbUser,  dbPswd,  dbIpAddress, dbPort, dumpPath, dbPath);
    }

}
