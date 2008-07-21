<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>


<c:url var="home" value="/jsp/index.jsp"/>
<c:url var="loginAgain" value="/jsp/login.jsp"/>

<html:form action="/updateExpiredPassword" focus="memberTempPassword"  onsubmit="javascript:document.forms[0].elements['JSEnabled'].value='true';">
<html:hidden property="action" value="updateExpiredPassword"/>
	<html:hidden property="memberUserName"/>
        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top"><div align="right">
              <table width="738" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

                <tr align="left">
                  <td height="34" align="center"><br>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td colspan="3">&nbsp;
                    </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td width="34%">&nbsp;</td>
                        <td height="25" colspan="2"><span class="greyTitle">Update Expired Password</span></td>
                      </tr>
  
                      <tr>
                        <td align="right">&nbsp;</td>
                        <td height="25" align="right" class="Smallbold"><bean:message key="label.username"/>:&nbsp;&nbsp;&nbsp; </td>
                        <td height="25"><strong><bean:write name="loginForm" property="memberUserName"/></strong></td>
                      </tr>
                     <tr>
                        <td align="right">&nbsp;</td>
                        <td height="25" align="right" class="Smallbold"><bean:message key="label.oldpassword"/>:&nbsp;&nbsp;&nbsp; </td>
                        <td height="25"><html:password property="memberTempPassword" size="50" maxlength="50" titleKey="label.oldpassword" redisplay="false"/></td>
                      </tr>                    
                      <tr>
                        <td align="right">&nbsp;</td>
                        <td height="25" align="right" class="Smallbold"><bean:message key="label.newpassword"/>:&nbsp;&nbsp;&nbsp; </td>
                        <td height="25"><html:password property="memberPassword" size="50" maxlength="50" titleKey="label.newpassword" redisplay="false"/></td>
                      </tr>
                      <tr>
                        <td align="right">&nbsp;</td>
                        <td height="25" align="right" class="Smallbold"><bean:message key="label.confirmnewpassword"/>:&nbsp;&nbsp;&nbsp; </td>
                        <td height="25"><html:password property="memberPasswordConfirm" size="50" maxlength="50" titleKey="label.confirmnewpassword" redisplay="false"/></td>
                      </tr>
                      <tr>
                        <td align="right">&nbsp;</td>
                        <td height="25" align="right" class="Small">&nbsp;</td>
                        <td height="25"><html:submit styleClass="button"><bean:message key="button.submit"/></html:submit></td>
                      </tr>
                      <tr class="redhighlight">
                        <td height="25" colspan="3">
		                                <tiles:insert name="/jsp/layout/common/message.jsp"/>
                            <noscript>
                                <strong><font color="#FF0000" size="2" face="Tahoma"><bean:message key="prompt.jsReq"/></font></strong><br>
                                <strong><font color="#FF0000" size="2" face="Tahoma"><bean:message key="prompt.jsNote"/></font></strong><br>
                            </noscript>                        
                        </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td height="25" colspan="2" align="center"></td>
                      </tr>                     
                    </table>
                    <html:img page="/images/spacer.gif" width="10" height="1"/>
                  </td>
                </tr>

              </table>
            </div></td>
          </tr>
          <tr>
            <td valign="top"></td>
          </tr>
        </table>
</html:form>