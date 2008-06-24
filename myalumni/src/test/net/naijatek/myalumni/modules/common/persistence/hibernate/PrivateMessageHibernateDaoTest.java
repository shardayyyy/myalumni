package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.util.BaseConstants;


import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

public class PrivateMessageHibernateDaoTest extends BaseDAOTestCase {

	public PrivateMessageHibernateDaoTest(final String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		init();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSave() throws MyAlumniBaseException{
		MemberVO mem1 = createMember("111111", "Mike", "Jones", "mjones");
		MemberVO mem2 = createMember("2222222", "Veronica", "Stevens", "vstevens");
		
		PrivateMessageVO entity = createPrivateMessage(mem1, mem2);
		pmService.save(entity);	
		List<PrivateMessageVO> entitys = pmService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testSoftDeleteObject() {
		try{
			testSave();
			List<PrivateMessageVO> entitys = pmService.findAll();
			PrivateMessageVO entity = entitys.get(0);
			assertEquals(1, entitys.size());	
			assertEquals(BaseConstants.ADDED, entity.getLastModification());
			pmService.softDelete(entity.getMessageId(), entity.getLastModifiedBy());
			List<PrivateMessageVO> entitys2 = pmService.findAllByStatus(BaseConstants.SOFT_DELETED);
			assertEquals(1, entitys2.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testHardDeleteObject() {
		try{
			testSave();
			List<PrivateMessageVO> entitys = pmService.findAll();
			assertEquals(1, entitys.size());
			pmService.hardDelete(entitys.get(0).getMessageId());
			entitys = pmService.findAll();
			assertEquals(0, entitys.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testFindAll() throws MyAlumniBaseException{
		testSave();
		List<PrivateMessageVO> entitys = pmService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testFindAllByStatus() {
		//testSave();
		//List<PrivateMessageVO> entitys = pmService.findAllByStatus(BaseConstants.ADDED);
		//assertEquals(1, entitys.size());
	}

	public void testFindById() throws MyAlumniBaseException{
		testSave();
		List<PrivateMessageVO> entitys = pmService.findAll();
		PrivateMessageVO entity = entitys.get(0);
		PrivateMessageVO entity1 = pmService.findById(entitys.get(0).getMessageId());
		assertEquals(entity1.getMessageId(), entity.getMessageId());
	}

	public void testMergeObject() throws MyAlumniBaseException{
		testSave();
		List<PrivateMessageVO> entitys = pmService.findAll();
		PrivateMessageVO entity = entitys.get(0);
		entity.setPriority(BaseConstants.PRIORITY_LOW);
		pmService.merge(entity);
		
		List<PrivateMessageVO> entitys2 = pmService.findAll();
		PrivateMessageVO entity2 = entitys2.get(0);
		assertEquals(BaseConstants.PRIORITY_LOW, entity2.getPriority());
	}
	
	
	public PrivateMessageVO createPrivateMessage(final MemberVO mem1, final MemberVO mem2){
		PrivateMessageVO o = new PrivateMessageVO();
		
		//o.setMessageId(messageId);						//  MESSAGE_ID VARCHAR(40)  NOT NULL  ,
		o.setMessageFromUserId(mem1.getMemberId());		//  MESSAGE_FROM_USER_ID VARCHAR(40)  NOT NULL  ,
		//o.setMessageToMember(mem1);
		o.setMessageToUserId(mem2.getMemberId());	//  MESSAGE_TO_USER_ID VARCHAR(40)  NOT NULL  ,
		//o.setMessageToMember(mem2);
		
		o.setFolderName(BaseConstants.FOLDER_INBOX);					//  FOLDER_NAME VARCHAR(30)  NOT NULL  ,
		o.setPriority(BaseConstants.PRIORITY_HIGH);						//  PRIORITY CHAR(1)  NOT NULL  ,
		o.setSubject("Hello There");							//  SUBJECT VARCHAR(60)  NOT NULL  ,
		o.setMessageDate(new Date());					//  MESSAGE_DATE TIMESTAMP  NOT NULL  ,
		o.setIpAddress("123.456.789.000"); 						//  IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		o.setMessageText("This is the message, where have you been all my life");					//  MESSAGE_TEXT VARCHAR(4000)  NOT NULL  ,
		o.setMessageStatus(BaseConstants.PM_STATUS_NEW);				//  MESSAGE_STATUS VARCHAR(7)  NOT NULL    ,
		o.setLastModifiedBy("lastModifiedBy");		//  LASTMODIFIED_BY VARCHAR(40)  NOT NULL  ,
		//o.setLastModification();		//    LASTMODIFICATION CHAR(1)  NOT NULL  ,
		//o.setLastModifiedDate(new Date());		//    LASTMODIFIED_DATE TIMESTAMP  NOT NULL    ,
		return o;
	}
	
	private MemberVO createMember(final String memberId, final String firstName, final String lastName, final String userName){
		MemberVO mem = new MemberVO();
		
		
		mem.setMemberUserName(userName); 									//  USER_NAME VARCHAR(40)  NOT NULL  ,
		mem.setMemberId(memberId); 											//  MEMBER_ID VARCHAR(40)  NOT NULL  ,
		mem.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE); 		//  MEMBER_STATUS VARCHAR(20)  NOT NULL  ,
		mem.setMemberPassword("memberPassword"); 						//  MEMBER_PASSWORD VARCHAR(40)  NOT NULL  ,
		mem.setEmail(""); 												//  EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setFirstIPAddress(""); 										//  FIRST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setLastIPAddress(""); 										//  LAST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setCreationDate(new Date()); 								//  CREATION_DATE TIMESTAMP  NOT NULL  ,
		mem.setLastLogonDate(new Date()); 								//  LAST_LOGON_DATE TIMESTAMP  NOT NULL  ,
		mem.setActivationCode("lkjshasahlajshflasjf7799hasdf"); 		//  ACTIVATION_CODE VARCHAR(40)  NULL  ,
		mem.setSignature("This is the signature"); 						//  SIGNATURE VARCHAR(70)  NULL  ,
		mem.setTitleId("rtreterter"); 											//  TITLE VARCHAR(30)  NOT NULL  ,
		mem.setAvatar("pic.jpg"); 										//  AVATAR VARCHAR(200)  NULL  ,
		mem.setFirstName(firstName); 									//  FIRST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setLastName(lastName) ; 									//  LAST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setMaidenName(null); 										//  MAIDEN_NAME VARCHAR(70)  NULL  ,
		mem.setNickName("shardayyy");  									//  NICK_NAME VARCHAR(70)  NULL  ,
		mem.setGender(BaseConstants.GENDER_MALE); 						//  GENDER VARCHAR(6)  NOT NULL  ,
		mem.setAddress("555 Silicon Valley"); 							//  ADDRESS VARCHAR(200)  NULL  ,
		mem.setFirstEmail("shardayyy@naijatek.com"); 					//  FIRST_EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setCountryId("lulklkhgkhl"); 											//  COUNTRY VARCHAR(70)  NOT NULL  ,
		mem.setPhone("301.555.1212"); 									//  PHONE VARCHAR(40)  NULL  ,
		mem.setCareerId("retertete5"); 								//  OCCUPATION VARCHAR(100)  NULL  ,
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
	
	
/*	
	public void testContactMail() {
		fail("Not yet implemented");
	}

	public void testCopyMeOnContactMail() {
		fail("Not yet implemented");
	}

	public void testCopyMeOnReplyMail() {
		fail("Not yet implemented");
	}

	public void testDeleteMail() {
		fail("Not yet implemented");
	}

	public void testEmptyMail() {
		fail("Not yet implemented");
	}

	public void testGetAllPrivateMessagesId() {
		fail("Not yet implemented");
	}

	public void testGetMailCountByUserName() {
		fail("Not yet implemented");
	}

	public void testGetMailListByUserName() {
		fail("Not yet implemented");
	}

	public void testGetPrivateMessagesStatusById() {
		fail("Not yet implemented");
	}

	public void testGetQuotaRatioByUserName() {
		fail("Not yet implemented");
	}

	public void testMoveMail() {
		fail("Not yet implemented");
	}

	public void testPrepareReply() {
		fail("Not yet implemented");
	}

	public void testReadOneMailByUserName() {
		fail("Not yet implemented");
	}

	public void testReplyMail() {
		fail("Not yet implemented");
	}

	public void testUpdatePrivateMessageUserNames() {
		fail("Not yet implemented");
	}*/

}
