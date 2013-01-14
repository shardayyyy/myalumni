<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="bg0" >
    <td height="30" width="100">Line</td>
    <td>Content</td>
  </tr>
  <tr class="portlet-section-body">
    <td colspan="2">

      <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <c:set var="lineNumber" scope="page" value="0"/>
        
          <logic:present name="logContent" >
	         <logic:iterate id="line" name="logContent" indexId="pIdx">
	          <tr>
	            <td class="bg4" width="20"><c:out value="${lineNumber}"/></td>
	            <td nowrap>
	                  <c:out value="${line}"/>
	            </td>
	          </tr>
	          <c:set var="lineNumber" scope="page" value="${pIdx+1}"/>
	          </logic:iterate>
          </logic:present>
      </table>
      
    </td>
  </tr>
</table>
<c:remove var="logContent" scope="session"/>
