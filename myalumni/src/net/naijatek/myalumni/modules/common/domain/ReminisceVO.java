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

public class ReminisceVO extends MyAlumniBaseVO{

	private String reminisceId ;
	private String fromYear;
	private String toYear;
	private String slangYear ;    // for jsp display only
	private String slang;
	private String meaning;
	private String pronounce;
	private String status;
	private String authorId;
	private MemberVO author;
	
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ReminisceVO() {
		super();
	}
	
	
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getPronounce() {
		return pronounce;
	}
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}
	public String getReminisceId() {
		return reminisceId;
	}
	public void setReminisceId(String reminisceId) {
		this.reminisceId = reminisceId;
	}
	public String getSlang() {
		return slang;
	}
	public void setSlang(String slang) {
		this.slang = slang;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}


	public String getSlangYear() {
		StringBuffer  sb = new StringBuffer();
		if (getFromYear() != null && getFromYear().length() > 0 )
		sb.append(getFromYear());
		
		if (getToYear() != null && getToYear().length() > 0){
			sb.append("-");
			sb.append(getToYear());
		}
		slangYear = sb.toString();
		return slangYear;
	}


	public void setSlangYear(String slangYear) {
		this.slangYear = slangYear;
	}
	
}
