<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" colspan="2" class="bg0"><bean:message key="table.title.yourlocation"/></td>
          </tr>
          <tr class="portlet-section-body">
            <td width="21%" class="fieldlabel"><bean:message key="label.address"/>:</td>
            <td width="79%"><bean:write property="address" name="memberForm"/> </td>
          </tr>
          <tr class="portlet-section-body">
            <td class="fieldlabel"><bean:message key="label.country"/>:</td>
            <td><bean:write property="countryLabel" name="memberForm"/></td>
          </tr>
          <tr class="portlet-section-body">
            <td valign="top" class="fieldlabel"><bean:message key="label.membercomments"/>:</td>
            <td><p>
            <bean:write property="comments" name="memberForm"/>

            </p></td>
          </tr>
          <c:choose>
                  <c:when test="${ MODULE == 'ADMIN'}">
                    <tr class="portlet-section-body">
                      <td valign="top" class="fieldlabel"><bean:message key="label.admincomments"/>:</td>
                      <td><p><bean:write property="adminComments" name="memberForm"/></p></td>
                    </tr>
                  </c:when>
          </c:choose>
        </table>
