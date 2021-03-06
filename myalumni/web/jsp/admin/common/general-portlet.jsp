<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="title_line" value="/images/title_line2.gif"/>
<c:url var="groups" value="/action/admin/listLookupGroups?action=listLookupGroups"/>
<c:url var="frontlinks" value="/action/admin/listFrontPageLinks?action=listFrontPageLinks"/>

 <c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
<table width="100%" border="0" cellpadding="5" cellspacing="5" >
	<tr>
		<td valign="top">

                        <table width="100%" border="0" cellpadding="1" cellspacing="0">
                            <tr>
                                    <td class="goldTitle"><bean:message key="tab.admin.general"/></td>
                            </tr>
                            <tr>
                                    <td height="6" background="<c:out value="${title_line}"/>"></td>
                            </tr>
                            <tr>
                                    <td valign="top" nowrap>
                                        <font size="2">                                            
                                            &#8226;&nbsp;<a href="<c:out value="${groups}"/>">Manage System Lookup Codes</a> - <span class="graysmall"><bean:message key="gen_module.dropdown"/></span><br><br>
                                            &#8226;&nbsp;<a href="<c:out value="${frontlinks}"/>">Manage Front Page Links</a> - <span class="graysmall"><bean:message key="gen_module.frontpagelinks"/></span>   <br><br>                                            
                                        </font>
                                    </td>
                            </tr>
                            <tr>
                                    <td height="10"></td>
                            </tr>
                        </table>

                </td>
	</tr>
</table>
</c:if>

