<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<html:form action="/admin/adminContactMail?action=contactAndReplyMail">
   <tiles:insert name="/jsp/common/mail-body.jsp"/>
	<html:hidden property="type" value="contact"/>
	<html:hidden property="roleType" value="ADMIN"/>
</html:form>