<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="DO_ACTIVATE" value="/jsp/myalumni/requestAccess.jsp"/>
<c:url var="SEND_ACTIVATION_CODE" value="/action/member/forgotPassword" />
<c:url var="PRIVACY" value="/jsp/privacy.jsp" />
<c:url var="HOME" value="/jsp/index.jsp" />
<c:url var="faq" value="/jsp/myalumni/faq.jsp"/>
<c:url var="emailWebmaster" value="/action/member/prepareEmailWebmaster?action=prepareEmailWebmaster"/>



<c:url var="forgotpassword" value="/jsp/forgotPassword.jsp"/>
<c:url var="forgotusername" value="/jsp/forgotUserName.jsp"/>
<c:url var="REGISTER" value="/action/member/prepareRegistration?action=prepareRegistration"/>


<html:form action="/member/login?action=login" focus="memberUserName">
      <table width="95%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr align="left">
      <td width="32%" rowspan="4" align="right"><a href="<c:out value="${HOME}"/>" ><html:img page="/images/logo/myalumni_03.png"  altKey="application.name" border="0"/></a> </td>
      <td height="34" colspan="2"><span class="greenTitle">&nbsp;&nbsp; MyAlumni Member Login </span><font color="#6E8F48" size="1"><bean:message key="application.version"/></font></td>
      </tr>
    <tr>
      <td width="9%" align="right" class="fieldlabel">User Login</td>
      <td width="59%" align="left">&nbsp;&nbsp;<html:text property="memberUserName" size="25" maxlength="25" title="memberUserName"/></td>
    </tr>
    <tr>
      <td align="right" class="fieldlabel">Password</td>
      <td align="left">&nbsp;&nbsp;<html:password property="memberPassword" size="25" maxlength="25" title="Member Password" redisplay="false"/></td>
    </tr>

    <tr>
      <td align="right" class="fieldlabel">&nbsp;</td>
      <td align="left"><html:image page="/images/icon/button_login.gif" value="Submit" title="Login" align="absbottom"/>
      
        <br>&nbsp;&nbsp;<a href="<c:out value="${REGISTER}"/>">Register</a>&nbsp;|&nbsp;<a href="<c:out value="${forgotpassword}"/>">Forgot Password?</a>&nbsp;|&nbsp;<a href="<c:out value="${forgotusername}"/>">Forgot Username?</a><br>
        &nbsp;&nbsp;<a href="<c:out value="${emailWebmaster}"/>">Registration Issues?</a>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td align="left">&nbsp;</td>
    </tr>   
    <tr align="center">
      <td height="35" colspan="3"><table width="65%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%" colspan="3">
          		<%-- GENERAL MESSAGE --%>
        	<tiles:insert name="/jsp/layout/common/message.jsp"/>
		<noscript>
			<b><font color="#FF0000" size="4" face="Tahoma"><bean:message key="prompt.jsReq"/></font></b>
			<b><font color="#FF0000" size="2" face="Tahoma"><bean:message key="prompt.jsNote"/></font></b>
		</noscript>
          </td>
        </tr>
      </table></td>
      </tr>
   </table>
</html:form>
