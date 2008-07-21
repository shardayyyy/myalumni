<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="webmaster" value="/action/member/prepareEmailWebmaster?action=prepareEmailWebmaster"/>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">Privacy</td>
  </tr>
  <tr class="portlet-section-body">
    <td>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td valign="top">
            <table border="0" cellPadding="0" cellSpacing="0" width="100%">
              <tbody>
              <tr>
                <td align="left" class="head" height="30" vAlign="center" width="100%">
                  <div align="center">
                    <h2><c:out value="${ORGANIZATION_NAME}" default="SCHOOL NAME HERE"/> Privacy Policy </h2>
                  </div>
                </td>
              </tr>
              </tbody>
            </table>
            <table border="0" cellPadding="6" cellSpacing="0" width="100%" bgcolor="#808080">
              <tbody>
              <tr>
                <td vAlign="top" width="100%"><font size="2" face="Tahoma" color="#FFFFFF">
                  Your privacy is of the utmost importance, because some basic
                  personal information are asked during the alumni registration
                  procedure, we want you to be fully informed of how this information
                  is gathered and used.&nbsp;</font>
                  <p>
                </td>
              </tr>
              <tr>
                <td vAlign="top" width="100%" bgcolor="#FFFFFF">
                  <table border="0" cellPadding="4" cellSpacing="0">
                    <tbody>
                    <tr>
                      <td width="100%">
                        <table border="0" cellPadding="0" cellSpacing="0">
                          <tbody>
                          <tr>
                            <td align="left" vAlign="bottom" width="100%" bgcolor="#FFFFFF"><b>Registration</b></td>
                          </tr>
                          <tr vAlign="top">
                            <td align="left" vAlign="top" bgcolor="#FFFFFF">
                              <p><br>
                                1. <b>Information <c:out value="${ORGANIZATION_SHORT_NAME}" default="we"/> require</b><br>
                                Some personal information is needed in order to
                                be reached unless you want otherwise: </p>
                              <p><strong>Name</strong> - Your first
                                and last name help alumni's remember who you might
                                be. Also a nickname might also be appropriate
                                since we might have duplicates of common names. This information
                                is made available to alumni's who wishes to make
                                contact.&nbsp; </p>
                                      <p><strong>Email Address </strong>-
                                        Your email address allows alumni's to
                                        send emails to your existing valid email
                                        address. Message Board announcements,
                                        and messages posted to the <a href="<c:out value="${FORUM_URL}"/>" target="_blank">
                                        WWWBoard</a> will be sent to you via the
                                        group portal Homepage. The ability
                                        to turn off email correspondence from
                                        <c:out value="${ORGANIZATION_SHORT_NAME}" default="SCHOOL NAME"/>'s eGroup is also under your
                                        direct control, as is the ability to change
                                        your email address that is associated
                                        to you.&nbsp; Due
                                        to web robots scanning websites for email
                                        address for spam, a decision has been
                                        made to remove all email address from
                                        the web site. All alumni information are
                                        kept safely in our database. Only an
                                        email icon would be placed on the site
                                        to indicate an email address is available.</p>
                                      <p><strong>Phone Number</strong>
                                        - Your phone number is never placed on
                                        the website, if you would like more information
                                        about an alumni, kindly search for the
                                        alumni and click on the request info button,
                                        this is forwarded to both the webmaster
                                        and intended alumni<font color="#FF0000">*</font>,
                                        and all inquires are answered electronically,
                                        if an alumni can not be contact. 
                                      </p>
                                      <p><strong>Instant Messenger</strong>
                                        - Your instant messneger id is never placed
                                        on the website - only an icon would be
                                        displayed. If you would like more information
                                        about an alumni, kindly search for the
                                        alumni and click on the request button,
                                        this is forwarded to the webmaster and
                                        the alumni<font color="#FF0000">*</font> and all inquires are answered
                                        electronically.</p>
                            </td>
                          </tr>
                          <tr>
                            <td align="left" class="servicehead" vAlign="bottom" bgcolor="#FFFFFF"><b><strong><br>
                              Summary</strong></b></td>
                          </tr>
                          <tr vAlign="top">
                            <td align="left" vAlign="top" bgcolor="#FFFFFF" class="blackregulartext"><p><br>
                                        In this electronic era, when personal
                                        information can easily be obtained through
                                        various sources, we appreciate
                                        the sensitivity of any private information
                                        that you disclose to us. It is our intention
                                        to treat your information with the highest
                                        level of care. If you have further questions
                                        about the policies with regard to how
                                        your information is treated, please feel
                                        free to send your concerns to the <a href="<c:out value="${webmaster}"/>">webmaster</a>.</p>
                                      <p><em><font color="#FF0000">* </font>The
                                        Alumni would need to have a valid email
                                        address for the request to be passed along.</em></p>
                                      <p>&nbsp;</p></td>
                          </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </table>

    </td>
  </tr>
</table>
