<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>




<html:form action="/admin/setupIntialization">
        <html:hidden property="action" value="setupIntialization"/>
        <html:hidden property="systemConfigId"/>


<table width="75%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold">
                  <bean:message key="label.common.update"/> <bean:message key="label.admin.org.info"/>
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
                      <td align="right" class="Smallbold"><bean:message key="label.admin.org.year"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="orgFirstYear" size="6" maxlength="4" titleKey="label.admin.org.year"/></td>
                </tr> 
		<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.org.name"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="organizationName" size="40" maxlength="40" titleKey="label.admin.org.name"/></td>
                </tr>  
		<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.org.shortname"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="organizationShortName" size="20" maxlength="20" titleKey="label.admin.org.shortname"/></td>
                </tr>                                               
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.org.email"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="orgEmail" size="50" maxlength="100" titleKey="label.admin.org.email"/></td>
                </tr>  
				<tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold">Does School have a <bean:message key="label.admin.dormitory"/>?:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
							<html:select property="hasDormitory" titleKey="label.admin.dormitory">
									<html:option value="">-- <bean:message key="label.admin.dormitory"/> --</html:option>							
									<html:option value="N"><bean:message key="label.common.no"/></html:option><%-- School has no dormitory --%>
									<html:option value="Y"><bean:message key="label.common.yes"/></html:option><%-- School does have dormitories --%>                                        
							</html:select>	                      
                      </td>
                </tr>   
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.albumurl"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="albumUrl" size="75" maxlength="250" titleKey="label.admin.albumurl"/></td>
                </tr>  
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.forumurl"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="forumUrl" size="75" maxlength="250" titleKey="label.admin.forumurl"/></td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.serverurl"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="serverUrl" size="75" maxlength="250" titleKey="label.admin.serverurl"/></td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.sessiontimeout"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
							<html:select property="sessionTimeout" titleKey="label.admin.sessiontimeout">
									<html:option value="900"><bean:message key="label.admin.15mins"/></html:option><%-- 15 mins --%>
									<html:option value="1800"><bean:message key="label.admin.30mins"/></html:option><%-- 30 mins --%>
									<html:option value="2700"><bean:message key="label.admin.45mins"/></html:option><%-- 45 mins --%>
									<html:option value="3600"><bean:message key="label.admin.60mins"/></html:option><%-- 60 mins --%>                                        
							</html:select>					  
					  </td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.rss.rssurl"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="rssUrl" size="75" maxlength="100" titleKey="label.admin.rss.rssurl"/></td>
                </tr>
                 <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.birthdaynotification"/>?:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
							<html:select property="birthdayNotification" titleKey="label.admin.birthdaynotification">
									<html:option value="">-- <bean:message key="label.admin.birthdaynotification"/> --</html:option>							
									<html:option value="N"><bean:message key="label.common.no"/></html:option><%-- School has no dormitory --%>
									<html:option value="Y"><bean:message key="label.common.yes"/></html:option><%-- School does have dormitories --%>                                        
							</html:select>	                      
                      </td>
                </tr>                                                                                                                                                           
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" class="Smallbold">&nbsp;</td>
                  <td align="center">&nbsp;</td>
                  <td>&nbsp;</td>
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
                                <html:submit styleClass="button"><bean:message key="button.update"/></html:submit>
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

</html:form>