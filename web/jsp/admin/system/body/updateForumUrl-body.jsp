<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/updateForumUrl">
        <html:hidden property="action" value="updateForumUrl"/>


	<c:set var="actionMode" value="update" scope="request"/>
	<tiles:insert name="/jsp/admin/system/common/forumurl-common.jsp"/>

</html:form>


