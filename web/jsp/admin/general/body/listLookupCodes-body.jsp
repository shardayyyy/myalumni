<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<c:url var="edit" value="/action/admin/prepareUpdateLookupCode?action=prepareUpdateLookupCode"/>

<html:form action="/admin/prepareAddLookupCode?action=prepareAddLookupCode">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/>
                    <span class="Bold">Group: <c:out value="${listOfLookupCodes.description}"/></span> </div></td>
                    <td align="right">
                    <c:if test="${listOfLookupCodes.updateable == 'Y'}">
                        
                            <input name="minLength" type="hidden" value="<c:out value="${listOfLookupCodes.minLength}"/>">
                            <input name="maxLength" type="hidden" value="<c:out value="${listOfLookupCodes.maxLength}"/>">
                            <input name="lookupGroupId" type="hidden" value="<c:out value="${listOfLookupCodes.lookupGroupId}"/>">
                            <input name="description" type="hidden" value="<c:out value="${listOfLookupCodes.description}"/>">
                            <html:submit styleClass="button"><bean:message key="button.addlookupcode"/></html:submit>&nbsp;&nbsp;
                       
                    </c:if>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfLookupCodes.details" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listLookupCodes?action=listLookupCodes" excludedParams="action" id="row">
                                <display:column titleKey="label.common.action" class="list-action-header">
                                	<c:if test="${row.label != '-UNKNOWN-'}">
	                                    <a href="<c:out value="${edit}"/>&lookupCodeId=<c:out value="${row.lookupCodeId}"/>&minLength=<c:out value="${requestScope.listOfLookupCodes.minLength}"/>&maxLength=<c:out value="${requestScope.listOfLookupCodes.maxLength}"/>&lookupGroupId=<c:out value="${requestScope.listOfLookupCodes.lookupGroupId}"/>&description=<c:out value="${requestScope.listOfLookupCodes.description}"/>"><bean:message key="label.common.edit"/></a>
                                    </c:if>
                                </display:column>
                                <display:column property="label" titleKey="label.admin.label" />
                                <display:column titleKey="label.admin.status"  >
                                <c:choose>
                                    <c:when test="${row.status == 'A'}">
                                            <div class="activeStatus"><bean:message key="label.common.active"/></div>
                                    </c:when>
                                    <c:when test="${row.status == 'I'}">
                                            <div class="inactiveStatus"><bean:message key="label.common.inactive"/></div>
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