<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="title_line" value="/images/title_line2.gif"/>

<c:url var="backupdatabase" value="/action/admin/listDatabaseBackup?action=listDatabaseBackup"/>
<c:url var="jcaptcha" value="/jsp/admin/admin/jcaptcha_stats.jsp"/>
<c:url var="session" value="/jsp/admin/system/sessioninfo.jsp"/>


<c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}"> 
<table width="100%" border="0" cellpadding="5" cellspacing="5" >
	<tr>
		<td valign="top">

                        <table width="100%" border="0" cellpadding="1" cellspacing="0">
                            <tr>
                                    <td class="goldTitle"><bean:message key="tab.admin.admin"/></td>
                            </tr>
                            <tr>
                                    <td height="6" background="<c:out value="${title_line}"/>"></td>
                            </tr>
                            <tr>
                                    <td valign="top" nowrap>
                                        <font size="2">                                            
	                                        &#8226;&nbsp;<a href="<c:out value="${backupdatabase}"/>">Backup Database</a> <br><br>
	                                        &#8226;&nbsp;<a href="<c:out value="${jcaptcha}"/>?task=list">jCaptcha Stats</a><br><br>
	                                        
<%--	                                        &#8226;&nbsp;DEVELOPER MODE - <a href="<c:out value="${session}"/>">View Session Contents</a><br>--%>
                                        </font>
                                    </td>
                            </tr>
                            <tr>
                                    <td height="10"></td>
                            </tr>
                        </table>

                </td>
	</tr>
</table>
</c:if>

