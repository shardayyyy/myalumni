<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="PRIVACY" value="/jsp/privacy.jsp" />
<c:url var="HOME" value="/jsp/index.jsp" />


<html:form action="/admin/login?action=login" focus="memberUserName">

      <table width="95%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr align="left">
      <td width="32%" rowspan="4" align="right"><html:img page="/images/logo/myalumni_03.png" altKey="application.name"/> </td>
      <td height="34" colspan="2"><span class="greenTitle">&nbsp;&nbsp; Admin Login&nbsp; </span> <span class="graysmall"><bean:message key="application.version"/></span></td>
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
      <td>&nbsp;</td>
      <td align="left">
      <html:image page="/images/icon/button_login.gif" value="Submit" title="Login"/>
        <br><br>
	  <span class="fieldlabel"><a href="<c:out value="${HOME}"/>">Return Home</a></span></td>
    </tr>

    <tr align="center">
      <td height="35" colspan="3"><table width="65%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center">
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