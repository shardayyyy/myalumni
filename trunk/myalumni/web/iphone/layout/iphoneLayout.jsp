<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<%-- related path --%>
<c:url var="iphoneCSS" value="/iphone/css/iphonenav.css" />
<c:url var="iphoneJS" value="/iphone/js/iphonenav.js" />

<c:url var="iuiCSS" value="/iphone/iui/iui.css" />
<c:url var="iuiJS" value="/iphone/iui/iui.js" />

<c:url var="customcss" value="/iphone/css/custom.css" />
<c:url var="iphoneHome" value="/iphone/index.jsp" />


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
         "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
         
         
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>
<%-- <title><tiles:getAsString name="title" ignore="true"/></title> --%>


<style type="text/css" media="screen">@import "<c:out value="${iphoneCSS}"/>";</style>
<style type="text/css" media="screen">@import "<c:out value="${customcss}"/>";</style>
<script type="application/x-javascript" src="<c:out value="${iphoneJS}"/>"></script>

</head>

<body>

	<a id="backButton" class="button" href="<c:out value="${iphoneHome}"/>">Home</a>
	<h1 id="pageTitle"><tiles:getAsString name="title" ignore="true"/></h1>

	
	<%-- Header --%>
    <div class="toolbar">
        <h1 id="pageTitle"></h1>
        <a id="backButton" class="button" href="#"></a>
        <html:link styleClass="button" page="/iphone/index.jsp">Home</html:link>
        
    </div>	
	
	<%--  Body --%>
	<tiles:insert attribute="body"/>

</body>
</html>
