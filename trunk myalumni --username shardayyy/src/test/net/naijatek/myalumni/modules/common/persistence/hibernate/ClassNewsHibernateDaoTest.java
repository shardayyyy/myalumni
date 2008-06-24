package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.ClassNewsVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.util.BaseConstants;


import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

public class ClassNewsHibernateDaoTest extends BaseDAOTestCase {

	public ClassNewsHibernateDaoTest(final String arg0) {
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
		ClassNewsVO entity = createClassNews();
		classNewsService.save(entity);	
		List<ClassNewsVO> entitys = classNewsService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testSoftDeleteObject() {
		try{
			testSave();
			List<ClassNewsVO> entitys = classNewsService.findAll();
			ClassNewsVO entity = entitys.get(0);
			assertEquals(1, entitys.size());	
			assertEquals(BaseConstants.ADDED, entity.getLastModification());
			classNewsService.softDelete(entity.getClassNewsId(), entity.getLastModifiedBy());
			List<ClassNewsVO> entitys2 = classNewsService.findAllByStatus(BaseConstants.SOFT_DELETED);
			assertEquals(1, entitys2.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testHardDeleteObject() {
		try{
			testSave();
			List<ClassNewsVO> entitys = classNewsService.findAll();
			assertEquals(1, entitys.size());
			classNewsService.hardDelete(entitys.get(0).getClassNewsId());
			entitys = classNewsService.findAll();
			assertEquals(0, entitys.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testFindAll() throws MyAlumniBaseException{
		testSave();
		List<ClassNewsVO> entitys = classNewsService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testFindAllByStatus() throws MyAlumniBaseException{
		testSave();
		List<ClassNewsVO> entitys = classNewsService.findAllByStatus(BaseConstants.ADDED);
		assertEquals(1, entitys.size());
	}

	public void testFindById() throws MyAlumniBaseException{
		testSave();
		List<ClassNewsVO> entitys = classNewsService.findAll();
		ClassNewsVO entity = entitys.get(0);
		ClassNewsVO entity1 = classNewsService.findById(entitys.get(0).getClassNewsId());
		assertEquals(entity1.getClassNewsId(), entity.getClassNewsId());
	}

	public void testMergeObject() throws MyAlumniBaseException{
		testSave();
		List<ClassNewsVO> entitys = classNewsService.findAll();
		ClassNewsVO entity = entitys.get(0);
		entity.setNews("This is the new Updated");
		classNewsService.merge(entity);
		
		entitys = classNewsService.findAll();
		entity = entitys.get(0);
		assertEquals("This is the new Updated", entity.getNews());
	}
	
	
	public ClassNewsVO createClassNews(){
		ClassNewsVO o = new ClassNewsVO();
		//o.setClassNewsId(classNewsId);		//  CLASS_NEWS_ID VARCHAR(40)  NOT NULL  ,
		o.setSubject("subject");				//  SUBJECT VARCHAR(50)  NOT NULL  ,
		o.setNews("news");						//  NEWS VARCHAR(4000)  NOT NULL  ,
		o.setToClassYear(2007);					//  CLASS_YEAR INTEGER  NOT NULL  ,
		o.setAuthorId("555555555");					//  AUTHOR_ID VARCHAR(40)  NOT NULL  ,
		o.setAuthor(createMember());
		//  LASTMODIFICATION CHAR(1)  NOT NULL,
		o.setLastModifiedBy("shardayyy");		//  LASTMODIFIED_BY VARCHAR(40)  NOT NULL  ,
		//  LASTMODIFIED_DATE TIMESTAMP  NOT NULL    , 
		return o;
	}
	
	private MemberVO createMember(){
		MemberVO mem = new MemberVO();
		
		
		mem.setMemberUserName("shardayyy"); 									//  USER_NAME VARCHAR(40)  NOT NULL  ,
		mem.setMemberId("555555555"); 											//  MEMBER_ID VARCHAR(40)  NOT NULL  ,
		mem.setMemberStatus(BaseConstants.ACCOUNT_ACTIVE); 				//  MEMBER_STATUS VARCHAR(20)  NOT NULL  ,
		mem.setMemberPassword("memberPassword"); 						//  MEMBER_PASSWORD VARCHAR(40)  NOT NULL  ,
		mem.setEmail(""); 												//  EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setFirstIPAddress(""); 										//  FIRST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setLastIPAddress(""); 										//  LAST_IP_ADDRESS VARCHAR(40)  NOT NULL  ,
		mem.setCreationDate(new Date()); 								//  CREATION_DATE TIMESTAMP  NOT NULL  ,
		mem.setLastLogonDate(new Date()); 								//  LAST_LOGON_DATE TIMESTAMP  NOT NULL  ,
		mem.setActivationCode("lkjshasahlajshflasjf7799hasdf"); 		//  ACTIVATION_CODE VARCHAR(40)  NULL  ,
		mem.setSignature("This is the signature"); 						//  SIGNATURE VARCHAR(70)  NULL  ,
		mem.setTitleId("2222222222"); 											//  TITLE VARCHAR(30)  NOT NULL  ,
		mem.setAvatar("pic.jpg"); 										//  AVATAR VARCHAR(200)  NULL  ,
		mem.setFirstName("Folashade"); 									//  FIRST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setLastName("Adeyosoye") ; 									//  LAST_NAME VARCHAR(70)  NOT NULL  ,
		mem.setMaidenName(null); 										//  MAIDEN_NAME VARCHAR(70)  NULL  ,
		mem.setNickName("shardayyy");  									//  NICK_NAME VARCHAR(70)  NULL  ,
		mem.setGender(BaseConstants.GENDER_MALE); 						//  GENDER VARCHAR(6)  NOT NULL  ,
		mem.setAddress("555 Silicon Valley"); 							//  ADDRESS VARCHAR(200)  NULL  ,
		mem.setFirstEmail("shardayyy@naijatek.com"); 					//  FIRST_EMAIL VARCHAR(60)  NOT NULL  ,
		mem.setCountryId("3333333333"); 											//  COUNTRY VARCHAR(70)  NOT NULL  ,
		mem.setPhone("301.555.1212"); 									//  PHONE VARCHAR(40)  NULL  ,
		mem.setCareerId("1111111111"); 								//  OCCUPATION VARCHAR(100)  NULL  ,
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

}
