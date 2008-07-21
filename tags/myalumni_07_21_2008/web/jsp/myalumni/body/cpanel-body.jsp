<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr valign="top">
      <td width="80%" height="20"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top">
              <%--  Class News  --%>
              <tiles:insert name="/jsp/common/listClassNews-body.jsp"/>
               

              <%--  RSS  --%>  
              <tiles:insert name="/jsp/common/rssFeed.jsp"/>
             

              <p>&nbsp;</p></td>
          </tr>
      </table></td>
      <td width="20%">
           <%--  control panel   --%>
           <tiles:insert name="/jsp/common/controlpanel-body.jsp"/>

        </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>

