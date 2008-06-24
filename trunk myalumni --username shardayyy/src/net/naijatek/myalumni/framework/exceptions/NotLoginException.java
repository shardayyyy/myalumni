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
package net.naijatek.myalumni.framework.exceptions;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;

public class NotLoginException extends MyAlumniBaseException {
   

    public static final int NOT_LOGIN                   = 0;
    public static final int ACCOUNT_LOCKED              = 1;
    public static final int ACCOUNT_UNAPPROVED          = 2;
    public static final int ACCOUNT_DEACTIVATED         = 3;
    public static final int ACCOUNT_ACTIVE              = 4;     
    public static final int NOT_ENOUGH_RIGHTS           = 5;
    public static final int WRONG_USERNAME          		= 6;
    public static final int WRONG_PASSWORD          	= 7;  
    public static final int USER_NOT_FOUND          		= 8;     
    public static final int SAME_PASSWORD          		= 9; 
    public static final int PROMPT_CHANGE_PASSWORD		= 10;
    
    
    
    /**
     * int exceptionReason
     */
    public int exceptionReason = NOT_LOGIN;

    /**
     * NotLoginException
     * @param msg String
     */
    public NotLoginException(String msg) {
        super(msg);
    }

    /**
     * NotLoginException
     * @param reason int
     */
    public NotLoginException(int reason) {
        exceptionReason = reason;
    }

    /**
     * NotLoginException
     * @param msg String
     * @param reason int
     */
    public NotLoginException(String msg, int reason) {
        super(msg);
        exceptionReason = reason;
    }


    /**
     * getErrorCode
     * @return String
     */
    public int getErrorCode() {
        return exceptionReason;
    }

	public int getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(int exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

 

}
