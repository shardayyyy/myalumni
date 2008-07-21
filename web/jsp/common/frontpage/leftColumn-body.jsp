<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<c:url var="yahooGroup" value="/jsp/forum/index.jsp"/>
<c:url var="school_song" value="/jsp/aboutus/schoolSong.jsp"/>
<c:url var="registration" value="/action/member/prepareRegistration?action=prepareRegistration"/>
<c:url var="album" value="/jsp/album/index.jsp"/>
<c:url var="alumni_listings" value="/jsp/myalumni/index.jsp"/>
<c:url var="poems" value="/jsp/reminisce/poem.jsp"/>
<c:url var="school_listings" value="/jsp/schoolListing.jsp"/>
<c:url var="banner" value="/jsp/aboutus/schoolBanner.jsp"/>
<c:url var="otherSide" value="/jsp/reminisce/schoolJokes.jsp"/>
<c:url var="reminisce" value="/action/member/listReminisce?action=listReminisce"/>
<c:url var="faq" value="/jsp/myalumni/faq.jsp"/>


<table width="100%"  border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${yahooGroup}"/>">Forum </a></td>
        </tr>
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${registration}"/>">Alunmi Registration </a></td>
      </tr>
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${album}"/>">Album</a> </td>
      </tr>
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${alumni_listings}"/>">Alunmi Listing </a></td>
      </tr>
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${reminisce}"/>">Slang's</a> </td>
      </tr>
      <tr>
        <td class="fieldlabel">&#8226; <a href="<c:out value="${faq}"/>">FAQ</a></td>
      </tr>
      <tr>
        <td class="fieldlabel">&nbsp;</td>
      </tr>
      <tr>
        <td class="fieldlabel">&nbsp;</td>
      </tr>
      
    </table>
