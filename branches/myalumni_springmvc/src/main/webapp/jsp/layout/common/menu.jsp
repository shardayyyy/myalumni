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
<c:url var="myalumni" value="/action/member/viewMyDesktop?action=displayMyDesktop" />


<div id="topbar">
    <c:choose>
        <c:when test="${menuTab == 'home'}">
                <a href="<c:out value="${home}"/>" title="<bean:message key="tab.main.home"/>" class="active"> <span><bean:message key="tab.main.home"/> </span></a> 
        </c:when>
        <c:otherwise>
                <a href="<c:out value="${home}"/>" title="<bean:message key="tab.main.home"/>"> <span><bean:message key="tab.main.home"/> </span></a> 
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'myalumni'}">
				<a href="<c:out value="${portal}"/>" title="<bean:message key="tab.main.portal"/>" class="active"> <span><bean:message key="tab.main.portal"/> </span></a> 
        </c:when>
        <c:otherwise>                
                <a href="<c:out value="${portal}"/>" title="<bean:message key="tab.main.portal"/>"> <span><bean:message key="tab.main.portal"/> </span></a> 
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${menuTab == 'reminisce'}">
				<a href="<c:out value="${reminisce}"/>" title="<bean:message key="tab.main.reminisce"/>" class="active"> <span><bean:message key="tab.main.reminisce"/> </span></a> 
        </c:when>
        <c:otherwise>
                <a href="<c:out value="${reminisce}"/>" title="<bean:message key="tab.main.reminisce"/>"> <span><bean:message key="tab.main.reminisce"/> </span></a> 
        </c:otherwise>
    </c:choose>






    <c:choose>
        <c:when test="${menuTab == 'album'}">
                <a href="<c:out value="${album}"/>" title="<bean:message key="tab.main.album"/>" class="active"> <span><bean:message key="tab.main.album"/> </span></a> 
        </c:when>
        <c:otherwise>
                <a href="<c:out value="${album}"/>" title="<bean:message key="tab.main.album"/>"> <span><bean:message key="tab.main.album"/> </span></a> 
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${menuTab == 'forum'}">
                    <a href="<c:out value="${forum}"/>" title="<bean:message key="tab.main.forum"/>" class="active"> <span><bean:message key="tab.main.forum"/> </span></a> 
        </c:when>
        <c:otherwise>
                    <a href="<c:out value="${forum}"/>" title="<bean:message key="tab.main.forum"/>"> <span><bean:message key="tab.main.forum"/> </span></a> 
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'aboutus'}">
                    <a href="<c:out value="${aboutus}"/>" title="<bean:message key="tab.main.aboutus"/>" class="active"> <span><bean:message key="tab.main.aboutus"/> </span></a> 
        </c:when>
        <c:otherwise>
                    <a href="<c:out value="${aboutus}"/>" title="<bean:message key="tab.main.aboutus"/>"> <span><bean:message key="tab.main.aboutus"/> </span></a> 
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'contact'}">
                    <a href="<c:out value="${contactus}"/>" title="<bean:message key="tab.main.contactus"/>" class="active"> <span><bean:message key="tab.main.contactus"/> </span></a> 
        </c:when>
        <c:otherwise>
                    <a href="<c:out value="${contactus}"/>" title="<bean:message key="tab.main.contactus"/>"> <span><bean:message key="tab.main.contactus"/> </span></a> 
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${menuTab == 'myalumni'}">
                    <c:choose>
                        <c:when test="${ isOnline == 'Y'}">
                                <a href="<c:out value="${myalumni}"/>" title="<bean:message key="tab.main.myalumni"/>" class="active"> <span><bean:message key="tab.main.myalumni"/> </span></a> 
                        </c:when>
                        <c:otherwise>
                                <a href="<c:out value="${login}"/>" title="<bean:message key="tab.main.myalumni"/>" class="active"> <span><bean:message key="tab.main.myalumni"/> </span></a> 
                        </c:otherwise>
                    </c:choose>
        </c:when>
        <c:otherwise>
                    <c:choose>
                        <c:when test="${ isOnline == 'Y'}">
								<a href="<c:out value="${myalumni}"/>" title="<bean:message key="tab.main.myalumni"/>" class="active"> <span><bean:message key="tab.main.myalumni"/> </span></a> 
                        </c:when>
                        <c:otherwise>
                                <a href="<c:out value="${login}"/>" title="<bean:message key="tab.main.myalumni"/>" class="active"> <span><bean:message key="tab.main.myalumni"/> </span></a> 
                        </c:otherwise> 
                    </c:choose>
        </c:otherwise>
    </c:choose>

</div>


