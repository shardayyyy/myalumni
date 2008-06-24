<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="title_line" value="/images/title_line2.gif"/>



<c:url var="scroll" value="/action/admin/maintainScroll?action=maintainScroll"/>
<c:url var="emailexception" value="/action/admin/maintainEmailException?action=maintainEmail"/>
<c:url var="listSystemLogs" value="/action/admin/listSystemLogs?action=listSystemLogs"/>
<c:url var="dormitory" value="/action/admin/prepareUpdateDormitory?action=prepareUpdateDormitory"/>

<c:url var="listClassNews" value="/action/admin/listClassNews?action=listClassNews"/>
<c:url var="listReminisce" value="/action/admin/listReminisce?action=listReminisce"/>

<c:url var="listErrorLogs" value="/action/admin/listErrorLogs?action=listErrorLogs"/>
<c:url var="serverurl" value="/action/admin/prepareUpdateServerUrl?action=prepareUpdateServerUrl"/>
<c:url var="albumurl" value="/action/admin/prepareUpdateAlbumUrl?action=prepareUpdateAlbumUrl"/>
<c:url var="forumurl" value="/action/admin/prepareUpdateForumUrl?action=prepareUpdateForumUrl"/>
<c:url var="sessionTimeout" value="/action/admin/prepareUpdateSessionTimeOut?action=prepareUpdateSessionTimeOut"/>
<c:url var="rss" value="/action/admin/prepareUpdateRssFeed?action=prepareUpdateRssFeed"/>
<c:url var="orginfo" value="/action/admin/prepareUpdateOrgInfo?action=prepareUpdateOrgInfo"/>
<c:url var="aboutorg" value="/action/admin/prepareUpdateOrgAboutUs?action=prepareUpdateOrgAboutUs"/>

 <c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
<table width="100%" border="0" cellpadding="5" cellspacing="5" >
	<tr>
		<td valign="top">

                        <table width="100%" border="0" cellpadding="1" cellspacing="0">
                            <tr>
                                    <td class="goldTitle"><bean:message key="tab.admin.system"/></td>
                            </tr>
                            <tr>
                                    <td height="6" background="<c:out value="${title_line}"/>"></td>
                            </tr>
                            <tr>
                                    <td valign="top" nowrap>
                                        <font size="2">                                         
                                    &#8226;&nbsp;<a href="<c:out value="${listClassNews}"/>">Manage Class News</a><br><br>  
                                    &#8226;&nbsp;<a href="<c:out value="${listErrorLogs}"/>">Manage Error Log</a><br><br>
									&#8226;&nbsp;<a href="<c:out value="${listSystemLogs}"/>">Manage System Log</a><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${rss}"/>">Manage RSS Feed</a><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${sessionTimeout}"/>">Manage User Session Timeout</a><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${serverurl}"/>">Manage Server URL</a><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${forumurl}"/>">Manage Forum URL</a><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${albumurl}"/>">Manage Album URL</a><br><br>                                                                        		                             
									&#8226;&nbsp;<a href="<c:out value="${scroll}"/>&type=list">Maintain Scroll</a><br><br>
									&#8226;&nbsp;<a href="<c:out value="${listReminisce}"/>">Maintain Reminisce</a><br><br>
									&#8226;&nbsp;<a href="<c:out value="${dormitory}"/>">School has dormitory?</a><br><br>
									&#8226;&nbsp;<a href="<c:out value="${orginfo}"/>">Organization Information</a><br><br>
									&#8226;&nbsp;<a href="<c:out value="${aboutorg}"/>">Update About Organization</a><br><br>										
									 
<%--									<br><br>												--%>
<%--									&#8226;&nbsp;<a href="<c:out value="${emailexception}"/>&task=list">Email Exception</a> - TODO log all emal exceptions in the database to be resent later.<br>--%>
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

