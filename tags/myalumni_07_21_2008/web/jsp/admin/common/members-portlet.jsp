<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="title_line" value="/images/title_line2.gif"/>

<c:url var="listAllMini" value="/action/admin/adminListMembers?action=listMembers&adminAction=FWD_ALL_MEMBERS&adminDisplay=mini&searchCategory=fullSearch&partialNameSearch=N"/>
<c:url var="search" value="/jsp/admin/members/adminSearch.jsp"/>
<c:url var="emailMember" value="/jsp/admin/members/adminEmailMember.jsp"/>
<c:url var="statistics" value="/action/admin/displayStatistics?action=displayStatistics"/>




 <c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
<table width="100%" border="0" cellpadding="5" cellspacing="5" >
	<tr>
		<td valign="top">

                        <table width="100%" border="0" cellpadding="1" cellspacing="0">
                            <tr>
                                    <td class="goldTitle"><bean:message key="tab.admin.members"/></td>
                            </tr>
                            <tr>
                                    <td height="6" background="<c:out value="${title_line}"/>"></td>
                            </tr>
                            <tr>
                                    <td valign="top" nowrap>
                                            &#8226;&nbsp;<a href="<c:out value="${search}"/>">Member Search</a><br><br>
											&#8226;&nbsp;<a href="<c:out value="${emailMember}"/>">Email Member</a><br><br>
											&#8226;&nbsp;<a href="<c:out value="${statistics}"/>">Statistics</a><br><br>											
                                      
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

