<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<ul>
  <c:forEach items="${result}" var="row" >
    <li><c:out value="${row}"/></li>
  </c:forEach>
</ul>

