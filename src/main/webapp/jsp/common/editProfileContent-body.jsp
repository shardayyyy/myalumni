<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<%--  About Me display  --%>
<tiles:insert name="/jsp/common/profileHeader-body.jsp"/>


<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr valign="top">
    <td width="100%">

      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="50%" valign="top">
		      <%--  About Me   --%>
			 <%-- <tiles:insert name="/jsp/common/aboutMeUpdate-body.jsp"/> --%>
			  <tiles:insert name="/jsp/common/aboutMeIn-body.jsp"/>
		  <p>&nbsp;</p></td>
          <td width="50%" valign="top">
		  
		  <%--  Instant Messengers  --%>
		<tiles:insert name="/jsp/common/instantMessengersIn-body.jsp"/>
		
		<%--  Your Location  --%>
		<tiles:insert name="/jsp/common/yourLocationIn-body.jsp"/>

		<%--  Button  --%>
		<tiles:insert name="/jsp/common/commonBottomButtons-body.jsp"/>
		
	  </td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
    </tr>
</table>