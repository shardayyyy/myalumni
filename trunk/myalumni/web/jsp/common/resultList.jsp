<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<table cellpadding="0" cellspacing="0">
<tr>
<td>
  <c:forEach items="${result}" var="row">
    <li><c:out value="${row}"/></li>
  </c:forEach>
</td>
</tr>
</table>
