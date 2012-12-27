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
package net.naijatek.myalumni.modules.common.persistence.iface;


import java.util.List;


import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;


public interface MemberDao  extends BaseCrudDao<MemberVO, String>{


  public boolean lockMemberAccount(String memberUserName, String lastModifiedBy);
  
  public boolean unLockMemberAccount(String memberUserName, String lastModifiedBy);

  public void createMember(MemberVO member) throws MyAlumniBaseException;

  public boolean isMemberAvailableByUserName(String memberUserName);
  
  public boolean isEmailRegisteredAlready(String email);

  public boolean isMemberAvailableByEmail(String email, String exceptmemberId);

  public void updateMemberPassword(String memberUserName, String memberPassword, String lastModifiedBy);

  public List<MemberVO>  searchFullSearchOnMembers(String firstName, String lastName,
                                             String dormitory, String gender,
                                             String yearIn, String yearOut,
                                             String marriageName,
                                             String nickName,
                                             String maidenName,
                                             String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchGender(String gender,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchMaidenName(String maidenName,
                                    String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchFirstName(String firstName, String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchLastName(String lastName, String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchUserNamePartial(String userName, String partialNameSearch,int offset, int rowsToReturn, String isAdmin);
  
  public List<MemberVO>  searchEmail(String email, String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchDomitory(String dormitory,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchYearIn(String yearIn,int offset, int rowsToReturn, String isAdmin) ;

  public List<MemberVO>  searchYearOut(String yearOut,int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  searchNickName(String nickname, String partialNameSearch,int offset, int rowsToReturn, String isAdmin);

  public MemberVO getMemberProfileByUserName(String memberUserName);

  public void updateMemberProfile(MemberVO member, String lastModifiedBy);

  public void updateMemberEmail(String email, String memberUserName, String lastModifiedBy);

  public String getMemberPasswordByUserName(String memberUserName);

  public void updateMemberSignature(String signature, String memberUserName, String lastModifiedBy);

  public void updateMemberAvatar(String avatar, String memberUserName, String lastModifiedBy);
  
  public void deleteMemberAvatar(String avatar, String memberUserName, String lastModifiedBy);

  public List<MemberVO>  getMembersToAdminister();

  public void activateMemberByUserName(String memberUserName, String lastModifiedBy);

  public void deactivateMemberByUserName(String memberUserName, String lastModifiedBy);

  public void lockMemberByUserName(String memberUserName, String lastModifiedBy);

  public List<MemberVO>  adminGetAllMembers(int offset, int rowsToReturn);
  
  public List<MemberVO> adminGetMembersByStatus(int offset, int rowsToReturn, String status);

  public boolean isAccountActivatedByMemberId(String memberId);
  
  public String getMemberEmailByMemberId(String memberId);

  public void updateMemberUserName(String memberTempUserName,
                                 String memberUserName, String memberEmail,
                                 String memberPassword, String lastModifiedBy);

  public void deleteMemberByUserName(String memberUserName);

    public List<MemberVO> getAllStatistics();

  public List<MemberVO>  adminGetOneMembers(String memberUsername);

  public List<MemberVO>  getLatestMembers(int rowsToReturn)  ;

  public String getMemberStatusByUserName(String memberUserName);

  public List<MemberVO>  getBirthdayListOfTheMonth(String month);
  
  public List<MemberVO> getTodayBirthdayMembers(String month, String day);

  public List<MemberVO>  searchAvatar(int offset, int rowsToReturn, String isAdmin);

  public List<MemberVO>  getAllMembers();
  
  
  
  // Admin USER Section

  public MemberVO getMember(String memberId);

  public void addUser(MemberVO member);

  public void updateUser(MemberVO member);

  public void deleteUser(String memberId) throws MyAlumniException;
  
  public void resetPassword(String memberUserName, String memberPassword, String lastModifiedBy);

  public List<LoginHistoryVO> getAccessTrailsByUserName(String memebrId);
  
  public List<LoginHistoryVO> getAllAccessTrails();  
  
  public List<MemberVO> getRoleUsers(String isAdmin);
  
  public void updateRoleUsers(String isAdmin, String[] memberIds, String lastModifiedBy);
  
  public void saveAll(List<MemberVO> members);
  
  public List<MemberVO> getUserNameStartingWith(String alpha, String isAdmin); 
  
  public List<MemberVO> getEmailStartingWith(String alpha, String isAdmin);
  
  public List<String> genericAjaxSearch(String searchWord, String searchCriteria);
  
  public List<MemberVO> genericAjaxSearchObjects(String searchWord, String searchCriteria);
  
  
  
  //******************************************
  // IPHONE MEMBER SERVICE
  //******************************************
  
  public List<MemberVO> getLastNameStartingWith(String alpha, String isAdmin);
  
  public List<MemberVO> getFirstNameStartingWith(String alpha, String isAdmin);
  
  public List<MemberVO> getGenderBy(String alpha, String isAdmin);
  
  public List<MemberVO> getMembersInDorm(String dormitoryId, String isAdmin);
  
  public List<XlatDetailVO> getAllActiveDormitory();    
    
}
