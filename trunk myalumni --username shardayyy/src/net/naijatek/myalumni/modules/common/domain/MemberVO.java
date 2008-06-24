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
package net.naijatek.myalumni.modules.common.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import net.naijatek.myalumni.util.utilities.StringUtil;


public class MemberVO  extends MyAlumniBaseVO {

	private String memberId;
	private String memberUserName;
	private String memberStatus;
	private String memberPassword;
	private String email;
	private String firstIPAddress;
	private String lastIPAddress;
	private Date creationDate;
	private Date lastLogonDate;
	private String activationCode;
	private String signature;
	private String titleId;
	private XlatDetailVO title;
	private String titleLabel;
	private String avatar;
	private String firstName;
	private String lastName;
	private String fullName;
	private String maidenName;
	private String nickName;
	private String gender;
	private String address;
	private String firstEmail;
	private String countryId;
	private XlatDetailVO country;
	private String countryLabel;
	private String phone;
	private String careerId;
	private XlatDetailVO career;
	private String careerLabel;
	private String website;
	private String favUrl1;
	private String favUrl2;
	private Integer yearIn;
	private Integer yearOut;
	private String isAdmin;
	private String dormitoryId;
	private XlatDetailVO dormitory;
	private String dormitoryLabel;
	private String comments;
	private String adminComments;
	private Date dob;
	private String hideEmail;
	private String hideAddress;
	private String hidePhone;
	private String hideIm;
	
	private String[] lstSelectedIMs;
	private String[] lstAvailableIMs;
	private List<XlatDetailVO> messengers = new ArrayList<XlatDetailVO>();
    
    
	// Other Attributes
	private boolean loginSuccessfull = false;
	private String loginStatus;
	private Date lastRequestTime;  // for online user manager
	private boolean overWriteAvatar = false;  // for the update avatar
    private String[] lstAvailableUsers;
    private String[] lstSelectedUsers;
	private String promptChange; // if user should be prompted for memberPassword change
	
	
    // iphone
    private String alpha;
	
	
	

  
//	 Non Database Support Attributes
	private String partialNameSearch ;
	

  public String getPartialNameSearch() {
		return partialNameSearch;
	}

	public void setPartialNameSearch(String partialNameSearch) {
		this.partialNameSearch = partialNameSearch;
	}

	public MemberVO() {
	  }
	
  public MemberVO(String id) {
  	this.memberId = id;
	}

		public String getActivationCode() {
			return activationCode;
		}
		
		public void setActivationCode(String activationCode) {
			this.activationCode = activationCode;
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getAdminComments() {
			return adminComments;
		}
		
		public void setAdminComments(String adminComments) {
			this.adminComments = adminComments;
		}
		
		public String getAvatar() {
			return avatar;
		}
		
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		
		public String getComments() {
			return comments;
		}
		
		public void setComments(String comments) {
			this.comments = comments;
		}
		
		public XlatDetailVO getCountry() {
			return country;
		}
		
		public void setCountry(XlatDetailVO country) {
			this.country = country;
		}
		
		public String getCountryId() {
			return countryId;
		}
		
		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}
		
		public Date getCreationDate() {
			return creationDate;
		}
		
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		
		public Date getDob() {
			return dob;
		}
		
