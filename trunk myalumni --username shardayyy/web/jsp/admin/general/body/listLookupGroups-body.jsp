<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="editgroup" value="/action/admin/prepareUpdateLookupGroup?action=prepareUpdateLookupGroup"/>
<c:url var="listCodes" value="/action/admin/listLookupCodes?action=listLookupCodes"/>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/>
                    <span class="Bold">List of LookUp Groups</span> </div></td>
                    <td align="right">&nbsp;&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="com.medxeed.neptune.modules.admin.presentation.resources.AdminResources">
                        <display:table name="requestScope.listOfLookupGroups" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listLookupGroups?action=listLookupGroups" excludedParams="action" id="row">
                        <%--  <c:if test="${row.updateable == 'Y'}"> --%>
                                <display:column titleKey="label.common.action" class="list-action-header">
                                    <a href="<c:out value="${listCodes}"/>&lookupGroupId=<c:out value="${row.lookupGroupId}"/>"><bean:message key="label.admin.managelist"/></a>
                                </display:column>
                                <display:column property="description" titleKey="label.admin.description"/>
                         <%--   </c:if>--%>
                        </display:table>  
                    </fmt:bundle>
                   
                    </td>
                  </tr>
                </tbody>
              </table>
                </td>
            </tr>
          </table>
