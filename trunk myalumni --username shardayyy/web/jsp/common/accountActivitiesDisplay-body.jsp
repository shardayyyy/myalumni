<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<c:url var="mail_box" value="/action/member/listMailBox?action=listMailBox"/>


<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
           <tr>
             <td height="30" colspan="2" class="bg0"><bean:message key="table.title.accountactivities"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td width="26%" class="fieldlabel"><bean:message key="label.username"/>:</td>
             <td width="74%"><bean:write property="memberUserName" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.lastipaddress"/>:</td>
             <td><bean:write property="lastIPAddress" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.joindate"/>: </td>
             <td><fmt:formatDate value="${profile.creationDate}" type="both" dateStyle="long" timeStyle="short"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.lastlogin"/>: </td>
             <td><fmt:formatDate value="${profile.lastLogonDate}" type="both" dateStyle="long" timeStyle="short"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.profileupdate"/>:</td>
             <td>
             	<fmt:formatDate value="${profile.lastModifiedDate}" type="both" dateStyle="long" timeStyle="short"/> 
             </td>
           </tr>

	   <c:choose>
		<c:when test="${ MODULE == 'ADMIN'}">
		</c:when>
		<c:otherwise>
			 <tr class="portlet-section-body">
			     <td class="fieldlabel"><bean:message key="label.newmessages"/>: </td>
			     <td>
			     [
			   <c:choose>
			   <c:when test="${USER_CONTAINER.newMailCount > 0}">
				    <span class="redhighlight"><c:out value="${USER_CONTAINER.newMailCount}"/></span> New Messages
			   </c:when>
			   <c:otherwise>
				     <c:out value="${USER_CONTAINER.newMailCount}"/>
			   </c:otherwise>
			      </c:choose>
			     ]

			     &nbsp; <span class="redhighlight">[<a href="<c:out value="${mail_box}"/>">View Messages</a>]</span> </td>
			   </tr>
		</c:otherwise>
	</c:choose>


<%--           <tr class="portlet-section-body">--%>
<%--             <td valign="top" class="fieldlabel"><bean:message key="label.signature"/>:</td>--%>
<%--             <td>--%>
<%--                ------------------------------------<br>--%>
<%--		<bean:write property="signature" name="memberForm"/>--%>
<%----%>
<%--	     </td>--%>
<%--           </tr>--%>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.coollink1"/>: </td>
             <td><bean:write property="favUrl1" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.coollink2"/>: </td>
             <td><bean:write property="favUrl2" name="memberForm"/></td>
           </tr>
         </table>
