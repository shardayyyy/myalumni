<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>

<c:url var="forum" value="/jsp/forum/index.jsp"/>
<c:url var="registration" value="/action/member/prepareRegistration?action=prepareRegistration"/>
<c:url var="album" value="/jsp/album/index.jsp"/>
<c:url var="alumni_listings" value="/jsp/myalumni/index.jsp"/>
<c:url var="reminisce" value="/action/member/listReminisce?action=listReminisce"/>
<c:url var="faq" value="/jsp/myalumni/faq.jsp"/>


<table width="100%"  border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${forum}"/>">Forum </a></td>
        </tr>
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${registration}"/>">Registration </a></td>
      </tr>
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${album}"/>">Album</a> </td>
      </tr>
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${alumni_listings}"/>">Search </a></td>
      </tr>
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${reminisce}"/>">Slang's</a> </td>
      </tr>
      <tr>
        <td class="fieldlabel"><span>&raquo;</span> <a href="<c:out value="${faq}"/>">FAQ</a></td>
      </tr>

      
      <myalumni:displayFrontPageLinks/> 
      
		<logic:notEmpty name="allFrontPageLinks">
			<logic:iterate id="row" name="allFrontPageLinks" indexId="pIdx">
			   	<tr>
        			<td class="fieldlabel"><span>&raquo;</span>  		
						<a href="<c:out value="${row.linkurl}"/>" target="_blank"><c:out value="${row.label}"/></a>
						<c:if test="${row.important == 'Y'}">
							<html:image page="/images/dot.gif"/>
						</c:if>
					</td>
			   </tr>
			</logic:iterate>
		</logic:notEmpty>
	  
	  <tr>
        <td class="fieldlabel">&nbsp;</td>
      </tr>
      <tr>
        <td class="fieldlabel">&nbsp;</td>
      </tr>		
			
    </table>
