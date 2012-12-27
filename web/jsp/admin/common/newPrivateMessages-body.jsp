<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>

<c:url var="readOneMailByUserName" value="/action/admin/adminReadOneMail?action=readOneMail&messageId="/>
<c:url var="viewAdminMail" value="/action/admin/viewFolderMail?action=displayMailFolder&type=inbox"/>

<c:set var="_guestId">
	<%= BaseConstants.GUEST_USERNAME_ID %>
</c:set>


<table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" class="bg0" colspan="3"><bean:message key="table.title.myprimessage"/> &nbsp;&nbsp;&nbsp;<a href="<c:out value="${viewAdminMail}"/>"><html:img page="/images/icon/folder_inbox.gif" border="0"/></a></td>
          </tr>


		<c:set var="num_rec5" scope="page" value="-1"/>
        <logic:iterate id="messages" name="MESSAGE_CENTER" property="listOfMessages">
           <c:if test="${messages.messageStatus == 'N'}"> <%-- Only show unread messages --%>
           <tr class="portlet-section-body">
		    <c:choose>
				<c:when test="${messages.messageStatus == 'N'}">
					 <td align="center"><html:img page="/images/icon/i.p.new.gif" alt="New Mail !" width="14" height="11"/> </td>
				</c:when>
				<c:otherwise>
					 <td align="center"><html:img page="/images/icon/i.p.read.gif" alt="Read Email" width="15" height="12"/> </td>
				</c:otherwise>
		    </c:choose>
		    
		    <c:choose>
				<c:when test="${messages.priority == 'H'}">
					 <td align="center"><html:img page="/images/icon/i.p.importance.h.gif" alt="High Importance" width="4" height="10"/>&nbsp; </td>
				</c:when>
				<c:when test="${messages.priority == 'L'}">
					 <td align="center"><html:img page="/images/icon/i.p.importance.l.gif" alt="Low Importance" width="7" height="10"/> </td>
				</c:when>
				<c:otherwise>
					 <td>&nbsp;&nbsp;&nbsp;</td>
				</c:otherwise>
		    </c:choose>

		    <%--<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${messages.message_Id}"/>" title="Message from <c:out value="${messages.fromFirstName}"/> <c:out value="${messages.fromLastName}"/>"><c:out value="${messages.messageFromFirstName}"/> <c:out value="${messages.messageFromLastName}"/></a>--%>

		   <%-- <c:out value="${messages.messageDate}"/> --%>

		<c:choose>
		    <c:when test="${messages.messageStatus == 'N'}">
			     <td>
			     		<c:choose>
			     			<c:when test="${messages.messageFromMember.memberId == _guestId}">
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${messages.messageId}"/>" title="Message from <c:out value="${messages.guestName}"/>"><strong><c:out value="${messages.guestName}"/> <c:out value="${messages.guestEmail}"/> </strong></a>
			     			</c:when>
			     			<c:otherwise>
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${messages.messageId}"/>" title="Message from <c:out value="${messages.messageFromMember.firstName}"/> <c:out value="${messages.messageFromMember.lastName}"/>"><strong><c:out value="${messages.messageFromMember.firstName}"/> <c:out value="${messages.messageFromMember.lastName}"/> <c:out value="${messages.guestEmail}"/> </strong></a>
			     			</c:otherwise>
			     		</c:choose>
			     		 
			     </td>
		    </c:when>
		    <c:otherwise>
			    <td> 
			    		<c:choose>
			     			<c:when test="${messages.messageFromMember.memberId == _guestId}">
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${messages.messageId}"/>" title="Message from <c:out value="${messages.guestName}"/>"><strong><c:out value="${messages.guestName}"/> <c:out value="${messages.guestEmail}"/> </strong></a>
			     			</c:when>
			     			<c:otherwise>
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${messages.messageId}"/>" title="Message from <c:out value="${messages.messageFromMember.firstName}"/> <c:out value="${messages.messageFromMember.lastName}"/>"><strong><c:out value="${messages.messageFromMember.firstName}"/> <c:out value="${messages.messageFromMember.lastName}"/> <c:out value="${messages.guestEmail}"/> </strong></a>
			     			</c:otherwise>
			     		</c:choose>
			    </td>
		    </c:otherwise>
        </c:choose>


			<c:set var="num_rec5" value="1"/>
     </tr>
     </c:if>
  </logic:iterate>

		<c:if test="${num_rec5 == '-1'}">
		  <tr class="portlet-section-body">
                  <td>
                    <bean:message key="message.none"/>
                  </td>
                  </tr>
		</c:if>



        </table>
