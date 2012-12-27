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
package net.naijatek.myalumni.util.utilities;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class SystemProp {
  private static Log logger = LogFactory.getLog(SystemProp.class);
  private Properties properties;
  private final static SystemProp CFG = new SystemProp();
  private final static String PROPERTY_FILENAME = "system.properties";

  private SystemProp() {
    properties = new Properties();
    InputStream is = null;
    try {
      is = getClass().getResourceAsStream("/"+PROPERTY_FILENAME);
      properties.load(is);
      logger.info("Loaded the Property file... " + PROPERTY_FILENAME);
    }
    catch (Exception exception) {
      logger.fatal("Can't read the properties file. " + PROPERTY_FILENAME);
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
  }

  /**
   * Use singleton pattern, only return one instance of Configuration.
* @return Configuration
   */
  public static SystemProp getInstance() {
    return CFG;
  }

  /**
   * Retun a value for certain key.
* @param key a certain key define in properties file.
* @return value
   */
  public String getValue(final String key) {
    return properties.getProperty(key).trim();
  }
}

