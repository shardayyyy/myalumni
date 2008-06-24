<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<%-- related path --%>
<c:url var="LOGOUT" value="/action/admin/logout?action=logout" />
<c:url var="PRIVACY" value="/action/admin/logout?action=logout" />
<c:url var="mainsite" value="/jsp/index.jsp" />

<c:choose>
	<c:when test="${ isOnline == 'Y'}">
		<font class="menuText2">Welcome <c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/>(<c:out value="${USER_CONTAINER.token.memberUserName}"/>)<br>[<a href="<c:out value="${LOGOUT}"/>">logout</a>]<br><a href="<c:out value="${mainsite}"/>">Back to main site</a></font>
	 </c:when>
	 <c:otherwise>
	<html:form  action="/admin/login?action=login" >
	      <table width="150" border="0" align="right">
		<tr>
		  <td width="76" align="right" class="graysmall">usename:</td>
		  <td width="114"><html:text property="memberUserName" styleClass="graysmall" title="memberUserName"/></td>
		</tr>
		<tr>
		  <td align="right" class="graysmall">password: </td>
		  <td><html:password property="memberPassword" styleClass="graysmall" title="Member Password"/></td>
		</tr>
		<tr>
		  <td colspan="2" align="right">
		    <html:image page='/images/icon/button_login.gif' value='Submit' title='Login' />
		    <a href="<c:out value="${PRIVACY}"/>"><html:img page="/images/icon/button_login_s.gif" alt="Secure" width="15" height="18" border="0"/></a><a href="<c:out value="${ACTIVATE}"/>"><html:img page="/images/help-16x16.gif" alt="Help" width="16" height="16" border="0" align="absmiddle"/></a></td>
		</tr>
	      </table>
	    </html:form>
	 </c:otherwise>
 </c:choose>