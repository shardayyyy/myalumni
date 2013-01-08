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
package net.naijatek.myalumni.modules.common.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.XlatGroupVO;
import net.naijatek.myalumni.modules.common.persistence.iface.XlatGroupDao;
import net.naijatek.myalumni.modules.common.service.IXlatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XlatGroupServiceImpl implements IXlatGroupService {

    @Autowired
	private XlatGroupDao xlatGroupDao;

    private static Log logger = LogFactory.getLog(XlatGroupServiceImpl.class);
    

/*    public XlatGroupServiceImpl(XlatGroupDao systemGroupDao) {
        this.xlatGroupDao = systemGroupDao;
    }*/

	public void save(XlatGroupVO entity){
		xlatGroupDao.save(entity);
	}
	

	public void merge(XlatGroupVO entity){
		xlatGroupDao.mergeObject(entity);
	}
	

	public List<XlatGroupVO> findAll(){
		return xlatGroupDao.findAll();
	}

	
	public void softDelete(String id, String lastModifiedBy) throws MyAlumniException{
		try{
			xlatGroupDao.softDeleteObject(id, lastModifiedBy);
		} catch(Exception e){
			logger.error("Could not delete XlatGroupVO because " + e.getMessage());
			throw new MyAlumniException("Could not delete XlatGroupVO because " + e.getMessage());
		}
	}
	
	public void hardDelete(String id) throws MyAlumniException{
		try{
			xlatGroupDao.hardDeleteObject(id);
		} catch(Exception e){
			logger.error("Could not delete XlatGroupVO because " + e.getMessage());
			throw new MyAlumniException("Could not delete XlatGroupVO because " + e.getMessage());
		}
	}

	public XlatGroupVO findById(String id){
		return xlatGroupDao.findById(id);
	}
    
	
	public List<XlatGroupVO> findAllByStatus(String status){
		return null;
	} 
    
	
//------------------------------------------------------------------------------------------


    
    public void updateGroupDetails(String userId, String groupId, List items) 
    throws MyAlumniException {    
    	xlatGroupDao.updateGroupDetails(userId, groupId, items); 
    }
	
    
    public List<XlatGroupVO> getAllGroups() {        
        return xlatGroupDao.findAll();
    }
    
/*    public XlatGroupVO getGroupWithDetails(String lookupGroupId){
    	return xlatGroupDao.getGroupWithDetails(lookupGroupId);
    }*/

}
