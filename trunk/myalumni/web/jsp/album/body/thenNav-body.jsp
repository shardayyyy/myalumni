<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="page1" value="/jsp/album/usThen.jsp"/>
<c:url var="page2" value="/jsp/album/usThen2.jsp"/>

<center>
<font size="3">The &quot;<font color="#0000FF">Us Then</font>&quot; Section <br>
                        Pages
                        [<a href="<c:out value="${page1}"/>"><font color="#0000FF">1</font></a>] 
                        [<a href="<c:out value="${page2}"/>"><font color="#0000FF">2</font></a>]                         
 </font></center>