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

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseForm;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.util.BaseConstants;


public class MemberForm extends MyAlumniBaseForm{

	private String approach;   // used for ajax search
	
	private String memberId;
	private String memberUserName;
	private String messageToUserName;
	private String memberTempUserName;
	private String memberStatus;
	private String oldMemberPassword;
	private String memberPassword;
	private String memberPasswordConfirm;
	private String activated;
	private String email;
	private String emailConfirm ;
	private String firstIPAddress;
	private String lastIPAddress;
	private String creationDate;  // Timestamp
	private String lastLogonDate;  // Timestamp
	private String activationCode;
	private String signature;
	private String titleId;
	private String titleLabel;
	private String avatar;
	private String firstName;
	private String lastName;
	private String maidenName;
	private String nickName;
	private String gender;
	private String address;
	private String firstEmail;
	private String countryId;
	private String countryLabel;
	private String phone;
	private String careerId;
	private String careerLabel;
	private String careerOther;
	private String website;
	private String favUrl1;
	private String favUrl2;
	private String yearIn;
	private String yearOut;
	private String isAdmin;
	private String dormitoryId;
	private String dormitoryLabel;
	private String comments;
	private String adminComments;
	private String dob;   // date
	private String hideEmail = BaseConstants.BOOLEAN_NO;
	private String hideAddress = BaseConstants.BOOLEAN_NO;
	private String hidePhone = BaseConstants.BOOLEAN_NO;
	private String hideIm = BaseConstants.BOOLEAN_NO;
	private String promptChange = BaseConstants.BOOLEAN_NO; // if user should be prompted for memberPassword change
	
	private String[] lstSelectedIMs;
	private String[] lstAvailableIMs;
	private List<XlatDetailVO> messengers = new ArrayList<XlatDetailVO>();
	
	
//	 Non Database Support Attributes
	private String partialNameSearch = BaseConstants.BOOLEAN_NO;
	private String searchCategory;
	private String searchCriteria;
	private String ajaxFormat;
	private FormFile avatarUpload;
	private String avatarUploadOverwrite;
	private String adminAction;
	private String adminDisplay;
	private String deleteConfirm;
    private String[] lstAvailableUsers;
    private String[] lstSelectedUsers;
    private String guestEmail;
    
    // iphone
    private String alpha;


    
	
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
	public String getAdminAction() {
		return adminAction;
	}
	public void setAdminAction(String adminAction) {
		this.adminAction = adminAction;
	}
	public String getAdminDisplay() {
		return adminDisplay;
	}
	public void setAdminDisplay(String adminDisplay) {
		this.adminDisplay = adminDisplay;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getPartialNameSearch() {
		return partialNameSearch;
	}
	public void setPartialNameSearch(String partialNameSearch) {
		this.partialNameSearch = partialNameSearch;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
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
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String country) {
		this.countryId = country;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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
	public String getDormitoryId() {
		return dormitoryId;
	}
	public void setDormitoryId(String dormitoryId) {
		this.dormitoryId = dormitoryId;
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
	public String getLastLogonDate() {
		return lastLogonDate;
	}
	public void setLastLogonDate(String lastLogonDate) {
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
	public String getCareerId() {
		return careerId;
	}
	public void setCareerId(String careerId) {
		this.careerId = careerId;
	}
	public String getTitleId() {
		return titleId;
	}
	public void setTitleId(String titleId) {
		this.titleId = titleId;
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
	public String getYearIn() {
		return yearIn;
	}
	public void setYearIn(String yearIn) {
		this.yearIn = yearIn;
	}
	public String getYearOut() {
		return yearOut;
	}
	public void setYearOut(String yearOut) {
		this.yearOut = yearOut;
	}
	public FormFile getAvatarUpload() {
		return avatarUpload;
	}
	public void setAvatarUpload(FormFile avatarUpload) {
		this.avatarUpload = avatarUpload;
	}
	public String getAvatarUploadOverwrite() {
		return avatarUploadOverwrite;
	}
	public void setAvatarUploadOverwrite(String avatarUploadOverwrite) {
		this.avatarUploadOverwrite = avatarUploadOverwrite;
	}
	public String getDeleteConfirm() {
		return deleteConfirm;
	}
	public void setDeleteConfirm(String deleteConfirm) {
		this.deleteConfirm = deleteConfirm;
	}
	public String getEmailConfirm() {
		return emailConfirm;
	}
	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	public String getCareerOther() {
		return careerOther;
	}
	public void setCareerOther(String careerOther) {
		this.careerOther = careerOther;
	}
	public String getMemberPasswordConfirm() {
		return memberPasswordConfirm;
	}
	public void setMemberPasswordConfirm(String memberPasswordConfirm) {
		this.memberPasswordConfirm = memberPasswordConfirm;
	}
	public String getCountryLabel() {
		return countryLabel;
	}
	public void setCountryLabel(String countryLabel) {
		this.countryLabel = countryLabel;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public String getCareerLabel() {
		return careerLabel;
	}
	public void setCareerLabel(String careerLabel) {
		this.careerLabel = careerLabel;
	}
	public String getDormitoryLabel() {
		return dormitoryLabel;
	}
	public void setDormitoryLabel(String dormitoryLabel) {
		this.dormitoryLabel = dormitoryLabel;
	}
	public String getPromptChange() {
		return promptChange;
	}
	public void setPromptChange(String promptChange) {
		this.promptChange = promptChange;
	}
	public String getOldMemberPassword() {
		return oldMemberPassword;
	}
	public void setOldMemberPassword(String oldMemberPassword) {
		this.oldMemberPassword = oldMemberPassword;
	}
	public String getMemberTempUserName() {
		return memberTempUserName;
	}
	public void setMemberTempUserName(String memberTempUserName) {
		this.memberTempUserName = memberTempUserName;
	}
	public String[] getLstSelectedIMs() {
		return lstSelectedIMs;
	}
	public void setLstSelectedIMs(String[] lstSelectedIMs) {
		this.lstSelectedIMs = lstSelectedIMs;
	}
	public String[] getLstAvailableIMs() {
		return lstAvailableIMs;
	}
	public void setLstAvailableIMs(String[] lstAvailableIMs) {
		this.lstAvailableIMs = lstAvailableIMs;
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
	public String getApproach() {
		return approach;
	}
	public void setApproach(String approach) {
		this.approach = approach;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public String getMessageToUserName() {
		return messageToUserName;
	}
	public void setMessageToUserName(String messageToUserName) {
		this.messageToUserName = messageToUserName;
	}
	public String getAjaxFormat() {
		return ajaxFormat;
	}
	public void setAjaxFormat(String ajaxFormat) {
		this.ajaxFormat = ajaxFormat;
	}

}
