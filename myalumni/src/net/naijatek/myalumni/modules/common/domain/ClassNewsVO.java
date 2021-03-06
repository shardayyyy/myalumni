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



public class ClassNewsVO extends MyAlumniBaseVO{

	private String classNewsId;
	private String subject;
	private String news;
	private Integer fromClassYear;
	private Integer toClassYear;
	private String systemNews;
	private MemberVO author;
	private String authorId;
	private String status;
	
	

    public ClassNewsVO() {
    }
	
    public ClassNewsVO(String id) {
    	this.classNewsId = id;
	}

    
	public MemberVO getAuthor() {
		return author;
	}
	public void setAuthor(MemberVO author) {
		this.author = author;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public Integer getFromClassYear() {
		return fromClassYear;
	}

	public void setFromClassYear(Integer fromClassYear) {
		this.fromClassYear = fromClassYear;
	}

	public String getSystemNews() {
		return systemNews;
	}

	public void setSystemNews(String systemNews) {
		this.systemNews = systemNews;
	}

	public Integer getToClassYear() {
		return toClassYear;
	}

	public void setToClassYear(Integer toClassYear) {
		this.toClassYear = toClassYear;
	}

	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassNewsId() {
		return classNewsId;
	}

	public void setClassNewsId(String classNewsId) {
		this.classNewsId = classNewsId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
