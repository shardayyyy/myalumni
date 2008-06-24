package test.net.naijatek.myalumni.modules;


import junit.framework.TestCase;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemTasksDao;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.modules.common.service.IErrorLogService;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IMessageFolderService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemTaskService;
import net.naijatek.myalumni.modules.common.service.IXlatGroupService;
import net.naijatek.myalumni.modules.common.service.IXlatService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class BaseDAOTestCase extends TestCase {
	private static Logger logger = Logger.getLogger(BaseDAOTestCase.class);
	
	//protected ClassPathXmlApplicationContext ctx;
	protected ApplicationContext ctx;
	
//	public static final String[] files = {
//		
//		"test/core-spring.xml", "test/datasource.xml", "test/local-transaction.xml", "test/session-factory.xml",
//		"test/employee-module-beans.xml",
//		"test/admin-module-beans.xml",
//		"test/client-module-beans.xml" /*"test/patient-module-beans.xml", "test/report-module-beans.xml",
//		"test/billing-module-beans.xml"*/
//	};
	
	public static final String[] files2 = {
    	"web/WEB-INF/spring/local-transaction.xml","web/WEB-INF/spring/session-factory.xml",
    	"web/WEB-INF/spring/datasource.xml","web/WEB-INF/spring/spring-hibernate-beans.xml"
	};	
//    	,
//    	"web/WEB-INF/admin/admin-module-actions.xml","web/WEB-INF/admin/struts-admin-config.xml",
//    	"web/WEB-INF/admin/validation.xml","web/WEB-INF/admin/validator-rules.xml",
//    	"web/WEB-INF/member/member-module-actions.xml","web/WEB-INF/member/struts-member-config.xml",
//    	"web/WEB-INF/member/validation.xml","web/WEB-INF/member/validator-rules.xml"
//	};
	
	
	//the various services
	protected IMemberService memberService;
	protected IClassNewsService classNewsService;
	protected IMessageFolderService messageFolderService;
	protected IPrivateMessageService pmService;
	protected ISystemTaskService sysTaskService;
	protected IXlatService sysLookupService;
	protected IXlatGroupService sysGroupService;
	protected IErrorLogService errorLogService;
	
	protected SystemTasksDao sysDao;
	

	
	public void init(){
		logger.debug("setting up TEST environment...");
		//ctx = new ClassPathXmlApplicationContext(files2);
		ctx = new FileSystemXmlApplicationContext(files2);
		
		//
		//SERVICES
		//
//		aaService = (delete_IAnnouncementService) ctx.getBean("aaService");
//		memberService = (IMemberServiceOld) ctx.getBean("memberService");
//		classNewsService = (IClassNewsService) ctx.getBean("classNewsService");
//		messageFolderService = (IMessageFolderService) ctx.getBean("mfService");
//		pmService = (IPrivateMessageService) ctx.getBean("pmService");
		//sysTaskService = (ISystemTaskService) ctx.getBean("sysTaskService");
//		orgService = (IOrganizationService) ctx.getBean("orgService");
//		sysLookupService = (IXlatService) ctx.getBean("sysLookupService");
//		sysGroupService = (ISystemGroupService) ctx.getBean("sysGroupService");
//		errorLogService = (IErrorLogService) ctx.getBean("errorLogService");
		
		sysDao = (SystemTasksDao) ctx.getBean("systemTaskDao");
		
		
		assertNotNull(ctx);
		
		
		//Services
//		assertNotNull(aaService);
//		assertNotNull(memberService);
//		assertNotNull(classNewsService);
//		assertNotNull(messageFolderService);
//		assertNotNull(pmService);
//		assertNotNull(sysTaskService);
//		assertNotNull(orgService);
//		assertNotNull(sysLookupService);
//		assertNotNull(sysGroupService);
//		assertNotNull(errorLogService);
		
		
/*		Session s = SessionManager.getInstance().getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("delete from MYALUMNI_PRIVATE_MESSAGE_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_MESSAGE_FOLDER_TBL").executeUpdate();
//		s.createSQLQuery("delete from MYALUMNI_SYSTEM_LOOKUP_TBL").executeUpdate();
//		s.createSQLQuery("delete from MYALUMNI_LOGINHISTORY_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_ERRORLOG_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_CLASS_NEWS_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_MEMBERS_TBL").executeUpdate();
//		s.createSQLQuery("delete from MYALUMNI_SYSCONFIG_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_SCROLL_TBL").executeUpdate();
//		s.createSQLQuery("delete from MYALUMNI_SYSTEM_GROUP_TBL").executeUpdate();
		s.createSQLQuery("delete from MYALUMNI_ORGANIZATION_TBL").executeUpdate();
		s.getTransaction().commit();
		s.close();*/
	}
	

	
	public BaseDAOTestCase(final String arg0) {
		super(arg0);
	}
}
