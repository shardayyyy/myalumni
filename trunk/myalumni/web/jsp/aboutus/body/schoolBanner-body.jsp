<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>





<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">Banner program</td>
  </tr>
  <tr class="portlet-section-body">
    <td>

	<br><br>
          <p align="center"><strong>If you are interested in displaying our banner
            on your page, kindly copy this code<br>
            below and paste it in an appropriate place in your HTML code...</strong></p>
          <p align="left">&nbsp;</p>
          <p align="left"><strong>&lt;!-- START HERE --&gt;</strong></p>
          <p align="left"> <strong>&lt;p align=&quot;center&quot;&gt;&lt;a href=&quot;<bean:message key="app.url"/>&quot;&gt;&lt;img
            src=&quot;<bean:message key="app.url"/>/images/fegocoid_banner2.gif&quot;
            border=&quot;0&quot;&gt;&lt;/a&gt;&lt;/p&gt;</strong></p>
          <p align="left"><strong>&lt;!-- STOP HERE --&gt;</strong></p>
          <p align="left">&nbsp;</p>
          <p align="center"><html:img page="/images/fegocoid_banner2.gif" border="0"/></p>
          <p align="center">&nbsp;</p>

    </td>
  </tr>
</table>
