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

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.persistence.iface.XlatDao;
import net.naijatek.myalumni.modules.common.service.IXlatService;

public class XlatServiceImpl implements IXlatService {


    private XlatDao xlatDao;
    //private static Log logger = LogFactory.getLog(XlatServiceImpl.class);
    

    public XlatServiceImpl(XlatDao xlatDao) {
        this.xlatDao = xlatDao;
    }
    public XlatDetailVO getDetail(String groupId, String detailId){
        return xlatDao.getDetail(groupId, detailId);
    }
    
    
    public List<XlatDetailVO> getGroupDetails(String groupId) {   
        return xlatDao.getGroupDetails(groupId);
    }
    
    public List<XlatDetailVO> getActiveGroupDetails(String groupId) {        
        return xlatDao.getActiveGroupDetails(groupId);
    }
    
        

    public void updateXlatDetail(String userId, String groupId, String lookupCodeId, String status, String label) 
    throws MyAlumniException {
        xlatDao.updateXlatDetail(userId, groupId, lookupCodeId, status, label);
    }
    
    
    public void addXlatDetail(XlatDetailVO detail){
        xlatDao.addXlatDetail(detail) ;
    }
	
}
