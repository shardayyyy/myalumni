<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>



<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="50%" valign="top" align="center"><table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="3" class="bg0">Total Break Down </td>
      </tr>
      <tr class="portlet-section-body">
        <td width="24%" class="fieldlabel">Unknown:</td>
        <td width="10%" align="center" class="fieldlabel"><html:img page="/images/user.gif" width="17" height="15"/></td>
        <td width="66%" class="fieldlabel"><c:out value="${stats.unknownGenderCount}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td class="fieldlabel">Males:</td>
        <td align="center" class="fieldlabel"><html:img page="/images/user.gif" width="17" height="15"/></td>
        <td class="fieldlabel"><c:out value="${stats.maleCount}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td class="fieldlabel">Females:</td>
        <td align="center" class="fieldlabel"><html:img page="/images/user.gif" width="17" height="15"/></td>
        <td class="fieldlabel"><c:out value="${stats.femaleCount}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td class="fieldlabel">Total:</td>
        <td align="center" class="fieldlabel"><html:img page="/images/user.gif" width="17" height="15"/></td>
        <td class="fieldlabel"><c:out value="${stats.totalCount}"/></td>
      </tr>      
    </table></td>
    <td width="50%" valign="top" align="center">
    <%-- ACCOUNT STATUS --%>
    <table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="2" class="bg0">Account Status </td>
      </tr>
      <tr class="portlet-section-body">
        <td width="21%"><span class="fieldlabel">Unregistered:</span></td>
        <td width="79%" class="fieldlabel"><c:out value="${stats.newMembers}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel">Registered:</span></td>
        <td class="fieldlabel"><c:out value="${stats.totalCount - (stats.lockedAccountCount + stats.deactivatedAccountCount)}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel">Deactivated:</span></td>
        <td class="fieldlabel"><c:out value="${stats.deactivatedAccountCount}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel">Locked:</span></td>
        <td class="fieldlabel"><c:out value="${stats.lockedAccountCount}"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel">No e-Mail:</span></td>
        <td class="fieldlabel"><c:out value="${stats.noEmailCount}"/></td>
      </tr>

    </table>
	
	</td>
  </tr>
  <tr>
    <td valign="top" align="center">
    <br>
    <c:if test="${hasDorm == 'Y'}">
    <table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="3" class="bg0">Dormitory Break Down </td>
      </tr>
      
   <logic:present name="stats" property="dormitoryMap">
	  	<logic:iterate id="dorm" name="stats" property="dormitoryMap">
	      <tr class="portlet-section-body">
	        <td width="25%" class="fieldlabel"><c:out value="${dorm.value}"/></td>
	        <td width="75%"><c:out value="${dorm.key}"/></td>
	      </tr>
	      </logic:iterate>
    </logic:present>
      
      
    </table>
    </c:if>
    <br>
      <%-- EMAIL --%>
    <table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="3" class="bg0">Email Domain Break Down </td>
      </tr>
      
   <logic:present name="stats" property="emailMap">
	  	<logic:iterate id="email" name="stats" property="emailMap">
	      <tr class="portlet-section-body">
	        <td width="25%" class="fieldlabel"><c:out value="${email.value}"/></td>
	        <td width="75%"><c:out value="${email.key}"/></td>
	      </tr>
	      </logic:iterate>
    </logic:present>
    </table>
    
    </td>
    <td valign="top" align="center">
    <br>
    <%-- COUNTRY DEMOGRAPHICS --%>
        <table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="3" class="bg0">Country Demographics </td>
      </tr>
      
    <logic:present name="stats" property="countryMap">
	  	<logic:iterate id="country" name="stats" property="countryMap">
	      <tr class="portlet-section-body">
	        <td width="25%" class="fieldlabel"><c:out value="${country.value}"/></td>
	        <td width="75%"><c:out value="${country.key}"/></td>
	      </tr>
	      </logic:iterate>
    </logic:present>
 
    </table>
    
	</td>
  </tr>
</table>
