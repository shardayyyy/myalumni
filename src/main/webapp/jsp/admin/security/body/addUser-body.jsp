<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/addUser">
        <html:hidden property="action" value="addUser"/>
        <html:hidden property="promptChange" value="Y"/>

	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/security/common/user-common.jsp"/>

</html:form>
