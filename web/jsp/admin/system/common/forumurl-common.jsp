<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


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
                                <bean:message key="label.common.add"/> <bean:message key="label.admin.forumurl"/>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <bean:message key="label.common.update"/> <bean:message key="label.admin.forumurl"/>
                            </c:when>
                        </c:choose>              
            </span> </div></td>
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
                      <td align="right" class="Smallbold"><bean:message key="label.admin.forumurl"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="forumUrl" size="75" maxlength="250" titleKey="label.admin.forumurl"/></td>
                </tr>            
                <tr>
                  <td width="6%" align="center">&nbsp;</td>
                      <td width="26%" align="right">&nbsp;</td>
                      <td width="3%" align="center">&nbsp;</td>
                      <td width="65%">&nbsp;</td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right">&nbsp;</td>
                      <td align="center">&nbsp;</td>
                      <td>
                         <c:choose>
                            <c:when test="${actionMode == 'add'}">
                                <html:submit styleClass="button"><bean:message key="button.add"/></html:submit>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:submit styleClass="button"><bean:message key="button.update"/></html:submit>
                            </c:when>
                        </c:choose>
                          &nbsp;&nbsp;
                            <html:link page="/jsp/admin/system/index.jsp" onmouseover="window.status=''; return true">
                                <html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                            </html:link>
                        </td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right">&nbsp;</td>
                  <td align="center">&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                </tbody>
              </table>              </td>
            </tr>
          </tbody>
      </table></td>
      </tr>
  </table>