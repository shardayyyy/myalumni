<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form  action="/admin/adminUpdateMemberProfile?action=updateMemberProfile">

		<%--  Edit Profile Content  --%>
		<tiles:insert name="/jsp/common/editProfileContent-body.jsp"/>
	
</html:form>
<script type="text/javascript">
   Calendar.setup({
        inputField     :    "dob",      // id of the input field
        ifFormat       :    "<c:out value="${dateFormatPattern}"/>",       // format of the input field
        button         :    "trigger1",   // trigger for the calendar (button ID)
        step           :    1                // show all years in drop-down boxes (instead of every other year as default)
    });      
</script> 