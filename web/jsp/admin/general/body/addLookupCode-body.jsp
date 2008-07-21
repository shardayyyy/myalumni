<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/addLookupCode">
        <html:hidden property="action" value="addLookupCode"/>


	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/general/common/lookupCode-common.jsp"/>

</html:form>


