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

import java.util.List;

import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemConfigDao;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;


public class SystemConfigServiceImpl implements ISystemConfigService {

    private SystemConfigDao systemConfigDao;

    public SystemConfigServiceImpl(SystemConfigDao systemConfigDao) {
        this.systemConfigDao = systemConfigDao;
    }
    
	// Org Info
	public void updateOrgInfo(SystemConfigVO systemConfigVO){
		systemConfigDao.updateOrgInfo(systemConfigVO);
	}
	
	public SystemConfigVO getOrgInfo(){
	    	return systemConfigDao.getOrgInfo();
	}
	
	
	/**
	 * Org About Us
	 */		
	public String getOrgAboutUs(){
		return systemConfigDao.getOrgAboutUs();
	}
	
	public void updateOrgAboutUs(String orgAboutUs, String lastModifiedBy){
		systemConfigDao.updateOrgAboutUs(orgAboutUs, lastModifiedBy);
	}
	
	
	
	
	// RSS FEED
	public void updateRssFeedUrl(String rssFeedUrl, String rssFeedHeader, String lastModifiedBy){
		systemConfigDao.updateRssFeedUrl(rssFeedUrl, rssFeedHeader, lastModifiedBy);
	}
	
	public SystemConfigVO getRssFeedSource(){
	    	return systemConfigDao.getRssFeedSource();
	}
	
	
	// SESSION TIMEOUT
	public int getSessionTimeOut(){
		return systemConfigDao.getSessionTimeOut();
	}
	
	public void updateSessionTimeOut(String sessionTimeout, String lastModifiedBy){
		systemConfigDao.updateSessionTimeOut(sessionTimeout, lastModifiedBy);
	}
	
	/**
	 * Server URL
	 */
	public SystemConfigVO getSystemConfig(){
		return systemConfigDao.getSystemConfig();
	}
	
	
	public void updateServerUrl(String serverUrl, String lastModifiedBy){
		systemConfigDao.updateServerUrl(serverUrl, lastModifiedBy);
	}
	
	/**
	 * FORUM URL
	 */
	public void updateForumUrl(String forumUrl, String lastModifiedBy){
		systemConfigDao.updateForumUrl(forumUrl, lastModifiedBy);
	}
	
	
	/**
	 * ALBUM URL
	 */
	public void updateAlbumUrl(String albumUrl, String lastModifiedBy){
		systemConfigDao.updateAlbumUrl(albumUrl, lastModifiedBy);
	}
	
	/**
	 * First year school started
	 */
	public int getFirstYearofSchool(){
		return systemConfigDao.getFirstYearofSchool();
	}
	
	
	public void updateFirstYearofSchool(SystemConfigVO sysConfigVO){
		systemConfigDao.updateFirstYearofSchool(sysConfigVO);
	}
	
	
	/**
	 * Scroll
	 */
    public ScrollVO getLatestScroll(){
    	return systemConfigDao.getLatestScroll();
    }
    
    public List<ScrollVO> getAllScrolls(){
    	return systemConfigDao.getAllScrolls();
    }
    
    
    public void addScroll(ScrollVO scroll){
    	systemConfigDao.addScroll(scroll);
    }
    
    
    public void updateScroll(String scrollId, String lastModifiedBy){
    	systemConfigDao.updateScroll( scrollId, lastModifiedBy);
    }
	
    /**
     * Dormitory
     */
    public void updateDormitory(SystemConfigVO systemConfigVO){
    	systemConfigDao.updateDormitory(systemConfigVO);
    }
    
    public String getDormitory(){
    	return systemConfigDao.getDormitory();
    }
}
