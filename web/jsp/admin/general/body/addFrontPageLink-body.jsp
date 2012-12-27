<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<html:form action="/admin/addFrontPageLinks">
        <html:hidden property="action" value="addFrontPageLinks"/>

	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/general/common/frontPageLink-common.jsp"/>

</html:form>


