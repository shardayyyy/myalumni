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
package net.naijatek.myalumni.modules.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.naijatek.myalumni.framework.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.framework.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.extensions.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.StatisticsVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.persistence.iface.MemberDao;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemConfigDao;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.encryption.PasswordGenerator;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
    private MemberDao memberDao;

    @Autowired
    private SystemConfigDao sysConfigDao;
    
    
    private static Log logger = LogFactory.getLog(MemberServiceImpl.class);

/*    public MemberServiceImpl(MemberDao memberDao, SystemConfigDao sysConfigDao) {
        this.memberDao = memberDao;
        this.sysConfigDao = sysConfigDao;
    }*/
    
    // ----------------------------------------------------------------------------------
    
	public void save(MemberVO o)  throws MyAlumniBaseException{
		
    	MemberVO tempMember  = memberDao.getMemberProfileByUserName(o.getMemberUserName());
    	
    	if (tempMember == null){
    		memberDao.save(o);
    	}
    	else{
    		throw new MyAlumniBaseException("myalumni.errorcode.00001");  //User Already exists
    	}
	}
	
	public void softDelete(String id, String lastModifiedBy) throws MyAlumniException{
		memberDao.softDeleteObject(id, lastModifiedBy);
	}	
	
	public void hardDelete(String id) throws MyAlumniException{
		memberDao.hardDeleteObject(id);
	}	

	public List<MemberVO> findAll() {
		return memberDao.findAll();
	}
        
    public List<MemberVO> findAllByStatus(String status) {
    	return memberDao.findAllByStatus(status);
	}
        

	public MemberVO findById(String id) {
		return memberDao.findById(id);
	}

	public void merge(MemberVO o) {
		memberDao.mergeObject(o);
	}
	
    
    
    // ----------------------------------------------------------------------------------

    public boolean isMemberAvailableByEmail(String email, String exceptmemberId) {
        return memberDao.isMemberAvailableByEmail(email, exceptmemberId);
    }

    public boolean lockMemberAccount(String username, String lastModifiedBy) {
        boolean status = false;
        if (memberDao.isMemberAvailableByUserName(username)){
            memberDao.lockMemberAccount(username, lastModifiedBy);
            status = true;
        }
        return status; 
    }
    
    public boolean unLockMemberAccount(String username, String lastModifiedBy){
        boolean status = false;
        if (memberDao.isMemberAvailableByUserName(username)){
            memberDao.unLockMemberAccount(username, lastModifiedBy);
            status = true;
        }
        return status;     	
    }

    public boolean isMemberAvailableByUserName(String username) {
        return memberDao.isMemberAvailableByUserName(username);
    }


    public void createMember(MemberVO member, HttpServletRequest request) throws MyAlumniBaseException {
    	
    	MemberVO tempMember  = memberDao.getMemberProfileByUserName(member.getMemberUserName());
    	
    	if (memberDao.isEmailRegisteredAlready(member.getEmail())){
    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
    	}
    	
    	if (tempMember == null){
    		member.setLastName(StringUtil.capitalize(member.getLastName()));
    		member.setFirstName(StringUtil.capitalize(member.getFirstName()));
    		member.setMaidenName(StringUtil.capitalize(member.getMaidenName()));
            member.setMemberStatus(BaseConstants.ACCOUNT_UNAPPROVED);
            member.setFirstIPAddress(request.getRemoteAddr());
            member.setLastIPAddress(request.getRemoteAddr());
            member.setCreationDate(new Date());
            member.setLastLogonDate(new Date());
            member.setFirstEmail(member.getEmail());
            member.setIsAdmin(BaseConstants.BOOLEAN_NO);
            member.setMemberPassword(Encoder.getMD5_Base64(member.getMemberPassword()));
            member.setPromptChange(BaseConstants.BOOLEAN_YES);      
            member.setHideAddress(BaseConstants.BOOLEAN_NO);
            member.setHideIm(BaseConstants.BOOLEAN_NO);
            member.setHidePhone(BaseConstants.BOOLEAN_NO);
            member.setHideEmail(BaseConstants.BOOLEAN_NO);
    		memberDao.createMember(member);
    	}
    	else{
    		throw new DuplicateMemberException("myalumni.errorcode.00001");  //User Already exists
    	}
    }

    public void createAdminMember(MemberVO member, HttpServletRequest request) throws MyAlumniBaseException {
    	
    	MemberVO tempMember  = memberDao.getMemberProfileByUserName(member.getMemberUserName());
    	
    	if (memberDao.isEmailRegisteredAlready(member.getEmail())){
    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
    	}
    	
    	if (tempMember == null){
    		member.setLastName(StringUtil.capitalize(member.getLastName()));
    		member.setFirstName(StringUtil.capitalize(member.getFirstName()));
    		member.setMaidenName(StringUtil.capitalize(member.getLastName()));
            member.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE);
            member.setFirstIPAddress(request.getRemoteAddr());
            member.setLastIPAddress(request.getRemoteAddr());
            member.setCreationDate(new Date());
            member.setLastLogonDate(new Date());
            member.setFirstEmail(member.getEmail());
            member.setIsAdmin(BaseConstants.BOOLEAN_YES);
            member.setMemberPassword(Encoder.getMD5_Base64(member.getMemberPassword()));
            member.setPromptChange(BaseConstants.BOOLEAN_NO);      
            member.setHideAddress(BaseConstants.BOOLEAN_NO);
            member.setHideIm(BaseConstants.BOOLEAN_NO);
            member.setHidePhone(BaseConstants.BOOLEAN_NO);
            member.setHideEmail(BaseConstants.BOOLEAN_NO);                      
    		memberDao.createMember(member);
    	}
    	else{
    		throw new DuplicateMemberException("myalumni.errorcode.00001");  //User Already exists
    	}
    	
            	
    }
    
    public void updateMemberPassword(String memberUserName, 
                                     String memberPassword, String lastModifiedBy) {
    	
        memberDao.updateMemberPassword(memberUserName, Encoder.getMD5_Base64(memberPassword), lastModifiedBy);
    }

    public MemberVO getMemberProfileByUserName(String memberUserName) {
        return memberDao.getMemberProfileByUserName(memberUserName);
    }
    
    public MemberVO getMemberProfileByMemberId(String memberId){
    	return memberDao.getMember(memberId);
    }

    public void updateMemberProfile(MemberVO member, String lastModifiedBy) {
        memberDao.updateMemberProfile(member, lastModifiedBy);
    }

    public void updateMemberEmail(String email, String memberUserName, String lastModifiedBy) {
        memberDao.updateMemberEmail(email, memberUserName, lastModifiedBy);
    }

    public String getMemberPasswordByUserName(String memberUserName) {
        return memberDao.getMemberPasswordByUserName(memberUserName);
    }

    public void updateMemberSignature(String signature, 
                                      String memberUserName, String lastModifiedBy) {
        memberDao.updateMemberSignature(signature, memberUserName, lastModifiedBy);
    }

    public void updateMemberAvatar(String avatar, String memberUserName, String lastModifiedBy) {
        memberDao.updateMemberAvatar(avatar, memberUserName, lastModifiedBy);
    }
    
    public void deleteMemberAvatar(String avatar, String memberUserName, String lastModifiedBy){
    	memberDao.deleteMemberAvatar(avatar, memberUserName, lastModifiedBy);
    }

    public List<MemberVO> getLatestMembers(int rowsToReturn) {
        return memberDao.getLatestMembers(rowsToReturn);
    }


    public List<MemberVO> getBirthdayListOfTheMonth(String month) {
        return memberDao.getBirthdayListOfTheMonth(month);
    }
    
    public List<MemberVO> getTodayBirthdayMembers(String month, String day){
    	return memberDao.getTodayBirthdayMembers(month, day);
    }

    public List<MemberVO> searchAvatar(int offset, int rowToReturn, String role) {
        return memberDao.searchAvatar(offset, rowToReturn, role);
    }

    public String getMemberStatusByUserName(String memberUserName) {
        return memberDao.getMemberStatusByUserName(memberUserName);
    }

    public StatisticsVO getAllStatistics(){
        StatisticsVO stats = new StatisticsVO();
        List<MemberVO> members = memberDao.getAllStatistics();
        Map<String, Integer> dormitoryMap = new TreeMap<String, Integer>();
        Map<String, Integer> countryMap = new TreeMap<String, Integer>();
        Map<String, Integer> emailMap = new TreeMap<String, Integer>();
       
        
        int maleCount = 0;//
        int femaleCount = 0;//
        int unknownGenderCount = 0;
        int deactivatedAccountCount = 0;//
        int deletedAccountCount = 0;//
        int lockedAccountCount = 0;//
        int totalCount = 0;//
        int newMembers = 0;//
        int noEmailCount = 0;//
        
        
        for (MemberVO mem : members){
            
            // Gender
            if (mem.getGender().equalsIgnoreCase(BaseConstants.GENDER_MALE)){
                maleCount++;
            }
            else if (mem.getGender().equalsIgnoreCase(BaseConstants.GENDER_FEMALE)){
                femaleCount++;
            }
            else if (mem.getGender().equalsIgnoreCase(BaseConstants.GENDER_UNKNOWN)){
                unknownGenderCount++;
            }
            
            
            // Country
            String key = mem.getCountryLabel();
            if (key != null && key.length() > 0){
	            if (!countryMap.containsKey(key)){
	            	countryMap.put(key, 1);
	            }
	            else{
	            	int currNumber = countryMap.get(key);
	            	countryMap.remove(key);
	            	countryMap.put(key, currNumber+1);
	            }
            }
            
            
            // Dormitory
            String key1 = mem.getDormitoryLabel();
            
            if (key1 != null && key1.length() > 0){
	            if (!dormitoryMap.containsKey(key1)){
	            	dormitoryMap.put(key1, 1);
	            }
	            else{
	            	int currNumber = dormitoryMap.get(key1);
	            	dormitoryMap.remove(key1);
	            	dormitoryMap.put(key1, currNumber+1);
	            }
            }
            
            
            // Email
            String email = mem.getEmail();
            String key2 = null;
            String[] emailArray = null;
            
            if (email != null && email.length() > 1){
            	emailArray = email.split("@");
                key2 = emailArray[1].toLowerCase();
            }
            
            if (key2 != null && key2.length() > 0){
	            if (!emailMap.containsKey(key2)){
	            	emailMap.put(key2, 1);
	            }
	            else{
	            	int currNumber = emailMap.get(key2);
	            	emailMap.remove(key2);
	            	emailMap.put(key2, currNumber+1);
	            }
            }
            
            
            
            // Status
            if (mem.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_DEACTIVATED)){
                deactivatedAccountCount++;
            }
            else if (mem.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_LOCKED)){
                lockedAccountCount++;
            }
            else if(mem.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_UNAPPROVED)){
                newMembers++;
            }
            else if(mem.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_DELETED)){
            	deletedAccountCount++;
            }            
            
            // no email
            if (mem.getEmail() == null || mem.getEmail().equalsIgnoreCase("")){
                noEmailCount++;
            }
            totalCount++;
            
        }
        
        stats.setEmailMap(emailMap);
        stats.setMaleCount(maleCount);
        stats.setFemaleCount(femaleCount);
        stats.setUnknownGenderCount(unknownGenderCount);
        stats.setDormitoryMap(dormitoryMap);
        stats.setCountryMap(countryMap);
        stats.setTotalCount(totalCount);
        stats.setLockedAccountCount(lockedAccountCount);
        stats.setDeactivatedAccountCount(deactivatedAccountCount);
        stats.setNewMembers(newMembers);
        stats.setNoEmailCount(noEmailCount);
        stats.setDeletedAccountCount(deletedAccountCount);
        return stats;
    }
    


    public boolean isAccountActivatedByMemberId(String memberId) {
        return memberDao.isAccountActivatedByMemberId(memberId);
    }

    public String getMemberEmailByMemberId(String memberId){
    	return memberDao.getMemberEmailByMemberId(memberId);
    }

    public void updateMemberUserName(String memberTempUserName, 
                                     String memberUserName, String memberEmail, 
                                     String memberPassword, String lastModifiedBy) {
        memberDao.updateMemberUserName(memberTempUserName, memberUserName, 
                                       memberEmail, memberPassword, lastModifiedBy);
    }

    public void deleteMemberByUserName(String memberUserName) {
        memberDao.deleteMemberByUserName(memberUserName);
    }

    public List<MemberVO> getAllMembers() {
        return memberDao.getAllMembers();
    }

    public List<MemberVO> searchFullSearchOnMembers(String firstName, String lastName, 
                                          String dorm, String gender, 
                                          String yearIn, String yearOut, 
                                          String marriageName, String nickName, 
                                          String maidenName, 
                                          String partialNameSearch, 
                                          int offset, int rowsToReturn, 
                                          String role) {
        return memberDao.searchFullSearchOnMembers(firstName, lastName, dorm, 
                                                   gender, yearIn, yearOut, 
                                                   marriageName, nickName, 
                                                   maidenName, 
                                                   partialNameSearch, offset, 
                                                   rowsToReturn, role);
    }

    public List<MemberVO> searchGender(String gender, int offset, int rowsToReturn, 
                             String role) {
        return memberDao.searchGender(gender, offset, rowsToReturn, role);

    }

    public List<MemberVO> searchMaidenName(String maidenName, String partialNameSearch, 
                                 int offset, int rowsToReturn, String role) {
        return memberDao.searchMaidenName(maidenName, partialNameSearch, 
                                          offset, rowsToReturn, role);
    }

    public List<MemberVO> searchFirstName(String firstName, String partialNameSearch, 
                                int offset, int rowsToReturn, String role) {
        return memberDao.searchFirstName(firstName, partialNameSearch, offset, 
                                         rowsToReturn, role);
    }

    public List<MemberVO> searchLastName(String lastName, String partialNameSearch, 
                               int offset, int rowsToReturn, String role) {
        return memberDao.searchLastName(lastName, partialNameSearch, offset, 
                                        rowsToReturn, role);
    }

    public List<MemberVO> searchUserName(String userName, String partialNameSearch, 
                               int offset, int rowsToReturn, String role) {
        return memberDao.searchUserNamePartial(userName, partialNameSearch, 
                                               offset, rowsToReturn, role);
    }

    public List<MemberVO> searchEmail(String userName, String partialNameSearch, 
            int offset, int rowsToReturn, String role) {
    		return memberDao.searchEmail(userName, partialNameSearch, 
                            offset, rowsToReturn, role);
    }
    
    public List<MemberVO> searchDormitory(String dorm, int offset, int rowsToReturn, 
                            String role) {

        return memberDao.searchDomitory(dorm, offset, rowsToReturn, role);
    }


    public List<MemberVO> searchYearIn(String yearIn, int offset, int rowsToReturn, 
                             String role) {
        return memberDao.searchYearIn(yearIn, offset, rowsToReturn, role);
    }

    public List<MemberVO> searchYearOut(String yearOut, int offset, int rowsToReturn, 
                              String role) {
        return memberDao.searchYearOut(yearOut, offset, rowsToReturn, role);
    }
    
    public List<MemberVO> adminGetMembersByStatus(int offset, int rowsToReturn, String status){
    	return memberDao.adminGetMembersByStatus(offset, rowsToReturn, status);
    }

    public List<MemberVO> searchNickName(String nickname, String partialNameSearch, 
                               int offset, int rowsToReturn, String role) {
        return memberDao.searchNickName(nickname, partialNameSearch, offset, 
                                        rowsToReturn, role);
    }

    public void activateMemberByUserName(String memberUserName, String lastModifiedBy) {
        memberDao.activateMemberByUserName(memberUserName, lastModifiedBy);
    }

    public void deactivateMemberByUserName(String memberUserName, String lastModifiedBy) {
        memberDao.deactivateMemberByUserName(memberUserName, lastModifiedBy);
    }

    public void lockMemberByUserName(String memberUserName, String lastModifiedBy) {
        memberDao.lockMemberByUserName(memberUserName, lastModifiedBy);
    }

    public List<MemberVO> getMemberToAdminister() {
        return memberDao.getMembersToAdminister();
    }

    public List<MemberVO> adminGetAllMembers(int offset, int rowsToReturn) {
        return memberDao.adminGetAllMembers(offset, rowsToReturn);
    }

    public List<MemberVO> adminGetOneMembersByUserName(String memberUsername) {
        return memberDao.adminGetOneMembers(memberUsername);
    }

    
    //******************************************
    // USER SERVICE
    //******************************************

    public MemberVO getUser(String id) {
        
        return memberDao.getMember(id);
    }

    public void addUser(MemberVO user)  throws DuplicateMemberException, DuplicateEmailException{
    	
    	MemberVO tempMember  = memberDao.getMemberProfileByUserName(user.getMemberUserName());
    	
    	if (tempMember.getEmail().equalsIgnoreCase(user.getEmail())){
    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
    	}
    	
    	if (tempMember.getMemberUserName().equalsIgnoreCase(user.getMemberUserName())){
    		throw new DuplicateMemberException("error.duplicate.member");  //User Already exists
    	}
    	
        user.setLastName(StringUtil.capitalize(user.getLastName()));
        user.setFirstName(StringUtil.capitalize(user.getFirstName()));
        user.setMaidenName(StringUtil.capitalize(user.getMaidenName()));
        memberDao.addUser(user);
    }

    public void updateUser(MemberVO user) throws DuplicateEmailException {
    	//MemberVO userProfile  = memberDao.getMemberProfileByUserName(user.getMemberUserName());
    	
    	if (isMemberAvailableByEmail(user.getEmail(), user.getMemberId())){
    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
    	}

//		// email address is changing for the same user
//    	if (!userProfile.getEmail().equalsIgnoreCase(user.getEmail()) && !userProfile.getMemberId().equalsIgnoreCase(user.getMemberId())){
//    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
//    	}
//    	
//    	// email address changes and address belongs to another username
//    	if (userProfile.getEmail().equalsIgnoreCase(user.getEmail()) && tempMember.getMemberUserName().equalsIgnoreCase(user.getMemberUserName())){
//    		throw new DuplicateEmailException("error.duplicate.email");  //Email Already exists
//    	}

    	
        memberDao.updateUser(user);
    }

    public void deleteUser(String id) throws MyAlumniException {
        
        memberDao.deleteUser(id);
    }
    
    public MemberVO getUserWithRolesAndAccessLogs(String memberId) {
        
        //return memberDao.getUserWithRolesAndAccessLogs(id);
    	return null;
    }
    
    public List<MemberVO> filterUsersByAlphabelt(String alpha, String searchType, String isAdmin){
    
    		List<MemberVO> members = new ArrayList<MemberVO>();
    		
    		if (searchType.equals(BaseConstants.USERNAME)){
    			members = memberDao.getUserNameStartingWith(alpha, isAdmin);
    		}
    		else if (searchType.equals(BaseConstants.FIRST_NAME)){
    			members = memberDao.getFirstNameStartingWith(alpha, isAdmin);
    		}
    		else if (searchType.equals(BaseConstants.LAST_NAME)){
    			members = memberDao.getLastNameStartingWith(alpha, isAdmin);
    		}
    		else if (searchType.equals(BaseConstants.EMAIL)){
    			members = memberDao.getEmailStartingWith(alpha, isAdmin);
    		}
    		//else if (searchType.equals("all")){
    		//	members = memberDao.getAllMembers();
    		//}
    		
    		return members;
    }
    
    
   public void resetPassword(String memberId, String lastModifiedBy) throws MyAlumniException {
        
    	try{
    		MemberVO memberVO = getUser(memberId);
    		
    		if (memberVO != null){
	    		// For security reasons, we generate a new random password for the user. 
	    		// User should be forced to change this on next logon.
	    		String newPasswd =  PasswordGenerator.createPassword(8);
	        
	    		memberDao.resetPassword(memberId, Encoder.getMD5_Base64(newPasswd), lastModifiedBy);
	    	
	    		SystemConfigVO sysConfig = sysConfigDao.getSystemConfig();
	    	
	    		SendMailUtil.sendPasswordReminderMail(memberVO.getEmail(), memberVO.getMemberUserName(), newPasswd, sysConfig);
    		}
    		else{
    			throw new MyAlumniException("Unable to resetPassword, user not found ");
    		}
        } catch (Exception e) {
            throw new MyAlumniException("Unable to resetPassword because " + e.getMessage());
        }
   }
    
    public List<LoginHistoryVO> getAccessTrailsByUserName(String memberId) {
        
        return memberDao.getAccessTrailsByUserName(memberId);
    }
    
    public List<LoginHistoryVO> getAllAccessTrails(){
        return memberDao.getAllAccessTrails();
    }    
    
    public List<MemberVO> getRoleUsers(String roleId){
    	return memberDao.getRoleUsers(roleId);
    }
    
    public void updateRoleUsers(String roleId, String[] memberIds, String lastModifiedBy ){
    	memberDao.updateRoleUsers(roleId, memberIds, lastModifiedBy);
    }
    
    public void saveAll(List<MemberVO> members){
    	memberDao.saveAll(members);
    }
  
    
    public List<String> genericAjaxSearch(String searchWord, String searchCriteria){
    	return memberDao.genericAjaxSearch(searchWord, searchCriteria);    	
    }
    
    public List<MemberVO> genericAjaxSearchObjects(String searchWord, String searchCriteria){
    	return memberDao.genericAjaxSearchObjects(searchWord, searchCriteria);    	
    }    

    //******************************************
    // IPHONE MEMBER SERVICE
    //******************************************
    
    public List<MemberVO> getLastNameStartingWith(String alpha, String isAdmin){
    	return memberDao.getLastNameStartingWith(alpha, isAdmin);
    }
    
    public List<MemberVO> getFirstNameStartingWith(String alpha, String isAdmin){
    	return memberDao.getFirstNameStartingWith(alpha, isAdmin);
    }
    
    public List<MemberVO> getGenderBy(String alpha, String isAdmin){
    	return memberDao.getGenderBy(alpha, isAdmin);
    }
    
	public List<MemberVO> getMembersInDorm(String dormitoryId, String isAdmin){
		return memberDao.getMembersInDorm(dormitoryId, isAdmin);
	}
    
    public List<XlatDetailVO> getAllActiveDormitory(){
    	return memberDao.getAllActiveDormitory();
    }
    
}
