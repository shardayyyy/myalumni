<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="iphoneHome" value="/iphone/index.jsp"/>
<div id="welcome" title="<tiles:getAsString name="title" ignore="true"/>" selected="true">
	<ul>
		<c:set var="num_rec" scope="page" value="-1"/>
		<logic:present name="listOfIphoneMembers">
			<logic:iterate id="mem" name="listOfIphoneMembers">
			
				<c:url var="searchForMembersLink" value="/action/iphone/viewMember" >
					<c:param name="action" value="viewMember" />
					<c:param name="memberId" value="${mem.memberId}" />
				</c:url>
	                       
	            <li>
	            	<a href="<c:out value="${searchForMembersLink}"/>">
	            		<p>
	            			<c:choose>
	            				<c:when test="${sortType eq 'firstName'}">
	            					<font color="grey"><c:out value="${mem.firstName}"/></font> <c:out value="${mem.lastName}"/>
	            				</c:when>
	            				<c:when test="${sortType eq 'lastName'}">
	            					<font color="grey"><c:out value="${mem.lastName}"/></font> <c:out value="${mem.firstName}"/>
	            				</c:when>            				
	            				<c:otherwise>
	            					<c:out value="${mem.firstName}"/> <c:out value="${mem.lastName}"/>
	            				</c:otherwise>
	            			</c:choose>
	            			
	            			<c:if test="${mem.avatar ne null && mem.avatar ne ''}">
	            				<html:img page="/images/avatar_available.jpg" align="absmiddle"/>
	            			</c:if>
	            		</p>
	            	
	            	</a>
	            </li>
			<c:set var="num_rec" value="1"/>
			</logic:iterate>
		</logic:present>
		
		<c:if test="${num_rec == '-1'}">
			<li><p>No Members Found.</p></li>
			<li><a href="<c:out value="${iphoneHome}"/>"><div class="list-link"><p>&laquo;&laquo; Back</p></div></a></li>
		</c:if>               
	</ul>  	

	<%--	<div id="spinner" style="display:block"><p><html:img page="/iphone/img/spinner.gif" align="absmiddle" /><br />Loading...</p></div>
	--%>
		