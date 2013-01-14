<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>


<script language="javaScript1.2" type="text/javascript">
<!--
    function selAllAdd(){
        for (i=0; i<document.memberForm.elements["lstSelectedIMs"].length; i++) {
            document.memberForm.elements["lstSelectedIMs"].options[i].selected = true;
        }
    }
    
    function submitMemberClass(button)
    {
      selAllAdd();
      document.memberForm.submit();
    }


//  End -->
</script>


<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="2"><br></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
    	<html:submit onclick="submitMemberClass();"><bean:message key="button.submit"/></html:submit>
    </td>
  </tr>
</table>