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
package net.naijatek.myalumni.modules.common.domain;



import java.util.Date;

import net.naijatek.myalumni.modules.common.helper.ReasonCodes;


/**
 * This class holds user login history information
 */
public class LoginHistoryVO extends MyAlumniBaseVO {

    private String loginHistoryId;
    private Date requestTime; // caches the Request time
    private String userName; // Caches the user id
    private String sourceIP; // caches the remote address
    private String loginStatus; // caches the login status
    private String userAgent;// caches the user agent
    private String reasonCode; // reason for login status.
    private String reasonCodeDesc;  // reason code description
    
    public String getReasonCodeDesc() {
    	reasonCodeDesc = ReasonCodes.getDescription(this.reasonCode);
		return reasonCodeDesc;
	}

	public void setReasonCodeDesc(String reasonCodeDesc) {
		this.reasonCodeDesc = reasonCodeDesc;
	}

	public LoginHistoryVO() {
    }

	public String getLoginHistoryId() {
		return loginHistoryId;
	}

	public void setLoginHistoryId(String accessHistoryId) {
		this.loginHistoryId = accessHistoryId;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }
}
