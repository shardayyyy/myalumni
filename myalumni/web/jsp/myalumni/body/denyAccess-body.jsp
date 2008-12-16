<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="stylesheet" value="/style/${CSS_TYPE}/myalumni.css" />
<c:url var="JS" value="/js/global.js" />
<c:url var="login" value="/jsp/myalumni/login.jsp"/>

<script language="JavaScript1.2" type="text/javascript" src="<c:out value="${JS}"/>"></script>


<table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">Please Read </td>
  </tr>
  <tr class="portlet-section-body">
    <td valign="top"><table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1">
      <tr>
        <td colspan="2"><br><br>
        <h2>As an alumni of Federal Government College Ido-ani, you have access to this fantastic website that has information about your fellow classmates at your fingertips. MyAlumni allows you to search for alumni's of the school by using a wide range search of criteria.
            <br>The wealth of information is password protected and available to members. Please apply for an account online . If you already have an account login below using your assigned username.
            <br><br>If you were registered with the old website, please click "REGISTER / CONTACT WEBMASTER TO ACTIVATE ACCOUNT"
            </h2>
         </td>
      </tr>
      <tr>
        <td width="50%">
            <div align="center">
    <c:choose>
    	<c:when test="${popup == 'false'}">
            	<h1><a href="<c:out value="${login}"/>"><b>Login</b></a></h1>
    	</c:when>
    	<c:otherwise>
    		<h1><a href="javascript:window.opener.location.href='<c:out value="${login}"/>';window.close();"><b>LOGIN</b></a></h1>
    	</c:otherwise>
    </c:choose>
            </div><br>
	</td>
        <td width="50%">
            <div align="center">

    <c:choose>
    	<c:when test="${popup == 'false'}">
            	<h1><a href="<c:out value="${login}"/>"><b>Register / Contact Webmaster to Activate Account</b></a></h1>
    	</c:when>
    	<c:otherwise>
    		<h1><a href="javascript:window.opener.location.href='<c:out value="${login}"/>';window.close();"><b>REGISTER / CONTACT WEBMASTER TO ACTIVATE ACCOUNT</b></a></h1>
    	</c:otherwise>
    </c:choose>
            </div><br>
        </td>
      </tr>
    </table></td>
  </tr>
</table>

<c:remove var="popup" scope="session"/>
