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
package net.naijatek.myalumni.util.encryption;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Encoder {

    private static Log logger = LogFactory.getLog(Encoder.class);

    // Please note that 2 below methods are used in #getMD5_Base64 only
    // use them in other methods will make it not thread-safe
    private static MessageDigest digest = null;
    private static boolean isInited = false;

    private Encoder() {
    }

    public static synchronized String getMD5_Base64(final String input) {
        // please note that we dont use digest, because if we
        // cannot get digest, then the second time we have to call it
        // again, which will fail again
        if (isInited == false) {
            isInited = true;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (Exception ex) {
                logger.error("Cannot get MessageDigest. Application may fail to run correctly.", ex);
            }
        }
        if (digest == null) {
          return input;
        }
        // now everything is ok, go ahead
        try {
            digest.update(input.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException ex) {
            logger.error("Assertion: This should never occur.");
        }
        byte[] rawData = digest.digest();
        byte[] encoded = Base64.encode(rawData);
        String retValue = new String(encoded);
        return retValue;
    }

//    public static String encodeURL(String input) {
//       return URLEncoder.encode(input);
//    }

 /*   public static void main(final String[] args) {
        //test data should be
        //a1            iou3zTQ6oq2Zt9diAwhXog==
        //Hello World   sQqNsWTgdUEFt6mb5y4/5Q==
        String testString[] = {"$john","$jack","$mary","$steve"};

        for (String element : testString) {
            String encrypted = getMD5_Base64(element);
            logger.debug("encrypted = " + encrypted);
            logger.debug("length = " + encrypted.length());
        }
    }*/
      
}
