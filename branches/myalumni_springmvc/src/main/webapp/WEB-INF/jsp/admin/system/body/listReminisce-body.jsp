<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="view" value="/action/admin/viewReminisce?action=viewReminisce"/>
<c:url var="edit" value="/action/admin/prepareUpdateReminisce?action=prepareUpdateReminisce"/>
<c:url var="delete" value="/action/admin/prepareUpdateReminisce?action=prepareUpdateReminisce"/>


 <html:form action="/admin/prepareAddReminisce?action=prepareAddReminisce"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.reminisce.listremiinisce"/></span></div></td>
                    <td align="right">  
                    	                               
             <html:submit styleClass="button"><bean:message key="button.addreminisce"/></html:submit>                                   
    					&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfReminisce" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listReminisce?action=listReminisce" excludedParams="action" id="row">
                                <display:column titleKey="label.common.action" class="list-action-header">
                                    <a href="<c:out value="${edit}"/>&reminisceId=<c:out value="${row.reminisceId}"/>"><bean:message key="label.common.edit"/></a>
                                </display:column>
                                <display:column property="slangYear" titleKey="label.reminisce.reminisceyears" sortable="true"/>                                
			                    <display:column property="slang" titleKey="label.reminisce.slang" sortable="true"/>
			                    <display:column property="pronounce" titleKey="label.reminisce.pronounciation"/>
			                    <display:column property="meaning" titleKey="label.reminisce.meaning"/>                           
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