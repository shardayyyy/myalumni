<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="faq" value="/jsp/myalumni/faq.jsp"/>
<br>
<table width="98%" border="0" cellspacing="1" cellpadding="3" align="center"  class="tborder">
  <tr>
    <td height="30" class="bg0">Frequently Asked Questions </td>
  </tr>
  <tr class="portlet-section-body">
    <td><p><strong>Questions</strong>
                </p>
      <ul>
        <ol>
                <li><a href="<c:out value="${faq}"/>#1"> I can't log in ?</a> </li>
                <li><a href="<c:out value="${faq}"/>#2"> Please I forgot my username and password, can you send me a new one </a></li>
                <li><a href="<c:out value="${faq}"/>#3"> I am not getting the password reset instructions via email. </a></li>
                <li><a href="<c:out value="${faq}"/>#4"> How do I upload my avatar?</a></li>
                <li><a href="<c:out value="${faq}"/>#5"> How do I place my picture in the album?</a></li>
                <li><a href="<c:out value="${faq}"/>#6"> I have a slang that I want to share, how do I get it on the site?</a></li>
                <li><a href="<c:out value="${faq}"/>#7"> Please send me instructions on how to activate my account</a></li>
                <li><a href="<c:out value="${faq}"/>#8"> I'm already a user. I tried activating "my account"  and it told me i'm a user already but I don't even remember my user name or password anymore</a></li>
                <li><a href="<c:out value="${faq}"/>#9">I want to update my account.</a></li>
                <li><a href="<c:out value="${faq}"/>#10">I tried to activate my account, but have been unable to.</a></li>
                <li><a href="<c:out value="${faq}"/>#11">How do I change my password?</a></li>
                <li><a href="<c:out value="${faq}"/>#12">How do I send a Private Message</a></li>
              </ol>
      </ul>      <p>&nbsp;</p>
      <p><strong>ANSWERS</strong></p>
      <ol>
        <a name="1"></a>  <li>Please make sure you have a valid username and password.<br></li>
        <a name="2"></a><li><strong>Please send me my username and password </strong><BR>
          <ul>
            <li>Browse to the website <A href="<c:out value="${SERVER_URL}"/>"><c:out value="${SERVER_URL}" default="[ORGANIZATION SERVER URL NOT CONFIGURED]"/></A></li>
            <li>Click the "Alumni Portal" link in the menu bar.</li>
            <li>Put your name in the search criteria.</li>
            <li>When you find your name, click on your name.</li>
            <li>When the little window pops up, you should see something like "Account Not Activated.: If you are <i>&lt;First name&gt; &lt;Last name&gt;</i>, Please activate your account."</li>
            <li>This would take you to a page to put in your email address that you had submitted with on the old website</li>
            <li>If this email is present in the system, you would receive an email shortly, giving you further instructions on how to activate your account.</li>
          </ul>
        </li>
        <a name="3"></a><li>This could be due to a number of reasons
	        			<ul>
	        				<li>Make sure you are checking the right email that the system might have.</li>
	        				<li>Make sure <c:out value="${ORG_EMAIL}" default="[ORGANIZATION EMAIL NOT CONFIGURED]"/> is not in your blocked/spam list.</li>
	        				<li>Most users of AOL does not get the email because its been labelled as SPAM.</li>
	        				<li>Check your Junk folder and make sure you add the <c:out value="${ORG_EMAIL}" default="[ORGANIZATION EMAIL NOT CONFIGURED]"/> into your safe list.</li>
	        				<li></li>
	        			</ul>
        			</li>
        <a name="4"></a><li>To upload you avatar, make sure the avatar matches the height and width requirements(), login and there is a place to upload this when you click on the link to edit your profile.<br>
        			<ul>
        				<li>Height: <bean:message key="avatar.image.height"/> px.</li>
        				<li>Width: <bean:message key="avatar.image.width"/> px.</li>
        				<li>Size: <bean:message key="avatar.image.size"/> kb.</li>
        			</ul>
        			</li>
        <a name="5"></a><li>Email the pictures to <c:out value="${ORG_EMAIL}" default="[ORGANIZATION EMAIL NOT CONFIGURED]"/> and this would be included in the monthly update. Also remember to include the name, year of those inthe picture.<br></li>
        <a name="6"></a><li>Email the slangs to <c:out value="${ORG_EMAIL}" default="[ORGANIZATION EMAIL NOT CONFIGURED]"/> and this would be included in the monthly update. Also remember to include the year, slang, slang pronunciation and the meaning of the picture.<br></li>
        <a name="7"></a><li>Point your browser to <a href="<c:out value="${SERVER_URL}"/><%=request.getContextPath() %>/action/activate"><c:out value="${SERVER_URL}"/><%=request.getContextPath() %>/action/activate</a> and click on the "Activate My Account" button.<br></li>
        <a name="8"></a><li>Point your browser to <a href="<c:out value="${SERVER_URL}"/><%=request.getContextPath() %>/action/activate"><c:out value="${SERVER_URL}"/><%=request.getContextPath() %>/action/activate</a> and click on the "Get Lost Password" button.<br></li>
        <a name="9"></a><li>Login to the system and click on the "Edit My Profile" link.<br></li>
        <a name="10"></a><li>This can be due to the fact that the activation code was copied wrong, Please make sure you copy and paste the code and do not leave off the "==" at the end of the code.<br></li>
        <a name="11"></a><li>Look at Question #8.<br></li>
        <a name="12"><li>You would have to login to the system, search for the member you would like to contact and click on the PM icon..<br></li>
      </ol>
      <hr>      <p>&nbsp;</p>
</td>
  </tr>
</table>
