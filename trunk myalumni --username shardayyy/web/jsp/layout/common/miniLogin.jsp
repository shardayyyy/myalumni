<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<%-- FGC related path --%>
<c:url var="LOGOUT" value="/action/member/logout?action=logout" />
<c:url var="SEND_ACTIVATION_CODE" value="/jsp/myalumni/sendActivationCode.jsp" />
<c:url var="PRIVACY" value="/jsp/privacy.jsp" />
<c:url var="REGISTER" value="/action/member/prepareRegistration?action=prepareRegistration"/>
<c:url var="ADMIN_USER" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=desktop" />
<c:url var="LOGIN" value="/jsp/login.jsp"/>


<c:choose>
	<c:when test="${ isOnline == 'Y'}">
		<font class="menuText2">Welcome <c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/><br><a href="<c:out value="${LOGOUT}"/>">Logout</a></font><br>
		<c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
			(<font class="menuText2"><a href="<c:out value="${ADMIN_USER}"/>">Administer System</a></font>)
		</c:if>
	 </c:when>
	 <c:otherwise>
	<html:form  action="/member/login?action=login" focus="memberUserName">
	      <table width="150" border="0" align="right">
		<tr>
		  <td width="76" align="right" class="graysmall">usename:</td>
		  <td width="114"><html:text property="memberUserName" styleClass="graysmall" title="Member UserName"/></td>
		</tr>
		<tr>
		  <td align="right" class="graysmall">password: </td>
		  <td><html:password property="memberPassword" styleClass="graysmall" title="Member Password" redisplay="false"/></td>
		</tr>
		<tr>
		  <td colspan="2" align="right" class="graysmall">
		    <html:image page='/images/icon/button_login.gif' value='Submit' title='Login' align="absmiddle" />
		    [<a href="<c:out value="${REGISTER}"/>">register</a>]<br>
		    [<a href="<c:out value="${LOGIN}"/>">forgot password?</a>]
		    
		    </td>
		</tr>
	      </table>
	    </html:form>
	 </c:otherwise>
 </c:choose>