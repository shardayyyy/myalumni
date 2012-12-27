<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

<%-- related path --%>
<c:url var="stylesheet" value="/style/${CSS_TYPE}/myalumni.css" />
<c:url var="stylesheet_menu" value="/style/${CSS_TYPE}/menu.css" />


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
<title><bean:message key="application.name"/> (<c:out value="${ORGANIZATION_NAME}" default="SCHOOL NAME HERE"/>)&nbsp; - &nbsp;<tiles:getAsString name="title" ignore="true"/></title>

<html:base/>

<link rel=stylesheet href="<c:out value="${stylesheet}"/>" type="text/css">
<link rel=stylesheet href="<c:out value="${stylesheet_menu}"/>" type="text/css">
<link rel=stylesheet href="<c:out value="${displaytag}"/>" type="text/css">
<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${myalumniJS}"/>"></script>
<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${overlibmws}"/>"></script>
<script type="text/javascript" src="<c:out value="${fckeditor}"/>"></script>

<link rel=stylesheet href="<c:out value="${calendarcss}"/>" type="text/css">
<script type="text/javascript" src="<c:out value="${calendar}"/>"></script>
<script type="text/javascript" src="<c:out value="${lang}"/>"></script>
<script type="text/javascript" src="<c:out value="${setup}"/>"></script>

<c:url var="greybox" value="/html/greybox/"/>

<!--  GREY BOX AJAX -->
<script type="text/javascript">
    var GB_ROOT_DIR = "<c:out value='${greybox}'/>";
</script>

    
<c:url var="AJS" value="/html/greybox/AJS.js" />
<c:url var="AJS_fx" value="/html/greybox/AJS_fx.js" />
<c:url var="gb_scripts" value="/html/greybox/gb_scripts.js" />
<c:url var="gb_styles" value="/html/greybox/gb_styles.css" />

<script type="text/javascript" src="<c:out value="${AJS}"/>"></script>
<script type="text/javascript" src="<c:out value="${AJS_fx}"/>"></script>
<script type="text/javascript" src="<c:out value="${gb_scripts}"/>"></script>
<link rel=stylesheet href="<c:out value="${gb_styles}"/>" type="text/css">
<link rel="shortcut icon" href="/favicon.ico">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%"  border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td background="<c:out value="${BACKGROUND}"/>"><a href="<c:out value="${HOME}"/>"><myalumni:buildImageTag imageType="logo"><c:out value="${LOGO_NAME}"/></myalumni:buildImageTag></a>&nbsp;&nbsp;&nbsp;&nbsp;<span class="greenTitle"><c:out value="${ORGANIZATION_NAME}" default="SCHOOL NAME HERE"/></span>&nbsp;</td>
    <td align="right" valign="top" background="<c:out value="${BACKGROUND}"/>">
      <%-- mini login --%>
      <tiles:insert attribute="miniLogin"/>
    </td>
  </tr>
</table>

	<%-- MENU  --%>
	<tiles:insert attribute="menu"/>

	<%--  SCROLL --%>
	<tiles:insert attribute="scroll"/>
	<c:choose>
		<c:when test="${FIRST_STARTUP == 'N'}">
			<%-- GENERAL MESSAGE --%>
		    <tiles:insert attribute="message"/>
		
			<%--  BODY --%>
			<tiles:insert attribute="body"/>
		</c:when>
		<c:otherwise>
			<tiles:insert name="/jsp/setup/body/index-body.jsp"/>				
		</c:otherwise>	
	</c:choose>
	<%-- FOOTER --%>
	<tiles:insert attribute="footer"/>


</body>
</html:html>
