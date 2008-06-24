<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>



<c:url var="jstxtBoxLib" value="/js/selectbox.js" />
<script language="JavaScript1.2" type="text/javascript" src='<c:out value="${jstxtBoxLib}" escapeXml="false" />'></script>


<script  language="JavaScript1.2" type="text/javascript">
    <!--
    function getUsersByRole()
            {
                    document.memberForm.executeAction.value="refresh";
                    document.memberForm.action.value="displayUsersByRole";
                    document.memberForm.submit();
            }

    function selAllAdd(){
        for (i=0; i<document.memberForm.elements["lstSelectedUsers"].length; i++) {
            document.memberForm.elements["lstSelectedUsers"].options[i].selected = true;
        }
    }


    function submitAssignRole2User(button)
    {
      //document.memberForm.buttonAction.value=button.value;
      selAllAdd();
      document.memberForm.submit();
    }
   //  End -->
</script>



<html:form action="/admin/assignRoleToUsers">
	  <html:hidden property="executeAction" value="add"/>
          <html:hidden property="action" value="assignRoleToUsers"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.admin.assign.assignrole"/></span></div></td>
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
                      <td align="right"><div class="Smallbold"><bean:message key="label.admin.assign.role"/>:</div></td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                        
                        <html:select property="isAdmin" onchange="getUsersByRole();">
                        	<html:option value="">-- Select --</html:option>
                        	<html:option value="N">Member</html:option>
                        	<html:option value="Y">Admin</html:option>
                        </html:select>
                        </td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                  <td align="right" valign="top"><div class="Smallbold"><bean:message key="label.admin.assign.selecteduserstoapply"/>:</div> </td>
                  <td align="center" valign="top"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                  <td valign="top"><table border="0" cellpadding="0" cellspacing="0" class="SearchBorderStyle">
                    <tbody>
                      <tr>
                        <td valign="top" ><table width="100%" border="0" cellpadding="2" cellspacing="0">
                            <tbody>
                              <tr align="center" class="Smallbold">
                                <td><bean:message key="label.admin.assign.availableusers"/></td>
                                <td><bean:message key="label.admin.addremove"/></td>
                                <td><bean:message key="label.admin.assign.selectedusers"/> <font color="#FF0000">*</font></td>
                              </tr>
                              <tr align="center">
                                <td width="45%"><p>
                                    <!-- List of available users -->
                                    <select name="lstAvailableUsers" multiple id="availableUsers" size="9" title="Available Users" onDblClick="moveSelectedOptions(this.form['lstAvailableUsers'],this.form['lstSelectedUsers'],false)">
                                    	<logic:notEmpty name="luAssignRoleAvailableUsers" scope="session">
                                        	<logic:iterate id="availUser" scope="session" name="luAssignRoleAvailableUsers" indexId="recIdx">
                                            	<option value="<c:out value="${availUser.memberId}" escapeXml="false" />"><c:out value="${availUser.firstName}" escapeXml="false" /> <c:out value="${availUser.lastName}" escapeXml="false" /></option>
                                        	</logic:iterate>
                                        </logic:notEmpty>
                                    </select>
                                  <br>
                                  <br>
                                  <input type="button" class="button" onclick="sortSelect(this.form['lstAvailableUsers'])" name="sort1" id="sort1" value="ReSort List Alpabeltically">
                                </p>
                                  <p>&nbsp; </p></td>
                                <td width="10%" class="Smallbold">
                                <p>
                                    <input TYPE="button" NAME="right" VALUE="&gt;&gt;" onclick="moveSelectedOptions(this.form['lstAvailableUsers'],this.form['lstSelectedUsers'],false)"><br><br>
                                    <input TYPE="button" NAME="left" VALUE="&lt;&lt;" onclick="moveSelectedOptions(this.form['lstSelectedUsers'],this.form['lstAvailableUsers'],false)"><br><br>
                                    <input TYPE="button" NAME="right" VALUE="All &gt;&gt;" onclick="moveAllOptions(this.form['lstAvailableUsers'],this.form['lstSelectedUsers'],false)"><br><br>
                                    <input TYPE="button" NAME="left" VALUE="All &lt;&lt;" onclick="moveAllOptions(this.form['lstSelectedUsers'],this.form['lstAvailableUsers'],false)">
                                  </p></td>
                                <td width="45%"><p>
                                    <!-- List of selected users -->
                                    <select name="lstSelectedUsers" multiple id="selectedUsers" size="9" title="Selected Users" onDblClick="moveSelectedOptions(this.form['lstSelectedUsers'],this.form['lstAvailableUsers'],false)">
                                    	<logic:notEmpty name="luAssignRoleSelectedUsers" scope="session">
                                        	<logic:iterate id="selectedUser" scope="session" name="luAssignRoleSelectedUsers" indexId="recIdx">
                                            	<option value="<c:out value="${selectedUser.memberId}" escapeXml="false" />"><c:out value="${selectedUser.firstName}" escapeXml="false" /> <c:out value="${selectedUser.lastName}" escapeXml="false" /></option>
                                        	</logic:iterate>
                                         </logic:notEmpty>
                                    </select>
                                  <br>
                                  <br>
                                  <input type="button" class="button" onclick="sortSelect(this.form['lstSelectedUsers'])" name="sort1" id="sort1" value="ReSort List Alpabeltically">
                                </p>
                                  <p>&nbsp; </p></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
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
                        <html:submit styleClass="button" onclick="submitAssignRole2User(this);"><bean:message key="button.save"/></html:submit>
                        &nbsp;
                            <html:link page="/jsp/admin/security/index.jsp" onmouseover="window.status=''; return true">
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