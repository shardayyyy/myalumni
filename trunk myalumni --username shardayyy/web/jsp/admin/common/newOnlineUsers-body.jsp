<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<c:set var="myUserName">
	<c:out value="${USER_CONTAINER.token.memberUserName}"/>
</c:set>

<c:url var="miniProfile" value="/action/admin/displayMiniProfile?action=displayMiniProfile"/>

<table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0"><bean:message key="table.title.onlineUsers"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td>
  <c:set var="num_rec" scope="page" value="-1"/>
        <logic:iterate id="user" name="onlineusers" indexId="pIdx">
               <c:if test="${ myUserName != user.memberUserName}">
               		<html:img page="/images/arrow.gif" width="11" height="11"/>
               		<a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${user.memberUserName}"/>" onclick="newPopup(this.href,'name');return false" title="View <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> details"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> (<c:out value="${user.yearOut}"/> set)</a> <br>
               		<c:set var="num_rec" value="1"/>
               </c:if>

	</logic:iterate>

        <c:if test="${num_rec == '-1'}">
            <bean:message key="message.none"/>
        </c:if>
	</td>
  </tr>
</table>
