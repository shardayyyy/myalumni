<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
	
<div id="welcome" title="Dormitory List" selected="true">
<ul>
<c:set var="num_rec" scope="page" value="-1"/>
	<logic:present name="listOfIphoneDorms">
		<logic:iterate id="dorm" name="listOfIphoneDorms">
		
			<c:url var="searchForMembersLink" value="/action/iphone/filter" >
				<c:param name="action" value="filterDorms" />
				<c:param name="dormitoryId" value="${dorm.lookupCodeId}" />
			</c:url>
                       
            <li><a href="<c:out value="${searchForMembersLink}"/>"><div class="list-link"><p><c:out value="${dorm.label}"/> Dormitory</p></div></a></li>
		<c:set var="num_rec" value="1"/>
		</logic:iterate>
	</logic:present>
	
	<c:if test="${num_rec == '-1'}">
		<li><p>No Dormitories Found.</p></li>
		<li><a href="index.jsp#welcome"><div class="list-link"><p>&laquo;&laquo; Back </p></div></a></li>
	</c:if>               
</ul>  	

</div>	