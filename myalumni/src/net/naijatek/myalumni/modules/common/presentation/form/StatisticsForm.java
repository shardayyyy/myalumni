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
package net.naijatek.myalumni.modules.common.presentation.form;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseForm;

public class StatisticsForm extends MyAlumniBaseForm{

	  private String statisticId;
	  private String maleCount;
	  private String femaleCount;
	  private String deactivatedAccountCount;

	  private String lockedAccountCount;
	  private String totalCount;
	  private String newMembers;
	  private String noEmailCount;
	  
	  
	public String getDeactivatedAccountCount() {
		return deactivatedAccountCount;
	}
	public void setDeactivatedAccountCount(String deactivatedAccountCount) {
		this.deactivatedAccountCount = deactivatedAccountCount;
	}
	public String getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(String femaleCount) {
		this.femaleCount = femaleCount;
	}
	public String getLockedAccountCount() {
		return lockedAccountCount;
	}
	public void setLockedAccountCount(String lockedAccountCount) {
		this.lockedAccountCount = lockedAccountCount;
	}
	public String getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(String maleCount) {
		this.maleCount = maleCount;
	}
	public String getNewMembers() {
		return newMembers;
	}
	public void setNewMembers(String newMembers) {
		this.newMembers = newMembers;
	}
	public String getNoEmailCount() {
		return noEmailCount;
	}
	public void setNoEmailCount(String noEmailCount) {
		this.noEmailCount = noEmailCount;
	}
	public String getStatisticId() {
		return statisticId;
	}
	public void setStatisticId(String statisticId) {
		this.statisticId = statisticId;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	  

	  
	  
	  
	  
}
