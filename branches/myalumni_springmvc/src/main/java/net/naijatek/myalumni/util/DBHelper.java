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
package net.naijatek.myalumni.util;


public interface DBHelper {

  // String Constants
  public final static String DB_TYPE_MYSQL = "mysql";
  public final static String DB_TYPE_DB2 = "db2";
  public final static String DB_TYPE_POSTGRESQL = "postgresql";
  public final static String DB_TYPE_ORACLE = "oracle";
  public final static String DB_TYPE_SQL_SERVER = "sqlserver";
  public final static String DB_TYPE_SYBASE = "sybase";
   
  
  
  // String Constants
  public final static String DIALET_MYSQL = "MySQLDialect";
  public final static String DIALET_DB2 = "DB2Dialect";
  public final static String DIALET_POSTGRESQL = "PostgreSQLDialect";
  public final static String DIALET_ORACLE = "OracleDialect";
  public final static String DIALET_SQL_SERVER = "SQLServerDialect";
  public final static String DIALET_SYBASE = "SybaseDialect";
  
  
}
