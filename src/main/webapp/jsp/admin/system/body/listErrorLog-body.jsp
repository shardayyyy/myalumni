<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<c:url var="view" value="/action/admin/viewErrorLog?action=viewErrorLog"/>

<script type="text/javascript" language="javaScript">
<!--

function deleteRecord()
{
    var enter=prompt('<bean:message key="js.doyouwanttodelete"/>','<bean:message key="js.deleteoptions"/>');
    if (enter == 'yes'){
        document.errorLogForm.submit();
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
 <html:form action="/admin/purgeLogHistory?action=purgeLogHistory">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.admin.system.listerrorlog"/> </span> </div></td>
                    <td align="right">                                         <html:button styleClass="button"  property="buttonAction" onclick="javascript:deleteRecord();"><bean:message key="button.purgeallerrorlogs"/></html:button> &nbsp;
						<html:submit styleClass="button" onclick="alert('Not Yet Implemented')"><bean:message key="button.batchlog"/></html:submit>
	                    &nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfErrorLogs" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listErrorLogs?action=listErrorLogs" excludedParams="action" id="row">
                                <display:column titleKey="label.common.action">
                                    <center><a href="<c:out value="${view}"/>&errorLogId=<c:out value="${row.errorLogId}"/>"><bean:message key="label.common.view"/></a></center>
                                </display:column>
                                <display:column titleKey="label.admin.system.loggedby">
                                	<c:out value="${row.loggedBy}"/>
                                </display:column>                                
                                <display:column titleKey="label.admin.system.incidenttime">
                                    <fmt:formatDate dateStyle="medium" type="both" timeStyle="short" value="${row.errorDate}"/>
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