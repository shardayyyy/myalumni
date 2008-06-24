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

/**
 *
 * <p>Title: MyAlumni</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Naijatek Solutions</p>
 * @author Folashade Adeyosoye
 * @version 1.0
 */

public class PrivateMessageVO  extends MyAlumniBaseVO{

	private String messageId;
	private String messageToUserId;
	private MemberVO messageToMember;
	private String messageFromUserId;
	private MemberVO messageFromMember;
	private String folderName;
	private String priority;
	private String subject;
	private Date messageDate;
	private String ipAddress;
	private String messageText;
	private String messageStatus;
	private String copyMe ;
	private String toWebmaster;
	
	// Non Database Support Attributes
	private String privMsgsAction;
	private String privAdminDelete;
	private String privAdminMove;
	private String type;	
	private String RoleType;
	private String guestEmail;
	private String guestName;
	
	
  public String getGuestName() {
		return guestName;
	}


	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}


public String getRoleType() {
		return RoleType;
	}


	public void setRoleType(String roleType) {
		RoleType = roleType;
	}


public PrivateMessageVO(String id) {
  	this.messageId = id;
	}

  
  /**
   * Constructor
   */
  public PrivateMessageVO() {
    super();
  }


public String getFolderName() {
	return folderName;
}


public void setFolderName(String folderName) {
	this.folderName = folderName;
}


public String getIpAddress() {
	return ipAddress;
}


public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}


public Date getMessageDate() {
	return messageDate;
}


public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
}

public String getMessageId() {
	return messageId;
}


public void setMessageId(String messageId) {
	this.messageId = messageId;
}


public String getMessageStatus() {
	return messageStatus;
}


public void setMessageStatus(String messageStatus) {
	this.messageStatus = messageStatus;
}


public String getMessageText() {
	return messageText;
}


public void setMessageText(String messageText) {
	this.messageText = messageText;
}


public String getPriority() {
	return priority;
}


public void setPriority(String priority) {
	this.priority = priority;
}


public String getSubject() {
	return subject;
}


public void setSubject(String subject) {
	this.subject = subject;
}


public String getCopyMe() {
	return copyMe;
}


public void setCopyMe(String copyMe) {
	this.copyMe = copyMe;
}


public String getPrivAdminDelete() {
	return privAdminDelete;
}


public void setPrivAdminDelete(String privAdminDelete) {
	this.privAdminDelete = privAdminDelete;
}


public String getPrivAdminMove() {
	return privAdminMove;
}


public void setPrivAdminMove(String privAdminMove) {
	this.privAdminMove = privAdminMove;
}


public String getPrivMsgsAction() {
	return privMsgsAction;
}


public void setPrivMsgsAction(String privMsgsAction) {
	this.privMsgsAction = privMsgsAction;
}

public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}

public String getGuestEmail() {
	return guestEmail;
}


public void setGuestEmail(String guestEmail) {
	this.guestEmail = guestEmail;
}


public MemberVO getMessageFromMember() {
	return messageFromMember;
}


public void setMessageFromMember(MemberVO messageFromMember) {
	this.messageFromMember = messageFromMember;
}


public String getMessageFromUserId() {
	return messageFromUserId;
}


public void setMessageFromUserId(String messageFromUserId) {
	this.messageFromUserId = messageFromUserId;
}


public MemberVO getMessageToMember() {
	return messageToMember;
}


public void setMessageToMember(MemberVO messageToMember) {
	this.messageToMember = messageToMember;
}


public String getMessageToUserId() {
	return messageToUserId;
}


public void setMessageToUserId(String messageToUserId) {
	this.messageToUserId = messageToUserId;
}


public String getToWebmaster() {
	return toWebmaster;
}


public void setToWebmaster(String toWebmaster) {
	this.toWebmaster = toWebmaster;
}
 
  
  
} //end of class PrivateMessageBean
