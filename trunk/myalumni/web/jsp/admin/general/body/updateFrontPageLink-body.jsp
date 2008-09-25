<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<html:form action="/admin/updateFrontPageLinks">
        <html:hidden property="action" value="updateFrontPageLinks"/>
        <html:hidden property="linkId"/>


	<c:set var="actionMode" value="update" scope="request"/>
	<tiles:insert name="/jsp/admin/general/common/frontPageLink-common.jsp"/>

</html:form>


