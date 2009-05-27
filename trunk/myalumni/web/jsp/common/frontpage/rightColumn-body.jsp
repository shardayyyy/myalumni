<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/twitter-taglib.tld" prefix="twitter" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<c:url var="miniProfile" value="/action/member/displayMiniProfile?action=displayMiniProfile"/>

			
	<table width="100%"  border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
			<tr>
		        <td height="30" class="bg0">Others logged on Now: </td>
		    </tr>

			<c:set var="num_rec" scope="page" value="-1"/>
			<logic:notEmpty name="onlineusers">
				<logic:iterate id="user" name="onlineusers" indexId="pIdx">
				    <tr class="portlet-section-body">
				        <td >				
							<div class="blacksmall"><a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${user.memberUserName}"/>"  onclick="newPopup(this.href,'name');return false" title="View <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> details"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> (<c:out value="${user.yearOut}"/>)</a></div>
							<c:set var="num_rec" value="1"/>
						</td>
				   </tr>
				</logic:iterate>
			</logic:notEmpty>
        	<c:if test="${num_rec == '-1'}">
			    <tr class="portlet-section-body">
			        <td >	        	
			            	<bean:message key="message.none"/>
					</td>
			   </tr>            	
	        </c:if>
	</table>

<br>

		<myalumni:displayLatestMembers tableWidth="100%"/> 
		
		<br><br>
		
	<twitter:displayMyTweetsTag/>
	<table width="100%"  border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
			<tr>
		        <td height="30" class="bg0"><c:out value="${TwitterVO.twitteruser}"/> Public TimeLine: </td>
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
