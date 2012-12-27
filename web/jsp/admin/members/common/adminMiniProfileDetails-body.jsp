<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>	
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>	
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>



<table width="450" border="0" align="center" cellpadding="3" cellspacing="0" class="tborder">
  <tr class="portlet-section-body">
    <td width="26%" align="center" valign="top">
    		<myalumni:buildImageTag imageType="avatar"><c:out value="${profile.avatar}"/></myalumni:buildImageTag>
    </td>
    <td width="74%" valign="top"> <p>
	<font color="green"><span class="fieldlabel"><bean:message key="label.username"/>:</span></font>
	<c:out value="${profile.memberUserName}"/>
	<br><span class="fieldlabel"><bean:message key="label.name"/>:</span>
    	<c:out value="${profile.firstName}"/> <c:out value="${profile.lastName}"/>

    	<c:if test="${profile.nickName != ''}">
    		(<c:out value="${profile.nickName}"/>)
    	</c:if>


	<c:choose>
		<c:when test="${profile.gender == 'M'}">
			<html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
		</c:when>
		<c:when test="${ profile.gender == 'F'}">
			<html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
			<span class="maiden"><i>(nee <c:out value="${profile.maidenName}"/>)</i></span>
		</c:when>
	</c:choose>

    	<br>
        <span class="fieldlabel"><bean:message key="label.location"/>:</span> <c:out value="${profile.address}"/> <c:out value="${profile.countryLabel}"/> <br>
        <span class="fieldlabel"><bean:message key="label.birthday"/>:</span>
        	<fmt:formatDate value="${profile.dob}"/>
        	<br>
        <span class="fieldlabel"><bean:message key="label.email"/>:</span>&nbsp;<html:img page="/images/email.gif" width="15" titleKey="label.email" height="15"  align="absmiddle"/> <c:out value="${profile.email}"/><br>
      <span class="fieldlabel"><bean:message key="label.instantmessengers"/>:</span>
            	<logic:notEmpty name="profile" property="messengers">
                	<logic:iterate id="im" name="profile" property="messengers" indexId="recIdx">
			            <c:out value="${im.messenger.label}"/>,
                	</logic:iterate>
                </logic:notEmpty>				
            	<br>
      <span class="fieldlabel"><bean:message key="label.websiteurl"/>:</span>
      		<c:if test="${profile.website != ''}">
      			<html:img page="/images/www.gif" width="15" titleKey="label.websiteurl" height="15" align="absmiddle"/> <c:out value="${profile.website}"/>
      		</c:if>
      		<br>
      <span class="fieldlabel"><bean:message key="label.yearsatschool"/>:</span> from <c:out value="${profile.yearIn}"/>-<c:out value="${profile.yearOut}"/><br>
      <span class="fieldlabel"><bean:message key="label.career"/>:</span> <c:out value="${profile.careerLabel}"/><br>
      <span class="fieldlabel"><bean:message key="label.phone"/>:</span> <c:out value="${profile.phone}"/><br>
      <span class="fieldlabel"><bean:message key="label.lastlogin"/>:</span>  <fmt:formatDate value="${profile.lastLogonDate}" type="both" timeStyle="short"/><br>
      <span class="fieldlabel"><bean:message key="label.comments"/>:</span> <c:out value="${profile.comments}"/>.<br>
      <span class="fieldlabel"><bean:message key="label.admincomments"/>:</span> <c:out value="${profile.adminComments}"/>.

</p>      </td>
  </tr>
</table>
<form name="form1" method="post" action="">
  <div align="center">
    <input name="Submit" type="submit" class="button" onClick="window.close();" value="Close Window">
  </div>
</form>
