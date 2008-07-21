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
package net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.EmailExceptionVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemTasksDao;
import net.naijatek.myalumni.util.DBHelper;

public class SystemTasksHibernateDao extends BaseHibernateDao implements
		SystemTasksDao {

	public void deleteLogEmailException(EmailExceptionVO email) {
		// TODO Auto-generated method stub

	}

	public List getAllEmailExceptions() {
		// TODO Auto-generated method stub
		return null;
	}

	public EmailExceptionVO getEmailException(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void logEmailException(EmailExceptionVO email) {
		// TODO Auto-generated method stub

	}

	public void systemDatabaseBackup(String dbName, String dbDialet,
			String dbUser, String dbPswd, String dbIpAddress, String dbPort,
			String dumpPath, String dbPath) throws MyAlumniBaseException {

		String dumpCommand = new String();
		
		if (dbDialet.equals(DBHelper.DB_TYPE_MYSQL)) {
			dumpCommand = dbPath + "mysqldump " + dbName + " -h " + dbIpAddress + " -u " + dbUser + " -p" + dbPswd;
		} else {
			logger.debug("WRONG DB TYPE");
			//throw Exception
			//TODO
		}
		
		Runtime rt = Runtime.getRuntime();
		File test = new File(dumpPath);
		PrintStream ps;

		try {
			Process child = rt.exec(dumpCommand);
			ps = new PrintStream(test);
			InputStream in = child.getInputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				ps.write(ch);
			}

			InputStream err = child.getErrorStream();
			while ((ch = err.read()) != -1) {
				logger.error(ch);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			//	throw Exception
			// file created by error thrown
			//TODO
		}
	}

}
