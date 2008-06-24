<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="jcaptcha" prefix="jcaptcha"%>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>


<c:set var="memberUserName">
	<bean:write name="privateMessageForm" property="fromUserName"/>
</c:set>

<html:form action="/member/emailWebmaster?action=emailWebmaster">

<html:hidden property="toMemberFirstName"/>
<html:hidden property="toMemberLastName"/>
<html:hidden property="messageFromUserId"/>
<html:hidden property="messageToUserId"/>
<html:hidden property="type" value="contact"/>

<c:set var="_guestId">
	<%= BaseConstants.GUEST_USERNAME_ID %>
</c:set>

  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">

   <tr>
         <td height="30" colspan="2" class="bg0">Sending a message to <bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/></td>
   </tr>
	<c:choose>
		<c:when test="${ privateMessageForm.messageFromUserId == _guestId}">
		    <tr class="portlet-section-body">
		      <td width="16%"><span class="fieldlabel"><bean:message key="label.fromFirstLast"/>:</span><font color="#cc0000">*</font></td>
		      <td width="84%"><html:text property="guestName" size="40" maxlength="40" titleKey="label.fromFirstLast"/></td>
		    </tr>
		    <tr class="portlet-section-body">
		      <td width="16%"><span class="fieldlabel"><bean:message key="label.email"/>:</span><font color="#cc0000">*</font></td>
		      <td width="84%"><html:text property="guestEmail" size="70" maxlength="70" titleKey="label.email"/> </td>
		    </tr>
		</c:when>
		<c:otherwise>
		    <tr class="portlet-section-body">
		      <td width="16%"><span class="fieldlabel"><bean:message key="label.from"/>:</span></td>
		      <td width="84%">
		      		<c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/>
		      		<input type="hidden" name="guestName" value="<c:out value="${USER_CONTAINER.token.firstName}"/> <c:out value="${USER_CONTAINER.token.lastName}"/>">
		      </td>
		    </tr>	
		    
		</c:otherwise>
	</c:choose>

    <tr class="portlet-section-body">
      <td><span class="fieldlabel"><bean:message key="label.to"/>:</span></td>
      <td>
                <bean:write name="privateMessageForm" property="toMemberFirstName"/> <bean:write name="privateMessageForm" property="toMemberLastName"/>
      </td>
    </tr>


    <tr class="portlet-section-body">
      <td><span class="fieldlabel"><bean:message key="label.subject"/>:</span><font color="#cc0000">*</font></td>
	<td>
		<html:text property="subject" size="60" maxlength="60" titleKey="label.subject"/>
	</td>
    </tr>


    <tr class="portlet-section-body">
      <td valign="top" class="fieldlabel">
        <bean:message key="label.message"/>:<font color="#cc0000">*</font>
        <br><br><br>
<bean:message key="label.priority"/><br>
<html:radio  value="L" property="priority" styleClass="noborder" titleKey="label.lowpriority"/>
<bean:message key="label.lowpriority"/> <html:img page="/images/icon/i.p.importance.l.gif" width="7" height="10" titleKey="label.lowpriority"/><br>

<html:radio  value="H" property="priority" styleClass="noborder" titleKey="label.highpriority"/>
<bean:message key="label.highpriority"/> <html:img page="/images/icon/i.p.importance.h.gif" width="4" height="10" titleKey="label.highpriority"/></td>

      <td valign="top">
      		<html:textarea property="messageText" cols="45" rows="5" titleKey="label.message"/>
      </td>
    </tr>

    <tr class="portlet-section-body">
      	<td valign="top"><span class="fieldlabel"><jcaptcha:question/>:</span><font color="#cc0000">*</font>
      	       <%-- Add the image--%>
               <br><html:img page="/action/jcaptcha" align="absmiddle"/>
               <br>
               
               <html:link action="/member/prepareEmailWebmaster?action=prepareEmailWebmaster">Get Another Challenge</html:link>
      	</td>
		<td valign="middle">
               <%-- Add the input tag--%>
               <input type="text"  name="jcaptcha_response" />
				<br>
                <%-- Add the message tag--%>
               <font color="#cc0000"><strong><jcaptcha:message/></strong></font><br>
		</td>
    </tr>
    <tr align="center" class="portlet-section-body">
      <td colspan="2">
                <html:submit styleClass="button">
                        <bean:message key="button.send"/>
                </html:submit>
                &nbsp;&nbsp;&nbsp;
		<input class="button" type="button" value="<bean:message key="button.cancel"/>" onclick="javascript:history.back();">
      </td>
      </tr>
  </table>
 </html:form>



