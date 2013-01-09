<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<script  language="JavaScript1.2" type="text/javascript">
    <!--
    function selAllAdd(){
        for (i=0; i<document.memberForm.elements["selectedIMs"].length; i++) {
            document.memberForm.elements["selectedIMs"].options[i].selected = true;
        }
    }

    function submitUpdateMemberProfile(button)
    {
      selAllAdd();
      document.memberForm.submit();
    }
   //  End -->
</script>


<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="2">
    <br></td>
  </tr>
  <tr>
    <td> <div align="center">
		<html:submit onclick="submitUpdateMemberProfile(this);">
			<bean:message key="button.submit"/>
		</html:submit>    
    </div></td>
    <td><div align="center">
        <html:cancel>
                <bean:message key="button.cancel" />
        </html:cancel>
    </div></td>
  </tr>
</table>