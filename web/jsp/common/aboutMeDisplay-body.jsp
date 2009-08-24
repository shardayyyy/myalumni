<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

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
             <td width="25%" class="fieldlabel"><bean:message key="label.title"/>:</td>
             <td width="75%"><bean:write property="titleLabel" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.firstname"/>:</td>
             <td><bean:write property="firstName" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.lastname"/>:</td>
             <td><bean:write property="lastName" name="memberForm"/></td>
           </tr>

           <c:choose>
                <c:when test="${ memberForm.gender == _female}">
                   <tr class="portlet-section-body">
                     <td class="fieldlabel"><bean:message key="label.maidenname"/>:</td>
                     <td><bean:write property="maidenName" name="memberForm"/></td>
                   </tr>
                </c:when>
           </c:choose>


           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.nickname"/>:</td>
             <td><bean:write property="nickName" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.gender"/>:</td>
             <td>
                <c:choose>
                        <c:when test="${memberForm.gender == _male}">
                                <bean:message key="label.male" /> <html:img page="/images/male.gif" width="11" height="11" titleKey="label.male"/>
                        </c:when>
                        <c:when test="${memberForm.gender == _female}">
                                <bean:message key="label.female" /> <html:img page="/images/female.gif" width="11" height="11" titleKey="label.female"/>
                        </c:when>
                        <c:otherwise>
                        	-UNKNOWN-
                        </c:otherwise>
                </c:choose>
              </td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.birthday"/>:</td>
             <td>
	             <fmt:formatDate value="${profile.dob}" type="date" dateStyle="long"/>
			</td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.primaryemail"/>:</td>
             <td><bean:write property="email" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.websiteurl"/>:</td>
             <td><bean:write property="website" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.phonenumber"/>:</td>
             <td><bean:write property="phone" name="memberForm"/></td>
           </tr>
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.yearsatschool"/>:</td>
             <td><bean:write property="yearIn" name="memberForm"/> - <bean:write property="yearOut" name="memberForm"/></td>
           </tr>
		<%-- Dormitory Name --%>
		<c:if test="${applicationScope.hasDorm == 'Y'}">
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.dormitory"/>:</td>
             <td><bean:write property="dormitoryLabel" name="memberForm"/></td>
           </tr>
		</c:if>           
           <tr class="portlet-section-body">
             <td class="fieldlabel"><bean:message key="label.career"/>:</td>
             <td>
                        <bean:write property="careerLabel" name="memberForm"/>
             </td>
           </tr>
           
          
         </table>
