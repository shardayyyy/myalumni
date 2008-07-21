<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:hidden property="buttonAction" value="ignore" />
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0">Activation Instructions</td>
  </tr>

  <tr class="portlet-section-body">
    <td align="center" valign="middle" width="18%"><br>
        <html:form action="/member/fwdDisplayRequestAccessPage">
                <html:submit styleClass="button">
                        <bean:message key="button.activate.my.account"/>
                </html:submit>
        </html:form>
        </td>
    <td width="82%">Welcome back to FGC Idoani's new state of the art website. If you are listed on the website <i>(To search, click on the highlighted Alumni Portal above on the menu bar)</i> already you are half way there. Search for your name, click on your name and there would be an activation link for you to activate your account. <b>If</b> you already have a username and password waiting for you. To gain access to this kindly click onthe &quot;<strong>ACTIVATE MY ACCOUNT</strong>&quot; button to the left and go from there. <br>
      <br><br>NOTE:<br>
      <br>If you are listed on the website <i><strong>but</strong></i> do not have an email registered along with your profile please send an email to fgcidoani @ hotmail dot com with the subject line &quot;<strong>ACTIVATE ACCOUNT</strong>&quot;  and you would get a response within a couple of hours with your username and password. Please make sure it includes your full name, house color, and the years you were at FGC Idoani.</td>
  </tr>



  <html:form action="/member/forgotPassword?action=forgotPassword">
  <tr class="portlet-section-body">
    <td align="center" valign="middle"><br>

                <html:submit styleClass="button">
                        <bean:message key="button.getpassword"/>
                </html:submit><br><br>
                </td>
    <td>Have you forgotten your password, have it sent to the email you registered with us. Please enter your email address.<br><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
  </tr>
  </html:form>
    <html:form action="/member/forgotUserName?action=forgotUserName">
  <tr class="portlet-section-body">
    <td align="center" valign="middle"><br>

                <html:submit styleClass="button">
                        <bean:message key="button.getusername"/>
                </html:submit><br><br>
                </td>
    <td>Have you forgotten your member username, have it sent to the email you registered with us. Please enter your email address.<br><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
  </tr>
  </html:form>

 <c:if test="${isOnline != 'Y'}" >
  <tr class="portlet-section-body">
    <td align="center" valign="middle"><br>
        <html:form action="/member/prepareRegistration?action=prepareRegistration">
                <html:submit styleClass="button">
                        <bean:message key="button.register"/>
                </html:submit>
        </html:form>	</td>
    <td>If you are not listed on the website kindly register with us. This would get you listed on the website and also give you the power at the tips of your fingers to search for other members and make contact with them. </td>
  </tr>

 </c:if>

</table>
