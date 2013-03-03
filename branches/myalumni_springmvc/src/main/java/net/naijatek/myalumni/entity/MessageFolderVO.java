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
package net.naijatek.myalumni.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/*
 * Included columns: FolderName, MemberID, FolderOrder, FolderStatus, FolderOption,
 *                   FolderType, FolderCreationDate, FolderModifiedDate
 * Excluded columns:
 */
@Entity
@Table(name = "")
public class MessageFolderVO  extends MyAlumniBaseVO{
	
	
	private String messageFolderId;
    private String folderName;
    private String memberId;
    private MemberVO member;
    private int folderOrder;
    private Date folderCreationDate;
    private Date folderModificationDate;
    private int messageCount;
    private int unreadMessageCount;



    public int getMessageCount() {
		return messageCount;
	}


	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}


	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}


	public void setUnreadMessageCount(int unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}


	public String getMessageFolderId() {
		return messageFolderId;
	}


	public void setMessageFolderId(String messageFolderId) {
		this.messageFolderId = messageFolderId;
	}


	public MessageFolderVO(String id) {
    	this.messageFolderId = id;
	}

    
    /**
     * Constructor
     */
    public MessageFolderVO() {
      super();
  }


	public Date getFolderCreationDate() {
		return folderCreationDate;
	}


	public void setFolderCreationDate(Date folderCreationDate) {
		this.folderCreationDate = folderCreationDate;
	}


	public Date getFolderModificationDate() {
		return folderModificationDate;
	}


	public void setFolderModificationDate(Date folderModifiedDate) {
		this.folderModificationDate = folderModifiedDate;
	}


	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


	public int getFolderOrder() {
		return folderOrder;
	}


	public void setFolderOrder(int folderOrder) {
		this.folderOrder = folderOrder;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String userName) {
		this.memberId = userName;
	}


	public MemberVO getMember() {
		return member;
	}


	public void setMember(MemberVO member) {
		this.member = member;
	}

    

} //end of class MessageFolder
