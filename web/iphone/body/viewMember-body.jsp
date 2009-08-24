<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<div id="welcome" title="Details" selected="true">
	<myalumni:buildImageTag imageType="avatar"><c:out value="${profile.avatar}"/></myalumni:buildImageTag>
	<p>
		<span class="listen"><bean:message key="label.name"/>:</span>
		<c:out value="${profile.firstName}"/> <c:out value="${profile.lastName}"/>
    	<c:if test="${profile.nickName != ''}">
    		<span class="nickname">(<c:out value="${profile.nickName}"/>)</span>
    	</c:if>		
  	<c:choose>
		<c:when test="${profile.gender == 'M'}">
			<html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
		</c:when>
		<c:when test="${ profile.gender == 'F'}">
			<html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
			<c:if test="${ profile.maidenName != ''}">
				<span class="maiden"><i>(nee <c:out value="${profile.maidenName}"/>)</i></span>
			</c:if>
		</c:when>
	</c:choose>  	
	</p>
	

    
    	<p>
        <span class="listen"><bean:message key="label.location"/>:</span> 
        	
       			<c:if test="${profile.address ne null && profile.address != ''}">
       				<c:choose>
	    	 			<c:when test="${profile.hideAddress ne 'Y'}">   		
	    	    			<c:out value="${profile.address}"/>
	    	    		</c:when>
		       			<c:otherwise>
		       				<span class="graysmall">ON FILE</span>
		       			</c:otherwise>
		        	</c:choose>    	    		
       			</c:if>
		</p>
		
		<p>
        	<span class="listen"><bean:message key="label.birthday"/>:</span>
        	<fmt:formatDate value="${profile.dob}" pattern="MMM d"/>
        </p>
        

        <p>
        <span class="listen"><bean:message key="label.email"/>:</span>&nbsp;
	
     	<c:if test="${profile.email ne null && profile.email != ''}">
    		<c:choose>
  	 				<c:when test="${profile.hideEmail ne 'Y'}">   		
						<!-- Now display email if user viewing info is logged in, other wise request the info -->
						<c:choose>
							<c:when test="${ USER_CONTAINER.token.memberId ne null}">
								<c:out value="${profile.email}"/>							
							</c:when>
							<c:otherwise>
			      				<html:img page="/images/email.gif" titleKey="label.email" align="absmiddle"/>
         			            <span class="graysmall">ON FILE</span>
							</c:otherwise>
						</c:choose>
  	    			</c:when>
      				<c:otherwise>
      					<span class="graysmall">ON FILE</span>
      				</c:otherwise>
       		</c:choose>    	    		
     	</c:if>	
     </p>	
	
	<p>
      <span class="listen"><bean:message key="label.instantmessengers"/>:</span>
      
      <html:img page="/images/icon/messengers.png" border="0" titleKey="label.instantmessengers" align="absmiddle"/>
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

  </p>      

       			        
   <p>         
      <span class="listen"><bean:message key="label.websiteurl"/>:</span>
      		<c:if test="${profile.website != ''}">
      			<c:out value="${profile.website}"/>             
      		</c:if>
  </p>
  
  <p>   
      <span class="listen"><bean:message key="label.yearsatschool"/>:</span> from <c:out value="${profile.yearIn}"/> - <c:out value="${profile.yearOut}"/>
  </p>    

		<%-- Dormitory Name --%>
		<c:if test="${applicationScope.hasDorm == 'Y'}">
			<p>
			<span class="listen"><bean:message key="label.dormitory"/>:</span>
			<c:if test="${profile.dormitory ne null}">
				<c:out value="${profile.dormitoryLabel}"/>
			</c:if>
			</p>
		</c:if>

      <p>
      <span class="listen"><bean:message key="label.career"/>:</span> <c:out value="${profile.career.label}"/>
	</p>
    
    <p>
      <span class="listen"><bean:message key="label.phone"/>:</span>

     	<c:if test="${profile.phone ne null && profile.phone != ''}">
    		<c:choose>
  	 				<c:when test="${profile.hidePhone ne 'Y'}">   		
						<!-- Now display phone number if user viewing info is logged in, other wise request the info -->
						<c:choose>
							<c:when test="${ USER_CONTAINER.token.memberId ne null}">
								<c:out value="${profile.phone}"/>							
							</c:when>
							<c:otherwise>
			      				<html:img page="/images/phone.gif" width="12" titleKey="label.phone" height="16"/>
         			            <c:out value="${profile.phone}"/>
							</c:otherwise>
						</c:choose>
  	    			</c:when>
      				<c:otherwise>
      					<span class="graysmall">ON FILE</span>
      				</c:otherwise>
       		</c:choose>    	    		
     	</c:if>
     </p>			      		
    			
    <p>			
      <span class="listen"><bean:message key="label.lastlogin"/>:</span>&nbsp; <fmt:formatDate value="${profile.lastLogonDate}" type="both" timeStyle="short"/><br>
    </p>  
      
    <p>  
      <span class="listen"><bean:message key="label.comments"/>:</span> <c:out value="${profile.comments}"/>.<br>
	</p>
	
	<br>

</div>
	