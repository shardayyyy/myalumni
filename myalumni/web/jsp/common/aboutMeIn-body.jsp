<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>

<c:set var="_male">
	<%= BaseConstants.GENDER_MALE %>
</c:set>

<c:set var="_female">
	<%= BaseConstants.GENDER_FEMALE %>
</c:set>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
            <tr>
              <td height="30" colspan="2" class="bg0"><bean:message key="table.title.aboutus"/> </td>
            </tr>
            <tr class="portlet-section-body">
              <td width="22%" class="fieldlabel"><bean:message key="label.title"/>:<font color="#cc0000">*</font></td>
              <td width="78%">
              <%--     TITLE --%>
                <myalumni:dropdown type="luGeneric" group="TIT" fieldName="titleId" titleKey="label.title"><bean:write name="memberForm" property="titleId"/></myalumni:dropdown>

              </td>
            </tr>
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.firstname"/>:<font color="#cc0000">*</font></td>
              <td><html:text property="firstName" size="30" maxlength="30" titleKey="label.firstname"/></td>
            </tr>
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.lastname"/>:<font color="#cc0000">*</font></td>
              <td><html:text property="lastName" size="30" maxlength="30" titleKey="label.lastname"/></td>
            </tr>
            
           <c:choose>
                <c:when test="${ memberForm.gender == _female}">            
		            <tr class="portlet-section-body">
		              <td class="fieldlabel"><bean:message key="label.maidenname"/>:</td>
		              <td><html:text property="maidenName" size="30" maxlength="30" titleKey="label.maidenname"/></td>
		            </tr>
                </c:when>
           </c:choose>
            
            
            
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.nickname"/>:</td>
              <td><html:text property="nickName" size="30" maxlength="30" titleKey="label.nickname"/></td>
            </tr>
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.gender"/>:<font color="#cc0000">*</font></td>
              <td>
                <%--     GENDER    --%>
                <html:select property="gender" titleKey="label.gender">
                        <option value="" selected>--<bean:message key="label.gender"/>--</option>
                        <html:options collection="luGender" property="value" labelProperty="label"/>
                </html:select>
              </td>
            </tr>
        <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.birthday"/>:<font color="#cc0000">*</font></td>
              <td>
		              <html:text property="dob" size="11" maxlength="11" titleKey="label.birthday" readonly="true" styleId="dob"/>
		              <html:img page="/images/icon/calendar.gif" titleKey="label.birthday" align="absmiddle" styleId="trigger1"/> 
		              <html:button property="buttonAction" onclick="document.memberForm.dob.value=''"><bean:message key="label.cleardate"/></html:button>

              </td>
            </tr>
            <tr class="portlet-section-body">
              <td class="fieldlabel" valign="top"><bean:message key="label.primaryemail"/>:<font color="#cc0000">*</font></td>
              <td>
				  <c:choose>
						<c:when test="${actionTask == 'update'}">
							<c:out value="${memberForm.email}"/>
							<html:hidden property="email"/><br>
							<html:checkbox property="hideEmail" value="Y" titleKey="label.makethisprivate"/><font class="graysmall"> <bean:message key="label.makethisprivate"/></font>
						</c:when>
						<c:otherwise>
		              	    <html:text property="email" size="40" maxlength="60" titleKey="label.email"/>
		                    <html:img page="/images/email.gif" titleKey="label.primaryemail" width="15" height="15" align="absmiddle"/><br>
		                    <html:checkbox property="hideEmail" value="Y" titleKey="label.makethisprivate"/><font class="graysmall"> <bean:message key="label.makethisprivate"/></font>
						</c:otherwise>
				  </c:choose>
               </td>
            </tr>
            
            <c:if test="${actionTask == 'add'}">
	            <tr class="portlet-section-body">
	              <td nowrap class="fieldlabel"><bean:message key="label.confirmemail"/>:<font color="#cc0000">*</font></td>
	              <td>
	                <html:text property="emailConfirm" size="40" maxlength="60" titleKey="label.emailconfirm"/>
	                <html:img page="/images/email.gif" alt="Email" width="15" height="15" align="absmiddle"/></td>
	            </tr>
            </c:if>
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.websiteurl"/>:</td>
              <td><html:text property="website" size="50" maxlength="70" titleKey="label.websiteurl"/>
                  <html:img page="/images/www.gif" alt="Website" width="15" height="15" align="absmiddle"/></td>
            </tr>
            <tr class="portlet-section-body">
              <td nowrap class="fieldlabel" valign="top"><bean:message key="label.phonenumber"/>:</td>
              <td><html:text property="phone" size="30" maxlength="30" titleKey="label.phonenumber"/><br>
                  <html:checkbox property="hidePhone" value="Y" titleKey="label.makethisprivate"/> <font class="graysmall"><bean:message key="label.makethisprivate"/></font></td>
            </tr>
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.yearsatschool"/>:<font color="#cc0000">*</font></td>
              <td>
                <%--     YEARS IN    --%>
      				<myalumni:dropdown type="luSpecific" group="YEARIN" fieldName="yearIn" titleKey="label.yearin"><bean:write name="memberForm" property="yearIn"/></myalumni:dropdown>

      TO
                <%--     YEARS OUT    --%>
      				<myalumni:dropdown type="luSpecific" group="YEAROUT" fieldName="yearOut" titleKey="label.yearout"><bean:write name="memberForm" property="yearOut"/></myalumni:dropdown>

                </td>
            </tr>
			<%-- Dormitory Name --%>
		<c:if test="${applicationScope.hasDorm == 'Y'}">            
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.dormitory"/>:<font color="#cc0000">*</font> </td>
              <td>    
				<myalumni:dropdown type="luGeneric" group="DOM" fieldName="dormitoryId" titleKey="label.dormitory"><bean:write name="memberForm" property="dormitoryId"/></myalumni:dropdown>

              </td>
            </tr>
		</c:if>            
            <tr class="portlet-section-body">
              <td class="fieldlabel"><bean:message key="label.career"/>:<font color="#cc0000">*</font></td>
              <td>

              <%--     CAREER --%>
                <myalumni:dropdown type="luGeneric" group="CAR" fieldName="careerId" titleKey="label.career"><bean:write name="memberForm" property="careerId"/></myalumni:dropdown>

              </td>
            </tr>
                <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.coollink1"/>: </td>
             <td><html:text property="favUrl1" size="50" maxlength="70" titleKey="label.coollink1"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.coollink2"/>: </td>
             <td><html:text property="favUrl2" size="50" maxlength="70" titleKey="label.coollink2"/></td>
           </tr>
          </table>
