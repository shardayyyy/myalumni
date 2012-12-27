<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<html:form action="/member/memberSearch?action=searchForMembers">
	<tiles:insert name="/jsp/common/searchContent-body.jsp"/>
</html:form>