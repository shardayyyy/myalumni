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
package net.naijatek.myalumni.service.impl;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import net.naijatek.myalumni.controller.exceptions.MyAlumniException;
import net.naijatek.myalumni.entity.MessageFolderVO;
import net.naijatek.myalumni.persistence.hibernate.iface.MessageFolderDao;
import net.naijatek.myalumni.service.IMessageFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageFolderServiceImpl implements IMessageFolderService {

/*    public MessageFolderServiceImpl(MessageFolderDao mfDao) {
        this.mfDao = mfDao;
    }*/

    @Autowired
    private MessageFolderDao mfDao;


    @Transactional
	public void save(MessageFolderVO entity){
		mfDao.save(entity);
	}

    @Transactional
	public void merge(MessageFolderVO entity){
		mfDao.mergeObject(entity);
	}

    @Transactional(readOnly = true)
	public List<MessageFolderVO> findAll(){
		return mfDao.findAll();
	}

    @Transactional
	public void softDelete(String id, String lastModifiedBy) throws MyAlumniException{
		try{
			mfDao.softDeleteObject(id, lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete MessageFolderVO because " + e.getMessage());
		}
	}

    @Transactional
	public void hardDelete(String id) throws MyAlumniException{
		try{
			mfDao.hardDeleteObject(id);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete MessageFolderVO because " + e.getMessage());
		}
	}

    @Transactional(readOnly = true)
	public MessageFolderVO findById(String id){
		return mfDao.findById(id);
	}

    @Transactional(readOnly = true)
	public List<MessageFolderVO> findAllByStatus(String status){
		return mfDao.findAllByStatus(status);
	}


    @Transactional(readOnly = true)
    public List<MessageFolderVO> getMessageFoldersForMemberByUserName(String memberId) {
        return mfDao.getMessageFoldersForMemberByMemberId(memberId);
    }

    @Transactional
    public void deleteMemberFolder(String memberId, String folderName) {
        mfDao.deleteMemberFolder(memberId, folderName);
    }

/*    public void createMemberFolder(String folderName, String memberId, 
                                   int folderOrder, int folderStatus, 
                                   int folderOption, int folderType, 
                                   Date folderCreationDate, 
                                   Date folderModifiedDate, String lastModifiedBy) {
        mfDao.createMemberFolder(folderName, memberId, folderOrder, 
                                 folderStatus, folderOption, folderType, 
                                 folderCreationDate, folderModifiedDate, lastModifiedBy);
    }*/

    @Transactional
    public void deleteMemberMessageFolders(String memberId){
        mfDao.deleteMemberMessageFolders(memberId);    
    }


    @Transactional
   public void createMemberMessageFolders(String memberId, String messageFolders, String lastModifiedBy) {
	
       StringTokenizer st = new StringTokenizer(messageFolders, ",");
       Date dt = new Date();
       int i = 1 ;
       while (st.hasMoreTokens()) {
         mfDao.createMemberFolder(
        		 ((String)st.nextElement()).trim() /*folderName*/, 
        		 memberId/*memberId*/,
        		 i /*folderOrder*/,
        		 0 /*folderStatus*/,
        		 0 /*folderOption*/,
        		 0 /*folderType*/,
        		 dt /*folderCreationDate*/,
        		 dt /*folderModifiedDate*/,
        		 lastModifiedBy /*lastModifiedBy*/);
         i++;
       }
	   
   }
}
