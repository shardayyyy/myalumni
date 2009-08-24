<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/addLookupGroup">
        <html:hidden property="action" value="addLookupGroup"/>
	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/common/lookupGroup-common.jsp"/>

</html:form>


