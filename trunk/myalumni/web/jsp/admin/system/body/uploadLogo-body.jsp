<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<%@ page import="net.naijatek.myalumni.util.SystemConfigConstants"%>


<c:set var="maxsize">
	<%= SystemConfigConstants.LOGO_MAX_SIZE %>
</c:set>

<html:form action="/admin/uploadLogo?action=uploadLogo" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0"><bean:message key="table.title.logo"/></td>
	  </tr>
	  <tr class="portlet-section-body">
	    <td>
                <myalumni:buildImageTag imageType="logo"/><br/>

	    </td>
	  </tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0">
	    <c:out value="${maxsize/1000}"/> KB or <c:out value="${maxsize/1000000}"/> MB)</td>
	  </tr>
	  <tr class="portlet-section-body">
	    <td>

              <html:file property="logoUpload" size="80" titleKey="table.title.logo"/>

    	      &nbsp;&nbsp;&nbsp;&nbsp;
	      <html:submit styleClass="button">
	      		<bean:message key="button.uploadlogo"/>
    	      </html:submit>
    	      &nbsp;&nbsp;&nbsp;&nbsp;
	      <html:cancel styleClass="button">
	      		<bean:message key="button.cancel"/>
    	  </html:cancel>

	    </td>
	  </tr>
	</table>
	<p>&nbsp;</p>
</html:form>
