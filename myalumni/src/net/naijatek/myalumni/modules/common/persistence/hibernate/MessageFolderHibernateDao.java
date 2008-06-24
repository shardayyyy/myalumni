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
package net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.MessageFolderVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.MessageFolderDao;

public class MessageFolderHibernateDao extends BaseHibernateDao implements MessageFolderDao {

	
	public void save(MessageFolderVO o) {
		add(o);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(MessageFolderVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete message folder because " + e.getMessage());
		}
	}
	
	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(MessageFolderVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete message Folder because " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<MessageFolderVO> findAll() {
		return getHibernateTemplate().find("from MessageFolderVO");
	}
        
	@SuppressWarnings("unchecked")
    public List<MessageFolderVO> findAllByStatus(String status) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam("messagefolder.bystatus", "status", status); 
	}
        

	public MessageFolderVO findById(String id) {
		return (MessageFolderVO) get(MessageFolderVO.class, id);
	}

	public void mergeObject(MessageFolderVO o) {
	    update(o);
	}
	
	
	// --------------------------------------------------------------------------
	
	
	
	public void createMemberFolder(String folderName, String memberId, 
            int folderOrder, int folderStatus, 
            int folderOption, int folderType, 
            Date folderCreationDate, 
            Date folderModifiedDate, String lastModifiedBy) {
		
		MessageFolderVO mf = new MessageFolderVO();
		mf.setFolderCreationDate(folderCreationDate);
		mf.setFolderModificationDate(new Date());
		mf.setFolderName(folderName);
		mf.setFolderOrder(folderOrder);
		mf.setLastModifiedBy(lastModifiedBy);
		mf.setLastModifiedDate(new Date());
		mf.setMemberId(memberId);
		mf.setMember((MemberVO)load(MemberVO.class, memberId));
		add(mf);
	}

	//public void createMemberMessageFolders(String memberId, String messageFolders) {
	//
	//}

	public void deleteMemberFolder(String folderName, String memberId) {
		MessageFolderVO o = getMessageFolder(folderName, memberId);
		hardDelete(o);
	}

	public void deleteMemberMessageFolders(String memberId) {
		List<MessageFolderVO> mfs = getMessageFoldersForMemberByMemberId(memberId);
		for(MessageFolderVO mf : mfs)
			hardDelete(mf);
	}

	public boolean findByPrimaryKey(String folderName, String memberId) {
		MessageFolderVO mf = null;
		boolean flag = false;
		mf = getMessageFolder(folderName, memberId);
		if (mf != null)
			flag = true;
		return flag;
	}

/*	@SuppressWarnings("unchecked")
	public int getAllMailFolderCountByUserName(String folderName,String memberId) {
		int size = getMessageFolder(folderName, memberId).si ;
		return size;
	}*/

	@SuppressWarnings("unchecked")
	public int getMailFolderCount(String folderName, String memberId,String status) {
		List<MessageFolderVO> mf = getSession().getNamedQuery("messagefolder.bymemberandfoldernameandstatus")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .setParameter("status", status)
        .list();
		return mf.size();
	}

	@SuppressWarnings("unchecked")
	public MessageFolderVO getMessageFolder(String folderName,String memberId) {
		return (MessageFolderVO) getSession().getNamedQuery("messagefolder.bymemberandfoldername")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<MessageFolderVO> getMessageFoldersForMemberByMemberId(String memberId) {
		List<MessageFolderVO> mfs = getSession().getNamedQuery("messagefolder.bymember")
        .setParameter("memberId", memberId)
        .list();
		
		for(MessageFolderVO mf : mfs){
			mf.setUnreadMessageCount(getAllUnReadPrivateMessageCount(mf.getFolderName(),memberId));
			mf.setMessageCount(getAllActivePrivateMessageCount(mf.getFolderName(),memberId));
		}
		
		return mfs;	
	}

	
	@SuppressWarnings("unchecked")
	public List<MessageFolderVO> getAdminMessageFoldersForMemberByMemberId(String memberId) {
		List<MessageFolderVO> mfs = getSession().getNamedQuery("messagefolder.bymember")
        .setParameter("memberId", memberId)
        .list();
		
		for(MessageFolderVO mf : mfs){
			mf.setUnreadMessageCount(getAllAdminUnReadPrivateMessageCount(mf.getFolderName(),memberId));
			mf.setMessageCount(getAllAdminActivePrivateMessageCount(mf.getFolderName(),memberId));
		}
		
		return mfs;	
	}
	
	//
	//   PRIVATE METHODS
	//
	
	@SuppressWarnings("unchecked")
	private int getAllUnReadPrivateMessageCount(String folderName,String memberId) {
		return getSession().getNamedQuery("privatemessage.getAllUnReadPrivateMessageCount")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list().size();
	}
	
	@SuppressWarnings("unchecked")
	private int getAllActivePrivateMessageCount(String folderName,String memberId) {
		return getSession().getNamedQuery("privatemessage.getAllActivePrivateMessageCount")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list().size();
	}
	
	
	@SuppressWarnings("unchecked")
	private int getAllAdminUnReadPrivateMessageCount(String folderName,String memberId) {
		return getSession().getNamedQuery("privatemessage.admin.getAllUnReadPrivateMessageCount")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list().size();
	}
	
	@SuppressWarnings("unchecked")
	private int getAllAdminActivePrivateMessageCount(String folderName,String memberId) {
		return getSession().getNamedQuery("privatemessage.admin.getAllActivePrivateMessageCount")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list().size();
	}
	
	
}
