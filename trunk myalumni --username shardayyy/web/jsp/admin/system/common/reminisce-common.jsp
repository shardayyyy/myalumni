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
                                <bean:message key="label.common.add"/> <bean:message key="label.reminisce.reminisce"/>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <bean:message key="label.common.update"/> <bean:message key="label.reminisce.reminisce"/>
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
                      <td width="10%">&nbsp;</td>
                      <td width="3%" align="center"><html:img page="/images/spacer.gif" height="1" width="10"/></td>
                      <td width="81%">&nbsp;</td>
                    </tr>
					
		<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.reminisce.reminisceyears"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                      	<myalumni:dropdown type="luSpecific" group="CLASSYEAR" fieldName="fromYear" titleKey="label.reminisce.reminisceyears"><bean:write name="reminisceForm" property="fromYear"/></myalumni:dropdown>
                      	&nbsp;
                      	To 
                      	&nbsp;
                      	<myalumni:dropdown type="luSpecific" group="CLASSYEAR" fieldName="toYear" titleKey="label.reminisce.reminisceyears"><bean:write name="reminisceForm" property="toYear"/></myalumni:dropdown>
                      </td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.reminisce.slang"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="slang" size="50" maxlength="50" titleKey="label.reminisce.slang"/></td>
                </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold" valign="top"><bean:message key="label.reminisce.pronounciation"/>:</td>
                      <td align="center" valign="top"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                      	<html:text property="pronounce" size="50" maxlength="50" titleKey="label.reminisce.pronounciation" />
                      	
                      </td>
                </tr>                
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold" valign="top"><bean:message key="label.reminisce.meaning"/>:</td>
                      <td align="center" valign="top"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                      	<html:textarea property="meaning" cols="100%" rows="15" titleKey="label.reminisce.meaning" onkeydown="Counter(this,'500','Meaning');" onkeyup="Counter(this,'500','Meaning');"/>
                      </td>
                </tr>
           <c:if test="${MODULE == 'ADMIN'}">     
       			<tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="Smallbold"><bean:message key="label.admin.status"/>:</td>
                  <td align="center"><html:img page="/images/dot.gif" width="9" height="9" /><br></td>
                  <td>
                        <c:choose>
                            <c:when test="${actionMode == 'add'}">
                                <div class="activeStatus"><bean:message key="label.common.active"/></div>
                                 <html:hidden property="status" value="A"/>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:select property="status" titleKey="label.admin.status">
                                        <option value="" selected>-- <bean:message key="label.admin.status"/> --</option>
                                        <html:options collection="luStatus" property="value" labelProperty="label"/>
                                </html:select> 
                            </c:when>
                        </c:choose>      
                  </td>
                </tr>
          </c:if>
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="Smallbold">&nbsp;</td>
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
                                <html:submit styleClass="button"><bean:message key="button.add"/></html:submit>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:submit styleClass="button"><bean:message key="button.update"/></html:submit>
                            </c:when>
                        </c:choose>
                          &nbsp;&nbsp;
                         <c:choose>
                            <c:when test="${MODULE == 'ADMIN'}">
						        <html:link action="/admin/listReminisce?action=listReminisce" onmouseover="window.status=''; return true">
                                	<html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                            	</html:link>
                            </c:when>
                            <c:otherwise>
						        <html:link action="/member/listReminisce?action=listReminisce" onmouseover="window.status=''; return true">
                                	<html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                            	</html:link>
                            </c:otherwise>
                        </c:choose>						
						
						
						

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