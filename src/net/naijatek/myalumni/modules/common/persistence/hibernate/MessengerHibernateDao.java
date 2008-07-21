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
package net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.MessengerVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.MessengerDao;

public class MessengerHibernateDao extends BaseHibernateDao implements MessengerDao {

    private void init(MessengerVO msg){
    	if(!StringUtils.isBlank(msg.getMemberId()))
    		msg.setMember((MemberVO) load(MemberVO.class, msg.getMemberId()));  // @TODO MemberVO can be null
    	
    	if(!StringUtils.isBlank(msg.getLookupCodeId()))
    		msg.setMessenger((XlatDetailVO) load(XlatDetailVO.class, msg.getLookupCodeId()));
    	
    }
    
	public void saveAll(List<MessengerVO> list, String memberId) throws MyAlumniException {
		hardDeleteAll(memberId);
    	for (MessengerVO msg : list){
    		init(msg);
    		add(msg);
    	}
	}

	public void hardDeleteAll(String memberId) throws MyAlumniException {
		List<MessengerVO> list = getAllMemberMessengers(memberId);
		try{
			for (MessengerVO messengerVO : list){
				hardDelete(load(MessengerVO.class, messengerVO.getMemberMessengerId()));
			}
		} catch(Exception e){
			throw new MyAlumniException("Could not delete message folder because " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<MessengerVO> getActiveMemberMessengers(String memberId){
		return getSession().getNamedQuery("messenger.active.bymember")
        .setParameter("memberId", memberId)
        .list();
	}
	
	
	@SuppressWarnings("unchecked")
	private List<MessengerVO> getAllMemberMessengers(String memberId){
		return getSession().getNamedQuery("messenger.all.bymember")
        .setParameter("memberId", memberId)
        .list();
	}

}
