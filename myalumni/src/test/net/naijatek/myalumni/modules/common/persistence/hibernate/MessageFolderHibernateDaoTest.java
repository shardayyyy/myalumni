package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.MessageFolderVO;
import net.naijatek.myalumni.util.BaseConstants;
import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

public class MessageFolderHibernateDaoTest extends BaseDAOTestCase {

	public MessageFolderHibernateDaoTest(final String arg0) {
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
		MessageFolderVO entity = createMessageFolder();
		messageFolderService.save(entity);	
		List<MessageFolderVO> entitys = messageFolderService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testHardDeleteObject() {
		try{
			testSave();
			List<MessageFolderVO> entitys = messageFolderService.findAll();
			assertEquals(1, entitys.size());
			messageFolderService.hardDelete(entitys.get(0).getMessageFolderId());
			entitys = messageFolderService.findAll();
			assertEquals(0, entitys.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testSoftDeleteObject() {
		try{
			testSave();
			List<MessageFolderVO> entitys = messageFolderService.findAll();
			MessageFolderVO entity = entitys.get(0);
			assertEquals(1, entitys.size());	
			assertEquals(BaseConstants.ADDED, entity.getLastModification());
			messageFolderService.softDelete(entity.getMessageFolderId(), entity.getLastModifiedBy());
			List<MessageFolderVO> entitys2 = messageFolderService.findAllByStatus(BaseConstants.SOFT_DELETED);
			assertEquals(1, entitys2.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

	public void testFindAll() throws MyAlumniBaseException{
		testSave();
		List<MessageFolderVO> entitys = messageFolderService.findAll();
		assertEquals(1, entitys.size());
	}

	public void testFindAllByStatus() throws MyAlumniBaseException{
		testSave();
		List<MessageFolderVO> entitys = messageFolderService.findAllByStatus(BaseConstants.ADDED);
		assertEquals(1, entitys.size());
	}

	public void testFindById() throws MyAlumniBaseException{
		testSave();
		List<MessageFolderVO> entitys = messageFolderService.findAll();
		MessageFolderVO entity = entitys.get(0);
		MessageFolderVO entity1 = messageFolderService.findById(entitys.get(0).getMessageFolderId());
		assertEquals(entity1.getMessageFolderId(), entity.getMessageFolderId());
	}

	public void testMergeObject() throws MyAlumniBaseException{
		testSave();
		List<MessageFolderVO> entitys = messageFolderService.findAll();
		MessageFolderVO entity = entitys.get(0);
		entity.setFolderOrder(99);
		messageFolderService.merge(entity);
		
		entitys = messageFolderService.findAll();
		entity = entitys.get(0);
		assertEquals(99, entity.getFolderOrder());
	}

	private MessageFolderVO createMessageFolder(){
		MessageFolderVO o = new MessageFolderVO();
		
		//o.setMessageFolderId(messageFolderId);		//  MESSAGE_FOLDER_ID VARCHAR(40)  NOT NULL  ,
		o.setMemberId("userName");						//  USER_NAME VARCHAR(40)  NOT NULL  ,
		o.setFolderName("folderName");					//  FOLDER_NAME VARCHAR(30)  NOT NULL  ,
		o.setFolderOrder(1);							//  FOLDER_ORDER INTEGER  NOT NULL  ,
		o.setFolderCreationDate(new Date());			//  FOLDER_CREATION_DATE TIMESTAMP  NOT NULL  ,
		o.setFolderModificationDate(new Date());		//  FOLDER_MODIFICATION_DATE TIMESTAMP  NOT NULL    ,
		//o.setLastModification(lastModification);  //  LASTMODIFICATION CHAR(1)  NOT NULL  ,
		o.setLastModifiedBy("lastModifiedBy");		//  LASTMODIFIED_BY VARCHAR(40)  NOT NULL  ,
		//o.setLastModifiedDate(new Date());			//  LASTMODIFIED_DATE TIMESTAMP  NOT NULL    ,
		return  o;
	}
	
	
	
/*	public void testCreateMemberFolder() {
		fail("Not yet implemented");
	}

	public void testCreateMemberMessageFolders() {
		fail("Not yet implemented");
	}

	public void testDeleteMemberFolder() {
		fail("Not yet implemented");
	}

	public void testDeleteMemberMessageFolders() {
		fail("Not yet implemented");
	}

	public void testFindByPrimaryKey() {
		fail("Not yet implemented");
	}

	public void testGetAllMailFolderCountByUserName() {
		fail("Not yet implemented");
	}

	public void testGetMailFolderCount() {
		fail("Not yet implemented");
	}

	public void testGetMessageFolder() {
		fail("Not yet implemented");
	}

	public void testGetMessageFoldersForMemberByUserName() {
		fail("Not yet implemented");
	}*/

}
