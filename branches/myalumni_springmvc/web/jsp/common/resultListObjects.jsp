<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<ul>
  <c:forEach items="${result}" var="row" >
    <li><c:out value="${row.firstName}"/> <c:out value="${row.lastName}"/></li>
  </c:forEach>
</ul>
<%--
<select name="messageToUserId" >
<c:forEach items="${result}" var="row" >
	<option value="<c:out value="${row.memberId}"/>"><c:out value="${row.firstName}"/> <c:out value="${row.lastName}"/> </option>
 </c:forEach>
</select>
--%>