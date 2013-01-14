<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<tr>
    <%-- MyDesktop Module --%>
          <c:choose>
                <c:when test="${adminTab == 'desktop'}">
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                        <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
                </c:when>
                <c:otherwise>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                        <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
                </c:otherwise>
          </c:choose>
          

    <%-- Members Module --%>
          <c:choose>
                <c:when test="${adminTab == 'members'}">
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                        <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
                </c:when>
                <c:otherwise>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                        <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
                </c:otherwise>
          </c:choose>


    <%-- Security Module --%>
          <c:choose>
                <c:when test="${adminTab == 'security'}">
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                        <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
                </c:when>
                <c:otherwise>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                        <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
                </c:otherwise>
          </c:choose>



    <%-- General Module in Edition --%>
          <c:choose>
                <c:when test="${adminTab == 'general'}">
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                        <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
                </c:when>
                <c:otherwise>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                        <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
                </c:otherwise>
          </c:choose>



    <%-- System Module in Edition --%>
    <c:choose>
        <c:when test="${adminTab == 'system'}">
                <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
        </c:when>
        <c:otherwise>
                <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
        </c:otherwise>
    </c:choose>



    <%-- Admin Module --%>
          <c:choose>
                <c:when test="${adminTab == 'admin'}">
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabLeft.gif" width="11" height="27" alt="Left tab corner"/></td>
                        <td rowspan="2" bgcolor="#6E8F48"><html:img page="/images/spacer.gif" width="2" height="2" alt=""/></td>
                        <td rowspan="5"><html:img page="/images/tabs/ActiveTabRight.gif" alt="Right tab corner" width="11" height="27"/></td>
                </c:when>
                <c:otherwise>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabLeft.gif" width="11" height="23" alt="Left tab corner"/></td>
                        <td bgcolor="#B7C0A4"><html:img page="/images/spacer.gif" width="1" height="1" alt=""/></td>
                        <td rowspan="3"><html:img page="/images/tabs/InactiveTabRight.gif" width="11" height="23" alt="Right tab corner"/></td>
                </c:otherwise>
          </c:choose>

</tr>