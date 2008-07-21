<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>


<c:url var="emailWebmaster" value="/action/member/prepareEmailWebmaster?action=prepareEmailWebmaster" />

<jsp:useBean id="now" class="java.util.Date" />

<br/><br/>

	<div align="center" class="blacksmall">Copyright &copy; 1997-<fmt:formatDate value="${now}" dateStyle="default" pattern="yyyy" /> MyAlumni<br>
	send your comments to <a href="<c:out value="${emailWebmaster}"/>">the webmaster</a>.<br>
      powered by
          <a href="http://www.naijatek.com"  target="_blank"><html:img page="/images/naijateklogo-100x10.gif" alt="Naijatek solutions (www.naijatek.com)" width="100" height="10" align="absmiddle" border="0"/></a><br>
  All rights reserved.
</div>
<br>
	<center>
		<span class="graysmall">
			<bean:message key="application.version"/>
		</span>&nbsp;
	</center>
<br>
