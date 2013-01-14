<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/string-taglib.tld" prefix="str" %>



<logic:iterate id="members" name="LIST_OF_MEMBERS" indexId="pIdx" scope="request">

<table width="700" border="0" cellspacing="1" cellpadding="3" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0">Listing One Members</td>
    </tr>
  <tr class="portlet-section-body">
    <td width="30%" valign="top"><p><strong>name:</strong> <c:out value="${members.firstName}"/> <c:out value="${members.lastName}"/> (is Admin: <c:out value="${members.isAdmin}"/>)</p>
    <strong>User Name: </strong> <c:out value="${members.memberUserName}"/><br>
      <strong>maidenname:</strong> <c:out value="${members.maidenName}"/><br>
            <strong>nickname:</strong> <c:out value="${members.nickName}"/> <br>
            <strong>dob:</strong>
        	<fmt:formatDate value="${members.dob}" type="date"/>
            <br>
            <strong>career:</strong> <c:out value="${members.career.label}"/> <br>
            <strong>last login:</strong> <fmt:formatDate value="${members.lastLogonDate}" type="both"/><br>
            <strong>join date:</strong> <fmt:formatDate  value="${members.creationDate}" type="both"/><br>
            <strong>last ip:</strong> <c:out value="${members.lastIPAddress}" /><br>


        <c:set var="num_rec" scope="page" value="-1"/>
        <logic:iterate id="user" name="onlineusers" indexId="pIdx2">
               <c:if test="${ members.memberUserName == user.memberUserName}">
					<c:set var="num_rec" value="1"/>
               </c:if>
		</logic:iterate>
<br>
	<c:choose>
        <c:when test="${num_rec == '-1'}">
            	<html:img page="/images/icon/notonline.gif" alt="Not Online" width="64" height="16" align="absmiddle"/>
        </c:when>
        <c:otherwise>
        	<html:img page="/images/icon/online.gif" alt="Online" width="64" height="16" align="absmiddle"/>
        </c:otherwise>
        </c:choose>


            <p><myalumni:buildImageTag imageType="avatar"><c:out value="${members.avatar}"/></myalumni:buildImageTag></p></td>
    <td width="20%" valign="top"><table width="100%"  border="0" cellpadding="1" cellspacing="3">
        <tr>
          <td width="26%" class="fieldlabel">Email:</td>
          <td width="74%"><c:out value="${members.email}"/></td>
        </tr>
        <tr>
          <td class="fieldlabel">House:</td>
          <td>
			<%-- Dormitory Name --%>
			<c:if test="${applicationScope.hasDorm == 'Y'}">
                   <c:if test="${member.dormitory ne null}">
                       [<c:out value="${member.dormitoryLabel}"/>]
                   </c:if>
           </c:if>
          </td>
        </tr>
        <tr>
          <td class="fieldlabel">Phone:</td>
          <td><c:out value="${members.phone}"/></td>
        </tr>
        <tr>
          <td class="fieldlabel" valign="top">IM:</td>
          <td>

 					<%-- Instant Messenger --%>
			        <c:if test="${members.messengers ne null && !empty members.messengers}">
			                <html:img page="/images/icon/messengers.png" border="0" titleKey="label.instantmessengers"/>
			        </c:if>

          </td>
        </tr>
        <tr>
          <td class="fieldlabel">Website:</td>
          <td><a href="<c:out value="${members.website}"/>" target="_blank"><c:out value="${members.website}"/></a></td>
        </tr>
        <tr>
          <td class="fieldlabel">Gender:</td>
          <td>
               <c:choose>
                        <c:when test="${members.gender == 'M'}">
                                <bean:message key="label.male"/> <html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
                        </c:when>
                        <c:when test="${ members.gender == 'F'}">
                                <bean:message key="label.female"/> <html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
                        </c:when>
                </c:choose>
          </td>
        </tr>
        <tr>
          <td class="fieldlabel">Year:</td>
          <td><c:out value="${members.yearIn}"/> - <c:out value="${members.yearOut}"/> </td>
        </tr>
        <tr>
          <td class="fieldlabel">Location:</td>
          <td><c:out value="${members.address}"/>, <c:out value="${members.country.label}"/> </td>
        </tr>
        <tr>
          <td valign="top" class="fieldlabel">Comments:</td>
          <td><c:out value="${members.comments}"/></td>
        </tr>
        <tr>
          <td valign="top" class="fieldlabel">Admin Comments:</td>
          <td><str:replace replace="NL" with="<br/>" newlineToken="NL"><c:out value="${members.adminComments}"/> </str:replace></td>
        </tr>
        <tr>
          <td colspan="2">

		<html:form action="/admin/adminMaintainMember?action=maintainMember">
		<p>

		<c:choose>
			<c:when test="${members.memberStatus == 'A'}"> <%-- ACCOUNT_ACTIVATED --%>
				<span class="greenTitle">Activated</span><br>
			</c:when>
			<c:when test="${members.memberStatus == 'L'}"> <%-- ACCOUNT_LOCKED --%>
				<span class="redTitle">Lockecd</span><br>
			</c:when>
			<c:when test="${ members.memberStatus == 'D'}"> <%-- ACCOUNT_DEACTIVATED --%>
				<span class="greyTitle">Deactivated</span><br>
			</c:when>
			<c:when test="${members.memberStatus  == 'U'}"> <%-- ACCOUNT_UNAPPROVED --%>
				<span class="greenTitle">New</span><br>
			</c:when>
			<c:when test="${members.memberStatus  == 'X'}"> <%-- ACCOUNT_DELETED --%>
				<span class="redTitle">Deleted</span><br>
			</c:when>			
		</c:choose>
		      <%--     ADMIN ACTION    --%>
			<html:select property="adminAction">
				<option value="" selected>-SELECT ACTION-</option>
				<html:options collection="luAdminAction" property="value" labelProperty="label"/>
			</html:select>
			<input type="hidden" name="memberUserName" value="<c:out value="${members.memberUserName}"/>">

			</p>
			<p>
			  <html:submit>
				<bean:message key="button.submit"/>
			  </html:submit>
			  &nbsp;&nbsp;&nbsp;
			  <html:cancel>
				<bean:message key="button.cancel"/>
			  </html:cancel>
		</p>
		</html:form>

            </td>
          </tr>
    </table></td>
    </tr>
</table>

</logic:iterate>

