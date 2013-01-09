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
package net.naijatek.myalumni.modules.common.service;

import java.util.List;

import net.naijatek.myalumni.framework.extensions.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.helper.PrivateMessageHelper;


/**
 * This is an interface to the Private Message service implementation.
 *
 * @author Folashade Adeyosoye
 * @version 1.0
 */
public interface IPrivateMessageService  extends BaseCrudService<PrivateMessageVO, String>{
        
	public void saveAll(List<PrivateMessageVO> o );
	
    public PrivateMessageVO readOneMailByMemberId(String memberId, String mailId, String lastModifiedBy);
    
    public PrivateMessageVO readOneAdminMailByMemberId(String memberId, String mailId, String lastModifiedBy);

    public void deleteMail(String[] mailArray, String memberId, String lastModifiedBy);

    public void moveMail(String[] mailArray, String memberId, String toFolder, String lastModifiedBy);

    public List<PrivateMessageVO>  getMailListByMemberId(String memberId, String folderName); //

    public int getMailCountByUserName(String memberId, String messageStatus);

    public PrivateMessageVO prepareReply(String memberId, String mailId);
    
    public PrivateMessageVO prepareAdminReply(String memberId, String mailId);

    public void contactMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) ;
    
    public void contactAdminMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress) ;

    public void copyMeOnContactMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress);

    public void copyMeOnReplyMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress);

    public void replyMail(PrivateMessageVO pm, String lastModifiedBy, String ipAddress);

    public List<String>  getAllPrivateMessagesId() ;

    public String getPrivateMessagesStatusById(String pmId);

    //public void updatePrivateMessageUserNames(String memberTempUserName, String memberUserName, String lastModifiedBy) ;
                                              
    public int getQuotaRatioByMemberId(String memberId);
    
    public PrivateMessageHelper getMessageCenter(String memberId, String folderType, MyAlumniUserContainer container);
    
    public PrivateMessageHelper getAdminMessageCenter(String memberId, String folderType, MyAlumniUserContainer container);
}
