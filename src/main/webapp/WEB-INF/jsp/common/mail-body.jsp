<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>



      <%-- Mail Body--%>
     <table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">

   <tr>
      <c:choose>
        <c:when  test="${ mailType == 'reply'}">
                <td height="30" colspan="2" class="bg0">Replying to 
                	<c:choose>
                		<c:when test="${privateMessageForm.toMemberFirstName != ''}">
                			<bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/>
                		</c:when>
                		<c:otherwise>
							 <bean:write name="privateMessageForm" property="guestEmail"/>               		
                		</c:otherwise>
                	</c:choose>
                </td>
        </c:when>
        <c:when  test="${ mailType == 'contact'}">
                <td height="30" colspan="2" class="bg0">Requesting To Make contact with <bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/></td>
        </c:when>
      </c:choose>
   </tr>


    <tr class="portlet-section-body">
      <td width="16%"><span class="fieldlabel"><bean:message key="label.from"/>:</span></td>
      <td width="84%"><c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/></td>
    </tr>


    <tr class="portlet-section-body">
      <td><span class="fieldlabel"><bean:message key="label.to"/>:</span></td>
      <td>
      <c:choose>
        <c:when  test="${ mailType == 'reply'}">
                 <bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/>
			 <c:if test="${ privateMessageForm.guestEmail ne null &&  privateMessageForm.guestEmail ne ''}">
				(<c:out value="${privateMessageForm.guestEmail}"/>)
			 </c:if>
        </c:when>
        <c:when  test="${ mailType == 'contact'}">
        	<bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/>

        </c:when>
      </c:choose>
      </td>
    </tr>


    <tr class="portlet-section-body">
      <td><span class="fieldlabel"><bean:message key="label.subject"/>:</span><font color="#cc0000">*</font></td>
      <c:choose>
        <c:when  test="${ mailType == 'reply'}">
                <td>
                	<html:text size ="60" maxlength="60" property="subject"/>
                </td>
        </c:when>
        <c:when  test="${ mailType == 'contact'}">
                <td>
                <c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/> would like to make contact
                </td>
        </c:when>
      </c:choose>
    </tr>

	<c:if test="${MODULE != 'ADMIN'}">
    <tr class="portlet-section-body">
      <td><span class="fieldlabel"><bean:message key="label.copyme"/>:</span></td>
                <td>
                	<html:checkbox property="copyMe" value="Y" titleKey="label.copyme"/>
                </td>
    </tr>
	</c:if>


    <tr class="portlet-section-body">
      <td valign="top"><span class="fieldlabel">
        <bean:message key="label.message"/>:<font color="#cc0000">*</font>
        <br><br><br>
<bean:message key="label.priority"/><br>
<html:radio value="L" property="priority" styleClass="noborder" titleKey="label.lowpriority"/>
<bean:message key="label.lowpriority"/> <html:img page="/images/icon/i.p.importance.l.gif" width="7" height="10" titleKey="label.lowpriority"/><br>

<html:radio  value="H" property="priority" styleClass="noborder" titleKey="label.highpriority"/>
<bean:message key="label.highpriority"/> <html:img page="/images/icon/i.p.importance.h.gif" width="4" height="10" titleKey="label.highpriority"/></span></td>

      <td valign="top">
      		<html:textarea property="messageText" cols="80" rows="10" titleKey="label.message"/>
      </td>
    </tr>
    <tr align="center" class="portlet-section-body">
      <td colspan="2">
                <html:submit>
                        <bean:message key="button.send"/>
                </html:submit>
                &nbsp;&nbsp;&nbsp;
                <html:cancel>
                	<bean:message key="button.cancel"/>
                </html:cancel>

				<html:hidden property="messageId"/>
				<html:hidden property="guestEmail"/>
				<html:hidden property="subject" />
				
				<html:hidden property="fromMemberFirstName"/>
				<html:hidden property="fromMemberLastName"/>
				
				<html:hidden property="toMemberLastName"/>
				<html:hidden property="toMemberFirstName"/>
				
				<html:hidden property="messageFromUserId"/>
				<html:hidden property="messageToUserId"/>


      </td>
      </tr>
  </table>


  <p>&nbsp;</p>
