<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>


<c:url var="mail_box" value="/action/member/listMailBox?action=listMailBox"/>
<c:url var="change_email" value="/jsp/myalumni/changeEmail.jsp"/>
<c:url var="edit_profile" value="/action/member/prepareUpdateMemberProfile?action=prepareUpdateMemberProfile"/>
<c:url var="change_password" value="/jsp/myalumni/changePassword.jsp"/>
<c:url var="change_avatar" value="/jsp/myalumni/changeAvatar.jsp"/>
<c:url var="change_signature" value="/jsp/myalumni/changeSignature.jsp"/>
<c:url var="delete_avatar" value="/action/member/deleteAvatar?action=deleteAvatar"/>


<c:set var="localAdminAction">
	<bean:write name="memberForm" property="adminAction"/>
</c:set>


<html:hidden property="avatar" name="memberForm"/>
<html:hidden property="memberUserName" name="memberForm"/>
<html:hidden property="memberId" name="memberForm"/>
<html:hidden property="adminAction" name="memberForm"/>


<table width="100%" align="center" border="0" cellpadding="3" cellspacing="1" class="tborder">
      <tr class="portlet-section-body">
         <td align="center" valign="middle">

                        <myalumni:buildImageTag imageType="editableavatar"><bean:write property="avatar" name="memberForm"/></myalumni:buildImageTag>
                        <c:if test="${USER_CONTAINER.token.memberId eq memberForm.memberId &&  memberForm.avatar ne '' && memberForm.avatar ne null}">
                       		<strong> <a href="<c:out value="${delete_avatar}"/>">REMOVE AVATAR</a></strong>
                       </c:if>

         </td>
         <td width="50%" valign="top" nowrap>
		<c:choose>
			<c:when test="${ MODULE == 'ADMIN'}">
				<c:if test="${ localAdminAction != 'true'}">
					<html:form action="/admin/adminMaintainMember?action=maintainMember">
					<p>
					<span class="greenTitle">Administrator Action:</span> Current Status - 
					<c:choose>
						<c:when test="${memberForm.memberStatus == 'A'}">
							<span class="greensmall">Activated</span>
						</c:when>					
						<c:when test="${memberForm.memberStatus == 'D'}">
							<span class="graysmall">Deactivated</span>
						</c:when>
						<c:when test="${memberForm.memberStatus == 'U'}">
							<span class="greensmall">New</span>
						</c:when>
						<c:when test="${memberForm.memberStatus == 'L'}">
							<span class="redsmall">(Locked)</span><html:img page="/images/spacer.gif" />
						</c:when>
						<c:otherwise>
							<span class="redsmall">(Unknown Status)</span><html:img page="/images/spacer.gif" />
						</c:otherwise>												
					</c:choose>
					
					<br>
					      <%--     ADMIN ACTION    --%>
						<html:select property="adminAction">
							<option value="" selected>-- SELECT ACTION --</option>
							<html:options collection="luAdminAction" property="value" labelProperty="label"/>
						</html:select><br>
						<c:if test="${ confirm == 'show'}">
							<html:select property="deleteConfirm">
								<option value="" selected>-- ARE YOU SURE YOU WANT TO DELETE --</option>
								<option value="1">Yes</option>
								<option value="0">No</option>
							</html:select>
						</c:if>
						<html:hidden property="memberUserName"/>
						</p>
						<p>
						  <html:submit styleClass="button">
							<bean:message key="button.submit"/>
						  </html:submit>
						  &nbsp;&nbsp;&nbsp;
						  <html:cancel styleClass="button">
							<bean:message key="button.cancel"/>
						  </html:cancel>
					</p>
					</html:form>
				</c:if>
			</c:when>
			<c:otherwise>
				<a href="<c:out value="${mail_box}"/>">My Private Messages</a><br>
				<a href="<c:out value="${edit_profile}"/>">Change my Profile</a><br>
				<a href="<c:out value="${change_email}"/>">Change my Email</a><br>
				<a href="<c:out value="${change_password}"/>">Change my Password</a><br>
				<a href="<c:out value="${change_avatar}"/>">Change my Avatar</a><br>
			<%--	<a href="<c:out value="${change_signature}"/>">Change my Signature</a> --%>
			</c:otherwise>
		</c:choose>
          </td>
       </tr>
    </table>
