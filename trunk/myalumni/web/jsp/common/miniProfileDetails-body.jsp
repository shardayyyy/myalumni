<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>

<c:set var="_male">
	<%= BaseConstants.GENDER_MALE %>
</c:set>

<c:set var="_female">
	<%= BaseConstants.GENDER_FEMALE %>
</c:set>


	<c:url var="makeContact" value="/action/member/prepareContact" >
	  <c:param name="action" value="prepareContactMessage" />
	  <c:param name="memberUserName" value="${profile.memberUserName}" />
	  <c:param name="toMemberLastName" value="${profile.lastName}" />
	  <c:param name="toMemberFirstName" value="${profile.firstName}" />
	</c:url>

<c:url var="myalumniJS" value="/js/global.js" />

<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${myalumniJS}"/>"></script>




<c:url var="activateLink" value="/jsp/myalumni/requestAccess.jsp"/>

<table width="450" height="250" border="0" align="center" cellpadding="3" cellspacing="0" class="tborder">
  <tr class="portlet-section-body">
    <td width="26%" align="center" valign="top">
    		<myalumni:buildImageTag imageType="avatar"><c:out value="${profile.avatar}"/></myalumni:buildImageTag>
    </td>
    <td width="74%" valign="top"> <p><span class="fieldlabel"><bean:message key="label.name"/>:</span>
    	<span class="blacksmall"><c:out value="${profile.firstName}"/> <c:out value="${profile.lastName}"/></span>

    	<c:if test="${profile.nickName != ''}">
    		<span class="nickname">(<c:out value="${profile.nickName}"/>)</span>
    	</c:if>


	<c:choose>
		<c:when test="${profile.gender == _male}">
			<html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
		</c:when>
		<c:when test="${ profile.gender == _female}">
			<html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
			<c:if test="${ profile.maidenName != ''}">
				<span class="maiden"><i>(nee <c:out value="${profile.maidenName}"/>)</i></span>
			</c:if>
		</c:when>
	</c:choose>

    	<br> 
        <span class="fieldlabel"><bean:message key="label.location"/>:</span> 
        	
       			<c:if test="${profile.address ne null && profile.address != ''}">
       				<c:choose>
	    	 			<c:when test="${profile.hideAddress ne 'Y'}">   		
	    	    			<span class="blacksmall"><c:out value="${profile.address}"/></span>
	    	    		</c:when>
		       			<c:otherwise>
		       				<span class="graysmall">ON FILE</span>
		       			</c:otherwise>
		        	</c:choose>    	    		
       			</c:if>
			<br>
			
        <span class="fieldlabel"><bean:message key="label.birthday"/>:</span>
        	<span class="blacksmall"><fmt:formatDate value="${profile.dob}" pattern="MMM d"/></span><br>
        	
       
        	
        <span class="fieldlabel"><bean:message key="label.email"/>:</span>&nbsp;
	
     	<c:if test="${profile.email ne null && profile.email != ''}">
    		<c:choose>
  	 				<c:when test="${profile.hideEmail ne 'Y'}">   		
						<!-- Now display email if user viewing info is logged in, other wise request the info -->
						<c:choose>
							<c:when test="${ USER_CONTAINER.token.memberId ne null}">
								<span class="blacksmall"><c:out value="${profile.email}"/></span>							
							</c:when>
							<c:otherwise>
			      				<html:img page="/images/email.gif" width="15" titleKey="label.email" height="15" align="absmiddle"/>
         			            <a href="javascript:window.opener.location.href='<c:out value="${makeContact}"/>';window.close();">(<span class="redsmall">Contact <c:out value="${profile.firstName}"/>.</span>)</a>
							</c:otherwise>
						</c:choose>
  	    			</c:when>
      				<c:otherwise>
      					<span class="graysmall">ON FILE</span>
      				</c:otherwise>
       		</c:choose>    	    		
     	</c:if>		
