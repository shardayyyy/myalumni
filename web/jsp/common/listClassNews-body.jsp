<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="viewClassNews" value="/action/member/viewClassNews?action=viewClassNews&classNewsId="/>

<myalumni:loadClassNews/>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
   <tr>
      <td height="30" class="bg0"><bean:message key="label.classnews"/><c:out value="${USER_CONTAINER.token.yearOut}"/></td>
   </tr>
   <tr class="portlet-section-body">
       <td>
<fmt:bundle basename="ApplicationResources">
             <display:table name="requestScope.listOfClassNews" cellpadding="3" cellspacing="1" pagesize="5" requestURI="/action/member/listClassNews?action=listClassNews" excludedParams="action memberPassword memberUserName" id="row">
                     <display:column titleKey="label.admin.system.subject">
                     		<c:if test="${row.systemNews == 'Y'}">	
	                     		<html:img page="/images/new.gif" align="absbottom"/>&nbsp;	
	                     	</c:if>
	                     	<a href="<c:out value="${viewClassNews}"/><c:out value="${row.classNewsId}"/>"><c:out value="${row.subject}"/></a>	
                     		<c:if test="${row.systemNews == 'Y'}">	
	                     		<html:img page="/images/new.gif" align="absbottom"/>&nbsp;	
	                     	</c:if>	                     	
                     </display:column> 
                      <display:column titleKey="label.admin.system.author">
                      	<c:out value="${row.author.firstName}"/> <c:out value="${row.author.lastName}"/>
                      </display:column>                                                                                         
             </display:table>  
</fmt:bundle>                    
      </td>
   </tr>
</table>
