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

import java.util.List;

import net.naijatek.myalumni.entity.ScrollVO;
import net.naijatek.myalumni.entity.SystemConfigVO;
import net.naijatek.myalumni.entity.TwitterVO;
import net.naijatek.myalumni.persistence.hibernate.iface.SystemConfigDao;
import net.naijatek.myalumni.service.ISystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

/*    public SystemConfigServiceImpl(SystemConfigDao systemConfigDao) {
        this.systemConfigDao = systemConfigDao;
    }*/
    
	// Org Info
    @Transactional
	public void updateOrgInfo(SystemConfigVO systemConfigVO){
		systemConfigDao.updateOrgInfo(systemConfigVO);
	}

    @Transactional(readOnly = true)
	public SystemConfigVO getOrgInfo(){
	    	return systemConfigDao.getOrgInfo();
	}
	
	
	/**
	 * Org About Us
	 */
    @Transactional(readOnly = true)
	public String getOrgAboutUs(){
		return systemConfigDao.getOrgAboutUs();
	}
	
	public void updateOrgAboutUs(String orgAboutUs, String lastModifiedBy){
		systemConfigDao.updateOrgAboutUs(orgAboutUs, lastModifiedBy);
	}
	
	
	/**
	 * Org Intro
	 */
    @Transactional(readOnly = true)
	public String getOrgIntro(){
		return systemConfigDao.getOrgIntro();
	}

    @Transactional
	public void updateOrgIntro(String orgIntro, String lastModifiedBy){
		systemConfigDao.updateOrgIntro(orgIntro, lastModifiedBy);
	}
	
	
	
	// RSS FEED
    @Transactional
	public void updateRssFeedUrl(String rssFeedUrl, String rssFeedHeader, String lastModifiedBy){
		systemConfigDao.updateRssFeedUrl(rssFeedUrl, rssFeedHeader, lastModifiedBy);
	}

    @Transactional(readOnly = true)
	public SystemConfigVO getRssFeedSource(){
	    	return systemConfigDao.getRssFeedSource();
	}
	
	
	// SESSION TIMEOUT
    @Transactional(readOnly = true)
	public int getSessionTimeOut(){
		return systemConfigDao.getSessionTimeOut();
	}

    @Transactional
	public void updateSessionTimeOut(String sessionTimeout, String lastModifiedBy){
		systemConfigDao.updateSessionTimeOut(sessionTimeout, lastModifiedBy);
	}
	
	/**
	 * Server URL
	 */
    @Transactional(readOnly = true)
	public SystemConfigVO getSystemConfig(){
		return systemConfigDao.getSystemConfig();
	}


    @Transactional
	public void updateServerUrl(String serverUrl, String lastModifiedBy){
		systemConfigDao.updateServerUrl(serverUrl, lastModifiedBy);
	}
	
	/**
	 * FORUM URL
	 */
    @Transactional
	public void updateForumUrl(String forumUrl, String lastModifiedBy){
		systemConfigDao.updateForumUrl(forumUrl, lastModifiedBy);
	}
	
	
	/**
	 * ALBUM URL
	 */
    @Transactional
	public void updateAlbumUrl(String albumUrl, String lastModifiedBy){
		systemConfigDao.updateAlbumUrl(albumUrl, lastModifiedBy);
	}
	
	/**
	 * First year school started
	 */
    @Transactional(readOnly = true)
	public int getFirstYearofSchool(){
		return systemConfigDao.getFirstYearofSchool();
	}


    @Transactional
	public void updateFirstYearofSchool(SystemConfigVO sysConfigVO){
		systemConfigDao.updateFirstYearofSchool(sysConfigVO);
	}
	
	
	/**
	 * Scroll
	 */
    @Transactional(readOnly = true)
    public ScrollVO getLatestScroll(){
    	return systemConfigDao.getLatestScroll();
    }

    @Transactional(readOnly = true)
    public List<ScrollVO> getAllScrolls(){
    	return systemConfigDao.getAllScrolls();
    }


    @Transactional
    public void addScroll(ScrollVO scroll){
    	systemConfigDao.addScroll(scroll);
    }

    @Transactional
    public void updateScroll(String scrollId, String lastModifiedBy){
    	systemConfigDao.updateScroll( scrollId, lastModifiedBy);
    }
	
    /**
     * Dormitory
     */
    @Transactional
    public void updateDormitory(SystemConfigVO systemConfigVO){
    	systemConfigDao.updateDormitory(systemConfigVO);
    }

    @Transactional(readOnly = true)
    public String getDormitory(){
    	return systemConfigDao.getDormitory();
    }
    
    
    /**
     * Upload Logo
     */
    @Transactional
	public void uploadLogo(SystemConfigVO systemConfigVO){
		systemConfigDao.uploadLogo(systemConfigVO);
	}
	
	
    
    /**
     * Birthday Notification
     */
    @Transactional
    public void updateBirthdayNotification(SystemConfigVO systemConfigVO){
    	systemConfigDao.updateBirthdayNotification(systemConfigVO);
    }

    @Transactional(readOnly = true)
    public String getBirthdayNotification(){
    	return systemConfigDao.getBirthdayNotification();
    }
    
    
    
	/**
	 * Setup
	 */
    @Transactional
	public void setupIntialization(SystemConfigVO systemConfigVO){
		systemConfigDao.setupIntialization(systemConfigVO);
	}
	
	
    /**
     * Twitter
     */
    @Transactional
    public void updateTwitterCredentials(TwitterVO twitterVO){
    	systemConfigDao.updateTwitterCredentials(twitterVO);	
    }

    @Transactional(readOnly = true)
    public TwitterVO getTwitterCredentials(){
    	return systemConfigDao.getTwitterCredentials();    	
    }
    
    
}
