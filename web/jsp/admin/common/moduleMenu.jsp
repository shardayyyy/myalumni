<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">

		<%-- START The upper part of the tabs --%>
		<tiles:insert page="/jsp/admin/common/adminTabUpper-body.jsp"/>
		<%-- END The upper part of the tabs --%>


		<tr>
			<td bgcolor="#ECEEDC"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
			<td bgcolor="#ECEEDC"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
			<td bgcolor="#ECEEDC"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
			<td bgcolor="#ECEEDC"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
		</tr>


		<%-- START The lower part of the tabs--%>
		<tiles:insert page="/jsp/admin/common/adminTabLower-body.jsp"/>
		<%-- END The lower part of the tabs --%>

		<tr>
			<%-- Desktop Module --%>
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
			
			
			<%-- Members Module --%>
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
			
			
			<%-- Security Module --%>                    
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
			
			
			<%-- General Module --%>
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
			
			
			<%-- System Module --%>
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
			
			<%-- Admin Module --%>                       
			<td height="2"></td>
			<td height="2"></td>
			<td height="2"></td>
                     
		</tr>
                
                
      <c:set var="columnTotal" value="0"/>          
                
		<tr>
		                <%-- MyDesktop Module --%>
                        <c:choose>
                            <c:when test="${adminTab == 'desktop'}">
                                <c:set var="columnTotal" value="${columnTotal+12}"/>
                                <td height="2" ></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>
                            </c:when>
                            <c:otherwise>
                            	<c:set var="columnTotal" value="${columnTotal+6}"/>
                                <td height="2" bgcolor="#6E8F48"></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>                        
                            </c:otherwise>
                        </c:choose>
                        
                        
                    <%-- Members Module --%>
                        <c:choose>
                            <c:when test="${adminTab == 'members'}">
                                <c:set var="columnTotal" value="${columnTotal+12}"/>
                                <td height="2" ></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>
                            </c:when>
                            <c:otherwise>
                            	<c:set var="columnTotal" value="${columnTotal+6}"/>
                                <td height="2" bgcolor="#6E8F48"></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>                        
                            </c:otherwise>
                        </c:choose>

                                
                    <%-- Security Module --%>                          
                        <c:choose>
                            <c:when test="${adminTab == 'security'}">
                                <c:set var="columnTotal" value="${columnTotal+12}"/>
                                <td height="2" ></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>
                            </c:when>
                            <c:otherwise>
                            	<c:set var="columnTotal" value="${columnTotal+6}"/>
                                <td height="2" bgcolor="#6E8F48"></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>                        
                            </c:otherwise>
                        </c:choose> 

                       
                            
                    <%-- General Module --%>                             
                       <c:choose>
                           <c:when test="${adminTab == 'general'}">
                               <c:set var="columnTotal" value="${columnTotal+12}"/>
                               <td height="2" ></td>
                               <td height="2" bgcolor="#6E8F48"></td> 
                               <td height="2" bgcolor="#6E8F48"></td>
                           </c:when>
                           <c:otherwise>
                           	   <c:set var="columnTotal" value="${columnTotal+6}"/>
                               <td height="2" bgcolor="#6E8F48"></td>
                               <td height="2" bgcolor="#6E8F48"></td> 
                               <td height="2" bgcolor="#6E8F48"></td>                        
                           </c:otherwise>
                       </c:choose> 

                                 
                                 
                      <%-- SYSTEM --%>                             
                      <c:choose>
                          <c:when test="${adminTab == 'system'}">
                          	  <c:set var="columnTotal" value="${columnTotal+12}"/>
                              <td height="2" ></td>
                              <td height="2" bgcolor="#6E8F48"></td> 
                              <td height="2" bgcolor="#6E8F48"></td>
                          </c:when>
                          <c:otherwise>
                          	  <c:set var="columnTotal" value="${columnTotal+6}"/>
                              <td height="2" bgcolor="#6E8F48"></td>
                              <td height="2" bgcolor="#6E8F48"></td> 
                              <td height="2" bgcolor="#6E8F48"></td>                        
                          </c:otherwise>
                      </c:choose>      
                            
                            
                    <%-- Admin Module --%>                       
                        <c:choose>
                            <c:when test="${adminTab == 'admin'}">
                            	<c:set var="columnTotal" value="${columnTotal+12}"/>
                                <td height="2" ></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>
                            </c:when>
                            <c:otherwise>
                            	<c:set var="columnTotal" value="${columnTotal+6}"/>
                                <td height="2" bgcolor="#6E8F48"></td>
                                <td height="2" bgcolor="#6E8F48"></td> 
                                <td height="2" bgcolor="#6E8F48"></td>                        
                            </c:otherwise>
                        </c:choose>      

		</tr>
		<tr>
			<td colspan="<c:out value="${columnTotal}"/>">&nbsp;</td>
		</tr>
	</table>
