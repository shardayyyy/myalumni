<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>





<table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr class="portlet-section-body">
    <td>
			<c:out value="${ORGANIZATION_ABOUTUS}" escapeXml="false" default="NEEDS TO BE UPDATED..."/>
    </td>
  </tr>
</table>


<table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">About Federal Government College Idoani</td>
  </tr>
  <tr class="portlet-section-body">
    <td>

                  <p align="left"><b><font color="#FF0000">TOWN
                    OF IDOANI</font></b></p>
                  <p>Added August 17th, 2001 According to a reliable source there were 70
                    students that started the great FGC idoani in January 1978. They
                    were actually the 1977 set of which they started late and then
                    finished in 1982. Please if you were one of what I would refer
                    to as &quot;The Golden Alumni's&quot;, please kindly <a href="mailto:<bean:message key="email.webmaster"/>">contact
                    me</a> to give me the gist of what things were like. </p>
                  <p></p>
                  <p>Thanks...</p>
                  <p>FGC Ido-ani Webmaster </p>
                <h4 align="center">Federal Government College Ido-ani <br>
                  P.M.B. 1054 <br>
                  Ido-ani, Ondo State. <br>
              Nigeria.</h4>

    </td>
  </tr>
</table>
<p></p>

	<%-- Begining of Head boys and girls and principles --%>
	<tiles:insert name="/jsp/common/frontpage/principlesandprefects-body.jsp" flush="true"/>
