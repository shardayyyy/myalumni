<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<c:url var="view" value="/action/admin/adminViewMember?action=viewMemberProfile"/>
<c:url var="unlock" value="/action/admin/unlockMemberAccount?action=unlockMemberAccount"/>

<table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" class="bg0"><bean:message key="table.title.newmembers"/> </td>
          </tr>
          <tr class="portlet-section-body">
            <td><p>
        	<c:set var="num_rec1" scope="page" value="-1"/>
        	<logic:iterate id="member" name="NEW_MEMBERS">
			<html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${view}"/>&memberUserName=<c:out value="${member.memberUserName}"/>"><c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/></a>

			<c:choose>
				<c:when test="${member.memberStatus == 'D'}">
					<span class="graysmall">(Deactivated)</span><html:img height="16" width="16" page="/images/spacer.gif" />
				</c:when>
				<c:when test="${ member.memberStatus == 'U'}">
                                  <span class="greensmall">(New)</span>
                                  
                                  <c:url var="searchForMembersLink1" value="/action/admin/memberSearch" >
						              <c:param name="action" value="searchForMembers" />
						              <c:param name="searchCategory" value="firstName" />
						              <c:param name="firstName" value="${member.firstName}" />
						              <c:param name="partialNameSearch" value="Y" />
						              <c:param name="isAdmin" value="Y" />
						          </c:url>
                                  <a href="<c:out value="${searchForMembersLink1}"/>"><html:img page="/images/search-16x16.gif" border="0" titleKey="label.searchbyfirstname"/></a>
                                  
                                  <c:url var="searchForMembersLink2" value="/action/admin/memberSearch" >
						              <c:param name="action" value="searchForMembers" />
						              <c:param name="searchCategory" value="lastName" />
						              <c:param name="lastName" value="${member.lastName}" />
						              <c:param name="partialNameSearch" value="Y" />
						              <c:param name="isAdmin" value="Y" />
						          </c:url>
                                  <a href="<c:out value="${searchForMembersLink2}"/>"><html:img page="/images/search-16x16.gif" border="0" titleKey="label.searchbylastname"/></a>
                                  

     
				</c:when>
				<c:when test="${member.memberStatus == 'L'}">
					<span class="redsmall">(Locked)</span><html:img page="/images/spacer.gif" width="5" /><a href="<c:out value="${unlock}"/>&memberUserName=<c:out value="${member.memberUserName}"/>"><html:img page="/images/lock.gif" border="0" align="absmiddle"/></a>
				</c:when>
				<c:when test="${member.memberStatus == 'X'}">
					<span class="redsmall">(Deleted)</span><html:img height="16" width="16" page="/images/spacer.gif" />
				</c:when>				
				<c:otherwise>
					<span class="redsmall">(Unknown Status)</span><html:img page="/images/spacer.gif" />
				</c:otherwise>
			</c:choose>


			<br>
			<c:set var="num_rec1" value="1"/>
		</logic:iterate>

		<c:if test="${num_rec1 == '-1'}">
		    <bean:message key="message.none"/>
		</c:if>
                </p>

            </td>
          </tr>
</table>