		public void setDob(Date dob) {
			this.dob = dob;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getFavUrl1() {
			return favUrl1;
		}
		
		public void setFavUrl1(String favUrl1) {
			this.favUrl1 = favUrl1;
		}
		
		public String getFavUrl2() {
			return favUrl2;
		}
		
		public void setFavUrl2(String favUrl2) {
			this.favUrl2 = favUrl2;
		}
		
		public String getFirstEmail() {
			return firstEmail;
		}
		
		public void setFirstEmail(String firstEmail) {
			this.firstEmail = firstEmail;
		}
		
		public String getFirstIPAddress() {
			return firstIPAddress;
		}
		
		public void setFirstIPAddress(String firstIPAddress) {
			this.firstIPAddress = firstIPAddress;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getGender() {
			return gender;
		}
		
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getHideAddress() {
			return hideAddress;
		}
		
		public void setHideAddress(String hideAddress) {
			this.hideAddress = hideAddress;
		}
		
		public String getHideEmail() {
			return hideEmail;
		}
		
		public void setHideEmail(String hideEmail) {
			this.hideEmail = hideEmail;
		}
		
		public String getHideIm() {
			return hideIm;
		}
		
		public void setHideIm(String hideIm) {
			this.hideIm = hideIm;
		}
		
		public String getHidePhone() {
			return hidePhone;
		}
		
		public void setHidePhone(String hidePhone) {
			this.hidePhone = hidePhone;
		}

		public String getIsAdmin() {
			return isAdmin;
		}
		
		public void setIsAdmin(String isAdmin) {
			this.isAdmin = isAdmin;
		}
		
		public String getLastIPAddress() {
			return lastIPAddress;
		}
		
		public void setLastIPAddress(String lastIPAddress) {
			this.lastIPAddress = lastIPAddress;
		}
		
		public Date getLastLogonDate() {
			return lastLogonDate;
		}
		
		public void setLastLogonDate(Date lastLogonDate) {
			this.lastLogonDate = lastLogonDate;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getMaidenName() {
			return maidenName;
		}
		
		public void setMaidenName(String maidenName) {
			this.maidenName = maidenName;
		}
		
		public String getMemberId() {
			return memberId;
		}
		
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		
		public String getMemberPassword() {
			return memberPassword;
		}
		
		public void setMemberPassword(String memberPassword) {
			this.memberPassword = memberPassword;
		}
		
		public String getMemberStatus() {
			return memberStatus;
		}
		
		public void setMemberStatus(String memberStatus) {
			this.memberStatus = memberStatus;
		}
		
		public String getNickName() {
			return nickName;
		}
		
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		
		public XlatDetailVO getCareer() {
			return career;
		}
		
		public void setCareer(XlatDetailVO career) {
			this.career = career;
		}
		
		public String getCareerId() {
			return careerId;
		}
		
		public void setCareerId(String careerId) {
			this.careerId = careerId;
		}
		
		public XlatDetailVO getDormitory() {
			return dormitory;
		}
		
		public void setDormitory(XlatDetailVO dormitory) {
			this.dormitory = dormitory;
		}
		
		public String getDormitoryId() {
			return dormitoryId;
		}
		
		public void setDormitoryId(String dormitoryId) {
			this.dormitoryId = dormitoryId;
		}
		
		public XlatDetailVO getTitle() {
			return title;
		}
		
		public void setTitle(XlatDetailVO title) {
			this.title = title;
		}
		
		public String getTitleId() {
			return titleId;
		}
		
		public void setTitleId(String titleId) {
			this.titleId = titleId;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public String getSignature() {
			return signature;
		}
		
		public void setSignature(String signature) {
			this.signature = signature;
		}
		
		public String getMemberUserName() {
			return memberUserName;
		}
		
		public void setMemberUserName(String userName) {
			this.memberUserName = userName;
		}
		
		public String getWebsite() {
			return website;
		}
		
		public void setWebsite(String website) {
			this.website = website;
		}
		
		public Integer getYearIn() {
			return yearIn;
		}
		
		public void setYearIn(Integer yearIn) {
			this.yearIn = yearIn;
		}
		
		public Integer getYearOut() {
			return yearOut;
		}
		
		public void setYearOut(Integer yearOut) {
			this.yearOut = yearOut;
		}
		
		public String getPromptChange() {
			return promptChange;
		}
		
		public void setPromptChange(String promptChange) {
			this.promptChange = promptChange;
		}
		
		public String getLoginStatus() {
			return loginStatus;
		}
		
		public void setLoginStatus(String loginStatus) {
			this.loginStatus = loginStatus;
		}
		
		public boolean isLoginSuccessfull() {
			return loginSuccessfull;
		}
		
		public void setLoginSuccessfull(boolean loginSuccessfull) {
			this.loginSuccessfull = loginSuccessfull;
		}
		
		public Date getLastRequestTime() {
			return lastRequestTime;
		}
		
		public void setLastRequestTime(Date lastRequestTime) {
			this.lastRequestTime = lastRequestTime;
		}
		
		public boolean isOverWriteAvatar() {
			return overWriteAvatar;
		}
		
		public void setOverWriteAvatar(boolean overWriteAvatar) {
			this.overWriteAvatar = overWriteAvatar;
		}
		
		
		public void updateUser(MemberVO user){
			
			if (user != null){
				setFirstName(user.getFirstName());
				setLastName(user.getLastName());
	
				if (user.getEmail() != null)
					setEmail(user.getEmail());
				
				setMemberStatus(user.getMemberStatus());
			}
		}
		
		
		public void updateMember(MemberVO user){
			
			if (user != null){
				
				//
				// ABOUT YOU
				//
				if (user.getTitleId() != null){
					setTitleId(user.getTitleId());
					setTitle(new XlatDetailVO(user.getTitleId()));
				}
				
				setFirstName(user.getFirstName());
				setLastName(user.getLastName());
				setMaidenName(user.getMaidenName());				
				setNickName(user.getNickName());
				setGender(user.getGender());
				setDob(user.getDob());
	
				if (user.getEmail() != null){
					setEmail(user.getEmail());
				}
				setHideEmail(user.getHideEmail());
				
				setWebsite(user.getWebsite());
				setPhone(user.getPhone());
				setHidePhone(user.getHidePhone());
				
				if (user.getYearIn() != null)
					setYearIn(user.getYearIn());
					
				if (user.getYearOut() != null)
					setYearOut(user.getYearOut());
				
				if (user.getDormitoryId() != null){
					setDormitoryId(user.getDormitoryId());
					setDormitory(new XlatDetailVO(user.getDormitoryId()));
				}
				
				if (user.getCareerId() != null){
					setCareerId(user.getCareerId());
					setCareer(new XlatDetailVO(user.getCareerId()));
				}
				
				setFavUrl1(user.getFavUrl1());
				setFavUrl2(user.getFavUrl2());
				
				
				//
				//INSTANT MESSENGERS
				//
				setHideIm(user.getHideIm());
				
				
				//
				//YOUR LOCATION
				//				
				setAddress(user.getAddress());
				setHideAddress(user.getHideAddress());
				
				if (user.getCountryId() != null){
					setCountryId(user.getCountryId());
					setCountry(new XlatDetailVO(user.getCountryId()));
				}
				
				setComments(user.getComments());
				
				if (user.getAdminComments() != null){
					setAdminComments(user.getAdminComments());
				}
			}
			
		}
		
		public String getFullName() {
			this.fullName = StringUtil.safeString(getFirstName()) + " " + StringUtil.safeString(getLastName()) ;
			return this.fullName;
		}
		
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getCareerLabel() {
			this.careerLabel = new String();
			if (getCareer() != null)
				this.careerLabel = getCareer().getLabel();			
			return this.careerLabel;
		}

		public String getCountryLabel() {
			this.countryLabel = new String();
			if (getCountry() != null)
				this.countryLabel = getCountry().getLabel();			
			return this.countryLabel;
		}

		public String getDormitoryLabel() {
			this.dormitoryLabel = new String();
			if (getDormitory() != null)
				this.dormitoryLabel = getDormitory().getLabel();			
			return this.dormitoryLabel;
		}

		public String getTitleLabel() {
			this.titleLabel = new String();
			if (getTitle() != null)
				this.titleLabel = getTitle().getLabel();			
			return this.titleLabel;
		}

		public void setCareerLabel(String careerLabel) {
			this.careerLabel = careerLabel;
		}

		public void setCountryLabel(String countryLabel) {
			this.countryLabel = countryLabel;
		}

		public void setDormitoryLabel(String dormitoryLabel) {
			this.dormitoryLabel = dormitoryLabel;
		}

		public void setTitleLabel(String titleLabel) {
			this.titleLabel = titleLabel;
		}

		public String[] getLstAvailableIMs() {
			return lstAvailableIMs;
		}

		public void setLstAvailableIMs(String[] lstAvailableIMs) {
			this.lstAvailableIMs = lstAvailableIMs;
		}

		public String[] getLstSelectedIMs() {
			return lstSelectedIMs;
		}

		public void setLstSelectedIMs(String[] lstSelectedIMs) {
			this.lstSelectedIMs = lstSelectedIMs;
		}

		public String[] getLstAvailableUsers() {
			return lstAvailableUsers;
		}

		public void setLstAvailableUsers(String[] lstAvailableUsers) {
			this.lstAvailableUsers = lstAvailableUsers;
		}

		public String[] getLstSelectedUsers() {
			return lstSelectedUsers;
		}

		public void setLstSelectedUsers(String[] lstSelectedUsers) {
			this.lstSelectedUsers = lstSelectedUsers;
		}

		public List<XlatDetailVO> getMessengers() {
			return messengers;
		}

		public void setMessengers(List<XlatDetailVO> messengers) {
			this.messengers = messengers;
		}

		public String getAlpha() {
			return alpha;
		}

		public void setAlpha(String alpha) {
			this.alpha = alpha;
		}

		
 
}
