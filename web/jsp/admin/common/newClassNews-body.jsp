<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<c:url var="view" value="/action/admin/viewClassNews?action=viewClassNews"/>



<table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" class="bg0"><bean:message key="table.title.newclassnews"/></td>
          </tr>
          <tr class="portlet-section-body">
            <td><p>
            <logic:present name="NEW_CLASSNEWS">
            	<c:set var="num_rec2" scope="page" value="-1"/>
            	<logic:iterate id="news" name="NEW_CLASSNEWS">
            		<html:img page="/images/arrow.gif" width="11" height="11"/> <a href="<c:out value="${view}"/>&classNewsId=<c:out value="${news.classNewsId}"/>"><c:out value="${news.subject}"/></a>
            		<br>
					<c:set var="num_rec2" value="1"/>
				</logic:iterate>
			</logic:present>
		<c:if test="${num_rec2 == '-1'}">
		    <bean:message key="message.none"/>
		</c:if>
                </p></td>
          </tr>
</table>
