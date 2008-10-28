<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<jsp:directive.page import="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="320">
<tr>
<td bgcolor="yellow">
<% 
List result = (List)request.getAttribute("result");
out.println("<ul>");
for(int i=0; i<result.size(); i++)
	out.println("<li>" + result.get(i) + "</li>");
out.println("</ul>");
%>
</td>
</tr>
</table>


<%-- not working? why?
<ul>
  <c:forEach items="${result}" var="row">
    <li><c:out value="${row}"/></li>
  </c:forEach>
</ul>
--%>

