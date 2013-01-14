<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="title_line" value="/images/title_line2.gif"/>
<c:url var="back" value="/action/admin/listClassNews?action=listClassNews"/>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold">
                                <bean:message key="label.common.view"/> <bean:message key="label.admin.system.classnews"/>            
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
                      <td width="12%">&nbsp;</td>
                      <td width="1%" align="center"><html:img page="/images/spacer.gif" height="1" width="10"/></td>
                      <td width="81%">&nbsp;</td>
                    </tr>				
		<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Bold"><bean:message key="label.admin.system.classyears"/>:</td>
                      <td align="center">&nbsp;</td>
                      <td><bean:write name="classNewsForm" property="fromClassYear"/> - <bean:write name="classNewsForm" property="toClassYear"/></td>
                </tr> 
		<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Bold"><bean:message key="label.admin.system.author"/>:</td>
                      <td align="center">&nbsp;</td>
                      <td><bean:write name="classNewsForm" property="author.fullName"/></td>
                </tr>                 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Bold"><bean:message key="label.admin.system.subject"/>:</td>
                      <td align="center">&nbsp;</td>
                      <td><bean:write name="classNewsForm" property="subject"/></td>
                </tr>
       <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="Bold"><bean:message key="label.admin.status"/>:</td>
                  <td align="center">&nbsp;</td>
                  <td>
                        <c:choose>
                            <c:when test="${classNewsForm.status == 'A'}">
                                    <div class="activeStatus"><bean:message key="label.common.active"/></div>
                            </c:when>
                            <c:when test="${classNewsForm.status == 'I'}">
                                    <div class="inactiveStatus"><bean:message key="label.common.inactive"/></div>
                            </c:when>
                            <c:when test="${classNewsForm.status == 'X'}">
                                    <div class="inactiveStatus"><bean:message key="label.common.approvalneeded"/></div>
                            </c:when>                            
                        </c:choose>     
                  </td>               
                </tr>
       				 <tr>
       				 <td colspan="3">&nbsp;</td>
					<td height="6"  background="<c:out value="${title_line}"/>"></td>
				</tr>       
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Bold" valign="top"><bean:message key="label.admin.system.news"/>:</td>
                      <td align="center" valign="top">&nbsp;</td>
                      <td>
                      	<c:out value="${classNewsForm.news}" escapeXml="false"/>
                      </td>
                </tr>
       				 <tr>
       				 <td colspan="3">&nbsp;</td>
					<td height="6"  background="<c:out value="${title_line}"/>"></td>
				</tr>           

                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="Bold">&nbsp;</td>
                  <td align="center">&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>


                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right">&nbsp;</td>
                  <td align="center">&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                 <td colspan="4"><a href="<c:out value="${back}"/>"><bean:message key="button.backtolist"/></a></td>
                 </tr>
                </tbody>
              </table>              </td>
            </tr>
          </tbody>
      </table></td>
      </tr>
  </table>