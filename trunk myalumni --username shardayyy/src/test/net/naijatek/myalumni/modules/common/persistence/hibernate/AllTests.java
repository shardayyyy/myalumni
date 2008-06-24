package test.net.naijatek.myalumni.modules.common.persistence.hibernate;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for test.net.naijatek.myalumni.modules.common.persistence.hibernate");
		//$JUnit-BEGIN$
		suite.addTestSuite(ErrorLogHibernateDaoTest.class);
		suite.addTestSuite(MemberHibernateDaoTest.class);
		suite.addTestSuite(MessageFolderHibernateDaoTest.class);
		suite.addTestSuite(ClassNewsHibernateDaoTest.class);
		suite.addTestSuite(PrivateMessageHibernateDaoTest.class);
		//$JUnit-END$
		return suite;
	}

}
