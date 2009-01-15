<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="jcaptcha" prefix="jcaptcha"%>


<html:form action="/member/deleteMyMemberProfile?action=deleteMyMemberProfile">
  <table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" class="bg0">Delete My Member Profile</td>
    </tr>
    <tr class="portlet-section-body">
      <td><p><strong>Current IP Address:</strong> <strong class="graysmall"><c:out value="${USER_CONTAINER.token.lastIPAddress}"/> </strong></p>
      
	   <b> <c:out value="${USER_CONTAINER.token.firstName}"/>      	<c:out value="${USER_CONTAINER.token.lastName}"/>'s Member account is about to be deleted.</b>
      </td>
      </tr>
    <tr class="portlet-section-body">
      <td><span class="redhighlight">
      	<ul>
      	<li>By deleteing your member profile, you would not be able to login anymore with your current username and password.</li>
      	<li>You would not access anymore to you private messages. Please print or copy before proceeding.</li>
      	</ul></span><br>
      </td>
      </tr>
    <tr class="portlet-section-body">
      <td class="fieldlabel"><jcaptcha:question/>:<font color="#cc0000">*</font>
					<%-- Add the image--%>
	               <br><html:img page="/action/jcaptcha" align="absmiddle"/>
	               <br>
						<%-- Add the input tag--%>
		               <input type="text"  name="jcaptcha_response" />
		                <%-- Add the message tag--%>
		               <font color="#cc0000"><strong><jcaptcha:message/></strong></font>  	               
      </td>
    </tr>
    <tr class="portlet-section-body">
      <td colspan="2">
      	<html:submit>
      		<bean:message key="button.deletemyaccount"/>
      	</html:submit>
      	&nbsp;&nbsp;
      	<html:cancel>
      		<bean:message key="button.cancel"/>
      	</html:cancel>
      	</td>
      </tr>
  </table>
  </html:form>
