<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/string-taglib.tld" prefix="str" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>


<c:url var="mail_box" value="/action/member/listMailBox?action=listMailBox"/>
<c:url var="search" value="/jsp/myalumni/search.jsp"/>
<c:url var="view_my_profile" value="/action/member/viewProfile?action=viewMemberProfile"/>
<c:url var="edit_profile" value="/action/member/prepareUpdateMemberProfile?action=prepareUpdateMemberProfile"/>
<c:url var="submit_classnews" value="/action/member/prepareAddClassNews?action=prepareAddClassNews"/>
<c:url var="submit_slang" value="/action/member/prepareAddReminisce?action=prepareAddReminisce"/>
<c:url var="miniProfile" value="/action/member/displayMiniProfile?action=displayMiniProfile"/>
<c:url var="faq" value="/jsp/myalumni/faq.jsp"/>



<c:set var="myUserName">
	<c:out value="${USER_CONTAINER.token.memberUserName}"/>
</c:set>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
     <tr>
            <td height="30" class="bg0"><bean:message key="label.controlpanel"/> </td>
          </tr>
          <tr class="portlet-section-body">
            <td>
                Welcome back, <b><str:capitalize><c:out value="${USER_CONTAINER.token.firstName}"/></str:capitalize> <str:capitalize><c:out value="${USER_CONTAINER.token.lastName}"/></str:capitalize></b><br>
                Last Login: <fmt:formatDate value="${USER_CONTAINER.token.lastLogonDate}" type="both" dateStyle="full" pattern="E, MMM d, yyyy hh:mm a"/>
              <hr>
              <html:img page="/images/email-y.gif" height="10" width="14"/> <a href="<c:out value="${mail_box}"/>"><b>Private Messages</b></a><br>
              <html:img page="/images/email-r.gif" height="10" width="14"/> New Mail:

              <c:choose>
              <c:when test="${USER_CONTAINER.newMailCount > 0}">
                        <span class="redhighlight"><c:out value="${USER_CONTAINER.newMailCount}"/></span>&nbsp;&nbsp;<html:img page="/images/newmail1.gif" align="absmiddle"/>
              </c:when>
              <c:otherwise>
                        &nbsp;&nbsp;<c:out value="${USER_CONTAINER.newMailCount}"/>
              </c:otherwise>
              </c:choose>

              <br>
              <%-- <html:img page="/images/email-g.gif" height="10" width="14"/> Opened Mail: &nbsp;&nbsp;&nbsp;<c:out value="${USER_CONTAINER.oldMailCount}"/><br> --%>
              <hr>
              <strong>MYALUMNI Menu:</strong> <br>
              <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${search}"/>">Look for Someone</a> <br><br>
              <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${view_my_profile}"/>">View My Profile</a> <br><br>
              <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${edit_profile}"/>">Edit My Profile</a> <br><br>
	          <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${submit_classnews}"/>">Submit Class News</a> <br><br>
	          <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${submit_slang}"/>">Submit a Slang</a> <br><br>
   
<%--              <html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${faq}"/>">FAQ</a><br><br>
--%>              
              <hr>
              <u><strong>Members Online Now:</strong></u><strong> <html:img page="/images/group-1.gif" width="17" height="14" align="absmiddle"/></strong><br>


        <c:set var="num_rec" scope="page" value="-1"/>
        <logic:iterate id="user" name="onlineusers" indexId="pIdx">
               <c:if test="${ myUserName != user.memberUserName}">
               		<html:img page="/images/arrow.gif" width="11" height="11"/>
               		<a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${user.memberUserName}"/>" onclick="return GB_showCenter('',this.href)" title="View <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> details"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> (<c:out value="${user.yearOut}"/> set)</a> <br>
               		<c:set var="num_rec" value="1"/>
               </c:if>

	</logic:iterate>

        <c:if test="${num_rec == '-1'}">
            <bean:message key="message.none"/>
        </c:if>


              </td>
   </tr>
   <tr class="portlet-section-body">
   	<td align="center"><br>

   		<html:form action="/member/prepareDeleteMyMemberProfile?action=prepareDeleteMyMemberProfile">
   			<html:submit >
   				<bean:message key="button.deletemyaccount"/>
   			</html:submit>
   		</html:form>

   	<br>
   	By deleting your account, your member profile will be completely removed from the system.<br>

   	</td>
   </tr>
</table>
