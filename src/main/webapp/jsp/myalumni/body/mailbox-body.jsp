<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<c:url var="prototype" value="/js/prototype/prototype.js" scope="page"/>
<c:url var="scriptaculous" value="/js/prototype/scriptaculous.js" scope="page"/>
<c:url var="autocomplete" value="/js/prototype/AutoComplete.js" scope="page"/>

<c:url var="effects" value="/js/prototype/effects.js" scope="page"/>
<c:url var="controls" value="/js/prototype/controls.js" scope="page"/>

<script type="text/javascript" src='<c:out value="${prototype}"/>'></script>
<script type="text/javascript" src='<c:out value="${scriptaculous}"/>'></script>
<script type="text/javascript" src='<c:out value="${autocomplete}"/>'></script>

<script type="text/javascript" src='<c:out value="${effects}"/>'></script>
<script type="text/javascript" src='<c:out value="${controls}"/>'></script>

<script type="text/javascript">
window.onload = function(){	

	document.getElementById('messageToUserName').focus();
				
  	new Ajax.Autocompleter('messageToUserName', 'suggestionBoxNick',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',	parameters: "action=genericAjaxSearch&approach=ajax&ajaxFormat=obj&searchCriteria=fullName"})					
	 }
</script>




<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" align="left">
		<tiles:insert name="/jsp/common/mailQuota-body.jsp" />
    </td>
    <td valign="middle" align="left">
    <%-- Mail Body --%>
    <html:form  action="/member/prepareComposePrivateMessage?action=prepareComposePrivateMessage">
    		<bean:message key="label.membername"/>:
    		
                <html:text property="messageToUserName" styleId="messageToUserName" size="30" maxlength="30" titleKey="label.membername"/>
                <div class="autocomplete" id="suggestionBoxNick" style="display:none;border:1px solid black;background-color:white;"></div>       


                                                                                    
            <html:submit>
                    <bean:message key="button.compose"/>
            </html:submit>
            <span class="autocomplete" id="indicator" style="display: none"><html:img page="/images/flower.gif" altKey="label.admin.member.working"/></span>            
    </html:form>

    </td>
  </tr>
</table>


<table width="99%"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="150" valign="top">
      <%-- Message Folders --%>
      <tiles:insert name="/jsp/common/mailFolder-body.jsp"/>
    </td>
    <td valign="top" align="left">
    <%-- Mail Body --%>
    <html:form  action="/member/deleteMail?action=deleteMail">
            <html:hidden property="privMsgsAction"/>
            <tiles:insert name="/jsp/common/mailboxContent-body.jsp"/>
    </html:form>

    </td>
  </tr>
</table>
<p></p>





