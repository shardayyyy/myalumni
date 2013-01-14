<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>



  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold">
                <c:choose>
                    <c:when test="${actionMode == 'add'}">
                        <bean:message key="label.common.add"/> <bean:message key="label.admin.users.user"/>
                    </c:when>
                    <c:when test="${actionMode == 'update'}">
                        <bean:message key="label.common.update"/> <bean:message key="label.admin.users.user"/>
                    </c:when>
                </c:choose>  
            </span></div></td>
              <td align="right" class="bg0">&nbsp;&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
            </tr>
          <tr>
            <td colspan="2" valign="top" ><table width="100%" border="0" cellpadding="2" cellspacing="0">
              <tbody>
                <tr>
                  <td width="6%"><html:img page="/images/spacer.gif" height="8" width="15"/></td>
                      <td width="26%">&nbsp;</td>
                      <td width="3%" align="center"><html:img page="/images/spacer.gif" height="1" width="10"/></td>
                      <td width="65%">&nbsp;</td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.firstname"/>:</td>
                  <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                  <td><html:text property="firstName" size="40" maxlength="40" titleKey="label.admin.users.firstname"/></td>
                </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.lastname"/>:</td>
                  <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                  <td><html:text property="lastName" size="40" maxlength="40" titleKey="label.admin.users.lastname"/></td>
                </tr>                    
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="SmallBold"><bean:message key="label.admin.users.username"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                            <c:choose>
                                <c:when test="${actionMode == 'add'}">
                                    <html:text property="memberUserName" size="40" maxlength="40" />
                                </c:when>
                                <c:when test="${actionMode == 'update'}">
                                    <bean:write name="memberForm" property="memberUserName"/>
                                    <html:hidden property="memberUserName"/>
                                </c:when>
                            </c:choose>                      
                      </td>
                    </tr>
                    
               <c:choose>
               		<c:when test="${actionMode == 'add'}">
               			<html:hidden property="promptChange" value="Y"/>
               		</c:when>
               		<c:when test="${actionMode == 'update'}">
			                <tr>
			                  <td align="center">&nbsp;</td>
			                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.changepassword"/>:</td>
			                  <td align="center">&nbsp;</td>
			                  <td><html:checkbox property="promptChange" value="Y" titleKey="label.admin.users.changepassword"/></td>
			                </tr>               		
               		</c:when>               
               </c:choose>     
      
                         
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.temppassword"/>:</td>
                  <td align="center">
               <c:choose>
               		<c:when test="${actionMode == 'add'}">
               		      <html:img page="/images/dot.gif" width="9" height="9"/>      	 		
               		</c:when>  
               		<c:otherwise>
               			&nbsp;
               		</c:otherwise>             
               </c:choose>
				  </td>
                  <td><html:password property="memberPassword" size="40" maxlength="40" redisplay="false" titleKey="label.admin.users.temppassword"/></td>
                </tr>                
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.email"/>:</td>
                  <td align="center">&nbsp;</td>
                  <td><html:text property="email" size="50" maxlength="50" titleKey="label.admin.users.email" /></td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.users.isadmin"/>:</td>
                  <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                  <td>
                  		<bean:write name="memberForm" property="isAdmin"/>
                  </td>
                </tr>                
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="SmallBold"><bean:message key="label.admin.status"/>:</td>
                  <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                  <td>
                        <c:choose>
                            <c:when test="${actionMode == 'add'}">
                                <div class="activeStatus"><bean:message key="label.common.active"/></div>
                                 <html:hidden property="memberStatus" value="A"/>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:select property="memberStatus" titleKey="label.admin.status">
                                        <option value="" selected>-- <bean:message key="label.admin.status"/> --</option>
                                        <html:options collection="luAccountStatus" property="value" labelProperty="label"/>
                                </html:select> 
                            </c:when>
                        </c:choose>
                </td>
                </tr>

                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right">&nbsp;</td>
                      <td align="center">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right">&nbsp;</td>
                      <td align="center">&nbsp;</td>
                      <td>
                         <c:choose>
                            <c:when test="${actionMode == 'add'}">
                                <html:submit><bean:message key="button.save"/></html:submit>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:submit><bean:message key="button.update"/></html:submit>
                            </c:when>
                        </c:choose>  
                        &nbsp;&nbsp;
                        <html:link action="/admin/listUsers?action=listUsers" onmouseover="window.status=''; return true">
                            <html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                        </html:link>                          
  </td>
                    </tr>
                <tr>
                  <td width="6%" align="center">&nbsp;</td>
                      <td width="26%" align="right">&nbsp;</td>
                      <td width="3%" align="center">&nbsp;</td>
                      <td width="65%">&nbsp;</td>
                    </tr>
                </tbody>
              </table>              </td>
            </tr>
          </tbody>
      </table></td>
      </tr>
  </table>
