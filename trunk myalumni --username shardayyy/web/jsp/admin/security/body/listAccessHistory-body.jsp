<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<script type="text/javascript" language="javaScript">
<!--

function deleteRecord()
{
    var enter=prompt('<bean:message key="js.doyouwanttodelete"/>','<bean:message key="js.deleteoptions"/>');
    if (enter == 'yes'){
        document.forms[0].submit();
    }else if (enter == 'no'){
        alert('<bean:message key="js.recordnotdeleted"/>');
        return;
    }else{
        alert('<bean:message key="js.recordnotdeleted"/>');
        return;
    }
}



//-->
</script>

<c:url var="miniProfile" value="/action/admin/displayMiniProfile?action=displayMiniProfile"/>

<html:form action="/admin/purgeAllAccessLogs?action=purgeAllAccessLogs">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                    </tr>
                    <tr class="bg0">
                      <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/><span class="Bold">Access History</span></div></td>
                      <td align="right">                        
                            <html:button styleClass="button" property="buttonAction" onclick="javascript:deleteRecord();"><bean:message key="button.purgeallaccesslogs"/></html:button>&nbsp;&nbsp;                        
                        &nbsp;
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                    </tr>
                    <tr>
                      <td colspan="2" >
                      
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfAccessHistory" cellpadding="3" cellspacing="1" pagesize="20" id="row" requestURI="/action/admin/listAccessHistory?action=listAccessHistory" excludedParams="action">
                            <display:column titleKey="label.admin.security.username" sortable="true">
                            	<a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${row.userName}"/>" onclick="newPopup(this.href,'name');return false"><c:out value="${row.userName}"/></a>
                            </display:column>
                            <display:column titleKey="label.admin.security.logintime" >
                                <fmt:formatDate value="${row.requestTime}" type="both" dateStyle="long" timeStyle="short"/>
                            </display:column>
                            <display:column property="sourceIP" titleKey="label.admin.security.sourceip" sortable="true"/>
                            <display:column property="userAgent" titleKey="label.admin.security.lang" sortable="true"/>
                            <display:column property="reasonCodeDesc" titleKey="label.admin.security.reasoncode" sortable="true"/>
                            <c:choose>
                                <c:when test="${row.loginStatus == 'P'}">
                                        <display:column titleKey="label.admin.status"  class="row-green" sortable="true">
                                            <bean:message key="label.admin.security.passed"/>
                                        </display:column>
                                </c:when>
                                <c:when test="${row.loginStatus == 'F'}">
                                        <display:column titleKey="label.admin.status"  class="row-red" sortable="true">
                                            <bean:message key="label.admin.security.failed"/>
                                        </display:column>
                                </c:when>
                            </c:choose>  
                                                              
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