<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


	

<%--  About Me display  --%>
<tiles:insert name="/jsp/common/profileHeader-body.jsp"/>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
       <tr valign="top"> 
       <td width="50%" height="50%"> 
	   
	   		      <%--  About Me display  --%>
			  <tiles:insert name="/jsp/common/aboutMeDisplay-body.jsp"/>
	   
	   		      <%--  About Me display  --%>
			  <tiles:insert name="/jsp/common/accountActivitiesDisplay-body.jsp"/>	   
	            </td> 
      <td width="50%" height="50%">     
	  	   		      <%--  Instant Messengers display  --%>
			  <tiles:insert name="/jsp/common/instantMessengersDisplay-body.jsp"/>	 
	     
	  	   		      <%--  Your Location display  --%>
			  <tiles:insert name="/jsp/common/yourLocationDisplay-body.jsp"/>
			  
			  <p>&nbsp;</p>
			  
			<c:choose>  
				<c:when test="${ MODULE == 'ADMIN'}">
					
				</c:when>
				<c:otherwise>
					  <html:form  action="/member/viewMyDesktop?action=displayMyDesktop">
						<center><html:submit>
							<bean:message key="button.back"/>
							</html:submit>
						</center>
					  </html:form>			
				</c:otherwise>
			</c:choose>
			
      </td> 
      </tr> 
    </table>