<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="webmaster" value="/action/member/prepareEmailWebmaster?action=prepareEmailWebmaster"/>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0">Contacts</td>
  </tr>
  <tr class="portlet-section-body" height="30">
    <td class="fieldlabel"width="10%">Contact Webmaster:</td>
    <td><a href="<c:out value="${webmaster}"/>">Contact Webmaster</a></td>
  </tr>  
  <tr class="portlet-section-body" height="30">
    <td class="fieldlabel"width="10%">Email:</td>
    <td><a href="mailto:<c:out value="${ORG_EMAIL}"  default="[ORGANIZATION EMAIL NOT CONFIGURED]"/>"><c:out value="${ORG_EMAIL}"/></a></td>
  </tr> 
  <tr class="portlet-section-body" height="30">
    <td class="fieldlabel">Website:</td>
    <td><c:out value="${SERVER_URL}" default="[ORGANIZATION SERVER URL NOT CONFIGURED]"/></td>
  </tr>
  <tr class="portlet-section-body" height="30">
    <td class="fieldlabel">Forum:</td>
    <td><a href="<c:out value="${FORUM_URL}" default="FORUM URL NOT CONFIGURED]"/>" target="_blank"><c:out value="${FORUM_URL}"/></a></td>
  </tr>
</table>

<br>
<br>