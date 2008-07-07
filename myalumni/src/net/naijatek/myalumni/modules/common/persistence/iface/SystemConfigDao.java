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
package net.naijatek.myalumni.modules.common.persistence.iface;

import java.util.List;

import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;



public interface SystemConfigDao {

	// RSS Feed
	public void updateOrgInfo(SystemConfigVO sysConfigVO);
	public SystemConfigVO getOrgInfo();
	
	// RSS Feed
	public void updateRssFeedUrl(String rssFeedUrl, String rssFeedHeader, String lastModifiedBy);
	public SystemConfigVO getRssFeedSource();
	
	
	// Session time out
	public int getSessionTimeOut();
	public void updateSessionTimeOut(String sessionTimeout, String lastModifiedBy);
	
	/**
	 * Org About Us
	 */		
	public String getOrgAboutUs();
	public void updateOrgAboutUs(String orgAboutUs, String lastModifiedBy);
	
	
	/**
	 * Server URL
	 */
	public SystemConfigVO getSystemConfig();
	public void updateServerUrl(String serverUrl, String lastModifiedBy);
	
	/**
	 * Forum Url
	 */
	public void updateForumUrl(String forumUrl, String lastModifiedBy);
	
	/**
	 * Album Url
	 */
	public void updateAlbumUrl(String albumUrl, String lastModifiedBy);
	
	/**
	 * First year school started
	 */
	public int getFirstYearofSchool();
	public void updateFirstYearofSchool(SystemConfigVO sysConfigVO);	
	
	
	
	/**
	 * Scroll
	 */
    public ScrollVO getLatestScroll();
    public List<ScrollVO> getAllScrolls();
    public void addScroll(ScrollVO scroll);
    public void updateScroll(String scrollId, String lastModifiedBy);
    
    
    /**
     * DOrmitory
     */
    public void updateDormitory(SystemConfigVO systemConfigVO);
    public String getDormitory();
    
    
    /**
     * Birthday Notification
     */
	public void updateBirthdayNotification(SystemConfigVO systemConfigVO);
	public String getBirthdayNotification();
	
    
    /**
     * Setup
     */
    public void setupIntialization(SystemConfigVO systemConfigVO);
}
