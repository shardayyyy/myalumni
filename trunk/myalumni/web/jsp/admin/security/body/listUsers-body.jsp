<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>




<c:url var="edit" value="/action/admin/prepareUpdateUser?action=prepareUpdateUser"/>
<c:url var="view" value="/action/admin/viewUser?action=viewUser"/>

<tiles:insert name="/jsp/admin/security/common/alphabelt-common.jsp"/>

<html:form action="/admin/prepareAddUser?action=prepareAddUser">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                    </tr>
                    <tr class="bg0">
                      <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="30" width="10"/><span class="Bold">Users</span></div></td>
                      <td align="right">                   
                            <html:submit><bean:message key="button.adduser" /></html:submit>&nbsp;&nbsp;           
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                    </tr>
                    <tr>
                      <td colspan="2" >
                      
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfUsers" cellpadding="3" cellspacing="1" pagesize="20" id="row" requestURI="/action/admin/listUsers?action=listUsers" excludedParams="action">
                            <display:column titleKey="label.common.action">
                               <center> <a href="<c:out value="${view}"/>&memberId=<c:out value="${row.memberId}"/>"><bean:message key="label.common.view"/></a> | <a href="<c:out value="${edit}"/>&memberId=<c:out value="${row.memberId}"/>"><bean:message key="label.common.edit"/></a> 
                               | 
                            	<c:url var="resetPassword" value="/action/admin/resetMemberPassword" >
								  <c:param name="action" value="resetMemberPassword" />
								  <c:param name="memberId" value="${row.memberId}" />
								</c:url>
								<a href="<c:out value="${resetPassword}"/>" title="Reset Password">Reset</a>
                               </center>
                            </display:column>
                            <display:column property="firstName" titleKey="label.firstname" sortable="true"/>
                            <display:column property="lastName" titleKey="label.lastname" sortable="true"/>
                            <display:column property="memberUserName" titleKey="label.admin.users.username" sortable="true"/>
                            <display:column titleKey="label.admin.users.email" sortable="true">
                            	<c:choose>
                            	<c:when test="${row.email eq '' or row.email eq null}">
                            		<span class="redsmall">No Email</span>
                            	</c:when>
                            	<c:otherwise>
                            		<c:out value="${row.email}"/>
                            	</c:otherwise>
                            </c:choose>
                            </display:column>
                            <display:column titleKey="label.admin.users.datecreated" sortable="true">
                                <fmt:formatDate dateStyle="medium" type="both" timeStyle="short" value="${row.creationDate}"/>
                            </display:column>
                            <display:column titleKey="label.admin.users.lastlogondate" sortable="true">
                                <fmt:formatDate dateStyle="medium" type="both" timeStyle="short" value="${row.lastLogonDate}"/>
                            </display:column>
                            <display:column titleKey="label.admin.users.isadmin" sortable="true">
                                <c:out value="${row.isAdmin}"/>
                            </display:column>
                            <display:column titleKey="label.admin.status" sortable="true">
                                <c:choose>
				       					<c:when test="${row.memberStatus == 'A'}">
											<span class="greensmall">Activated</span>
										</c:when>					
										<c:when test="${row.memberStatus == 'D'}">
											<span class="graysmall">Deactivated</span>
										</c:when>
										<c:when test="${row.memberStatus == 'U'}">
											<span class="greensmall">New</span>
										</c:when>
										<c:when test="${row.memberStatus == 'L'}">
											<span class="redsmall">(Locked)</span>
										</c:when>
										<c:otherwise>
											<span class="redsmall">(Unknown Status)</span>
										</c:otherwise>	                                 
                                </c:choose>                            
                            </display:column>
                        </display:table>  
                    </fmt:bundle>                      
                    
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
</table>
 </html:form>
 
 <tiles:insert name="/jsp/admin/security/common/alphabelt-common.jsp"/>

