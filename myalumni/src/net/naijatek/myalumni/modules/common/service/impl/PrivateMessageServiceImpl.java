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
package net.naijatek.myalumni.modules.common.service.impl;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.struts.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.helper.PrivateMessageHelper;
import net.naijatek.myalumni.modules.common.persistence.iface.MessageFolderDao;
import net.naijatek.myalumni.modules.common.persistence.iface.PrivateMessageDao;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;



public class PrivateMessageServiceImpl implements IPrivateMessageService {
    
	private PrivateMessageDao pmDao;
    private MessageFolderDao messageFolderDao;
    
    //private static Log logger = LogFactory.getLog(PrivateMessageServiceImpl.class);

    public PrivateMessageServiceImpl(PrivateMessageDao pmDao, MessageFolderDao messageFolderDao) {
        this.pmDao = pmDao;
        this.messageFolderDao = messageFolderDao;
    }

    public void saveAll(List<PrivateMessageVO> o ){
    	pmDao.saveAll(o);
    }
    
    
	public void save(PrivateMessageVO entity){
		pmDao.save(entity);
	}
	

	public void merge(PrivateMessageVO entity){
		pmDao.mergeObject(entity);
	}
	

	public List<PrivateMessageVO> findAll(){
		return pmDao.findAll();
	}

	
	public void softDelete(String id, String lastModifiedBy) throws MyAlumniException{
		try{
			pmDao.softDeleteObject(id, lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete PrivateMessageVO because " + e.getMessage());
		}
	}
	
	public void hardDelete(String id) throws MyAlumniException{
		try{
			pmDao.hardDeleteObject(id);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete PrivateMessageVO because " + e.getMessage());
		}
	}	

	public PrivateMessageVO findById(String id){
		return pmDao.findById(id);
	}
        


	public List<PrivateMessageVO> findAllByStatus(String status){
		return pmDao.findAllByStatus(status);
	}    
    
    
    
    
    
    
    
    public PrivateMessageVO readOneMailByMemberId(String memberId, String messageId, String lastModifiedBy) {
    	PrivateMessageVO pm = pmDao.readOneMailByMemberId(memberId, messageId);
    	pmDao.updateMessageRead(memberId, messageId, lastModifiedBy);
        return pm;
    }
    
    public PrivateMessageVO readOneAdminMailByMemberId(String memberId, String messageId, String lastModifiedBy) {
    	PrivateMessageVO pm = pmDao.readOneAdminMailByMemberId(memberId, messageId);
    	pmDao.updateMessageRead(memberId, messageId, lastModifiedBy);
        return pm;
    }

    public void deleteMail(String[] mailArray, String userName, String lastModifiedBy) {
        pmDao.deleteMail(mailArray, userName, lastModifiedBy );
    }

    public void moveMail(String[] mailArray, String userName, String toFolder, String lastModifiedBy) {
        pmDao.moveMail(mailArray, userName, toFolder, lastModifiedBy);
    }

    public List<PrivateMessageVO>  getMailListByMemberId(String memberId, String folder) {
        return pmDao.getMailListByMemberId(memberId, folder);
    }

    public int getMailCountByUserName(String userName, String messageStatus) {
        return pmDao.getMailCountByUserName(userName, messageStatus);
    }

    public PrivateMessageVO prepareReply(String userName, String messageId) {
        return pmDao.prepareReply(userName, messageId);
    }
    
    public PrivateMessageVO prepareAdminReply(String memberId, String messageId) {
        return pmDao.prepareAdminReply(memberId, messageId);
    }

    public void contactMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) {
    	pm.setMessageDate(new Date());
    	pm.setMessageStatus(BaseConstants.PM_STATUS_NEW);
    	pm.setFolderName(BaseConstants.FOLDER_INBOX);
    	pm.setIpAddress(ipAddress);
    	pm.setToWebmaster(BaseConstants.BOOLEAN_NO);
    	pm.setLastModifiedBy(lastModifiedBy);
        pmDao.contactMail(pm);
    }
    
