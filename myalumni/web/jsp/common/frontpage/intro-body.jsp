<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>



<%--
<table width="75%"  border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td>
					<c:choose>
						<c:when test="${ORGANIZATION_INTRO eq '' or ORGANIZATION_INTRO eq NULL}">
								<div class="redhighlight"><center>Your Organization Intro has not been configured yet.</center></div>
						</c:when>
						<c:otherwise>
							<c:out value="${ORGANIZATION_INTRO}" escapeXml="false"/>
						</c:otherwise>
					</c:choose>
      	</td>
	  </tr>
</table>
--%>
<myalumni:loadOrgIntro/>
<br><br>
<table width="75%"  border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td>
					<c:choose>
						<c:when test="${ORGANIZATION_INTRO eq '' or ORGANIZATION_INTRO eq NULL}">
								<div class="redhighlight"><center>Your Organization Intro has not been configured yet.</center></div>
						</c:when>
						<c:otherwise>
							<blockquote>
								<c:out value="${ORGANIZATION_INTRO}" escapeXml="false"/>
							</blockquote>
						</c:otherwise>
					</c:choose>
      	</td>
	  </tr>
</table>