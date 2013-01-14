<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="desktop_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=dashboard"/>
<c:url var="members_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=members"/>
<c:url var="security_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=security"/>
<c:url var="general_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=general"/>
<c:url var="system_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=system"/>
<c:url var="admin_url" value="/action/admin/showAdminDesktop?action=displayMyDesktop&t=admin"/>


<c:set var="modulesCount" value="6"/>
<c:set var="widthPercentage" value="${100/modulesCount}"/>


<tr>

    <%-- Desktop Module --%>
       <c:choose>
         <c:when test="${adminTab == 'desktop'}">
                           <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${desktop_url}"/>"><bean:message key="table.title.mydashboard"/></a></b></font></td>
         </c:when>
         <c:otherwise>
                           <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${desktop_url}"/>"><bean:message key="table.title.mydashboard"/></a></td>
         </c:otherwise>
       </c:choose>
       
       
    <%-- Members Module --%>
       <c:choose>
         <c:when test="${adminTab == 'members'}">
                           <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${members_url}"/>"><bean:message key="tab.admin.members"/> <bean:message key="tab.admin.module"/></a></b></font></td>
         </c:when>
         <c:otherwise>
                           <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${members_url}"/>"><bean:message key="tab.admin.members"/> <bean:message key="tab.admin.module"/></a></td>
         </c:otherwise>
       </c:choose>


	<%--  SECURITY --%>
        <c:choose>
          <c:when test="${adminTab == 'security'}">
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${security_url}"/>"><bean:message key="tab.admin.security"/> <bean:message key="tab.admin.module"/></a></b></font></td>
          </c:when>
          <c:otherwise>
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${security_url}"/>"><bean:message key="tab.admin.security"/> <bean:message key="tab.admin.module"/></a></td>
          </c:otherwise>
        </c:choose>


	<%--  GENERAL --%>
        <c:choose>
          <c:when test="${adminTab == 'general'}">
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${general_url}"/>"><bean:message key="tab.admin.general"/> <bean:message key="tab.admin.module"/></a></b></font></td>
          </c:when>
          <c:otherwise>
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${general_url}"/>"><bean:message key="tab.admin.general"/> <bean:message key="tab.admin.module"/></a></td>
          </c:otherwise>
        </c:choose>
        
    <%-- System Module --%>   
	      <c:choose>
	        <c:when test="${adminTab == 'system'}">
	        	    <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${system_url}"/>"><bean:message key="tab.admin.system"/> <bean:message key="tab.admin.module"/></a></b></font></td>
	        </c:when>
	        <c:otherwise>
			       <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${system_url}"/>"><bean:message key="tab.admin.system"/> <bean:message key="tab.admin.module"/></a></td>
	        </c:otherwise>
	      </c:choose>
        

    <%-- Admin Module --%>   
        <c:choose>
          <c:when test="${adminTab == 'admin'}">
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap><font color="#3C4780"><b><a href="<c:out value="${admin_url}"/>"><bean:message key="tab.admin.admin"/> <bean:message key="tab.admin.module"/></a></b></font></td>
          </c:when>
          <c:otherwise>
                            <td width="<c:out value="${widthPercentage}"/>%" height="21" nowrap bgcolor="#F0F0D6"><a href="<c:out value="${admin_url}"/>"><bean:message key="tab.admin.admin"/> <bean:message key="tab.admin.module"/></a></td>
          </c:otherwise>
        </c:choose>
</tr>

