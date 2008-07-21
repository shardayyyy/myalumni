<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<c:url var="PRIVACY" value="/jsp/privacy.jsp" />

<jsp:useBean id="now" class="java.util.Date" />
  
  

<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
        <TBODY>
          <TR>
            <TD align=right bgcolor="#B4CC80">
              <div align="center"><br>
                <table width="70%"  border="0" align="center" cellpadding="1" cellspacing="1">
                  <tr>
                    <td align="center">WARNING! This system is for the use of authorized users only. Individuals using this computer system without authority, or in excess of authority, are
subject to having all of their activities on this system monitored and recorded by system personnel. In the course of monitoring individuals improperly using this system, or in the course of system maintenance, the activities of authorized users may also be monitored. Anyone using this system expressly consents to such monitoring and is advised that if such monitoring reveals possible evidence of criminal activity, system personnel may provide the evidence of such monitoring to law enforcement officials.</td>
                  </tr>
                </table>
                 <p><font size="1" face="Tahoma" color="#f5f5f5">Copyright &copy; 1997-<fmt:formatDate value="${now}" dateStyle="default" pattern="yyyy" />
                      MyAlumni<br>
                All rights reserved. <a href="<c:out value="${PRIVACY}"/>" title="Privacy Policy">Privacy Policy</a><br/>
                All comments can be sent to the webmaster at myalumni@naijatek.com<br/>
                powered by <a href="http://www.naijatek.com" target="_blank"><html:img page="/images/naijateklogo-100x10.gif" alt="Naijatek Solutions" width="100" height="10" border="0" align="absmiddle"/></a>  <br/>
                                  </font><br/>
               </p>
            </div></TD>
          </TR>
        </TBODY>
      </TABLE>
