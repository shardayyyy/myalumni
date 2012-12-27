<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

<c:url var="home" value="/jsp/index.jsp"/>
<c:url var="loginAgain" value="/jsp/login.jsp"/>


<p>&nbsp;</p>
<p>&nbsp;</p>

<html:form action="/forgotPassword?action=forgotPassword" focus="memberUserName"  onsubmit="javascript:document.loginForm.elements['JSEnabled'].value='true';">

              <table width="738" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                <tr align="left">
                  <td align="center"><br>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td colspan="3" align="center">&nbsp;</td>
                      </tr>
                      <tr>
                        <td width="1%" height="25">&nbsp;<a href="<c:out value="${home}"/>"><myalumni:buildImageTag imageType="logo"><c:out value="${LOGO_NAME}"/></myalumni:buildImageTag></a></td>
                        <td width="99%" colspan="2">
							<table>
								<tr>
		                        <td>&nbsp;</td>
		                        <td height="25" colspan="2"><span class="greenTitle">Request Forgotten Password</span>&nbsp;<span class="graysmall"><bean:message key="application.version"/></span></td>
		                      </tr>
		                      <tr>
		                        <td width="1%" height="25" align="right">&nbsp;</td>
		                        <td width="10%" height="25" align="right"><strong><bean:message key="label.username"/>:</strong>&nbsp;&nbsp;&nbsp; </td>
		                        <td width="89%"><html:text property="memberUserName" size="50" maxlength="50" titleKey="label.username"/></td>
		                      </tr>
		                      <tr>
		                        <td align="right">&nbsp;</td>
		                        <td height="25" align="right">&nbsp;</td>
		                        <td><html:submit><bean:message key="button.submit"/></html:submit></td>
		                      </tr>
		                      <tr>
		                        <td>&nbsp;</td>
		                        <td height="25">&nbsp;</td>
		                        <td><a href="<c:out value="${loginAgain}"/>" title="Login Again">Login Again</a></td>
		                      </tr>
		                    </table>
						</td>                       						
                      </tr>
                      
                      <tr>
                        <td height="25" colspan="3">
                          <tiles:insert name="/jsp/layout/common/message.jsp"/>
                            <noscript>
                                <strong><font color="#FF0000" size="2" face="Tahoma"><bean:message key="prompt.jsReq"/></font></strong><br>
                                <strong><font color="#FF0000" size="2" face="Tahoma"><bean:message key="prompt.jsNote"/></font></strong><br>
                            </noscript>                        
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
             </table>
</html:form>