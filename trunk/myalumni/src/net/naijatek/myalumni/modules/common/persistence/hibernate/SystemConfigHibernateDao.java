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

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemConfigDao;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;




public class SystemConfigHibernateDao extends BaseHibernateDao implements SystemConfigDao {

	
	@SuppressWarnings("unchecked")
	public void updateOrgInfo(SystemConfigVO config){
		
		SystemConfigVO systemConfig = new SystemConfigVO(); 
	
		SystemConfigVO _systemConfig = getSystemConfig();
		

		if (_systemConfig.getSystemConfigId() != null)	{						
			_systemConfig.setLastModification(BaseConstants.UPDATED);
			_systemConfig.setLastModifiedBy(config.getLastModifiedBy());
			_systemConfig.setLastModifiedDate(new Date());
			_systemConfig.setOrganizationName(config.getOrganizationName());
			_systemConfig.setOrganizationShortName(config.getOrganizationShortName());
			_systemConfig.setOrgEmail(config.getOrgEmail());
			_systemConfig.setOrgFirstYear(config.getOrgFirstYear());
			update(_systemConfig);
		}
		else{
			systemConfig.setLastModification(BaseConstants.ADDED);
			systemConfig.setLastModifiedBy(config.getLastModifiedBy());
			systemConfig.setLastModifiedDate(new Date());
			systemConfig.setOrganizationName(config.getOrganizationName());
			systemConfig.setOrganizationShortName(config.getOrganizationShortName());
			systemConfig.setOrgEmail(config.getOrgEmail());
			systemConfig.setOrgFirstYear(config.getOrgFirstYear());		
			add(systemConfig);
		}

	}
	
