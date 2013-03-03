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
package net.naijatek.myalumni.persistence.hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.naijatek.myalumni.controller.exceptions.MyAlumniException;
import net.naijatek.myalumni.entity.XlatDetailVO;
import net.naijatek.myalumni.entity.XlatGroupVO;
import net.naijatek.myalumni.persistence.hibernate.iface.XlatGroupDao;
import net.naijatek.myalumni.util.BaseConstants;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class XlatGroupHibernateDao extends BaseHibernateDao implements XlatGroupDao {
	
	public void save(XlatGroupVO o) {
		add(o);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(XlatGroupVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete system group because " + e.getMessage());
		}
	}

	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(XlatGroupVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete system group because " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<XlatGroupVO> findAll() {
		return getHibernateTemplate().find("from XlatGroupVO");
	}
        
	@SuppressWarnings("unchecked")
	public List<XlatGroupVO> findAllByStatus(String status) {
		return null;             
	}
        

	public XlatGroupVO findById(String id) {
		return (XlatGroupVO) get(XlatGroupVO.class, id);
	}

	public void mergeObject(XlatGroupVO o) {
	    update(o);
	}
	
	//------------------------------------------------------------------------------------
	
    public void updateGroupDetails(String userName, String groupId, List items) throws MyAlumniException{
    	XlatDetailVO detail, pinstance;
        //TODO help: what is this doing?
    	for(Iterator i=items.iterator(); i.hasNext();){
    		detail = (XlatDetailVO) i.next();
    		pinstance = (XlatDetailVO) getHibernateTemplate().load(XlatDetailVO.class, detail.getLookupCodeId());
    		pinstance.setGroup(new XlatGroupVO(groupId));
                pinstance.setLastModification(BaseConstants.UPDATED);
                pinstance.setLastModifiedDate(new Date());
    		//TODO: what are we doing with the userId??????
    		getHibernateTemplate().update(pinstance);
    	}
    }
    
    
/*	@SuppressWarnings("unchecked")    
    public XlatGroupVO getGroupWithDetails(String lookupGroupId){
    		return (XlatGroupVO)getSession().getNamedQuery("xlatgroup.group.eager")
            .setParameter("lookupGroupId", lookupGroupId)
            .uniqueResult();
    }  */  	
  
}
