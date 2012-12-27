<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="link" value="/action/filterUsersByAlphabelt?action=filterUsersByAlphabelt&alpha="/>

<script type="text/javascript" language="javaScript">

        function filter(alpha)
        {
			document.forms[0].alpha.value = alpha; 	
			document.forms[0].submit();
			// alert(alpha);
        }
    
</script>

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<td>

<p>&nbsp;</p>
<html:form action="/admin/filterUsersByAlphabelt" styleId="myForm">
	<html:hidden property="action" value="filterUsersByAlphabelt"/>
	<html:hidden property="alpha"/>
<font size="4"><center>
<a href="javascript:filter('a');">A</a> |
<a href="javascript:filter('b');">B</a> | 
<a href="javascript:filter('c');">C</a> | 
<a href="javascript:filter('d');">D</a> | 
<a href="javascript:filter('e');">E</a> | 
<a href="javascript:filter('f');">F</a> | 
<a href="javascript:filter('g');">G</a> | 
<a href="javascript:filter('h');">H</a> | 
<a href="javascript:filter('i');">I</a> | 
<a href="javascript:filter('j');">J</a> | 
<a href="javascript:filter('k');">K</a> | 
<a href="javascript:filter('l');">L</a> | 
<a href="javascript:filter('m');">M</a> | 
<a href="javascript:filter('n');">N</a> | 
<a href="javascript:filter('o');">O</a> | 
<a href="javascript:filter('p');">P</a> | 
<a href="javascript:filter('q');">Q</a> | 
<a href="javascript:filter('r');">R</a> | 
<a href="javascript:filter('s');">S</a> | 
<a href="javascript:filter('t');">T</a> | 
<a href="javascript:filter('u');">U</a> | 
<a href="javascript:filter('v');">V</a> | 
<a href="javascript:filter('w');">W</a> | 
<a href="javascript:filter('x');">X</a> | 
<a href="javascript:filter('y');">Y</a> | 
<a href="javascript:filter('z');">Z</a> <br><br>
<html:select property="searchCategory" styleId="searchCategory">
	<html:option value="firstName">First Name</html:option>
	<html:option value="lastName">Last Name</html:option>
	<html:option value="memberUserName">User Name</html:option>
	<html:option value="email">Email</html:option>
</html:select> 
</center></font>
</html:form>
</td>
</tr>
</table>
