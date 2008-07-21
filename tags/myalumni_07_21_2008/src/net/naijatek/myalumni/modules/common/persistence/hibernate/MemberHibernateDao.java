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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;


import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.MemberDao;
import net.naijatek.myalumni.util.BaseConstants;


public class MemberHibernateDao extends BaseHibernateDao implements MemberDao {


	//*********************************************************************************************
	//****************************  END OF HELPERS *******************************************************
	//*********************************************************************************************

	
	public void save(MemberVO o) {
		add(o);
	}
	
	public void saveAll(List<MemberVO> members){
		batchAdd(members);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(MemberVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete Black List because " + e.getMessage());
		}
	}

	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(MemberVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete member because " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> findAll() {
		return getHibernateTemplate().find("from MemberVO");
	}
       
	@SuppressWarnings("unchecked")
    public List<MemberVO> findAllByStatus(String memberStatus) {
 		return getHibernateTemplate().findByNamedQueryAndNamedParam("members.bystatus", "memberStatus", memberStatus);
	}
        
	public MemberVO findById(String id) {
		return (MemberVO) get(MemberVO.class, id);
	}

	public void mergeObject(MemberVO o) {
		 update(o);
	}
	
	@SuppressWarnings("unchecked")
    private MemberVO findMemberByUserName(String memberUserName) {
		return (MemberVO) getSession().getNamedQuery("members.byusername")
        .setParameter("memberUserName", memberUserName)
        .uniqueResult();
	}
       
	
	
