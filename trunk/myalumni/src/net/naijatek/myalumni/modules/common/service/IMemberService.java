package net.naijatek.myalumni.modules.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.naijatek.myalumni.framework.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.framework.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.StatisticsVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;


/**
 * This is an interface to the Member service implementation.
 *
 * @author Folashade Adeyosoye
 * @version 1.0
 */
public interface IMemberService  extends BaseCrudService<MemberVO, String>{



    public boolean isMemberAvailableByEmail(String email, String exceptmemberId);

    public boolean lockMemberAccount(String username, String lastModifiedBy);
    
    public boolean unLockMemberAccount(String username, String lastModifiedBy);

    public boolean isMemberAvailableByUserName(String username);

    public void createMember(MemberVO member, HttpServletRequest request) throws MyAlumniBaseException;

    public void updateMemberPassword(String memberUserName, 
                                     String memberPassword, String lastModifiedBy);

    public MemberVO getMemberProfileByUserName(String memberUserName);
    
    public MemberVO getMemberProfileByMemberId(String memberId);

    public void updateMemberProfile(MemberVO member, String lastModifiedBy);

    public void updateMemberEmail(String email, String memberUserName, String lastModifiedBy);

    public String getMemberPasswordByUserName(String memberUserName);

    public void updateMemberSignature(String signature, String memberUserName, String lastModifiedBy);

    public void updateMemberAvatar(String avatar, String memberUserName, String lastModifiedBy);
    
    public void deleteMemberAvatar(String avatar, String memberUserName, String lastModifiedBy);

    public List<MemberVO> getLatestMembers(int rowsToReturn);
    
    public List<MemberVO> getBirthdayListOfTheMonth(String month);
    
    public List<MemberVO> getTodayBirthdayMembers(String month, String day);

    public List<MemberVO> searchAvatar(int offset, int rowToReturn, String isAdmin);

    public String getMemberStatusByUserName(String memberUserName);

    public StatisticsVO getAllStatistics();

    public boolean isAccountActivatedByMemberId(String memberUserName);
    
    public String getMemberEmailByMemberId(String memberId);

    public void deleteMemberByUserName(String memberUserName);

    public List<MemberVO> getAllMembers();

    public List<MemberVO> searchFullSearchOnMembers(String firstName, String lastName, 
                                          String dorm, String gender, 
                                          String yearIn, String yearOut, 
                                          String marriageName, String nickName, 
                                          String maidenName, 
                                          String partialNameSearch, 
                                          int offset, int rowsToReturn, 
                                          String isAdmin);

    public List<MemberVO> searchGender(String gender, int offset, int rowsToReturn, 
                             String isAdmin);

    public List<MemberVO> searchMaidenName(String maidenName, String partialNameSearch, 
                                 int offset, int rowsToReturn, String isAdmin);

    public List<MemberVO> searchFirstName(String firstName, String partialNameSearch, 
                                int offset, int rowsToReturn, String isAdmin);

    public List<MemberVO> searchLastName(String lastName, String partialNameSearch, 
                               int offset, int rowsToReturn, String isAdmin);

    public List<MemberVO> searchUserName(String userName, String partialNameSearch, 
                               int offset, int rowsToReturn, String isAdmin);

    public List<MemberVO> searchEmail(String email, String partialNameSearch, 
            int offset, int rowsToReturn, String isAdmin);
    
    public List<MemberVO> searchDormitory(String dorm, int offset, int rowsToReturn, 
                            String isAdmin);


    public List<MemberVO> searchYearIn(String yearIn, int offset, int rowsToReturn, 
                             String isAdmin);

    public List<MemberVO> searchYearOut(String yearOut, int offset, int rowsToReturn, 
                              String isAdmin);

    public List<MemberVO> searchNickName(String nickname, String partialNameSearch, 
                               int offset, int rowsToReturn, String isAdmin);

    public void activateMemberByUserName(String memberUserName, String lastModifiedBy);

    public void deactivateMemberByUserName(String memberUserName, String lastModifiedBy);
    
    public List<MemberVO> adminGetMembersByStatus(int offset, int rowsToReturn, String status);

    public void lockMemberByUserName(String memberUserName, String lastModifiedBy);

    public List<MemberVO> getMemberToAdminister();

    public List<MemberVO> adminGetAllMembers(int offset, int rowsToReturn);

    public List<MemberVO> adminGetOneMembersByUserName(String memberUsername);

  
    
    //******************************************
    // USER SERVICE
    //******************************************

    public MemberVO getUser(String id);

    public void addUser(MemberVO user) throws DuplicateMemberException, DuplicateEmailException;

    public void updateUser(MemberVO user) throws DuplicateEmailException;

    public void deleteUser(String id) throws MyAlumniException;
    
    public MemberVO getUserWithRolesAndAccessLogs(String id);
    
    public void resetPassword(String memberId, String langId, String lastModifiedBy) throws MyAlumniException;
    
    public List<MemberVO> filterUsersByAlphabelt(String alphabelt, String searchType, String isAdmin);
    
    public List<LoginHistoryVO> getAccessTrailsByUserName(String userId);
    
    public List<LoginHistoryVO> getAllAccessTrails();
    
    public List<MemberVO> getRoleUsers(String roldId);
    
    public void updateRoleUsers(String roleId, String[] memberIds, String lastModifiedBy);
    
    public void saveAll(List<MemberVO> members);
    

    //******************************************
    // IPHONE MEMBER SERVICE
    //******************************************
    
    public List<MemberVO> getLastNameStartingWith(String alpha, String isAdmin);
    
    public List<MemberVO> getFirstNameStartingWith(String alpha, String isAdmin);
    
    public List<MemberVO> getGenderBy(String alpha, String isAdmin);
    
    public List<MemberVO> getMembersInDorm(String dormitoryId, String isAdmin);
    
    public List<XlatDetailVO> getAllActiveDormitory();    
   

    
}
