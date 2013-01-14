<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="stylesheet" value="/style/${CSS_TYPE}/myalumni.css" />


<html:html xhtml="true">
<head>

<tiles:insert attribute="meta" />

<title><bean:message key="application.name"/>&nbsp; - &nbsp;<tiles:getAsString name="title" ignore="true"/></title>

<html:base/>
<link rel=stylesheet href="<c:out value="${stylesheet}"/>" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<br>
                <tiles:insert attribute="body" />
</body>
</html:html>
