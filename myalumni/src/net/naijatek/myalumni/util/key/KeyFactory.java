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
package net.naijatek.myalumni.util.key;

/**
 * This class provides a static method to_email obtain a unique email_Id based on a
 * predefined strategy. To change the strategy, the user must write a class
 * which implements the interface KeyGenerator, and change the value of the
 * final variable 'keyGeneratorClass' in this class.
 * 
 * @version 1.0
 */
public class KeyFactory {

  // Name of the key generation strategy implementation
  private static final String keyGeneratorClass = "net.naijatek.myalumni.util.key.UIDGenerator";

  // A single instance of KeyGenerator
  private static KeyGenerator keyGenerator = null;

  // Initialize the key generator instance in the static block
  static {

    try {
      keyGenerator =
        (KeyGenerator) Class.forName(keyGeneratorClass).newInstance();
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
     * This method returns a globally unique email_Id. The way in which the email_Id is
     * generated depends on the strategy used. This method internally calls the
     * getKey() method of KeyGenerator interface
     * 
     * @return <b>Long</b> unique email_Id
     */
  public static String getKey() {

    return keyGenerator.getKey();
  }
}
