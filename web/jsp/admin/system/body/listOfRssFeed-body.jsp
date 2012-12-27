<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<c:url var="edit" value="/action/admin/prepareUpdateRssFeed?action=prepareUpdateRssFeed"/>


<html:form action="/admin/prepareAddRssFeed?action=prepareAddRssFeed">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tbody>
            <tr>
              <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
            </tr>
            <tr class="bg0">
              <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.admin.rss.listofrss"/></span></div></td>
              <td align="right"><html:submit styleClass="button"><bean:message key="buttons.admin.rss.addrss"/></html:submit>&nbsp;&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
            </tr>
            <tr>
              <td colspan="2" ><table width="100%" border="0" cellspacing="1" cellpadding="3" bgcolor="#CCCCCC">
                  <tr bgcolor="#ffffff" class="BoldSmallBlack">
                    <td height="25" width="15%" align="center"><bean:message key="label.common.action"/></td>
                    <td width="75%"><bean:message key="label.admin.description"/></td>
                    <td width="10%" align="center"><bean:message key="label.admin.status"/></td>
                    </tr>

                        <c:set var="num_rec" scope="page" value="-1"/>
                        <logic:iterate id="rss" name="listOfRssFeeds" indexId="pIdx">
                            <tr bgcolor="#ffffff">
                                <td height="25" align="center"><a href="<c:out value="${edit}"/>&rssFeedId=<c:out value="${rss.rssFeedId}"/>"><bean:message key="label.common.edit"/></a></td>
                                <td><c:out value="${rss.url}"/></td>                                
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${rss.status == 'A'}">
                                                <div class="activeStatus"><bean:message key="label.active"/></div>
                                        </c:when>
                                        <c:when test="${rss.status == 'I'}">
                                                <div class="inactiveStatus"><bean:message key="label.inactive"/></div>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        <c:set var="num_rec" scope="page" value="${pIdx+1}"/>
                        </logic:iterate>

                        <c:if test="${num_rec == '-1'}">
                            <tr  bgcolor="#ffffff">
                                    <td scope="row" height="25" colspan="4"><bean:message key="message.norecordsfound"/></td>
                            </tr>
                        </c:if>

              </table></td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
</table>
 </html:form>


