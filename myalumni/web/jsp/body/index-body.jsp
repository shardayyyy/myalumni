<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>



<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="25" colspan="3" class="bg0">&nbsp;</td>
  </tr>
  <tr class="portlet-section-body">
    <td width="15%" valign="top">

    	<tiles:insert name="/jsp/common/frontpage/leftColumn-body.jsp" flush="true"/>

    </td>
    <td width="70%" valign="top">

	<%--   INTRO --%>
	<tiles:insert name="/jsp/common/frontpage/intro-body.jsp" flush="true"/>

	<%--   PRINCIPLES 
	<tiles:insert name="/jsp/common/frontpage/principlespix-body.jsp" flush="true"/>--%>
	
	<br/>
		<br/>



    	<p></p>
    	<%-- Birthday Announcements --%>
		<myalumni:buildBirthdayList tableWidth="90%"/>
	
	<br><br>
	</td>
    <td width="15%" valign="top">

    	<%-- Birthday Announcements --%>
	<tiles:insert name="/jsp/common/frontpage/rightColumn-body.jsp" flush="true"/>


 
    </td>
  </tr>

</table>
