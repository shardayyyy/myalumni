<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<%-- related path --%>
<c:url var="stylesheet" value="/style/myalumni.css" />
<c:url var="login" value="/action/member/logout?action=logout" />
<c:url var="myalumniJS" value="/js/global.js" />
<c:url var="HOME" value="/jsp/index.jsp" />
<c:url var="BACKGROUND" value="" /> 
<c:url var="fckeditor" value="/html/FCKeditor/fckeditor.js" />
<c:url var="displaytag" value="/html/displaytag/css/displaytag.css" />

<%-- Overlib Mouse overs http://www.macridesweb.com/oltest/ --%>
<c:url var="overlibmws" value="/js/overlibmws.js" />


<%-- Calendar Setup --%>
<c:url var="calendarcss" value="/html/calendar/calendar-win2k-1.css" />
<c:url var="calendar" value="/html/calendar/calendar.js" />
<c:url var="lang" value="/html/calendar/lang/calendar-en.js" />
<c:url var="setup" value="/html/calendar/calendar-setup.js" />



<html:html xhtml="true">
<head>

<tiles:insert attribute="meta" />
<title><bean:message key="application.name"/> &nbsp; - &nbsp;<tiles:getAsString name="title" ignore="true"/></title>

<html:base/>

<link rel=stylesheet href="<c:out value="${stylesheet}"/>" type="text/css">
<link rel=stylesheet href="<c:out value="${displaytag}"/>" type="text/css">
<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${myalumniJS}"/>"></script>
<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${overlibmws}"/>"></script>
<script type="text/javascript" src="<c:out value="${fckeditor}"/>"></script>

<link rel=stylesheet href="<c:out value="${calendarcss}"/>" type="text/css">
<script type="text/javascript" src="<c:out value="${calendar}"/>"></script>
<script type="text/javascript" src="<c:out value="${lang}"/>"></script>
<script type="text/javascript" src="<c:out value="${setup}"/>"></script>


</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%"  border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td background="<c:out value="${BACKGROUND}"/>"><a href="<c:out value="${HOME}"/>"><html:img page="/images/logo/myalumni_03.png" border="0" align="absmiddle"  altKey="application.name"  titleKey="application.name"/></a>&nbsp;</td>
  </tr>
</table>


	<%-- GENERAL MESSAGE --%>
    <tiles:insert attribute="message"/>

	<%--  BODY --%>
	<tiles:insert attribute="body"/>

	<%-- FOOTER
	<tiles:insert attribute="footer"/> --%>


</body>
</html:html>
