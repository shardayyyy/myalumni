<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>


<html:form action="/member/addMember?action=addMember">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr valign="top">
    <td width="100%" height="50%">

      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="50%" valign="top">
                      <%--  About Me   --%>
                      <tiles:insert name="/jsp/common/aboutMeIn-body.jsp"/>
                      <p>&nbsp;</p>
          </td>
          <td width="50%" valign="top">
                  <%--  Instant Messengers  --%>
                <tiles:insert name="/jsp/common/instantMessengersIn-body.jsp"/>

                <%--  Your Location  --%>
                <tiles:insert name="/jsp/common/yourLocationIn-body.jsp"/>

                <%--  My FeGoCoId Account  --%>
                <tiles:insert name="/jsp/common/myAccountIn-body.jsp"/>
                
                <%--  Register button  --%>
                <tiles:insert name="/jsp/common/registerButton-body.jsp"/>
          </td>
        </tr>
      </table>
      <p>&nbsp;

      </p>
    </td>
  </tr>
</table>
</html:form>

<script type="text/javascript">
   Calendar.setup({
        inputField     :    "dob",      // id of the input field
        ifFormat       :    "<c:out value="${dateFormatPattern}"/>",       // format of the input field
        button         :    "trigger1",   // trigger for the calendar (button ID)
        step           :    1                // show all years in drop-down boxes (instead of every other year as default)
    }); 
  
     window.onload = function()
     {

		alert('Before registering please make sure you do not have a duplicate registration in the system. \n\n You can find this out by searching for your name under MyAlumni Search  from the menu bar above. If you DO find yourself and do not have access to login, this might be due to one of the following reasons.\n\n 1. Forgotten Password: Click on the \"forgot password?\" link at the top right corner above.  \n\n 2. Forgotten UserName: Click on the \"forgot password?\" link at the top right corner above. \n\n 3. Do not have access to the email you think you have registered with in the past, send the Administrator an email about this.\n\n 4. For any other problem, email the administrator, by using the link at the bottom of the page.');
     }
   
    	       
</script> 



