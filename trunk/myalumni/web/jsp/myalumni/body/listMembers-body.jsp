<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>

	<h1 id="pageTitle">MyAlumni - <c:out value="${ORG_NAME}" default="SCHOOL NAME HERE"/></h1>
	
<c:url var="searchAgain" value="/jsp/myalumni/search.jsp"/>
<c:url var="miniProfile" value="/action/member/displayMiniProfile?action=displayMiniProfile"/>
<c:choose>
	<c:when test="${ USER_CONTAINER.token.memberId ne null}">
		<c:url var="contactMember" value="/action/member/prepareContact?action=prepareContactMessage">
			<c:param name="subject" value="${USER_CONTAINER.token.firstName} ${USER_CONTAINER.token.lastName}  would like to make contact."/>
		</c:url>
	</c:when>
	<c:otherwise>
		<c:url var="contactMember" value="/jsp/myalumni/denyAccess.jsp?"/>
	</c:otherwise>
</c:choose>




 
 <fmt:bundle basename="ApplicationResources">

<display:table name="requestScope.LIST_OF_MEMBERS" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/member/memberSearch?action=searchForMembers" excludedParams="action" id="member">
		<%-- Name (click on name for details for popup) --%>
		<display:column title="Name (click on name for details for popup)" sortable="true">
	        <c:choose>
	                <c:when test="${member.gender eq 'M'}">
	                        <html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
	                  </c:when>
	                  <c:otherwise>
	                        <html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
	                </c:otherwise>
	        </c:choose>
		
		    <a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${member.memberUserName}"/>" title="View <c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/> details" onclick="newPopup(this.href,'name');return false"><c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/></a>
		
		
		    <c:if test="${member.nickName ne '' && member.nickName ne null}">
		    	<span class="nickname">(<c:out value="${member.nickName}"/>)</span>
		    </c:if>
		    <c:if test="${member.maidenName ne '' && member.gender eq 'F'}">
		    	<span class="maiden"><i>(nee <c:out value="${member.maidenName}"/>)</i></span>
		    </c:if>		    

			<c:if test="${member.avatar ne '' && member.avatar ne null}">
        			<img src="<%=request.getContextPath() %>/images/avatar_available.jpg" align="middle" border="0" onmouseover="overlib('&lt;img src=\'<%=request.getContextPath() %>/upload/memberavatars/<c:out value="${member.avatar}"/>\'&gt;', CAPBELOW,
        			CAPTION,'&lt;div align=\'center\'&gt;<c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/>&lt;/div&gt;',
        			WRAP, TEXTPADDING,1, CAPTIONPADDING,5, HAUTO, VAUTO, BASE,2);"
        			onmouseout="nd();">
			</c:if>
	
		</display:column>
		
		<%-- Year --%>
		<display:column titleKey="label.yearinyearout" sortable="true">
			<c:out value="${member.yearIn}"/> - <c:out value="${member.yearOut}"/>
		</display:column>
		
		<%-- Contacts --%>
		<display:column title="Contacts">
					<%-- Dormitory Name --%>
					<c:if test="${applicationScope.hasDorm == 'Y'}">
	                    <c:if test="${member.dormitory ne null}">
							<span class="graysmall">[<c:out value="${member.dormitoryLabel}"/>]</span>
	                    </c:if>
                    </c:if>
			        
			        <%-- Instant Messengers --%>
					<c:if test="${member.hideIm ne 'Y'}">
 						<%-- Instant Messenger --%>
				        <c:if test="${member.messengers ne null && !empty member.messengers}">
				        <html:img page="/images/icon/messengers.png" border="0" titleKey="label.instantmessengers"/>
				        </c:if>
				    </c:if>
			
					<c:if test="${member.hideEmail ne 'Y'}">
				        <c:if test="${member.email ne '' && member.email ne null}">
				                <html:img page="/images/email.gif" titleKey="label.email" align="absmiddle"/>
				        </c:if>
			        </c:if>
			
					<c:if test="${member.hidePhone ne 'Y'}">
				        <c:if test="${member.phone ne '' && member.phone ne null}">
				                <html:img page="/images/phone.gif" titleKey="label.phone" align="absmiddle"/>
				        </c:if>
			        </c:if>
			
			        <c:if test="${member.website ne '' && member.website ne null}">
			                <a href="<c:out value="${member.website}"/>" target="_blank"><html:img page="/images/icon/weburl.png" border="0" titleKey="label.websiteurl"/></a>
			        </c:if>
		</display:column>
		
		<%-- Location --%>
		<display:column title="Location">
		<c:choose>
			<c:when test="${member.hideAddress ne 'Y'}">
				<c:out value="${member.address}"/>, <c:out value="${member.country.label}"/>
			</c:when>
			<c:otherwise>
				<span class="graysmall">ON FILE</span>
			</c:otherwise>
		</c:choose>
		</display:column>
		
		<%-- Request Information --%>
		<display:column title="Request Information"  >
	      	<c:choose>
	                <c:when test="${member.email ne '' && member.email ne null}">
	                         <a href="<c:out value="${contactMember}"/>&messageToUserId=<c:out value="${member.memberId}"/>">
	                         <html:img page="/images/icon/pm.png" border="0" align="absbottom" title="Send Private Message"/></a>
	                  </c:when>
	                  <c:otherwise>
	                        No Email
	                </c:otherwise>
	        </c:choose>
		</display:column>
  </display:table>
  <table width="100%" >
  	<tr>
  		<td>
			<center><h1><strong><a href="<c:out value="${searchAgain}"/>">Search Again !!!</a></strong></h1></center>
  		</td>
  	</tr>
  </table>
</fmt:bundle>

