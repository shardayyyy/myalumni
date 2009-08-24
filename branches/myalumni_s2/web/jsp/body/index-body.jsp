<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/twitter-taglib.tld" prefix="twitter" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>



<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="25" colspan="3" class="bg0">&nbsp;</td>
  </tr>
  <tr class="portlet-section-body">
    <td width="15%" valign="top">

    	<tiles:insert name="/jsp/common/frontpage/leftColumn-body.jsp" flush="true"/>

    </td>
    <td width="65%" valign="top">

	<%--   INTRO --%>
	<tiles:insert name="/jsp/common/frontpage/intro-body.jsp" flush="true"/>


	<br/>
		<br/>



    	<p></p>
    	<%-- Birthday Announcements --%>
		<myalumni:buildBirthdayList tableWidth="90%"/>
	
	
	<br><br>
	
		
	<twitter:displayMyTweetsTag/>
	<table width="80%"  border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
			<tr>
		        <td height="30" class="bg0"><c:out value="${TwitterVO.twitteruser}"/> Twitter Public TimeLine: </td>
		    </tr>

			<c:set var="num_rec1" scope="page" value="-1"/>
			<logic:notEmpty name="TwitterVO">
				<logic:iterate id="tweet" name="TwitterVO" property="mytweets" indexId="pIdx">
				    <tr class="portlet-section-body">
				        <td >				
							<div class="blacksmall"><c:out value="${tweet}" escapeXml="false"/></div>
							<c:set var="num_rec1" value="1"/>
						</td>
				   </tr>
				</logic:iterate>
			</logic:notEmpty>
        	<c:if test="${num_rec1 == '-1'}">
			    <tr class="portlet-section-body">
			        <td >	        	
			            	<bean:message key="message.none"/>
					</td>
			   </tr>            	
	        </c:if>
	</table>
	
	
	</td>
    <td width="20%" valign="top">

    	<%-- Birthday Announcements --%>
	<tiles:insert name="/jsp/common/frontpage/rightColumn-body.jsp" flush="true"/>


 
    </td>
  </tr>

</table>
