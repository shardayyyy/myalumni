<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<%@ page import="net.naijatek.myalumni.util.SystemConfigConstants"%>


<script type="text/javascript" language="javaScript">
<!--

function removeLogo()
{
    var enter=prompt('<bean:message key="js.doyouwanttoremovelogo"/>','<bean:message key="js.deleteoptions"/>');
    if (enter == 'yes'){
        document.systemConfigForm[0].submit();
    }else if (enter == 'no'){
        alert('<bean:message key="js.logonotdeleted"/>');
        return;
    }else{
        alert('<bean:message key="js.logonotdeleted"/>');
        return;
    }
}



//-->
</script>


<c:set var="maxsize">
	<%= SystemConfigConstants.LOGO_MAX_SIZE %>
</c:set>


	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0"><bean:message key="table.title.logo"/></td>
	  </tr>
	  <tr class="portlet-section-body">
	    <td>
			<html:form action="/admin/removeLogo?action=removeLogo">	    
			            <myalumni:buildImageTag imageType="logo"><c:out value="${LOGO_NAME}"/></myalumni:buildImageTag>&nbsp;&nbsp;&nbsp;&nbsp; 
			            <c:if test="${LOGO_NAME ne null && LOGO_NAME ne ''}">
				            <a href="javascript:removeLogo();"><span class="redTitle">Remove Logo</span></a>
			            </c:if>
			</html:form>
	    </td>
	  </tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
	  <tr>
	    <td height="30" class="bg0">
	    (Maximum logo size allowed: <c:out value="${maxsize/1000}"/> KB or <c:out value="${maxsize/1000000}"/> MB)</td>
	  </tr>
	  <tr class="portlet-section-body">
	    <td>
<html:form action="/admin/uploadLogo?action=uploadLogo" enctype="multipart/form-data">
              <html:file property="logoUpload" size="80" titleKey="table.title.logo"/>
    	      &nbsp;&nbsp;&nbsp;&nbsp;
	      <html:submit>
	      		<bean:message key="button.uploadlogo"/>
    	      </html:submit>              
</html:form>


	    </td>
	  </tr>
	</table>
	<p>&nbsp;</p>

