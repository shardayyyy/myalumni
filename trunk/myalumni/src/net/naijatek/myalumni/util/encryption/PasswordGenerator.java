/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni Board MUST remain 
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
 * <p>Title: MyAlumni Board </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.util.encryption;

import java.util.Random;


/**
 * This class is a utility class for generating random passwords
 * for users. This could be used for temporay system authentication
 * purposes, and password change should be enforced on users.
 *
 * @author Folashade Adeyosoye
 * @version 1.0
 */
public class PasswordGenerator {

    /**
     * This method creates a new random password with the maximum
     * length specified in the input parameter.
     * 
     * @return <b>password</b> password generated.
     * 
     * @param <b>length</b> max length of the password.
     */
    public static String createPassword(int length) {

        String password = "";
        // Get a random object.
        Random generator = new Random();

        // array of possible password contents.
        final char characters[] =
        { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                                    'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                                    'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                                    'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
                                    '2', '3', '4', '5', '6', '7', '8', '9' };

        // loop thru to build append contents of password, up to max length.
        for (int i = 0; i < length; i++) {
            int rnd_int = generator.nextInt((characters.length) - 1);
            password += characters[rnd_int];
        }

        return password;
    }
}
