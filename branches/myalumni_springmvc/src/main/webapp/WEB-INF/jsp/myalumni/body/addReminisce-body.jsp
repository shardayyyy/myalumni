<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<html:form action="/member/addReminisce">
        <html:hidden property="action" value="addReminisce"/>


	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/system/common/reminisce-common.jsp"/>
  
</html:form>