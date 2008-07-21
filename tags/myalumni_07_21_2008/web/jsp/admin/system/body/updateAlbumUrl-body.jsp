<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/updateAlbumUrl">
        <html:hidden property="action" value="updateAlbumUrl"/>


	<c:set var="actionMode" value="update" scope="request"/>
	<tiles:insert name="/jsp/admin/system/common/albumurl-common.jsp"/>

</html:form>