<br>		
      <span class="fieldlabel"><bean:message key="label.instantmessengers"/>:</span>
      <span class="blacksmall">
			<c:choose>
					<c:when test="${profile.hideIm ne 'Y'}">   		
						<!-- Now display messengers if user viewing info is logged in, other wise request the info -->
						<c:choose>
							<c:when test="${ USER_CONTAINER.token.memberId ne null}">
								<logic:notEmpty name="profile" property="messengers">
			                		<logic:iterate id="im" name="profile" property="messengers" indexId="recIdx">
						            	<c:out value="${im.messenger.label}"/>,
			                		</logic:iterate>
			                	</logic:notEmpty>								
							</c:when>
							<c:otherwise>
								<span class="graysmall">ON FILE</span>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<span class="graysmall">ON FILE</span>
					</c:otherwise>
			</c:choose>   
	</span>
        

       			        
            	<br>
      <span class="fieldlabel"><bean:message key="label.websiteurl"/>:</span>
      		<c:if test="${profile.website != ''}">
      			<html:img page="/images/www.gif" width="15" titleKey="label.websiteurl" height="15" align="absmiddle" />
                        <span class="blacksmall"><a href="#" target="_blank" onclick="window.open('<c:out value="${profile.website}"/>');"><c:out value="${profile.website}"/></a></span>
      		</c:if>
      		<br>
      <span class="fieldlabel"><bean:message key="label.yearsatschool"/>:</span> from <c:out value="${profile.yearIn}"/>-<c:out value="${profile.yearOut}"/>
      

		<%-- Dormitory Name --%>
		<c:if test="${applicationScope.hasDorm == 'Y'}">
			<br><span class="fieldlabel"><bean:message key="label.dormitory"/>:</span>
			<c:if test="${profile.dormitory ne null}">
				<span class="blacksmall">[<c:out value="${profile.dormitoryLabel}"/>]</span>
			</c:if>
		</c:if>

      <br>
      <span class="fieldlabel"><bean:message key="label.career"/>:</span> <c:out value="${profile.career.label}"/><br>
      <span class="fieldlabel"><bean:message key="label.phone"/>:</span>

     	<c:if test="${profile.phone ne null && profile.phone != ''}">
    		<c:choose>
  	 				<c:when test="${profile.hidePhone ne 'Y'}">   		
						<!-- Now display phone number if user viewing info is logged in, other wise request the info -->
						<c:choose>
							<c:when test="${ USER_CONTAINER.token.memberId ne null}">
								<span class="blacksmall"><c:out value="${profile.phone}"/></span>							
							</c:when>
							<c:otherwise>
			      				<html:img page="/images/phone.gif" width="12" titleKey="label.phone" height="16"/>
         			            <a href="javascript:window.opener.location.href='<c:out value="${makeContact}"/>';window.close();"> (<span class="redsmall">Contact <c:out value="${profile.firstName}"/>.</span>)</a>						
							</c:otherwise>
						</c:choose>
  	    			</c:when>
      				<c:otherwise>
      					<span class="graysmall">ON FILE</span>
      				</c:otherwise>
       		</c:choose>    	    		
     	</c:if>
     			      		
    			<br>
      <span class="fieldlabel"><bean:message key="label.lastlogin"/>:</span>&nbsp; <span class="blacksmall"><fmt:formatDate value="${profile.lastLogonDate}" type="both" timeStyle="short"/></span><br>
      <span class="fieldlabel"><bean:message key="label.comments"/>:</span> <span class="blacksmall"><c:out value="${profile.comments}"/>.</span><br>
	<br>
	<c:if test="${ profile.memberStatus == 'V'}">
	<span class="redhighlight"><bean:message key="label.accountnotactivated"/>: <a href="javascript:window.opener.location.href='<c:out value="${activateLink}"/>';window.close();">If you are <c:out value="${profile.firstName}"/> <c:out value="${profile.lastName}"/>, Please activate your account.</a></span>
		<br><i>IP Address Logged</i>
	</c:if>

</p>      </td>
  </tr>

</table>
<form name="form1" method="post" action="">
  <div align="center">
    <input class="button" class="button" name="Submit" type="submit" onClick="window.close();" value="Close Window">
  </div>
</form>
