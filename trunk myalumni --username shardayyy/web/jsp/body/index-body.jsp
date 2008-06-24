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
	<div align="center">
      
      <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://active.macromedia.com/flash2/cabs/swflash.cab#version=4,0,0,0" width="299" height="69">
        <param name="movie" value="<html:rewrite page='/images/flash/fgc2.swf'/>">
        <param name="quality" value="high">
        <param name=bgcolor value=#f5f5f5>
        <embed src="<html:rewrite page='/images/flash/fgc2.swf'/>" quality="high" width="299" height="69"  type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"> </embed>
      </object>
      

	</td>
    <td width="15%" valign="top">

    	<%-- Birthday Announcements --%>
	<tiles:insert name="/jsp/common/frontpage/rightColumn-body.jsp" flush="true"/>


 
    </td>
  </tr>

</table>
