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
package net.naijatek.myalumni.modules.common.domain;



public class SystemConfigVO  extends MyAlumniBaseVO{
	
	private String systemConfigId;
	private String sessionTimeout;
	private String serverUrl;
	private String albumUrl;
	private String forumUrl;
	private String rssHeader;
	private String rssUrl;
	private String hasDormitory;	
	private String birthdayNotification;
	private String adminSignature; // not persisted
	private String logoFileName;
	
	
	private String orgFirstYear;	
	private String organizationName;
	private String organizationShortName;
	private String orgEmail;
	private String orgAboutUs;
	private String orgIntro;
	
	//twitter
	private String twitteruser;
	private String twitterpswd;
	
	
	public String getTwitterpswd() {
		return twitterpswd;
	}
	public void setTwitterpswd(String twitterpswd) {
		this.twitterpswd = twitterpswd;
	}
	public String getTwitteruser() {
		return twitteruser;
	}
	public void setTwitteruser(String twitteruser) {
		this.twitteruser = twitteruser;
	}
	public String getOrgIntro() {
		return orgIntro;
	}
	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}
	public String getOrgAboutUs() {
		return orgAboutUs;
	}
	public void setOrgAboutUs(String orgAboutUs) {
		this.orgAboutUs = orgAboutUs;
	}
	public String getOrgEmail() {
		return orgEmail;
	}
	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getAdminSignature() {
		StringBuffer sig = new StringBuffer();
		sig.append("\n\n\n--------------------------------------------------------\n\n");
		sig.append(getOrganizationName() + " Webmaster");
		sig.append("\nWebsite: " + getServerUrl());
		sig.append("\nEmail: " + getOrgEmail());
		this.adminSignature = sig.toString();
		return adminSignature;
	}
	public void setAdminSignature(String adminSignature) {
		this.adminSignature = adminSignature;
	}
	public String getOrgFirstYear() {
		return orgFirstYear;
	}
	public void setOrgFirstYear(String orgFirstYear) {
		this.orgFirstYear = orgFirstYear;
	}
	public String getRssHeader() {
		return rssHeader;
	}
	public void setRssHeader(String rssHeader) {
		this.rssHeader = rssHeader;
	}
	public String getRssUrl() {
		return rssUrl;
	}
	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}
	public String getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(String sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	public String getSystemConfigId() {
		return systemConfigId;
	}
	public void setSystemConfigId(String systemConfigId) {
		this.systemConfigId = systemConfigId;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getHasDormitory() {
		return hasDormitory;
	}
	public void setHasDormitory(String hasDormitory) {
		this.hasDormitory = hasDormitory;
	}
	public String getOrganizationShortName() {
		return organizationShortName;
	}
	public void setOrganizationShortName(String organizationShortName) {
		this.organizationShortName = organizationShortName;
	}
	public String getAlbumUrl() {
		return albumUrl;
	}
	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
	}
	public String getForumUrl() {
		return forumUrl;
	}
	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}
	public String getBirthdayNotification() {
		return birthdayNotification;
	}
	public void setBirthdayNotification(String birthdayNotification) {
		this.birthdayNotification = birthdayNotification;
	}
	public String getLogoFileName() {
		return logoFileName;
	}
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
	
	
}
