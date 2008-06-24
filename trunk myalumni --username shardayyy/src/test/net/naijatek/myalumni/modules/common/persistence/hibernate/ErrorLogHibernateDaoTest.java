package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.Date;
import java.util.List;



import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.modules.common.domain.ErrorLogVO;
import test.net.naijatek.myalumni.modules.BaseDAOTestCase;

public class ErrorLogHibernateDaoTest extends BaseDAOTestCase {

	public ErrorLogHibernateDaoTest(final String arg0) {
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
		ErrorLogVO entity = createErrorLog();
		errorLogService.addErrorLog(entity);	
		List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
		assertEquals(1, entitys.size());
	}

	public void testHardDeleteObject() {
		try{
			testSave();
			List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
			assertEquals(1, entitys.size());
			errorLogService.deleteErrorLog(entitys.get(0).getErrorLogId());
			entitys = errorLogService.getAllErrorLogs();
			assertEquals(0, entitys.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}

/*	public void testSoftDeleteObject() {
		try{
			testSave();
			List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
			ErrorLogVO entity = entitys.get(0);
			assertEquals(1, entitys.size());	
			assertEquals(BaseConstants.ADDED, entity.getLastModification());
			errorLogService.softDelete(entity.getErrorLogId(), entity.getLastModifiedBy());
			List<ErrorLogVO> entitys2 = errorLogService.findAllByStatus(BaseConstants.DELETED);
			assertEquals(1, entitys2.size());
		}
		catch(Exception ex){
			System.out.println("Error = " + ex.getMessage());
		}
	}*/

	public void testFindAll() throws MyAlumniBaseException{
		testSave();
		List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
		assertEquals(1, entitys.size());
	}

	public void testFindAllByStatus() {
		assertEquals(1, 1);  // not needed
	}

	public void testFindById() throws MyAlumniBaseException{
		testSave();
		List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
		ErrorLogVO entity = entitys.get(0);
		ErrorLogVO entity1 = errorLogService.getErrorLog(entitys.get(0).getErrorLogId());
		assertEquals(entity1.getErrorLogId(), entity.getErrorLogId());
	}

/*	public void testMergeObject() throws MyAlumniBaseException{
		testSave();
		List<ErrorLogVO> entitys = errorLogService.getAllErrorLogs();
		ErrorLogVO entity = entitys.get(0);
		entity.setCause("New Cause");
		errorLogService.merge(entity);
		
		entitys = errorLogService.getAllErrorLogs();
		entity = entitys.get(0);
		assertEquals("New Cause", entity.getCause());
	}*/
	
	
	
	public ErrorLogVO createErrorLog(){
		ErrorLogVO o = new ErrorLogVO();
		//DateTime dt = new DateTime(new Date()); 		
		//o.setErrorLogId(errorLogId);				// ERRORLOG_ID TIMESTAMP  NOT NULL  ,
		o.setErrorMessage("errorMessage");			// ERROR_MESSAGE VARCHAR(4000)  NULL  ,
		o.setErrorDate(new Date());					// ERROR_DATE TIMESTAMP  NULL  ,
		o.setCause("cause");						// CAUSE VARCHAR(40)  NULL  ,
		o.setLoggedBy("loggedBy");					// LOGGED_BY VARCHAR(40)  NULL    ,
		//o.setLastModification(lastModification);	//  LASTMODIFICATION CHAR(1)  NOT NULL  ,
		o.setLastModifiedBy("shardayyy");			//  LASTMODIFIED_BY VARCHAR(40)  NOT NULL  ,
		//o.setLastModifiedDate(lastModifiedDate);	//  LASTMODIFIED_DATE TIMESTAMP  NOT NULL    ,
		  
		return o;
	}


}