	//*********************************************************************************************
	//****************************  END OF HELPERS *******************************************************
	//*********************************************************************************************

	
	public void activateMemberByUserName(String memberUserName, String lastModifiedBy) {
		MemberVO o = findMemberByUserName(memberUserName);
		
		o.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE);
		o.setLastModifiedBy(lastModifiedBy);
		update(o);
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> adminGetAllMembers(int offset, int rowsToReturn) {
		return getHibernateTemplate().find("from MemberVO");
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> adminGetOneMembers(String memberUserName) {
		MemberVO o =  findMemberByUserName(memberUserName);
		List<MemberVO> lst = new ArrayList<MemberVO>();
		lst.add(o);
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> adminGetMembersByStatus(int offset, int rowsToReturn, String status) {
		return findAllByStatus(status);
	}


	public void createMember(MemberVO member) throws MyAlumniBaseException {
		
		
        // set dropdown menus
        if(!StringUtils.isBlank(member.getTitleId()))
        	member.setTitle(new XlatDetailVO(member.getTitleId()));
        else
        	member.setTitle(new XlatDetailVO());
        
        if (!StringUtils.isBlank(member.getDormitoryId()))
        	member.setDormitory(new XlatDetailVO(member.getDormitoryId()));
        else
        	member.setDormitory(new XlatDetailVO());
        	
        if (!StringUtils.isBlank(member.getCareerId()))
        	member.setCareer(new XlatDetailVO(member.getCareerId()));
        else
            member.setCareer(new XlatDetailVO());
        
        if (!StringUtils.isBlank(member.getCountryId()))
        	member.setCountry(new XlatDetailVO(member.getCountryId()));
        else
        	member.setCountry(new XlatDetailVO());
   

        add(member);
		
	}

	public void deactivateMemberByUserName(String memberUserName, String lastModifiedBy) {
		MemberVO o = findMemberByUserName(memberUserName);
		
		if (o != null){
			o.setMemberStatus(BaseConstants.ACCOUNT_DEACTIVATED);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}
	}

	public void deleteMemberByUserName(String memberUserName) {
		MemberVO o = findMemberByUserName(memberUserName);
		
		if (o != null){
			hardDelete(o);
		}

	}

	public List<MemberVO> getAllMembers() {
		return findAll();
	}

	public List<MemberVO> getAllStatistics() {
		return findAll();
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> getBirthdayListOfTheMonth(String month) {
        Calendar now = new GregorianCalendar();
        int today = now.get(java.util.Calendar.DATE);
		return  getSession().getNamedQuery("member.bday.bymonth.list")
        .setParameter("month", month)
        .setParameter("today", String.valueOf(today))
        .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getTodayBirthdayMembers(String month, String day){
		return  getSession().getNamedQuery("member.bday.bymonth.byday")
        .setParameter("month", month)
        .setParameter("day", day)
        .list();		
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> getLatestMembers(int rowsToReturn) {
		return  getSession().createQuery("select from MemberVO e where e.memberStatus = :memberStatus order by e.creationDate desc")
		.setParameter("memberStatus", BaseConstants.ACCOUNT_ACTIVE)
		.setMaxResults(rowsToReturn)
		.list();
		
	}

	public String getMemberEmailByMemberId(String memberId){
		MemberVO o = findById(memberId);
		String email = "";
		if (o != null){
			email = o.getEmail();
		}
		return email;		
	}
	
	public String getMemberPasswordByUserName(String memberUserName) {
		MemberVO o = findMemberByUserName(memberUserName);
		String pswd = "";
		if (o != null){
			pswd = o.getMemberPassword();
		}
		return pswd;
	}

	public MemberVO getMemberProfileByUserName(String memberUserName) {
		return findMemberByUserName(memberUserName);
	}
	

	public String getMemberStatusByUserName(String memberUserName) {
		MemberVO o = findMemberByUserName(memberUserName);
		String memberStatus = "";
		if (o != null){
			memberStatus = o.getMemberStatus();
		}
		return memberStatus;
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembersToAdminister() {
		return getHibernateTemplate().findByNamedQuery("members.toadminister");
	}
	
	public boolean isAccountActivatedByMemberId(String memberId) {
		MemberVO o = findById(memberId);
		boolean activated = false;
		
		if (o != null && !o.getMemberStatus().equals(BaseConstants.ACCOUNT_UNAPPROVED)){
			activated = true;
		}
		return activated;
	}	

	@SuppressWarnings("unchecked")
	public boolean isMemberAvailableByEmail(String email, String exceptmemberId) {
		
		int count =  getSession().getNamedQuery("member.doesemailalreadyexist")
        .setParameter("email", email)
        .setParameter("memberId", exceptmemberId)
        .list().size();
		
		if (count > 0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isEmailRegisteredAlready(String email) {
		
		int count =  getSession().getNamedQuery("members.byemail")
        .setParameter("email", email)
        .list().size();
		
		if (count > 0)
			return true;
		else
			return false;
	}

	
	public boolean isMemberAvailableByUserName(String memberUserName) {
		MemberVO o = findMemberByUserName(memberUserName);
		
		if (o == null)
			return false;
		else
			return true;
	}

	
	public boolean lockMemberAccount(String memberUserName, String lastModifiedBy) {
		MemberVO o = findMemberByUserName(memberUserName);
		boolean status = false;
		if (o != null){
			o.setMemberStatus(BaseConstants.ACCOUNT_LOCKED);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
			status = true;
		}
		return status;
	}
	
	public boolean unLockMemberAccount(String memberUserName, String lastModifiedBy){
		MemberVO o = findMemberByUserName(memberUserName);
		boolean status = false;
		if (o != null){
			o.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
			status = true;
		}
		return status;
	}

	
	public void lockMemberByUserName(String memberUserName, String lastModifiedBy) {
		MemberVO o = findMemberByUserName(memberUserName);
		
		if (o != null){
			o.setMemberStatus(BaseConstants.ACCOUNT_LOCKED);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchAvatar(int offset, int rowsToReturn, String isAdmin) {

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		// start query
		query.append("from MemberVO e where e.avatar != ''");

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}


	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchFirstName(String firstName,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {

		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.firstName)").append(partialNameSearchFlag).append("upper('%");
		query.append(firstName);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
		
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchFullSearchOnMembers(String firstName,
			String lastName, String dormitory, String gender, String yearIn,
			String yearOut, String marriageName, String nickName,
			String maidenName, String partialNameSearch, int offset,
			int rowsToReturn, String isAdmin) {
		
			List<MemberVO> result = new ArrayList<MemberVO>();
			StringBuffer query = new StringBuffer();
			String partialNameSearchFlag ;
			boolean firstCategory = false;
			
			
			if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
				partialNameSearchFlag = " like ";
			}
			else{
				partialNameSearchFlag = " = ";
			}
			
			// start query
			query.append("select from MemberVO e where ");
			
			if (firstName != null && firstName.length() > 0){
				query.append("upper(e.firstName)").append(partialNameSearchFlag).append("upper('%");
				query.append(firstName);
				query.append("%') " );
				firstCategory = true;
			}
			
			if (lastName != null && lastName.length() > 0){
				query.append(verifyFirstCategory(firstCategory) + " upper(e.lastName)").append(partialNameSearchFlag).append("upper('%");
				query.append(lastName);
				query.append("%') " );
				firstCategory = true;
			}
			
			if (dormitory != null && dormitory.length() > 0){
				query.append(verifyFirstCategory(firstCategory) + " e.dormitory = '" + dormitory + "' ");
				firstCategory = true;
			}
			
			if (gender != null && gender.length() > 0){ 
				query.append(verifyFirstCategory(firstCategory) + " e.gender =  '" + gender + "' ");
				firstCategory = true;
			}
			
			if (yearIn != null && yearIn.length() > 0){ 
				query.append(verifyFirstCategory(firstCategory) + " e.yearIn =  '" + yearIn + "' ");
				firstCategory = true;
			}
			
			if (yearOut != null && yearOut.length() > 0){ 
				query.append(verifyFirstCategory(firstCategory) + " e.yearOut =  '" + yearOut + "' ");
				firstCategory = true;
			}

			
			if (marriageName != null && marriageName.length() > 0){
				query.append(verifyFirstCategory(firstCategory) + " upper(e.lastName)").append(partialNameSearchFlag).append("upper('%");
				query.append(marriageName);
				query.append("%') " );
				firstCategory = true;
			}
			
			if (maidenName != null && maidenName.length() > 0){
				query.append(verifyFirstCategory(firstCategory) + " upper(e.maidenName)").append(partialNameSearchFlag).append("upper('%");
				query.append(maidenName);
				query.append("%') " );
				firstCategory = true;
			}
			
			if (nickName != null && nickName.length() > 0){
				query.append(verifyFirstCategory(firstCategory) + " upper(e.nickName)").append(partialNameSearchFlag).append("upper('%");
				query.append(nickName);
				query.append("%') " );
				firstCategory = true;
			}
			
			if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
				query.append(verifyFirstCategory(firstCategory) + " e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
			}
			
			logger.warn(query.toString());
			result = getSession().createQuery(query.toString()).list();
			return result;
	}

	private String verifyFirstCategory(boolean firstCategory){
		if (firstCategory)
			return " and ";
		else
			return "";
	}
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchGender(String gender, int offset,
			int rowsToReturn, String isAdmin) {

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		// start query
		query.append("from MemberVO e where e.gender = '" + gender + "' ");

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchDomitory(String domitory, int offset, 
			int rowsToReturn, String isAdmin) {

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		
		// start query
		query.append("from MemberVO e where e.dormitoryId = '" + domitory + "' ");

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;

	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchLastName(String lastName,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {

		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.lastName)").append(partialNameSearchFlag).append("upper('%");
		query.append(lastName);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchMaidenName(String maidenName,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {

		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.maidenName)").append(partialNameSearchFlag).append("upper('%");
		query.append(maidenName);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchNickName(String nickname,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {

		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.nickName)").append(partialNameSearchFlag).append("upper('%");
		query.append(nickname);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchUserNamePartial(String memberUserName,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {


		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.memberUserName)").append(partialNameSearchFlag).append("upper('%");
		query.append(memberUserName);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchEmail(String email,
			String partialNameSearch, int offset, int rowsToReturn, String isAdmin) {


		String partialNameSearchFlag ;
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		if (partialNameSearch.equals(BaseConstants.BOOLEAN_YES)){
			partialNameSearchFlag = " like ";
		}
		else{
			partialNameSearchFlag = " = ";
		}
		
		// start query
		query.append("from MemberVO e where ");
		query.append("upper(e.email)").append(partialNameSearchFlag).append("upper('%");
		query.append(email);
		query.append("%') " );

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchYearIn(String yearIn, int offset,
			int rowsToReturn, String isAdmin) {

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		// start query
		query.append("from MemberVO e where e.yearIn = '" + yearIn + "' ");

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MemberVO> searchYearOut(String yearOut, int offset,
			int rowsToReturn, String isAdmin) {


		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		// start query
		query.append("from MemberVO e where e.yearOut = '" + yearOut + "' ");

		if (!isAdmin.equals(BaseConstants.BOOLEAN_YES)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		
		result = getSession().createQuery(query.toString()).list();
		return result;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void updateMemberAvatar(String avatar, String memberUserName, String lastModifiedBy) {
		
		MemberVO o = findMemberByUserName(memberUserName);

		if (o != null){
			o.setAvatar(avatar);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMemberAvatar(String avatar, String memberUserName, String lastModifiedBy){
		MemberVO o = findMemberByUserName(memberUserName);
		
		if (o != null && o.getAvatar().equalsIgnoreCase(avatar)){
			o.setAvatar("");
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}
	}

	public void updateMemberEmail(String email, String memberUserName, String lastModifiedBy) {
		
		MemberVO o = findMemberByUserName(memberUserName);

		if (o != null){
			o.setEmail(email);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}

	}

	public void updateMemberPassword(String memberUserName, String memberPassword, String lastModifiedBy) {
		
		MemberVO o = findMemberByUserName(memberUserName);

		if (o != null){
			o.setMemberPassword(memberPassword);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}

	}

	public void updateMemberProfile(MemberVO o, String lastModifiedBy) {
		
		MemberVO _o = findMemberByUserName(o.getMemberUserName());
		_o.updateMember(o);
		
		if (_o != null){
			_o.setLastModifiedBy(lastModifiedBy);
			update(_o);
		}

	}

	public void updateMemberSignature(String signature, String memberUserName, String lastModifiedBy) {

		MemberVO o = findMemberByUserName(memberUserName);

		if (o != null){
			o.setSignature(signature);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}

	}

	public void updateMemberUserName(String memberTempUserName,
			String memberUserName, String memberEmail, String memberPassword, String lastModifiedBy) {

		MemberVO o = findMemberByUserName(memberTempUserName);

		if (o != null){
			o.setMemberUserName(memberUserName);
			o.setMemberPassword(memberPassword);
			o.setEmail(memberEmail);
			o.setLastModifiedBy(lastModifiedBy);
			update(o);
		}

	}

	//************************************************
	// Admin User Section
	//************************************************
    @SuppressWarnings("unchecked")
    public List<LoginHistoryVO> getAccessTrailsByUserName(String memebrId){	
    	return getHibernateTemplate().findByNamedQueryAndNamedParam("accesslog.byusername", "memberUsername", getMember(memebrId).getMemberUserName());
    }
    
    @SuppressWarnings("unchecked")
    public List<LoginHistoryVO> getAllAccessTrails(){
    	return getHibernateTemplate().find("from LoginHistoryVO lh order by lh.requestTime desc");
    }

    public MemberVO getMember(String memebrId) {
    	return (MemberVO) getHibernateTemplate().get(MemberVO.class, memebrId);
    }

    public void addUser(MemberVO user) {
    	user.setCreationDate(new Date());
    	add(user);
    }

    public void updateUser(MemberVO user) {
    	MemberVO _user = (MemberVO) get(MemberVO.class, user.getMemberId());
    	_user.updateUser(user);
    	update(_user);
    }

    public void deleteUser(String memberId) throws MyAlumniException {
        
        try {
           MemberVO memberVO = getMember(memberId);
           hardDelete(memberVO);
        } catch (Exception e) {
            throw new MyAlumniException("Unable to delete user because " + e.getMessage());
        }
    }
    
    public void resetPassword(String userId, String password, String lastModifiedBy) {
        
    	MemberVO user = getMember(userId);
    	user.setMemberPassword(password);
    	user.setPromptChange(BaseConstants.BOOLEAN_YES);
        user.setLastModifiedDate(new Date());
		user.setLastModification(BaseConstants.UPDATED);
		user.setLastModifiedBy(lastModifiedBy);    	
    	getHibernateTemplate().update(user);

    }
   
    
    @SuppressWarnings("unchecked")
    public List<MemberVO> getRoleUsers(String isAdmin){
    	return getHibernateTemplate().findByNamedQueryAndNamedParam("member.byrole", "isAdmin", isAdmin);
    }
    
    
	@SuppressWarnings("unchecked")
	public void updateRoleUsers(String isAdmin, String[] memberIds, String lastModifiedBy) {
		
		MemberVO user;
				
		for (String id : memberIds){
			user = (MemberVO) getHibernateTemplate().get(MemberVO.class, id);
			
			if(user != null){			
				user.setIsAdmin(isAdmin);
				user.setLastModification(BaseConstants.UPDATED);
				user.setLastModifiedDate(new Date());
				user.setLastModifiedBy(lastModifiedBy);
				getHibernateTemplate().update(user);
			}
		}
		getHibernateTemplate().flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getUserNameStartingWith(String alpha, String isAdmin){

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where upper(e.memberUserName) like upper('");
		query.append(alpha);
		query.append("%') " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		query.append(" order by e.memberUserName");
				
		result =  getSession().createQuery(query.toString()).list();
		return result;   	
    } 
	  
	
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getEmailStartingWith(String alpha, String isAdmin){

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where upper(e.email) like upper('");
		query.append(alpha);
		query.append("%') " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "'");
		}
		query.append(" order by e.email");
				
		result =  getSession().createQuery(query.toString()).list();
		return result;   	
    }
	
	
    //******************************************
    // IPHONE MEMBER SERVICE
    //******************************************
	@SuppressWarnings("unchecked")
    public List<MemberVO> getLastNameStartingWith(String alpha, String isAdmin){

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where upper(e.lastName) like upper('");
		query.append(alpha);
		query.append("%') " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		query.append(" order by e.lastName");
				
		result =  getSession().createQuery(query.toString()).list();
		return result;   	
    }
	
	@SuppressWarnings("unchecked")
    public List<MemberVO> getFirstNameStartingWith(String alpha, String isAdmin){

		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where upper(e.firstName) like upper('");
		query.append(alpha);
		query.append("%') " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		query.append(" order by e.firstName");
				
		result =  getSession().createQuery(query.toString()).list();
		return result;   	
    	
    }
    
	@SuppressWarnings("unchecked")
	public List<MemberVO> getGenderBy(String alpha, String isAdmin){
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where upper(e.gender) like upper('");
		query.append(alpha);
		query.append("') " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		query.append(" order by e.firstName");
				
		result =  getSession().createQuery(query.toString()).list();
		return result;   
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembersInDorm(String dormitoryId, String isAdmin){
		StringBuffer query = new StringBuffer();
		List<MemberVO> result = new ArrayList<MemberVO>();

		// start query
		query.append("from MemberVO e where e.dormitory = '");
		query.append(dormitoryId);
		query.append("' " );
		if (isAdmin.equals(BaseConstants.BOOLEAN_NO)){
			query.append("and e.memberStatus =  '" + BaseConstants.ACCOUNT_ACTIVE + "' ");
		}
		query.append(" order by e.firstName");
				
		result =  getSession().createQuery(query.toString()).list();
		return result; 
	}
	
	@SuppressWarnings("unchecked")
    public List<XlatDetailVO> getAllActiveDormitory(){
    	
    	StringBuffer query = new StringBuffer();
    	List<XlatDetailVO> result = new ArrayList<XlatDetailVO>();
		query.append("from XlatDetailVO x where XLATGROUP_ID = 'DOM' ");
		query.append("and x.status =  '" + BaseConstants.ACTIVE + "' ");    	

		result =  getSession().createQuery(query.toString()).list();
		return result;      	
    }
    	
	
}
