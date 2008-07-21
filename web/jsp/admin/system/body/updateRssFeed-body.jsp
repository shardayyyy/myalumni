<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<html:form action="/admin/updateRssFeed">
        <html:hidden property="action" value="updateRssFeed"/>
        <html:hidden property="systemConfigId"/>


	<c:set var="actionMode" value="update" scope="request"/>
	<tiles:insert name="/jsp/admin/system/common/rssFeed-common.jsp"/>

</html:form>


