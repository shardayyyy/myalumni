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
package net.naijatek.myalumni.controller.helper;

public final class ReasonCodes {
    
    public static String SUCCESS = "1";
    
    public static String INVALID_CREDENTIAL = "2";
    
    public static String ACCOUNT_INVALID = "3";
    
    public static String INVALID_EDITION_MODULES = "4";
    
    public static String ACCOUNT_LOCKED = "5";
    
    public static String NO_ROLES_FOUND = "6";
    
    public static String CHANGE_PASSWORD = "7";
    
    public static String ACCOUNT_UNAPPROVED = "8";
    
    public static String ACCOUNT_DEACTIVATED = "9";
    
    public static String ACCOUNT_UNAUTHORIZED = "10";
    
    public static String ACCOUNT_DELETED		= "11";

    
    public static synchronized String  getDescription(String code){
    	
    	if (code.equals("1")){
    		return "Success";
    	}
    	else if (code.equals("2")){
    		return "Invalid Credentials";
    	}
    	else if (code.equals("3")){
    		return "Invalid Account";
    	}
    	else if (code.equals("4")){
    		return "Invalid Edition Module";
    	}
    	else if (code.equals("5")){
    		return "Account Locked";
    	}
    	else if (code.equals("6")){
    		return "No Roles Found";
    	}
    	else if (code.equals("7")){
    		return "Change Password";
    	}
    	else if (code.equals("8")){
    		return "Account Unapproved";
    	}
    	else if (code.equals("9")){
    		return "Account Deactivated";
    	}
    	else if (code.equals("10")){
    		return "Account Unauthorized";
    	}
    	else if (code.equals("11")){
    		return "Account Previously Deleted";
    	}    	
    	else{
    		return "Unknown";
    	}
    }
    
    
}
