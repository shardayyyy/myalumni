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

import java.util.HashMap;
import java.util.Map;

public class StatisticsVO extends MyAlumniBaseVO {

	private String statisticId;

	private int maleCount;

	private int femaleCount;

	private int unknownGenderCount;

	private int deactivatedAccountCount;
	
	private int deletedAccountCount;

	private int lockedAccountCount;

	private int totalCount;

	private int newMembers;

	private int noEmailCount;

	private Map<String, Integer> dormitoryMap = new HashMap<String, Integer>();

	private Map<String, Integer> countryMap = new HashMap<String, Integer>();
	
	private Map<String, Integer> emailMap = new HashMap<String, Integer>();



	public StatisticsVO() {
		super();
	}

	public StatisticsVO(String id) {
		this.statisticId = id;
	}

	public int getDeactivatedAccountCount() {
		return deactivatedAccountCount;
	}

	public void setDeactivatedAccountCount(int deactivatedAccountCount) {
		this.deactivatedAccountCount = deactivatedAccountCount;
	}

	public int getFemaleCount() {
		return femaleCount;
	}

	public void setFemaleCount(int femaleCount) {
		this.femaleCount = femaleCount;
	}

	public int getLockedAccountCount() {
		return lockedAccountCount;
	}

	public void setLockedAccountCount(int lockedAccountCount) {
		this.lockedAccountCount = lockedAccountCount;
	}

	public int getMaleCount() {
		return maleCount;
	}

	public void setMaleCount(int maleCount) {
		this.maleCount = maleCount;
	}

	public int getNewMembers() {
		return newMembers;
	}

	public void setNewMembers(int newMembers) {
		this.newMembers = newMembers;
	}

	public int getNoEmailCount() {
		return noEmailCount;
	}

	public void setNoEmailCount(int noEmailCount) {
		this.noEmailCount = noEmailCount;
	}

	public String getStatisticId() {
		return statisticId;
	}

	public void setStatisticId(String statisticId) {
		this.statisticId = statisticId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, Integer> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<String, Integer> countryMap) {
		this.countryMap = countryMap;
	}

	public Map<String, Integer> getDormitoryMap() {
		return dormitoryMap;
	}

	public void setDormitoryMap(Map<String, Integer> dormitoryMap) {
		this.dormitoryMap = dormitoryMap;
	}

	public int getUnknownGenderCount() {
		return unknownGenderCount;
	}

	public void setUnknownGenderCount(int unknownGenderCount) {
		this.unknownGenderCount = unknownGenderCount;
	}

	public Map<String, Integer> getEmailMap() {
		return emailMap;
	}

	public void setEmailMap(Map<String, Integer> emailMap) {
		this.emailMap = emailMap;
	}

	public int getDeletedAccountCount() {
		return deletedAccountCount;
	}

	public void setDeletedAccountCount(int deletedAccountCount) {
		this.deletedAccountCount = deletedAccountCount;
	}

}
