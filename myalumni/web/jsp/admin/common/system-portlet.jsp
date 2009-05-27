<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="title_line" value="/images/title_line2.gif"/>



<c:url var="scroll" value="/action/admin/maintainScroll?action=maintainScroll"/>
<c:url var="emailexception" value="/action/admin/maintainEmailException?action=maintainEmail"/>
<c:url var="listSystemLogs" value="/action/admin/listSystemLogs?action=listSystemLogs"/>
<c:url var="dormitory" value="/action/admin/prepareUpdateDormitory?action=prepareUpdateDormitory"/>
<c:url var="birthday" value="/action/admin/prepareUpdateBirthdayNotification?action=prepareUpdateBirthdayNotification"/>
<c:url var="uploadlogo" value="/action/admin/prepareUploadLogo?action=prepareUploadLogo"/>

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
<c:url var="orgintro" value="/action/admin/prepareUpdateOrgIntro?action=prepareUpdateOrgIntro"/>
<c:url var="twitter" value="/action/admin/prepareUpdateTwitterCred?action=prepareUpdateTwitterCred"/>



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
                                    &#8226;&nbsp;<a href="<c:out value="${listClassNews}"/>">Manage Class News</a> - <span class="graysmall"><bean:message key="sys_module.classnews"/></span><br><br>  
                                    &#8226;&nbsp;<a href="<c:out value="${listErrorLogs}"/>">Manage Error Log</a> - <span class="graysmall"><bean:message key="sys_module.errorlog"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${listSystemLogs}"/>">Manage System Log</a> - <span class="graysmall"><bean:message key="sys_module.systemlog"/></span><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${rss}"/>">Manage RSS Feed</a> - <span class="graysmall"><bean:message key="sys_module.rss"/></span><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${sessionTimeout}"/>">Manage User Session Timeout</a> - <span class="graysmall"><bean:message key="sys_module.session"/></span><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${serverurl}"/>">Manage Server URL</a> - <span class="graysmall"><bean:message key="sys_module.server"/></span><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${forumurl}"/>">Manage Forum URL</a> - <span class="graysmall"><bean:message key="sys_module.forum"/></span><br><br>
                                    &#8226;&nbsp;<a href="<c:out value="${albumurl}"/>">Manage Album URL</a> - <span class="graysmall"><bean:message key="sys_module.album"/></span><br><br>                                                                        		                             
									&#8226;&nbsp;<a href="<c:out value="${scroll}"/>&type=list">Maintain Scroll</a> - <span class="graysmall"><bean:message key="sys_module.scroll"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${listReminisce}"/>">Maintain Reminisce</a> - <span class="graysmall"><bean:message key="sys_module.reminisce"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${dormitory}"/>">School has dormitory?</a> - <span class="graysmall"><bean:message key="sys_module.dorm"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${birthday}"/>">Send Birthday Notifications?</a> - <span class="graysmall"><bean:message key="sys_module.birthday"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${orginfo}"/>">Organization Information</a> - <span class="graysmall"><bean:message key="sys_module.orginfo"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${aboutorg}"/>">Update Organization About Us</a> - <span class="graysmall"><bean:message key="sys_module.aboutus"/></span><br><br>										
									&#8226;&nbsp;<a href="<c:out value="${orgintro}"/>">Update Front Page Introduction</a> - <span class="graysmall"><bean:message key="sys_module.intro"/></span><br><br>										
									&#8226;&nbsp;<a href="<c:out value="${uploadlogo}"/>">Upload School Logo</a> - <span class="graysmall"><bean:message key="sys_module.logo"/></span><br><br>
									&#8226;&nbsp;<a href="<c:out value="${twitter}"/>">Manage Twitter Credentials</a> - <span class="graysmall"><bean:message key="sys_module.twitter"/></span><br><br>
									 
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