    public void contactAdminMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) {
    	pm.setMessageDate(new Date());
    	pm.setMessageStatus(BaseConstants.PM_STATUS_NEW);
    	pm.setFolderName(BaseConstants.FOLDER_INBOX);
    	pm.setIpAddress(ipAddress);
    	pm.setToWebmaster(BaseConstants.BOOLEAN_YES);
    	pm.setLastModifiedBy(lastModifiedBy);
        pmDao.contactMail(pm);
    }

    public void copyMeOnContactMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) {
    	pm.setMessageDate(new Date());
    	pm.setMessageStatus(BaseConstants.PM_STATUS_NEW);
    	pm.setFolderName(BaseConstants.FOLDER_SENT);
    	pm.setIpAddress(ipAddress);
    	pm.setToWebmaster(BaseConstants.BOOLEAN_NO);
    	pm.setLastModifiedBy(lastModifiedBy);
        pmDao.copyMeOnContactMail(pm, lastModifiedBy);
    }

    public void copyMeOnReplyMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) {
    	pm.setMessageDate(new Date());
    	pm.setMessageStatus(BaseConstants.PM_STATUS_NEW);
    	pm.setFolderName(BaseConstants.FOLDER_SENT);
    	pm.setIpAddress(ipAddress);
    	pm.setToWebmaster(BaseConstants.BOOLEAN_NO);
    	pm.setLastModifiedBy(lastModifiedBy);    	
        pmDao.copyMeOnReplyMail(pm, lastModifiedBy);
    }

    public void replyMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) {
    	pm.setMessageDate(new Date());
    	pm.setMessageStatus(BaseConstants.PM_STATUS_NEW);
    	pm.setFolderName(BaseConstants.FOLDER_INBOX);
    	pm.setIpAddress(ipAddress);
    	pm.setToWebmaster(BaseConstants.BOOLEAN_NO);
    	pm.setLastModifiedBy(lastModifiedBy);    	
        pmDao.replyMail(pm);
    }

    public List<String>  getAllPrivateMessagesId() {
        return pmDao.getAllPrivateMessagesId();
    }

    public String getPrivateMessagesStatusById(String messageId) {
        return pmDao.getPrivateMessagesStatusById(messageId);
    }

//    public void updatePrivateMessageUserNames(String memberTempUserName, String memberUserName, String lastModifiedBy) {
//        pmDao.updatePrivateMessageUserNames(memberTempUserName, memberUserName, lastModifiedBy);
//    }

    public int getQuotaRatioByMemberId(String memberId){
       return pmDao.getQuotaRatioByMemberId(memberId);
    }
    
    public PrivateMessageHelper getMessageCenter(String memberId, String folderType, MyAlumniUserContainer container){
    	PrivateMessageHelper pmHelper = new PrivateMessageHelper();
    	
    	// set the messges
    	pmHelper.setListOfMessages(pmDao.getMailListByMemberId(memberId, folderType));
    	
    	//set member folders
    	pmHelper.setMessageFolders(messageFolderDao.getMessageFoldersForMemberByMemberId(memberId));
    	
    	//set Max quota
    	pmHelper.setMaxQuota(SystemConfigConstants.MAIL_QUOTA);

    	//set member quota
    	pmHelper.setMemberQuota(pmDao.getQuotaRatioByMemberId(memberId));

    	//set folder type
    	pmHelper.setFolderType(folderType.toLowerCase());
    	
    	//set number of new and old message
    	container.setNewMailCount(pmDao.getMailCountByUserName(memberId, BaseConstants.PM_STATUS_NEW));  	
    	
    	return pmHelper;
    }
    
    
    public PrivateMessageHelper getAdminMessageCenter(String memberId, String folderType, MyAlumniUserContainer container){
    	PrivateMessageHelper pmHelper = new PrivateMessageHelper();
    	
    	// set the messges
    	pmHelper.setListOfMessages(pmDao.getAdminMailListByAdminId(memberId, folderType));
    	
    	//set member folders
    	pmHelper.setMessageFolders(messageFolderDao.getAdminMessageFoldersForMemberByMemberId(memberId));
    	
    	//set Max quota
    	pmHelper.setMaxQuota(SystemConfigConstants.MAIL_QUOTA);

    	//set member quota
    	pmHelper.setMemberQuota(pmDao.getAdminQuotaRatioByMemberId(memberId));

    	//set folder type
    	pmHelper.setFolderType(folderType.toLowerCase());
    	
    	//set number of new and old message
    	container.setNewMailCount(pmDao.getAdminMailCountByUserName(memberId, BaseConstants.PM_STATUS_NEW));  	
    	
    	return pmHelper;
    }
    
    
    
}
