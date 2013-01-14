<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/string-taglib.tld" prefix="str" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>

<c:set var="_guestId">
<%= BaseConstants.GUEST_USERNAME_ID %>
</c:set>


<tiles:insert name="/jsp/common/mailQuota-body.jsp" />
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="150" valign="top">
      <%-- Message Folders --%>
      
      <c:choose>
      <c:when test="${MODULE == 'ADMIN'}">
            <tiles:insert name="/jsp/common/mailFolder-body.jsp?MODULE=ADMIN"/>
      </c:when>
      <c:otherwise>
            <tiles:insert name="/jsp/common/mailFolder-body.jsp"/>
      </c:otherwise>
    </c:choose>
    
    </td>
    <td valign="top" align="left">
    <%-- Mail Body --%>
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0">Sent From 
			     		<c:choose>
			     			<c:when test="${privateMessageForm.messageFromUserId eq _guestId}">
			     				<bean:write name="privateMessageForm" property="guestName"/> <bean:write name="privateMessageForm" property="guestEmail"/>
			     			</c:when>
			     			<c:otherwise>
			     					<bean:write name="privateMessageForm" property="fromMemberFirstName"/> <bean:write name="privateMessageForm" property="fromMemberLastName"/> 
			     			</c:otherwise>
			     		</c:choose>     	
    	
    	
    	on <b><bean:write name="privateMessageForm" property="messageDate"/></b></td>
    <td width="27%" align="right" class="bg0"><a href="javascript:window.print();"><html:img page="/images/printer.gif" width="19" height="15" border="0" align="ABSMIDDLE" title="Print this Private Message"/></a> <a href="javascript:window.print();">Print this Message</a></td>
  </tr>
  <tr class="portlet-section-body">
    <td colspan="3"><b><bean:write name="privateMessageForm" property="subject"/> </b><br/>

        	<p>
        	        <str:replace replace="NL" with="<br/>" newlineToken="NL">
                             <bean:write name="privateMessageForm" property="messageText"/>
        	        </str:replace>
                </p>


----------------------------------------------<br/>
Yours Sincerly, <br/>
  		<c:choose>
  			<c:when test="${privateMessageForm.messageFromUserId eq _guestId}">
  				<bean:write name="privateMessageForm" property="guestName"/> <bean:write name="privateMessageForm" property="guestEmail"/>
  			</c:when>
  			<c:otherwise> 
  				<bean:write name="privateMessageForm" property="fromMemberFirstName"/> <bean:write name="privateMessageForm" property="fromMemberLastName"/><br>
  			</c:otherwise>
  		</c:choose>

</td>
  </tr>


    <c:choose>
	<c:when test="${ MODULE == 'ADMIN'}">
		  <tr align="center" valign="middle" class="portlet-section-body">
		    <td width="31%" height="30"><br>
			&nbsp;
		    </td>
		    <td width="42%"><br>

					<html:form action="/admin/prepareReply?action=prepareReplyMail">
						<html:submit>
							<bean:message key="button.reply"/>
						</html:submit>
						<html:hidden property="messageId"/>
					</html:form>					

		    </td>
		    <td><br>

			<html:form action="/admin/deleteMail?action=deleteMail">
				<html:submit>
					<bean:message key="button.movetotrash"/>
				</html:submit>
				<html:hidden property="messageId"/>
				<html:hidden property="folderName" value="trash"/>
                <html:hidden property="privMsgsAction" value="move"/>
			</html:form>

		    </td>
		  </tr>
	</c:when>
	<c:otherwise>
		  <tr align="center" valign="middle" class="portlet-section-body">
		      <td width="31%" height="30"><br>

			<html:form  action="/member/listMailBox?action=listMailBox">
				<html:submit>
					<bean:message key="button.returnToInbox"/>
				</html:submit>
			</html:form>

		      </td>
		      <td width="42%"><br>


				<c:choose>
					<c:when test="${privateMessageForm.copyMe == 'Y'}">
						<strong>Viewing a copy of a sent message.</strong>
					</c:when>
					<c:otherwise>
							<html:form action="/member/prepareReply?action=prepareReplyMessage">
								<html:submit>
									<bean:message key="button.reply"/>
								</html:submit>
								<html:hidden property="messageId"/>
							</html:form>					
					</c:otherwise>
				</c:choose>
		      </td>
		      <td><br>

			<html:form action="/member/deleteMail?action=deleteMail">
                          <html:hidden property="privMsgsAction" value="move"/>
                          <html:hidden property="folderName" value="trash"/>
				<html:submit>
					<bean:message key="button.movetotrash"/>
				</html:submit>
				<html:hidden property="messageId"/>
			</html:form>

		      </td>
		  </tr>
	</c:otherwise>
   </c:choose>
</table>


    </td>
  </tr>
</table>
<p></p>

