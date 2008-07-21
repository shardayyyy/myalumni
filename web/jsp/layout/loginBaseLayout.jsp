<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="stylesheet" value="/style/myalumni.css" />


<html:html xhtml="true">
<head>

<tiles:insert attribute="meta" />

<title><bean:message key="application.name"/>&nbsp; - &nbsp;<tiles:getAsString name="title" ignore="true"/></title>

<html:base/>
<link rel=stylesheet href="<c:out value="${stylesheet}"/>" type="text/css">

</head>

<body bgcolor="#B4CC80" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#FFFFFF"><html:img page="/images/spacer.gif" height="1" width="100%"/></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF">
		<tiles:insert attribute="body" />
	</td>
  </tr>
  <tr>
    <td>
	<tiles:insert attribute="footer" />
    </td>
  </tr>
</table>
</body>
</html:html>