	@SuppressWarnings("unchecked")
	public SystemConfigVO getOrgInfo() {
		
		SystemConfigVO config = new SystemConfigVO(); 
		
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{				
			config.setOrganizationName(_systemConfig.getOrganizationName());
			config.setOrganizationShortName(_systemConfig.getOrganizationShortName());
			config.setOrgEmail(_systemConfig.getOrgEmail());
			config.setOrgFirstYear(_systemConfig.getOrgFirstYear());	
			config.setOrgAboutUs(_systemConfig.getOrgAboutUs());
			config.setOrgIntro(_systemConfig.getOrgIntro());
		}			
		return config;
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateRssFeedUrl(String rssFeedUrl, String rssFeedHeader, String lastModifiedBy){
		
		SystemConfigVO systemConfig = new SystemConfigVO(); 
	
		SystemConfigVO _systemConfig = getSystemConfig();
		

		if (_systemConfig.getSystemConfigId() != null)	{						
			_systemConfig.setLastModification(BaseConstants.UPDATED);
			_systemConfig.setLastModifiedBy(lastModifiedBy);
			_systemConfig.setLastModifiedDate(new Date());
			_systemConfig.setRssHeader(rssFeedHeader);
			_systemConfig.setRssUrl(rssFeedUrl);			
			update(_systemConfig);
		}
		else{
			systemConfig.setLastModification(BaseConstants.ADDED);
			systemConfig.setLastModifiedBy(lastModifiedBy);
			systemConfig.setLastModifiedDate(new Date());
			systemConfig.setRssHeader(rssFeedHeader);
			systemConfig.setRssUrl(rssFeedUrl);		
			add(systemConfig);
		}

	}
	
	@SuppressWarnings("unchecked")
	public SystemConfigVO getRssFeedSource() {
		
		SystemConfigVO config = new SystemConfigVO(); 
		
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{	
			config.setRssUrl(_systemConfig.getRssUrl());
			config.setRssHeader(_systemConfig.getRssHeader());			
		}			
		return config;
	}
	
	@SuppressWarnings("unchecked")
	public int getSessionTimeOut(){
		
		int timeout = SystemConfigConstants.DEFAULT_SESSION_TIMEOUT;
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{	
			if (_systemConfig.getSessionTimeout() !=  null){
				try{
					timeout = Integer.parseInt(_systemConfig.getSessionTimeout());
				}
				catch(NumberFormatException nfe){
					timeout = SystemConfigConstants.DEFAULT_SESSION_TIMEOUT;
				}
			}								
		}		
		return timeout;
	}	
	
	
	@SuppressWarnings("unchecked")
	public void updateSessionTimeOut(String sessionTimeout, String lastModifiedBy){		
		SystemConfigVO _systemConfig = getSystemConfig();
	
		if(_systemConfig.getSystemConfigId() != null){//do update
			_systemConfig.setLastModification(BaseConstants.UPDATED);
			_systemConfig.setSessionTimeout(sessionTimeout);			
		} else{ //do new add
			_systemConfig = new SystemConfigVO();
			_systemConfig.setLastModification(BaseConstants.ADDED);
		}
		
		_systemConfig.setLastModifiedDate(new Date());
		_systemConfig.setLastModifiedBy(lastModifiedBy);
		
		getHibernateTemplate().saveOrUpdate(_systemConfig);
	}
	
	/**
	 * Org About Us
	 */		
	public String getOrgAboutUs(){
		return getSystemConfig().getOrgAboutUs();
	}
	
	
	public void updateOrgAboutUs(String orgAboutUs, String lastModifiedBy){
		SystemConfigVO _systemConfig = getSystemConfig();
				
		
		if(_systemConfig.getSystemConfigId() != null){//do update
			_systemConfig.setLastModification(BaseConstants.UPDATED);			
		} else{ //do new add
			_systemConfig = new SystemConfigVO();
			_systemConfig.setLastModification(BaseConstants.ADDED);
		}
				
		
		_systemConfig.setOrgAboutUs(orgAboutUs);
		_systemConfig.setLastModifiedBy(lastModifiedBy);
		_systemConfig.setLastModifiedDate(new Date());
		
		getHibernateTemplate().saveOrUpdate(_systemConfig);
	}
	
	
	
	
	/**
	 * Org Intro
	 */		
	public String getOrgIntro(){
		return getSystemConfig().getOrgIntro();
	}
	
	
	public void updateOrgIntro(String orgIntro, String lastModifiedBy){
		SystemConfigVO _systemConfig = getSystemConfig();
				
		
		if(_systemConfig.getSystemConfigId() != null){//do update
			_systemConfig.setLastModification(BaseConstants.UPDATED);			
		} else{ //do new add
			_systemConfig = new SystemConfigVO();
			_systemConfig.setLastModification(BaseConstants.ADDED);
		}
				
		
		_systemConfig.setOrgIntro(orgIntro);
		_systemConfig.setLastModifiedBy(lastModifiedBy);
		_systemConfig.setLastModifiedDate(new Date());
		
		getHibernateTemplate().saveOrUpdate(_systemConfig);
	}
	
	
	
	
	/**
	 * Server URL
	 */
	public SystemConfigVO getSystemConfig(){	
		SystemConfigVO systemSetup = (SystemConfigVO) getSession()
    		.getNamedQuery("get.systemConfig")
    		.uniqueResult();
    	
    	return (systemSetup == null)? new SystemConfigVO(): systemSetup;
	}
	
	
	public void updateServerUrl(String serverUrl, String lastModifiedBy){
		
		SystemConfigVO systemSetup = getSystemConfig();
		
		if(systemSetup.getSystemConfigId() == null)
			systemSetup = new SystemConfigVO();			
		
		systemSetup.setServerUrl(serverUrl);
		systemSetup.setLastModifiedBy(lastModifiedBy);
		
		getHibernateTemplate().saveOrUpdate(systemSetup);
	}	
	
	public void updateAlbumUrl(String albumUrl, String lastModifiedBy){
		
		SystemConfigVO systemSetup = getSystemConfig();
		
		if(systemSetup.getSystemConfigId() == null)
			systemSetup = new SystemConfigVO();			
		
		systemSetup.setAlbumUrl(albumUrl);
		systemSetup.setLastModifiedBy(lastModifiedBy);
		
		getHibernateTemplate().saveOrUpdate(systemSetup);
	}	
	
	public void updateForumUrl(String forumUrl, String lastModifiedBy){
		
		SystemConfigVO systemSetup = getSystemConfig();
		
		if(systemSetup.getSystemConfigId() == null)
			systemSetup = new SystemConfigVO();			
		
		systemSetup.setForumUrl(forumUrl);
		systemSetup.setLastModifiedBy(lastModifiedBy);
		
		getHibernateTemplate().saveOrUpdate(systemSetup);
	}	
	
	
	/**
	 * First year school started
	 */
	@SuppressWarnings("unchecked")
	public int getFirstYearofSchool(){
		int year = 1900;
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{	
			if (_systemConfig.getOrgFirstYear() !=  null){
				try{
					year = Integer.parseInt(_systemConfig.getOrgFirstYear());
				}
				catch(NumberFormatException nfe){
					year = 1900;
				}
			}								
		}		
		return year;
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateFirstYearofSchool(SystemConfigVO sysConfigVO){
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if(_systemConfig.getSystemConfigId() != null){//do update
			_systemConfig.setLastModification(BaseConstants.UPDATED);
			_systemConfig.setOrgFirstYear(sysConfigVO.getOrgFirstYear());			
		} else{ //do new add
			_systemConfig = new SystemConfigVO();
			_systemConfig.setLastModification(BaseConstants.ADDED);
		}
		
		_systemConfig.setLastModifiedDate(new Date());
		_systemConfig.setLastModifiedBy(sysConfigVO.getLastModifiedBy());
		
		getHibernateTemplate().saveOrUpdate(_systemConfig);
	}
	
	
	/**
	 * Scroll
	 */
	public void addScroll(ScrollVO scroll) {
		add(scroll);
	}

	@SuppressWarnings("unchecked")
	public List<ScrollVO> getAllScrolls() {
		return getHibernateTemplate().find("from ScrollVO");
	}

	@SuppressWarnings("unchecked")
	public ScrollVO getLatestScroll() {
		ScrollVO scroll = new ScrollVO();
		List<ScrollVO> scrolls = getSession().createQuery("select from ScrollVO s order by s.lastModifiedDate desc")
		.list();
		
		if (scrolls != null && scrolls.size() > 0){
			scroll = scrolls.get(0);
		}
		return scroll;	
	}

	public void updateScroll(String scrollId, String lastModifiedBy) {
		ScrollVO scrollVO = (ScrollVO)load(ScrollVO.class, scrollId);
		scrollVO.setLastModifiedBy(lastModifiedBy);
		update(scrollVO);
	}
	
	
	/**
	 * Dormitory
	 */
	public void updateDormitory(SystemConfigVO systemConfigVO){
		SystemConfigVO systemSetup = getSystemConfig();
	
	if(systemSetup.getSystemConfigId() == null)
		systemSetup = new SystemConfigVO();			
	
	systemSetup.setHasDormitory(systemConfigVO.getHasDormitory());
	
	getHibernateTemplate().saveOrUpdate(systemSetup);		
	}
	
	
	
	public String getDormitory(){
		String hasDorm = BaseConstants.BOOLEAN_NO;
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{	
			if (_systemConfig.getHasDormitory() !=  null){
				try{
					hasDorm = _systemConfig.getHasDormitory();
				}
				catch(NumberFormatException nfe){
					hasDorm = BaseConstants.BOOLEAN_NO;
				}
			}								
		}		
		return hasDorm;		
	}
	
	
	
    /**
     * Upload Logo
     */	
	public void uploadLogo(SystemConfigVO systemConfigVO){
		SystemConfigVO systemSetup = getSystemConfig();
		
		if(systemSetup.getSystemConfigId() == null)
			systemSetup = new SystemConfigVO();			
		
		systemSetup.setLogoFileName(systemConfigVO.getLogoFileName());
		
		getHibernateTemplate().saveOrUpdate(systemSetup);
		
	}
	
	
	
	/**
	 * Birthday Notification
	 */
	public void updateBirthdayNotification(SystemConfigVO systemConfigVO){
		SystemConfigVO systemSetup = getSystemConfig();
	
	if(systemSetup.getSystemConfigId() == null)
		systemSetup = new SystemConfigVO();			
	
	systemSetup.setBirthdayNotification(systemConfigVO.getBirthdayNotification());
	
	getHibernateTemplate().saveOrUpdate(systemSetup);		
	}
	
	
	
	public String getBirthdayNotification(){
		String notification = BaseConstants.BOOLEAN_NO;
		SystemConfigVO _systemConfig = getSystemConfig();
		
		if (_systemConfig.getSystemConfigId() != null)	{	
			if (_systemConfig.getBirthdayNotification() !=  null){
					notification = _systemConfig.getBirthdayNotification();
			}								
		}		
		return notification;		
	}
	
	
	/**
	 * Setup
	 */
	public void setupIntialization(SystemConfigVO systemConfigVO){
		SystemConfigVO systemSetup = getSystemConfig();
		
		if(systemSetup.getSystemConfigId() == null)
			systemSetup = new SystemConfigVO();			
		
		systemSetup.setOrgFirstYear(systemConfigVO.getOrgFirstYear());
		systemSetup.setOrganizationName(systemConfigVO.getOrganizationName());
		systemSetup.setOrgEmail(systemConfigVO.getOrgEmail());
		systemSetup.setOrganizationShortName(systemConfigVO.getOrganizationShortName());
		systemSetup.setHasDormitory(systemConfigVO.getHasDormitory());
		systemSetup.setAlbumUrl(systemConfigVO.getAlbumUrl());
		systemSetup.setForumUrl(systemConfigVO.getForumUrl());
		systemSetup.setServerUrl(systemConfigVO.getServerUrl());
		systemSetup.setSessionTimeout(systemConfigVO.getSessionTimeout());
		systemSetup.setRssUrl(systemConfigVO.getRssUrl());
		systemSetup.setBirthdayNotification(systemConfigVO.getBirthdayNotification());
		
		systemSetup.setLastModifiedBy("sysadmin");
		systemSetup.setLastModification(BaseConstants.ADDED);
		systemSetup.setLastModifiedDate(new Date());
	
		getHibernateTemplate().saveOrUpdate(systemSetup);
		
	}
	
}
