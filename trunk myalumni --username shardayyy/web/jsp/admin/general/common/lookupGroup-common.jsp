<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<html:hidden property="lookupGroupId"/>
<input name="origMinLength" type="hidden" value="<bean:write name="generalModuleForm" property="minLength"/>">
<input name="origMaxLength" type="hidden" value="<bean:write name="generalModuleForm" property="maxLength"/>">


    
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> 
                    <c:choose>
                        <c:when test="${actionMode == 'add'}">
                            <span class="Bold">Add New lookup Group</span>
                        </c:when>
                        <c:when test="${actionMode == 'update'}">
                            <span class="Bold">Update Lookup Group</span>
                        </c:when>
                    </c:choose>              
            
            </div></td>
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
                      <td align="right"><bean:message key="label.admin.general.grpcode"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><%--<html:text property="lookupGroupId" titleKey="label.admin.general.grpcode" size="15" maxlength="15"/>--%>
                          <bean:write name="generalModuleForm" property="lookupGroupId"/>
                      </td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right"><bean:message key="label.admin.general.desc"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="groupDescription" titleKey="label.admin.general.desc" size="40" maxlength="40"/></td>
                </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right"><bean:message key="label.admin.general.minlen"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                                    <html:select property="minLength">
                                            <option value="" selected>-- <bean:message key="label.admin.general.minlen"/> --</option>
                                            <html:options collection="luNumbers" property="value" labelProperty="label"/>
                                    </html:select>&nbsp;&nbsp;<span class="SmallDisabled"><bean:message key="label.admin.general.currently"/> <bean:write name="generalModuleForm" property="minLength"/></span>                        
                      </td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right"><bean:message key="label.admin.general.maxlen"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                                    <html:select property="maxLength">
                                            <option value="" selected>-- <bean:message key="label.admin.general.maxlen"/> --</option>
                                            <html:options collection="luNumbers" property="value" labelProperty="label"/>
                                    </html:select>&nbsp;&nbsp;<span class="SmallDisabled"><bean:message key="label.admin.general.currently"/> <bean:write name="generalModuleForm" property="maxLength"/></span>                       
                      </td>
                </tr>                
                          <tr>
                            <td align="center" width="6%">&nbsp;</td>
                            <td align="right" valign="top" width="26%">&nbsp;</td>
                            <td align="center" valign="top" width="3%">&nbsp;</td>
                            <td width="65%">&nbsp;</td>
                          </tr>
                          <tr>
                            <td align="center">&nbsp;</td>
                            <td align="right" valign="top">&nbsp;</td>
                            <td align="center" valign="top">&nbsp;</td>
                            <td>
				    <c:choose>
					<c:when test="${actionMode == 'add'}">
					    <html:submit styleClass="button"><bean:message key="button.add"/></html:submit>
					</c:when>
					<c:when test="${actionMode == 'update'}">
					    <html:submit styleClass="button"><bean:message key="button.update"/></html:submit>
					</c:when>
				    </c:choose>
                            <br>
                        </td>
                          </tr>
                          <tr>
                            <td align="center">&nbsp;</td>
                            <td align="right" valign="top">&nbsp;</td>
                            <td align="center" valign="top">&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                </tbody>
              </table>              </td>
            </tr>
          </tbody>
      </table></td>
      </tr>
  </table>















