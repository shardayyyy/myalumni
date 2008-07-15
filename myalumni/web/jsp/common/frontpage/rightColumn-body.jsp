<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<c:url var="miniProfile" value="/action/member/displayMiniProfile?action=displayMiniProfile"/>


<table width="100%"  border="0" cellspacing="1" cellpadding="1">
<tr>
        <td class="bg0">Others logged on Now: </td>
      </tr>
      <tr>
        <td>
	<c:set var="num_rec" scope="page" value="-1"/>
	<logic:notEmpty name="onlineusers">
		<logic:iterate id="user" name="onlineusers" indexId="pIdx">
				<div class="blacksmall"><a href="<c:out value="${miniProfile}"/>&memberUserName=<c:out value="${user.memberUserName}"/>"  onclick="newPopup(this.href,'name');return false" title="View <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> details"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> (<c:out value="${user.yearOut}"/>)</a></div>
				<c:set var="num_rec" value="1"/>

		</logic:iterate>
	</logic:notEmpty>
        <c:if test="${num_rec == '-1'}">
            <bean:message key="message.none"/>
        </c:if>

		</td>
      </tr>
      <tr>
      	<td>&nbsp;</td>
      </tr>
      
      <tr>
        <td class="fieldlabel">

		<myalumni:displayLatestMembers tableWidth="100%"/> 
        </td>
      </tr>
      <tr>
        <td class="fieldlabel"></td>
      </tr>

    </table>
