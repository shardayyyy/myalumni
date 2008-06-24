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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.PrivateMessageDao;
import net.naijatek.myalumni.util.BaseConstants;


public class PrivateMessageHibernateDao extends BaseHibernateDao implements PrivateMessageDao {

    public void init(PrivateMessageVO pm){
    	if(!StringUtils.isBlank(pm.getMessageToUserId()))
    		pm.setMessageToMember((MemberVO) load(MemberVO.class, pm.getMessageToUserId()));  // @TODO MemberVO can be null
    	
    	if(!StringUtils.isBlank(pm.getMessageFromUserId()))
    		pm.setMessageFromMember((MemberVO) load(MemberVO.class, pm.getMessageFromUserId()));  
    }
    
    public void saveAll(List<PrivateMessageVO> o ){
    	for (PrivateMessageVO pm : o){
    		init(pm);
    	}
    	batchAdd(o);
    }

	public void save(PrivateMessageVO o) {
		o.setMessageId(null);
		init(o);
		add(o);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(PrivateMessageVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete private message because " + e.getMessage());
		}
	}

	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(PrivateMessageVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete private message because " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PrivateMessageVO> findAll() {
		return getHibernateTemplate().find("from PrivateMessageVO");
	}
        
	@SuppressWarnings("unchecked")
    public List<PrivateMessageVO> findAllByStatus(String status) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam("privatemessage.bymessagestatus", "status", status);                
	}
        

	public PrivateMessageVO findById(String id) {
		return (PrivateMessageVO) get(PrivateMessageVO.class, id);
	}

	public void mergeObject(PrivateMessageVO o) {
	    update(o);
	}
	
	
	//----------------------------------------------------------------


	public void replyMail(PrivateMessageVO pm) {
		pm.setCopyMe(BaseConstants.BOOLEAN_NO);
		save(pm);
	}
	
	public void contactMail(PrivateMessageVO pm) {
		pm.setCopyMe(BaseConstants.BOOLEAN_NO);
		save(pm);
	}

	public void copyMeOnContactMail(PrivateMessageVO pm, String lastModifiedBy) {
		pm.setCopyMe(BaseConstants.BOOLEAN_YES);
		save(pm);
	}

	public void copyMeOnReplyMail(PrivateMessageVO pm, String lastModifiedBy) {
		pm.setCopyMe(BaseConstants.BOOLEAN_YES);
		save(pm);
	}

	
	
	public void deleteMail(String[] mailArray, String memberId, String lastModifiedBy) {
		
		List<PrivateMessageVO> listOfPM = new ArrayList<PrivateMessageVO>();
		PrivateMessageVO pm = new PrivateMessageVO();
		for (int i = 0 ; i < mailArray.length ; i++){
			pm = findById(mailArray[i]);
			if ((pm.getMessageToUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_NO)) 
					|| (pm.getMessageFromUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_YES))){
				pm.setMessageStatus(BaseConstants.PM_STATUS_DELETED);
				pm.setFolderName(BaseConstants.FOLDER_TRASH);
				pm.setLastModifiedBy(lastModifiedBy);
				listOfPM.add(findById(mailArray[i]));
			}
		}
		batchUpdate(listOfPM);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllPrivateMessagesId() {
		return  getHibernateTemplate().findByNamedQuery("privatemessage.ids");
	}

	@SuppressWarnings("unchecked")
	public int getMailCountByUserName(String memberId, String messageStatus) {
		List<PrivateMessageVO> pms =  getSession().getNamedQuery("privatemessage.bymessagestatus")
        .setParameter("messageStatus", messageStatus)
        .setParameter("memberId", memberId)
        .list();
		
		if (pms == null)
			return 0;
		return pms.size();
	}
	
	@SuppressWarnings("unchecked")
	public int getAdminMailCountByUserName(String memberId, String messageStatus) {
		List<PrivateMessageVO> pms =  getSession().getNamedQuery("privatemessage.admin.bymessagestatus")
        .setParameter("messageStatus", messageStatus)
        .setParameter("memberId", memberId)
        .list();
		
		if (pms == null)
			return 0;
		return pms.size();
	}

	// TODO
	@SuppressWarnings("unchecked")
	public List<PrivateMessageVO> getMailListByMemberId(String memberId, String folderName) {
		return getSession().getNamedQuery("privatemessage.bymemberandfoldername")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrivateMessageVO> getAdminMailListByAdminId(String memberId, String folderName) {
		return getSession().getNamedQuery("privatemessage.admin.bymemberandfoldername")
        .setParameter("memberId", memberId)
        .setParameter("folderName", folderName)
        .list();
	}

	public String getPrivateMessagesStatusById(String pmID) {
		PrivateMessageVO pmVO = findById(pmID);
		if (pmVO != null)
			return pmVO.getMessageStatus();
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	public int getQuotaRatioByMemberId(String memberId) {
		List<PrivateMessageVO> pmVOs =  getHibernateTemplate().findByNamedQueryAndNamedParam("privatemessage.quotabyusername", "memberId", memberId);
		return pmVOs.size();
	}
	
	@SuppressWarnings("unchecked")
	public int getAdminQuotaRatioByMemberId(String memberId) {
		List<PrivateMessageVO> pmVOs =  getHibernateTemplate().findByNamedQueryAndNamedParam("privatemessage.admin.quotabyusername", "memberId", memberId);
		return pmVOs.size();
	}

	public void moveMail(String[] mailArray, String memberId, String toFolder, String lastModifiedBy) {

		List<PrivateMessageVO> listOfPM = new ArrayList<PrivateMessageVO>();
		PrivateMessageVO pm = new PrivateMessageVO();
		for (int i = 0 ; i < mailArray.length ; i++){
			pm = findById(mailArray[i]);
			if ((pm.getMessageToUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_NO)) 
					|| (pm.getMessageFromUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_YES))){
				pm.setFolderName(toFolder);
				pm.setLastModifiedBy(lastModifiedBy);
				listOfPM.add(findById(mailArray[i]));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public PrivateMessageVO prepareReply(String memberId, String messageId) {
		return (PrivateMessageVO) getSession().getNamedQuery("privatemessage.bymemberandmessage.eager")
        .setParameter("memberId", memberId)
        .setParameter("messageId", messageId)
        .uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public PrivateMessageVO prepareAdminReply(String memberId, String messageId) {
		return (PrivateMessageVO) getSession().getNamedQuery("privatemessage.admin.bymemberandmessage.eager")
        .setParameter("memberId", memberId)
        .setParameter("messageId", messageId)
        .uniqueResult();
	}

	public PrivateMessageVO readOneMailByMemberId(String memberId, String messageId) {
		return (PrivateMessageVO) getSession().getNamedQuery("privatemessage.bymemberandmessage.eager")
        .setParameter("memberId", memberId)
        .setParameter("messageId", messageId)
        .uniqueResult();
	}
	
	public PrivateMessageVO readOneAdminMailByMemberId(String memberId, String messageId) {
		return (PrivateMessageVO) getSession().getNamedQuery("privatemessage.admin.bymemberandmessage.eager")
        .setParameter("memberId", memberId)
        .setParameter("messageId", messageId)
        .uniqueResult();
	}


//	@SuppressWarnings("unchecked")
//	public void updatePrivateMessageUserNames(String memberTempUserName, String memberUserName, String lastModifiedBy) {
//		
//		MemberVO o =  (MemberVO) getSession().getNamedQuery("members.byusername")
//        .setParameter("memberUserName", memberTempUserName)
//        .uniqueResult();
//
//		if (o != null){
//			o.setMemberUserName(memberUserName);
//			o.setLastModifiedBy(lastModifiedBy);  
//			update(o);
//		}
//
//	}
	
	
    public void updateMessageRead(String memberId, String messageId, String lastModifiedBy) {
    	PrivateMessageVO pm = findById(messageId);
		if ((pm.getMessageToUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_NO)) 
				|| (pm.getMessageFromUserId().equals(memberId) && pm.getCopyMe().equals(BaseConstants.BOOLEAN_YES))){
    		pm.setMessageStatus(BaseConstants.PM_STATUS_READ);
    		pm.setLastModifiedBy(lastModifiedBy);
    		update(pm);
    	}
    }
	

}
