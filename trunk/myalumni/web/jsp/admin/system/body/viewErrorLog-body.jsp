<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<c:url var="back" value="/action/admin/listErrorLogs?action=listErrorLogs"/>

<html:form action="/admin/deleteErrorLog">
<html:hidden property="action" value="deleteErrorLog"/>
<html:hidden property="errorLogId"/>

<table width="100%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td colspan="2" class="bg0"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                    </tr>
                    <tr>
                      <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold">View Error Log</span></div></td>
                      <td align="right" clmit styleClass="button"><bean:message key="button.delete"/></html:submit>
                        &nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                    </tr>
                    <tr>
                      <td colspan="2" valign="top" ><table width="100%" border="0" cellpadding="2" cellspacing="5">
                          <tbody>
                          <tr>
                              <td align="right" valign="top" class="Bold">Logged By:</td>
                              <td valign="top"><bean:write name="ObjectVO" property="loggedBy"/></td>
                            </tr>
                            <tr>
                              <td align="right" class="Bold">Incident Date:</td>
                              <td>
                              <fmt:formatDate value="${ObjectVO.errorDate}" type="both" timeStyle="short"/></td>
                            </tr>                            
                            <tr>
                              <td width="35%" align="right" class="Bold" nowrap="nowrap" valign="top">Message:</td>
                              <td width="65%"><bean:write name="ObjectVO" property="errorMessage"/></td>
                            </tr>
                            <tr>
                              <td align="right" valign="top" class="Bold">Cause:</td>
                              <td valign="top">
                                <bean:write name="ObjectVO" property="cause"/>
                              </td>
                            </tr>       
                            <tr>
                              <td align="right" valign="top" class="Bold"><span class="Small">Stack Trace:</span></td>
                              <td valign="top">
                                <div class="Small"><bean:write name="ObjectVO" property="trace"/></div>
                              </td>
                            </tr> 
                            <tr>
                            	<td colspan="2">&nbsp;</td>
                            </tr>       
                            <tr>
                            	<td colspan="2"> &laquo; &laquo; <a href="<c:out value="${back}"/>">Back to List</a></td>
                            </tr>                     
                          </tbody>
                      </table></td>
                    </tr>
                  </tbody>
                </table>
            </td>
            </tr>
          </table>
   </html:form>