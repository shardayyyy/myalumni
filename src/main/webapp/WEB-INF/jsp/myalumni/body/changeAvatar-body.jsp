<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
<!-- Begin
function reselectImage(form) {
	if (form.value == 'TRUE'){
		alert('Remember to reselect your avatar again by clicking on the browse button.');
	}
}
//  End -->
</script>

<c:set var="maxsize">
<bean:message key="avatar.image.size"/>
</c:set>

<html:form action="/member/updateAvatar?action=updateMemberAvatar" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0">1. <bean:message key="table.title.currentavatar"/>:</td>
	  </tr>
	  <tr class="portlet-section-body">
	    <td>
                <myalumni:buildImageTag imageType="editableavatar"><c:out value="${USER_CONTAINER.token.avatar}"/></myalumni:buildImageTag><br/>

	    </td>
	  </tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0">2. <bean:message key="table.title.avatarinst1"/>
	    <c:out value="${maxsize/1000}"/> KB or <c:out value="${maxsize/1000000}"/> MB)</td>
	  </tr>
	  <c:if test="${ USER_CONTAINER.token.overWriteAvatar == 'true'}">
		  <tr class="portlet-section-body">
		    <td>
			<b><font color="red"><bean:message key="table.title.uploadoverwrite"/></font> :</b>
			<html:select property="avatarUploadOverwrite" titleKey="table.title.uploadoverwrite" onchange="reselectImage(this);">
				<option value="" selected>-- Overwrite Avatar --</option>
				<option value="TRUE" selected>Yes</option>
				<option value="FALSE" selected>No</option>
			</html:select>
		    </td>
		  </tr>
	  </c:if>
	  <tr class="portlet-section-body">
	    <td>

              <html:file property="avatarUpload" size="80" titleKey="table.title.avatarinst1"/>

 &nbsp;&nbsp;
	      <html:submit styleClass="button">
	      		<bean:message key="button.uploadavatar"/>
    	      </html:submit>&nbsp;&nbsp;&nbsp;
	      <html:cancel styleClass="button">
	      		<bean:message key="button.cancel"/>
    	  </html:cancel>

	    </td>
	  </tr>
	</table>
	<p>&nbsp;</p>
</html:form>
