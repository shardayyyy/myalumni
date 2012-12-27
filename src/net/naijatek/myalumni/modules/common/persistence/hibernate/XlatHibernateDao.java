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


import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.domain.XlatGroupVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.XlatDao;
import net.naijatek.myalumni.util.BaseConstants;


public class XlatHibernateDao extends BaseHibernateDao implements
		XlatDao {

    
    @SuppressWarnings("unchecked")
	public XlatDetailVO getDetail(String groupId, String detailId){    	
    	List<XlatDetailVO> list = getHibernateTemplate().findByNamedQueryAndNamedParam("xlatdetail.byorgAndgroupAndid", 
    			new String[]{"id", "groupId"}, 
    			new Object[]{detailId, groupId});
    	if(list != null && !list.isEmpty())
    		return list.get(0);
    	
    	return null;
    }
    
    @SuppressWarnings("unchecked")
    public List<XlatDetailVO> getGroupDetails(String groupId){
    	return getHibernateTemplate().findByNamedQueryAndNamedParam("xlatdetail.byorgAndgroup", "groupId", groupId);        
    }
    
    @SuppressWarnings("unchecked")
    public List<XlatDetailVO> getActiveGroupDetails(String groupId){
        return getHibernateTemplate().findByNamedQueryAndNamedParam("xlatdetail.active.byorgAndgroup", 
                        new String[]{"groupId","status"},
                        new Object[]{ groupId, BaseConstants.ACTIVE});        
    }
    
    
    

    
    public void updateXlatDetail(String userName, String groupId, String lookupCodeId, String status, String label) 
        throws MyAlumniException{
        XlatDetailVO detail ;
        detail = (XlatDetailVO) getHibernateTemplate().load(XlatDetailVO.class, lookupCodeId);
        detail.setGroup(new XlatGroupVO(groupId));
        detail.setStatus(status);
        detail.setLastModifiedBy(userName);
        detail.setLabel(label);
        update(detail);
    }
    
    public void addXlatDetail(XlatDetailVO detail) {
    	detail.setGroup(new XlatGroupVO(detail.getLookupGroupId()));
    	add(detail);
    	//old working version, see how new plays out
//        XlatGroupVO group = (XlatGroupVO) getHibernateTemplate().load(XlatGroupVO.class, detail.getLookupGroupId());
//        detail.setOrganization(new OrganizationVO(detail.getOrganizationId()));
//        detail.setLastModification(BaseConstants.ADDED);
//        detail.setLastModifiedDate(new Date());        
//        group.addDetail(detail);
//        getHibernateTemplate().update(group);
//        getHibernateTemplate().flush();
    }
	
	
}
