<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>


<c:set var="BAR_BORDER" value="5"/>
<c:set var="BAR_WIDTH" value="300"/>


<table width="100%" align="center">
  <tr>
    <td align="left">
      <table width="<c:out value="${BAR_BORDER + BAR_WIDTH}"/>" border="0" cellspacing="5.0" cellpadding="1" class="bg0">
        <tr bgcolor="#FFFFFF">
          <td align="left" height="17" nowrap>
            <myalumni:buildImageTag imageType="image"><c:out value="${MESSAGE_CENTER.memberQuota}"/></myalumni:buildImageTag>
          </td>
        </tr>
        <tr><td height="12" align="center" class="messageTextBold"><c:out value="${MESSAGE_CENTER.usedQuotaPercent}"/>% of <c:out value="${MESSAGE_CENTER.maxQuota}"/> Private Messages</td></tr>
      </table>
	<c:if test="${ MESSAGE_CENTER.usedQuotaPercent > 90}">
	    <font class="redhighlight">Please take note that you are reaching your quota, and you would not be able to receive new messages.</font><br>
	</c:if>      
    </td>
  </tr>
</table>
