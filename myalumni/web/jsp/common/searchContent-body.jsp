<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

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
		
	displayOptions(document.memberForm.searchCategory);
		
	var firstNameCompleter = new Ajax.Autocompleter('firstName', 'suggestionBoxFirst',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',	parameters: "action=genericAjaxSearch&ajaxFormat=str&approach=ajax&searchCriteria=firstName"})
				
  	var lastNameCompleter = new Ajax.Autocompleter('lastName', 'suggestionBoxLast',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',	parameters: "action=genericAjaxSearch&ajaxFormat=str&approach=ajax&searchCriteria=lastName"})

  	var maidenNameCompleter = new Ajax.Autocompleter('maidenName', 'suggestionBoxMaiden',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',	parameters: "action=genericAjaxSearch&ajaxFormat=str&approach=ajax&searchCriteria=maidenName"})
				
  	var nickNameCompleter = new Ajax.Autocompleter('nickName', 'suggestionBoxNick',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',	parameters: "action=genericAjaxSearch&ajaxFormat=str&approach=ajax&searchCriteria=nickName"})		
						
	}	
</script>

<%--  	

		completer = new Ajax.Autocompleter('search', 'suggestionBoxFirst',"/myalumni/action/member/genericAjaxSearch?", {	
				indicator: 'indicator',
				parameters: "action=genericAjaxSearch&approach=ajax&searchCriteria=firstName"
			}
		) 
			
<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
window.onload = function()
{
	displayOptions(document.memberForm.searchCategory);
}

</script> --%>

  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0"><bean:message key="table.title.searchForAnIndividual"/> </td>
    </tr>
    <tr class="portlet-section-body">
      <td width="19%" align="right" class="fieldlabel"><font color="#cc0000">*</font><bean:message key="label.searchby"/> :</td>
      <td width="81%">

      <%--     SEARCH CATEGORY    --%>
      <c:choose>
        <c:when test="${ MODULE == 'ADMIN'}">
          <html:select property="searchCategory" titleKey="label.searchCategory" onchange="displayOptions(this)">
            <option value="" selected>-SELECT SEARCH CATEGORY-</option>
            <html:options collection="luAdminSearchCategory" property="value" labelProperty="label"/>
          </html:select>
        </c:when>
        <c:otherwise>
          <html:select property="searchCategory" titleKey="label.searchCategory" onchange="displayOptions(this)">
            <option value="" selected>-SELECT SEARCH CATEGORY-</option>
            <html:options collection="luSearchCategory" property="value" labelProperty="label"/>
          </html:select>
        </c:otherwise>
      </c:choose>


        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="fieldlabel"><bean:message key="label.patialNameSearch"/></span>

<html:checkbox property="partialNameSearch" value="Y" titleKey="label.patialNameSearch"/>

</td>
    </tr>

    <c:choose>
      <c:when test="${ MODULE == 'ADMIN'}">
        <tr class="portlet-section-body">
          <td align="right" class="fieldlabel"><bean:message key="label.username"/>:</td>
          <td>
            <html:text property="memberUserName" size="30" maxlength="30" titleKey="label.username"/>
          </td>
        </tr>
        <tr class="portlet-section-body">
          <td align="right" class="fieldlabel"><bean:message key="label.email"/>:</td>
          <td>
            <html:text property="email" size="50" maxlength="50" titleKey="label.email"/>
          </td>
        </tr>        
      </c:when>
      <c:otherwise>
        <html:hidden property="memberUserName"/>
        <html:hidden property="email"/>
      </c:otherwise>
    </c:choose>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.firstname"/>:</td>
      <td>
            <html:text property="firstName" styleId="firstName" size="30" maxlength="30" titleKey="label.firstname"/>                                   
            <div class="autocomplete" id="suggestionBoxFirst" style="display:none;border:1px solid black;background-color:white;"></div> 
            <span class="autocomplete" id="indicator" style="display: none"><html:img page="/images/flower.gif" altKey="label.admin.member.working"/></span>                
      </td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.lastname"/>:</td>
      <td>
                <html:text property="lastName" styleId="lastName"  size="30" maxlength="30" titleKey="label.lastname"/>
                <div class="autocomplete" id="suggestionBoxLast" style="display:none;border:1px solid black;background-color:white;"></div>   
               <span class="autocomplete" id="indicator" style="display: none"><html:img page="/images/flower.gif" altKey="label.admin.member.working"/></span>    
                
      </td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.maidenname"/>:</td>
      <td>
                <html:text property="maidenName" styleId="maidenName" size="30" maxlength="30" titleKey="label.maidenname"/>
                <div class="autocomplete" id="suggestionBoxMaiden" style="display:none;border:1px solid black;background-color:white;"></div>   
               <span class="autocomplete" id="indicator" style="display: none"><html:img page="/images/flower.gif" altKey="label.admin.member.working"/></span>    
      </td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.nickname"/>:</td>
      <td>
                <html:text property="nickName" styleId="nickName" size="30" maxlength="30" titleKey="label.nickname"/>
                <div class="autocomplete" id="suggestionBoxNick" style="display:none;border:1px solid black;background-color:white;"></div>       
               <span class="autocomplete" id="indicator" style="display: none"><html:img page="/images/flower.gif" altKey="label.admin.member.working"/></span>    
                
      </td>
    </tr>
	<%-- Dormitory Name --%>

		<c:if test="${applicationScope.hasDorm == 'Y'}">    
		    <tr class="portlet-section-body">
		      <td align="right" class="fieldlabel"><bean:message key="label.dormitory"/>:</td>
		      <td>
						<myalumni:dropdown type="luGeneric" group="DOM" fieldName="dormitoryId" firstOptionBlank="Y" titleKey="label.dormitory"><bean:write name="memberForm" property="dormitoryId"/></myalumni:dropdown>	
		      </td>
		    </tr>
		</c:if>
    
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.gender"/>:</td>
      <td>
        <%--     GENDER    --%>
        <html:select property="gender" titleKey="label.gender">
                <option value="" selected>-<bean:message key="label.gender"/>-</option>
                <html:options collection="luGender" property="value" labelProperty="label"/>
        </html:select>

      </td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.yearin"/>:</td>
      <td>
      <%--     YEARS IN --%>
      <myalumni:dropdown type="luSpecific" group="YEARIN" fieldName="yearIn" titleKey="label.yearin"><bean:write name="memberForm" property="yearIn"/></myalumni:dropdown>

      </td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.yearout"/>:</td>
      <td>
        <%--     YEARS OUT --%>
        <myalumni:dropdown type="luSpecific" group="YEAROUT" fieldName="yearOut" titleKey="label.yearout">><bean:write name="memberForm" property="yearOut"/></myalumni:dropdown>
      </td>
    </tr>

 
    <tr align="center" class="portlet-section-body">
      <td colspan="2">
                <html:submit>
                        <bean:message key="button.search"/>
                </html:submit>
                &nbsp;&nbsp;&nbsp;
      </td>
      </tr>
  </table>
