<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<%-- ------------------------------------------------------------   HOME --%>
<c:url var="home" value="/jsp/index.jsp" />
<c:url var="home_schoolListing" value="/jsp/schoolListing.jsp"/>
<c:url var="home_emailWebmaster" value="/action/member/prepareEmailWebmaster?action=prepareEmailWebmaster"/>

<%-- ------------------------------------------------------------   ALUMNI PORTAL --%>
<c:url var="portal" value="/jsp/myalumni/index.jsp" />


<%-- ------------------------------------------------------------   REMINISCE --%>
<c:url var="reminisce" value="/action/member/listReminisce?action=listReminisce" />
<c:url var="reminisce_schoollife" value="/jsp/reminisce/schoolLife.jsp" />
<c:url var="reminisce_poem" value="/jsp/reminisce/poem.jsp" />
<c:url var="reminisce_playlet" value="/jsp/reminisce/playlet.jsp" />
<c:url var="reminisce_comments" value="/jsp/reminisce/comments.jsp" />
<c:url var="reminisce_schooljokes" value="/jsp/reminisce/schoolJokes.jsp" />


<%-- ------------------------------------------------------------   ALBUM --%>
<c:url var="album" value="/jsp/album/index.jsp" />
<c:url var="album_schoolpix" value="/jsp/album/schoolPix.jsp" />
<c:url var="album_usnow" value="/jsp/album/usNow.jsp" />
<c:url var="album_usthen" value="/jsp/album/usThen.jsp" />

<%-- ------------------------------------------------------------   NEWS --%>
<c:url var="news" value="/jsp/news/index.jsp" />
<c:url var="news_news" value="/jsp/news/news.jsp" />
<c:url var="news_headlines" value="/jsp/news/headlines.jsp" />



<%-- ------------------------------------------------------------   FORUM --%>
<c:url var="forum" value="/jsp/forum/index.jsp" />


<%-- ------------------------------------------------------------   ABOUT US --%>
<c:url var="aboutus" value="/jsp/aboutus/index.jsp" />
<c:url var="aboutus_aboutus" value="/jsp/aboutus/index.jsp" />
<c:url var="aboutus_banner" value="/jsp/aboutus/schoolBanner.jsp" />
<c:url var="aboutus_schoolsong" value="/jsp/aboutus/schoolSong.jsp" />
<c:url var="aboutus_coollinks" value="/jsp/aboutus/coolLinks.jsp" />


<%-- ------------------------------------------------------------   CONTACT US --%>
<c:url var="contactus" value="/jsp/contact/index.jsp" />


<%-- ------------------------------------------------------------   MISC --%>
<c:url var="login" value="/jsp/myalumni/login.jsp" />
<c:url var="myfegocoid" value="/action/member/viewMyFeGoCoId?action=displayMyDesktop" />



<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#006699">
  <tr bgcolor="#4E5CA4">

    <c:choose>
        <c:when test="${menuTab == 'home'}">
                <td class="menuText" height="25" align="center" nowrap> <a href="<c:out value="${home}"/>" style="color: #FFFFFF; text-decoration: none;" title="Home"> <bean:message key="tab.main.home"/> </a> </td>
        </c:when>
        <c:otherwise>
                <td class="menuTextOn" height="25" align="center" nowrap> <a href="<c:out value="${home}"/>" style="color: #3C4780; text-decoration: none;" title="Home"> <bean:message key="tab.main.home"/> </a> </td>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'myfegocoid'}">
                <td class="menuText" align="center" nowrap> <a href="<c:out value="${portal}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.portal"/>"> <bean:message key="tab.main.portal"/> </a> </td>
        </c:when>
        <c:otherwise>
                <td class="menuTextOn" align="center" nowrap> <a href="<c:out value="${portal}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.portal"/>"> <bean:message key="tab.main.portal"/> </a> </td>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${menuTab == 'reminisce'}">
                <td class="menuText" align="center" nowrap> <a href="<c:out value="${reminisce}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.reminisce"/>"> <bean:message key="tab.main.reminisce"/></a></td>
        </c:when>
        <c:otherwise>
                <td class="menuTextOn" align="center" nowrap> <a href="<c:out value="${reminisce}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.reminisce"/>"> <bean:message key="tab.main.reminisce"/></a></td>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${menuTab == 'album'}">
                <td class="menuText" align="center" nowrap> <a href="<c:out value="${album}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.album"/>"> <bean:message key="tab.main.album"/></a> </td>
        </c:when>
        <c:otherwise>
                <td class="menuTextOn" align="center" nowrap> <a href="<c:out value="${album}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.album"/>"> <bean:message key="tab.main.album"/></a> </td>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${menuTab == 'forum'}">
                    <td class="menuText" align="center" nowrap> <a href="<c:out value="${forum}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.forum"/>"> <bean:message key="tab.main.forum"/></a> </td>
        </c:when>
        <c:otherwise>
                    <td class="menuTextOn" align="center" nowrap> <a href="<c:out value="${forum}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.forum"/>"> <bean:message key="tab.main.forum"/></a> </td>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'aboutus'}">
                    <td class="menuText" align="center" nowrap> <a href="<c:out value="${aboutus}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.aboutus"/>"> <bean:message key="tab.main.aboutus"/> </a> </td>
        </c:when>
        <c:otherwise>
                    <td class="menuTextOn" align="center" nowrap> <a href="<c:out value="${aboutus}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.aboutus"/>"> <bean:message key="tab.main.aboutus"/> </a> </td>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'contact'}">
                    <td class="menuText" align="center" nowrap><a href="<c:out value="${contactus}"/>"  style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.contactus"/>"><bean:message key="tab.main.contactus"/> </a></td>
        </c:when>
        <c:otherwise>
                    <td class="menuTextOn" align="center" nowrap><a href="<c:out value="${contactus}"/>"  style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.contactus"/>"><bean:message key="tab.main.contactus"/></a></td>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'myfegocoid'}">
                    <td class="menuText" align="center" nowrap>
                    <c:choose>
                        <c:when test="${ isOnline == 'Y'}">
                                <a href="<c:out value="${myfegocoid}"/>" style="color: #FFFF00; text-decoration: none;" title="<bean:message key="tab.main.myalumni"/>"> <bean:message key="tab.main.myalumni"/></a>
                        </c:when>
                        <c:otherwise>
                                <a href="<c:out value="${login}"/>" style="color: #FFFFFF; text-decoration: none;" title="<bean:message key="tab.main.myalumni"/>"> <bean:message key="tab.main.myalumni"/></a>
                        </c:otherwise>
                    </c:choose>
                    </td>
        </c:when>
        <c:otherwise>
                    <td class="menuTextOn" align="center" nowrap>

                    <c:choose>
                        <c:when test="${ isOnline == 'Y'}">
                                <a href="<c:out value="${myfegocoid}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.myalumni"/>"> <bean:message key="tab.main.myalumni"/></a>
                        </c:when>
                        <c:otherwise>
                                <a href="<c:out value="${login}"/>" style="color: #3C4780; text-decoration: none;" title="<bean:message key="tab.main.myalumni"/>"> <bean:message key="tab.main.myalumni"/></a>
                        </c:otherwise> 
                    </c:choose>


                    </td>

        </c:otherwise>
    </c:choose>

  </tr>
</table>


