package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.util.BaseConstants;
import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

/**
 * @author shardayyy
 *
 */
public class MemberHibernateDaoTest extends BaseDAOTestCase {

	/**
	 * @param arg0
	 */
	public MemberHibernateDaoTest(final String arg0) {
		super(arg0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		init();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception{
		super.tearDown();
	}


	
	public void testSave() {
		try{
			MemberVO entity = new MemberVO();
			entity = createMember();
			memberService.save(entity);	
			
			List<MemberVO> mems = memberService.findAll();
			assertEquals(1, mems.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
		
	}



	public void testFindAll() {
		testSave();
		List<MemberVO> mems = memberService.findAll();
		assertEquals(1, mems.size());
	}


	public void testFindAllByStatus() {
		testSave();
		List<MemberVO> mems = memberService.findAllByStatus(BaseConstants.ADDED);
		assertEquals(1, mems.size());
	}


	public void testFindById() {
		testSave();
		List<MemberVO> members = memberService.findAll();
		MemberVO mem = members.get(0);
		MemberVO mem1 = memberService.findById(members.get(0).getMemberId());
		assertEquals(mem1.getMemberId(), mem.getMemberId());
	}


	public void testUpdateMemberVO() {
		testSave();
		List<MemberVO> mems = memberService.findAll();
		MemberVO mem = mems.get(0);
		mem.setGender(BaseConstants.GENDER_FEMALE);
		memberService.merge(mem);
		mems = memberService.findAll();
		MemberVO mem1 = mems.get(0);
		assertEquals(BaseConstants.GENDER_FEMALE, mem1.getGender());
		assertEquals(BaseConstants.UPDATED, mem1.getLastModification());
	}
	
	
	
	public void testSoftDeleteObject() throws Exception{
		testSave();
		List<MemberVO> mems = memberService.findAll();
		MemberVO mem = mems.get(0);
		assertEquals(1, mems.size());	
		assertEquals(BaseConstants.ADDED, mem.getLastModification());
		memberService.softDelete(mem.getMemberId(), mem.getLastModifiedBy());
		List<MemberVO> mems2 = memberService.findAllByStatus(BaseConstants.SOFT_DELETED);
		assertEquals(1, mems2.size());
	}
	
	

	public void testHardDeleteObject() throws Exception{
		testSave();
		List<MemberVO> mems = memberService.findAll();
		assertEquals(1, mems.size());
		memberService.hardDelete(mems.get(0).getMemberId());
		mems = memberService.findAll();
		assertEquals(0, mems.size());
	}
	
	
	
	
	private MemberVO createMember(){
		MemberVO mem = new MemberVO();
		
		
		mem.setMemberUserName("shardayyy"); 									//  USER_NAME VARCHAR(40)  NOT NULL  ,
		//mem.setMemberId(""); 											//  MEMBER_ID VARCHAR(40)  NOT NULL  ,
		mem.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE); 		//  MEMBER_STATUS VARCHAR(20)  NOT NULL  ,
		mem.setMemberPassword("memberPassword"); 						//  MEMBER_PASSWORD VARCHAR(40)  NOT NULL  ,
		mem.setEmail(""); 												//  EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setFirstIPAddress(""); 										//  FIRST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setLastIPAddress(""); 										//  LAST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setCreationDate(new Date()); 								//  CREATION_DATE TIMESTAMP  NOT NULL  ,
		mem.setLastLogonDate(new Date()); 								//  LAST_LOGON_DATE TIMESTAMP  NOT NULL  ,
		mem.setActivationCode("lkjshasahlajshflasjf7799hasdf"); 		//  ACTIVATION_CODE VARCHAR(40)  NULL  ,
		mem.setSignature("This is the signature"); 						//  SIGNATURE VARCHAR(70)  NULL  ,
		mem.setTitleId("333333"); 											//  TITLE VARCHAR(30)  NOT NULL  ,
		mem.setAvatar("pic.jpg"); 										//  AVATAR VARCHAR(200)  NULL  ,
		mem.setFirstName("Folashade"); 									//  FIRST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setLastName("Adeyosoye") ; 									//  LAST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setMaidenName(null); 										//  MAIDEN_NAME VARCHAR(70)  NULL  ,
		mem.setNickName("shardayyy");  									//  NICK_NAME VARCHAR(70)  NULL  ,
		mem.setGender(BaseConstants.GENDER_MALE); 						//  GENDER VARCHAR(6)  NOT NULL  ,
		mem.setAddress("555 Silicon Valley"); 							//  ADDRESS VARCHAR(200)  NULL  ,
		mem.setFirstEmail("shardayyy@naijatek.com"); 					//  FIRST_EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setCountryId("555555555"); 											//  COUNTRY VARCHAR(70)  NOT NULL  ,
		mem.setPhone("301.555.1212"); 									//  PHONE VARCHAR(40)  NULL  ,
		mem.setCareerId("44444444444"); 								//  OCCUPATION VARCHAR(100)  NULL  ,
		mem.setWebsite("http://www.naijatek.com"); 						//  WEBSITE VARCHAR(200)  NULL  ,
		mem.setFavUrl1("http://www.youtube.com"); 						//  FAV_URL_1 VARCHAR(200)  NULL  ,
		mem.setFavUrl2("http://www.goggle.com");; 						//  FAV_URL_2 VARCHAR(200)  NULL  ,
		mem.setYearIn(2001); 											//  YEAR_IN INTEGER  NOT NULL  ,
		mem.setYearOut(2007); 											//  YEAR_OUT INTEGER  NOT NULL  ,
		mem.setIsAdmin(BaseConstants.BOOLEAN_YES); 						//  IS_ADMIN CHAR(1)  NOT NULL  ,
		// @TODO DELETE mem.setHouseColor(""); 							//  DORMITORY VARCHAR(30)  NULL  ,
		mem.setComments("This is user comments"); 						//  COMMENTS VARCHAR(1000)  NULL  ,
		mem.setAdminComments("This is admin comments"); 				//  ADMIN_COMMENTS VARCHAR(1000)  NULL  ,
		mem.setDob(new Date()); 										//  DOB DATE  NULL  ,
		mem.setHideEmail(BaseConstants.BOOLEAN_YES); 					//  HIDE_EMAIL CHAR(1)  NOT NULL  ,
		mem.setHideAddress(BaseConstants.BOOLEAN_NO); 					//  HIDE_ADDRESS CHAR(1)  NOT NULL  ,
		mem.setHidePhone(BaseConstants.BOOLEAN_YES); 					//  HIDE_PHONE CHAR(1)  NOT NULL  ,
		mem.setHideIm(BaseConstants.BOOLEAN_NO); 						//  HIDE_IM CHAR(1)  NOT NULL  ,
		mem.setLastModification(BaseConstants.ADDED); 					//  LASTMODIFICATION VARCHAR(1)  NOT NULL  ,
		mem.setLastModifiedBy("shardayyy"); 							//  LASTMODIFIED_BY VARCHAR(40)  NOT NULL  ,
		mem.setLastModifiedDate(new Date()); 							//  LASTMODIFIED_DATE TIMESTAMP  NOT NULL    ,
		
		return mem;
	}
	
	
	
	
	
/*	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#save(net.naijatek.myalumni.modules.core.common.domain.MemberVO)}.
	 *//*
	public void testSave() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#hardDeleteObject(java.lang.String)}.
	 *//*
	public void testRemove() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#findAll()}.
	 *//*
	public void testFindAll() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#findAllByStatus(java.lang.String)}.
	 *//*
	public void testFindAllActive() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#findById(java.lang.String)}.
	 *//*
	public void testFindById() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#merge(net.naijatek.myalumni.modules.core.common.domain.MemberVO)}.
	 *//*
	public void testMerge() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#activateMemberByUserName(java.lang.String)}.
	 *//*
	public void testActivateMemberByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminCountAllMembers()}.
	 *//*
	public void testAdminCountAllMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminCountMembersByStatus(java.lang.String)}.
	 *//*
	public void testAdminCountMembersByStatus() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminGetAdverSubmitterEmail(java.lang.String)}.
	 *//*
	public void testAdminGetAdverSubmitterEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminGetAllMembers(int, int)}.
	 *//*
	public void testAdminGetAllMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminGetDeactivatedMembers(int, int)}.
	 *//*
	public void testAdminGetDeactivatedMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminGetOneMembers(java.lang.String)}.
	 *//*
	public void testAdminGetOneMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#adminGetUnregisteredMembers(int, int)}.
	 *//*
	public void testAdminGetUnregisteredMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#authenticate(net.naijatek.myalumni.modules.core.common.domain.AuthBeanVO)}.
	 *//*
	public void testAuthenticate() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#authenticateAdmin(net.naijatek.myalumni.modules.core.common.domain.AuthBeanVO)}.
	 *//*
	public void testAuthenticateAdmin() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#createMember(net.naijatek.myalumni.modules.core.common.domain.MemberVO)}.
	 *//*
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#deactivateMemberByUserName(java.lang.String)}.
	 *//*
	public void testDeactivateMemberByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#deleteMemberByUserName(java.lang.String)}.
	 *//*
	public void testDeleteMemberByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getAllMembersUserNames()}.
	 *//*
	public void testGetAllMembersUserNames() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getAllStatistics()}.
	 *//*
	public void testGetAllStatistics() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getBirthdayListOfTheMonth(java.lang.String)}.
	 *//*
	public void testGetBirthdayListOfTheMonth() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getLatestMembers()}.
	 *//*
	public void testGetLatestMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberEmailByUserName(java.lang.String)}.
	 *//*
	public void testGetMemberEmailByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberPasswordByUserName(java.lang.String)}.
	 *//*
	public void testGetMemberPasswordByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberProfileByUserName(java.lang.String)}.
	 *//*
	public void testGetMemberProfileByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberStatusByEmail(java.lang.String)}.
	 *//*
	public void testGetMemberStatusByEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberStatusByUserName(java.lang.String)}.
	 *//*
	public void testGetMemberStatusByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMemberUserNameByEmail(java.lang.String)}.
	 *//*
	public void testGetMemberUserNameByEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getMembersToAdminister()}.
	 *//*
	public void testGetMembersToAdminister() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getUnActivatedMembers()}.
	 *//*
	public void testGetUnActivatedMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#getUnActivatedMembersCount()}.
	 *//*
	public void testGetUnActivatedMembersCount() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#isAccountActivatedByEmail(java.lang.String)}.
	 *//*
	public void testIsAccountActivatedByEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#isAccountActivatedByUserName(java.lang.String)}.
	 *//*
	public void testIsAccountActivatedByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#isActivationCodeCorrect(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testIsActivationCodeCorrect() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#isMemberAvailableByEmail(java.lang.String)}.
	 *//*
	public void testIsMemberAvailableByEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#isMemberAvailableByUserName(java.lang.String)}.
	 *//*
	public void testIsMemberAvailableByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#lockMemberAccount(java.lang.String)}.
	 *//*
	public void testLockMemberAccount() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#lockMemberByUserName(java.lang.String)}.
	 *//*
	public void testLockMemberByUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchAvatar(int, int, java.lang.String)}.
	 *//*
	public void testSearchAvatar() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountAvatar(java.lang.String)}.
	 *//*
	public void testSearchCountAvatar() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountFirstName(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountFirstName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountFullSearchOnMembers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountFullSearchOnMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountGender(java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountGender() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountHouse(java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountHouse() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountLastName(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountLastName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountMaidenName(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountMaidenName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountNickName(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountNickName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountUserName(java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountUserNameStringString() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountUserName(java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountUserNameStringStringString() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountYearIn(java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountYearIn() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchCountYearOut(java.lang.String, java.lang.String)}.
	 *//*
	public void testSearchCountYearOut() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchFirstName(java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchFirstName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchFullSearchOnMembers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchFullSearchOnMembers() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchGender(java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchGender() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchHouse(java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchHouse() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchLastName(java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchLastName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchMaidenName(java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchMaidenName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchNickName(java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchNickName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchUserName(java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchUserName() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchUserNamePartial(java.lang.String, java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchUserNamePartial() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchYearIn(java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchYearIn() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#searchYearOut(java.lang.String, int, int, java.lang.String)}.
	 *//*
	public void testSearchYearOut() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberActivationCode(java.lang.String)}.
	 *//*
	public void testUpdateMemberActivationCode() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberAvatar(java.lang.String, java.lang.String)}.
	 *//*
	public void testUpdateMemberAvatar() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberEmail(java.lang.String, java.lang.String)}.
	 *//*
	public void testUpdateMemberEmail() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberPassword(java.lang.String, java.lang.String)}.
	 *//*
	public void testUpdateMemberPassword() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberProfile(net.naijatek.myalumni.modules.core.common.domain.MemberVO, net.naijatek.myalumni.modules.core.common.domain.AuthBeanVO)}.
	 *//*
	public void testUpdateMemberProfile() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberSignature(java.lang.String, java.lang.String)}.
	 *//*
	public void testUpdateMemberSignature() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link net.naijatek.myalumni.modules.core.common.persistence.hibernate.MemberHibernateDao#updateMemberUserName(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 *//*
	public void testUpdateMemberUserName() {
		fail("Not yet implemented");
	}*/

}
