<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>


<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
<!-- Dynamic Version by: Nannette Thacker -->
<!-- http://www.shiningstar.net -->
<!-- Original by :  Ronnie T. Moore -->
<!-- Web Site:  The JavaScript Source -->
<!-- Use one function for multiple text areas on a page -->
<!-- Limit the number of characters per textarea -->
<!-- Begin
function textCounter(field,cntfield,maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else
		cntfield.value = maxlimit - field.value.length;
}
//  End -->

window.onload = function()
{
	textCounter(document.memberForm.comments,document.memberForm.remLen1,200);
}

</script>





<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
              <tr>
                <td height="30" colspan="2" class="bg0"><bean:message key="table.title.yourlocation"/></td>
              </tr>
              <tr class="portlet-section-body">
                <td width="30%" valign="top"><span class="fieldlabel"><bean:message key="label.address"/>:</span></td>
                <td width="70%">
                <html:textarea property="address" cols="40" rows="5" onkeydown="Counter(this,'200','Address');" onkeyup="Counter(this,'200','Address');" titleKey="label.address"/><br>
                <html:checkbox property="hideAddress" value="Y" titleKey="label.makethisprivate"/><font class="graysmall"> <bean:message key="label.makethisprivate"/></font>
                </td>
              </tr>
            <tr class="portlet-section-body">
                <td><span class="fieldlabel"><bean:message key="label.country"/>:<font color="#cc0000">*</font></span></td>
                <td>
                <%--     COUNTRY    --%>
                <myalumni:dropdown type="luGeneric" group="COU" fieldName="countryId" titleKey="label.country"><bean:write name="memberForm" property="countryId"/></myalumni:dropdown>
               	

               </td>
              </tr>
              <tr class="portlet-section-body">
                <td valign="top"><span class="fieldlabel"><bean:message key="label.membercomments"/><font color="#cc0000">*</font></span></td>
                <td>
			<html:textarea property="comments" cols="40" rows="5" titleKey="label.comments" onkeydown="textCounter(document.memberForm.comments,document.memberForm.remLen1,200)" onkeyup="textCounter(document.memberForm.comments,document.memberForm.remLen1,200)"/><div class="graysmall"><br>Maximum Length = <bean:message key="comments.length"/><br/></div>
		<input type="text" name="remLen1" size="3" maxlength="3" readonly="true" style="text-align:right;border:none;background-color:#FFFFFF;">&nbsp;Characters left
		</td>
              </tr>
            <c:choose>
              <c:when test="${ MODULE == 'ADMIN'}">
                <tr class="portlet-section-body">
                  <td valign="top"><span class="fieldlabel"><bean:message key="label.admincomments"/><font color="#cc0000">*</font></span></td>
                  <td>
                    <html:textarea property="adminComments" cols="40" rows="5" titleKey="label.comments" />
                  </td>
                </tr>
              </c:when>
            </c:choose>
            </table>
