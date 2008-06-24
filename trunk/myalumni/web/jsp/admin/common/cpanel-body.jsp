<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
<table width="100%"  border="0" align="center" cellpadding="2" cellspacing="1">
  <tr>
    <td valign="top">    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr align="center">
        <td height="30" colspan="2" class="bg0"><strong><bean:message key="table.title.mydashboard"/></strong></td>
        </tr>
      <tr align="center">
        <td height="30" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td width="50%" valign="top">

        <!-- NEW MEMBERS -->
        <tiles:insert name="/jsp/admin/common/newMembers-body.jsp"/>
        
        <p>&nbsp;</p>
        </td>
        <td width="50%" valign="top">
        
        <!-- NEW PRIVATE MESSAGES -->
        <tiles:insert name="/jsp/admin/common/newPrivateMessages-body.jsp"/>  
        
        <p>&nbsp;</p>
        </td>
      </tr>
      <tr>
        <td valign="top">
        
        <!--  NEW Class NEWS   --> 
        <tiles:insert name="/jsp/admin/common/newClassNews-body.jsp"/>     
        
          <p>&nbsp;</p></td>
        <td valign="top">

              <!-- NEW ONLINE USERS -->
        <tiles:insert name="/jsp/admin/common/newOnlineUsers-body.jsp"/>
            
        <p>&nbsp;</p>    
        </td>
      </tr>
      <tr>
        <td valign="top">
        
		<!-- portlet space -->
        

          <p>&nbsp;</p></td>
        <td valign="top">
        
        <!-- portlet space -->
        
        <p>&nbsp;</p>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</c:if>