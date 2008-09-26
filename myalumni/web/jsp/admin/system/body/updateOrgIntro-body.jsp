<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>



<script type="text/javascript">

        window.onload = function()
        {
                // Automatically calculates the editor base path based on the _samples directory.
                // This is usefull only for these samples. A real application should use something like this:
                // oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
                var sBasePath = '<%=request.getContextPath()%>/html/FCKeditor/';
                var oFCKeditor = new FCKeditor( 'orgIntro' ) ;
                oFCKeditor.BasePath	= sBasePath ;
                oFCKeditor.Height	= 400 ;
                oFCKeditor.ToolbarSet   =  'Default';  // Default, Basic
                oFCKeditor.ReplaceTextarea() ;
        }

</script>


<html:form action="/admin/updateOrgIntro?action=updateOrgIntro">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
          <tr>
            <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold">
 
                                <bean:message key="label.common.update"/> <bean:message key="label.admin.org.intro"/>
              
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
                  <td width="15%"><html:img page="/images/spacer.gif" height="8" width="15"/></td>
                      <td width="85%">&nbsp;</td>
                    </tr>
					
				<tr>
                      <td align="right" class="Smallbold" valign="top"><bean:message key="label.admin.org.intro"/>:</td>
                      <td><html:textarea property="orgIntro" cols="90"   /></td>
                </tr> 
		
                <tr>
                      <td align="center">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                <tr>
                  <td align="center">&nbsp;</td>
                      <td colspan="2">
                      		<html:submit styleClass="button"><bean:message key="button.update"/></html:submit>
                          	&nbsp;&nbsp;
                            <html:link page="/jsp/admin/system/index.jsp" onmouseover="window.status=''; return true">
                                <html:img page="/images/cancel.png" border="0" align="absbottom"/>    
                            </html:link>
                        </td>
                    </tr>
                <tr>
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