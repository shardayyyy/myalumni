<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

<script type="text/javascript">

        window.onload = function()
        {
                // Automatically calculates the editor base path based on the _samples directory.
                // This is usefull only for these samples. A real application should use something like this:
                // oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
                var sBasePath = '<%=request.getContextPath()%>/html/FCKeditor/';
        
                var oFCKeditor = new FCKeditor( 'news' ) ;
                oFCKeditor.BasePath	= sBasePath ;
                oFCKeditor.ToolbarSet   =  'Default';  // Default, Basic
                oFCKeditor.ReplaceTextarea() ;
        }
</script>


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
                                <bean:message key="label.common.add"/> <bean:message key="label.admin.system.classnews"/>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <bean:message key="label.common.update"/> <bean:message key="label.admin.system.classnews"/>
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
                      <td align="right" class="Smallbold"><bean:message key="label.admin.system.classyears"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                      	<myalumni:dropdown type="luSpecific" group="CLASSYEAR" fieldName="fromClassYear" titleKey="label.admin.system.classyears"><bean:write name="classNewsForm" property="fromClassYear"/></myalumni:dropdown>
                      	&nbsp;
                      	To 
                      	&nbsp;
                      	<myalumni:dropdown type="luSpecific" group="CLASSYEAR" fieldName="toClassYear" titleKey="label.admin.system.classyears"><bean:write name="classNewsForm" property="toClassYear"/></myalumni:dropdown>
                      </td>
                </tr> 
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold"><bean:message key="label.admin.system.subject"/>:</td>
                      <td align="center"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td><html:text property="subject" size="40" maxlength="50" titleKey="label.admin.system.subject"/></td>
                </tr>
                
                <c:if test="${MODULE == 'ADMIN'}">
	                <tr>
	                  <td align="center">&nbsp;</td>
	                      <td align="right" class="Smallbold" valign="top"><bean:message key="label.admin.system.systemnews"/>:</td>
	                      <td align="center" valign="top">&nbsp;</td>
	                      <td>
	                      	<html:checkbox property="systemNews" titleKey="label.admin.system.systemnews" value="Y"/>
	                      </td>
	                </tr>                
                </c:if>
                
                <tr>
                  <td align="center">&nbsp;</td>
                      <td align="right" class="Smallbold" valign="top"><bean:message key="label.admin.system.news"/>:</td>
                      <td align="center" valign="top"><html:img page="/images/dot.gif" width="9" height="9"/></td>
                      <td>
                      	<html:textarea property="news" cols="100%" rows="15" titleKey="label.admin.system.news"/>
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
                                <html:submit><bean:message key="button.add"/></html:submit>
                            </c:when>
                            <c:when test="${actionMode == 'update'}">
                                <html:submit><bean:message key="button.update"/></html:submit>
                            </c:when>
                        </c:choose>
                          &nbsp;&nbsp;
                         <c:choose>
                            <c:when test="${MODULE == 'ADMIN'}">
						        <html:link action="/admin/listClassNews?action=listClassNews" onmouseover="window.status=''; return true">
                                	<html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                            	</html:link>
                            </c:when>
                            <c:otherwise>
						        <html:link action="/member/viewMyDesktop?action=displayMyDesktop" onmouseover="window.status=''; return true">
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