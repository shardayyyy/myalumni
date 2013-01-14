<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<script type="text/javascript" language="javaScript">
<!--

function deleteRecord(logFileName)
{
    var enter=prompt('<bean:message key="js.doyouwanttodelete"/>','<bean:message key="js.deleteoptions"/>');
    if (enter == 'yes'){
    	document.systemForm.logFileName.value = logFileName;
        document.systemForm.submit();
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

<c:url var="view" value="/action/admin/viewSystemLogs?action=viewSystemLogs"/>


<html:form action="/admin/deleteSystemLog?action=deleteSystemLog">
<html:hidden property="logFileName"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/>
                    <span class="Bold">Maintain System Logs.</span> </div></td>
                    <td align="right">&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
	                    <fmt:bundle basename="ApplicationResources">
	                        <display:table name="requestScope.systemLogs" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/admin/listSystemLogs?action=listSystemLogs" excludedParams="action" id="row">
	                                
	                                <display:column titleKey="label.common.action" class="list-action-header">
	                                	<c:choose>
		                                	<c:when test="${row.fileName != 'myalumni.log'}">
		                                		<a href="javascript:deleteRecord('<c:out value="${row.fileName}"/>')">Delete</a>&nbsp;|&nbsp;  
		                                	</c:when>
		                                	<c:otherwise>
		                                	&nbsp;
		                                	</c:otherwise>
	                                	</c:choose>
	                                	<a href="<c:out value="${view}"/>&logFileName=<c:out value="${row.fileName}"/>">View</a> 
	                                
	                                
	                                </display:column>
	                                <display:column title="File Name" sortable="true">
	                                	<c:out value="${row.fileName}"/>
	                                </display:column>
	                                <display:column title="File Size" sortable="true">
	                                	<c:out value="${row.fileSize}"/>
	                                </display:column>
	                              	<display:column title="Date" sortable="true">
	                                	<fmt:formatDate value="${row.fileDate}" type="both" timeStyle="long" dateStyle="long"/>
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

