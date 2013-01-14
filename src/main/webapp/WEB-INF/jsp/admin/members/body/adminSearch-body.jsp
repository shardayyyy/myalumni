<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<html:form action="/admin/memberSearch?action=searchForMembers">
	<tiles:insert name="/jsp/common/searchContent-body.jsp"/>
</html:form>