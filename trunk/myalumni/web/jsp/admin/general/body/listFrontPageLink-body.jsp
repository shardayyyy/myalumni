<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="view" value="/action/admin/viewFrontPageLinks?action=viewFrontPageLinks"/>
<c:url var="edit" value="/action/admin/prepareUpdateFrontPageLinks?action=prepareUpdateFrontPageLinks"/>
<c:url var="delete" value="/action/admin/prepareUpdateFrontPageLinks?action=prepareUpdateFrontPageLinks"/>


 <html:form action="/admin/prepareAddFrontPageLinks?action=prepareAddFrontPageLinks"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.frontpage.listfrontpage"/></span></div></td>
                    <td align="right">  
                    	                               
                         	<html:submit styleClass="button"><bean:message key="button.addfrontpage"/></html:submit>                                   
    					&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfFronPageLinks" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listFrontPageLinks?action=listFrontPageLinks" excludedParams="action" id="row">
                                <display:column titleKey="label.common.action" class="list-action-header">
                                    <a href="<c:out value="${edit}"/>&linkId=<c:out value="${row.linkId}"/>"><bean:message key="label.common.edit"/></a>    
                                </display:column>
                                <display:column property="label" titleKey="label.frontpage.label" sortable="true"/>                                
			                    <display:column titleKey="label.frontpage.linkurl" sortable="true">
			                    	<a href="<c:out value="${row.linkurl}"/>" target="_blank"><c:out value="${row.linkurl}"/></a>
			                    </display:column>
			                    <display:column property="important" titleKey="label.frontpage.important"/>              
                                <display:column titleKey="label.admin.status">
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