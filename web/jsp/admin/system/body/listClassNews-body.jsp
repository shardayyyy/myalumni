<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="view" value="/action/admin/viewClassNews?action=viewClassNews"/>
<c:url var="edit" value="/action/admin/prepareUpdateClassNews?action=prepareUpdateClassNews"/>
<c:url var="delete" value="/action/admin/prepareUpdateClassNews?action=prepareUpdateClassNews"/>


 <html:form action="/admin/prepareAddClassNews?action=prepareAddClassNews"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.admin.system.listclassnews"/></span></div></td>
                    <td align="right">  
                    	                               
                         	<html:submit><bean:message key="button.addclassnews"/></html:submit>                                   
    					&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfClassNews" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listClassNews?action=listClassNews" excludedParams="action" id="row">
                                <display:column titleKey="label.common.action" class="list-action-header">
                                    <a href="<c:out value="${edit}"/>&classNewsId=<c:out value="${row.classNewsId}"/>"><bean:message key="label.common.edit"/></a> | 
                                    <a href="<c:out value="${view}"/>&classNewsId=<c:out value="${row.classNewsId}"/>"><bean:message key="label.common.view"/></a>
                                </display:column>
                                <display:column property="fromClassYear" titleKey="label.admin.system.fromclassyear" sortable="true"/>
                                <display:column property="toClassYear" titleKey="label.admin.system.toclassyear" sortable="true"/>                                
			                    <display:column titleKey="label.admin.system.subject" sortable="true">
			                     		<c:if test="${row.systemNews == 'Y'}">	
				                     		<html:img page="/images/new.gif" align="absbottom"/>&nbsp;	
				                     	</c:if>
					                     	<c:out value="${row.subject}"/>
			                     		<c:if test="${row.systemNews == 'Y'}">	
				                     		<html:img page="/images/new.gif" align="absbottom"/>&nbsp;	
				                     	</c:if>	                     	
			                     </display:column> 
                                <display:column titleKey="label.admin.system.author" sortable="true">
                                	<c:out value="${row.author.firstName}"/> <c:out value="${row.author.lastName}"/>
                                </display:column>                                
                                <display:column titleKey="label.admin.status">
                                    <c:choose>
                                        <c:when test="${row.status == 'A'}">
                                                <div class="activeStatus"><bean:message key="label.common.active"/></div>
                                        </c:when>
                                        <c:when test="${row.status == 'I'}">
                                                <div class="inactiveStatus"><bean:message key="label.common.inactive"/></div>
                                        </c:when>
			                            <c:when test="${row.status == 'X'}">
			                                    <div class="inactiveStatus"><bean:message key="label.common.approvalneeded"/></div>
			                            </c:when>                                         
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