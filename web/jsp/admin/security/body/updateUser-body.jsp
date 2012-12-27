<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/updateUser">
        <html:hidden property="action" value="updateUser"/>
        <html:hidden property="memberId"/>

	<c:set var="actionMode" value="update" scope="request"/>
	<tiles:insert name="/jsp/admin/security/common/user-common.jsp"/>

</html:form>
