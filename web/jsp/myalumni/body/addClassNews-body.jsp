<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<html:form action="/member/addClassNews">
        <html:hidden property="action" value="addClassNews"/>


	<c:set var="actionMode" value="add" scope="request"/>
	<tiles:insert name="/jsp/admin/system/common/classNews-common.jsp"/>
  
</html:form>