 <%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
 <%@ page import="java.util.*" %>
 <%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>

<body>
<c:if test="${USER_CONTAINER.token.isAdmin == 'Y'}">
<%! public void writeTable(String val1, String val2, javax.servlet.jsp.JspWriter out) throws java.io.IOException
     {
     out.print("    <tr>  <td width=\"200\" bgcolor=\"#808080\"><p align=\"left\"><strong><font color=\"#00FF00\">");
     out.print(val1);
     out.print("</font></p></td></strong>");
     out.print("<td width=\"350\" bgcolor=\"#808080\"><p align=\"right\"><font color=\"#FFFF00\"> ");
     if (val2 == null)
         { out.println("null");
         } else  {out.println(val2);}
     out.println("</font></p></td></tr>");
     }
 %>

 <div align="center"><center>
 <h1> Session & State Information </h1>
 <hr>

 <h1> Request Information </h1>

 <hr>
 <BR>
 <font size="4">

 <table border="1" cellpadding="4" cellspacing="0"  bordercolor="#C0C0C0">
     <tr>
         <td bgcolor="#008080"><p align="center"><font
         color="#00FF00">Name</font></p>
         </td>
         <td bgcolor="#008080"><p align="center"><font
         color="#00FF00">Value</font></p>
         </td>
     </tr>
 <% writeTable("JSP Request Method:",request.getMethod(),out); %>
 <% writeTable("Request URI:", request.getRequestURI(),out); %>
 <% writeTable("Request Protocol:", request.getProtocol(),out); %>
 <% writeTable("Servlet path:", request.getServletPath(),out); %>
 <% writeTable("Path info:",request.getPathInfo(),out);
    writeTable("Path translated:", request.getPathTranslated(),out);
    writeTable("Query string:",request.getQueryString(),out);
    writeTable("Content length:",Integer.toString(request.getContentLength()),out);
    writeTable("Content type:" ,request.getContentType(),out);
    writeTable("Server name:" ,request.getServerName(),out);
    writeTable("Server port:" ,Integer.toString(request.getServerPort()).toString(),out);
    writeTable("Remote user:", request.getRemoteUser(),out);
    writeTable("Remote address:",request.getRemoteAddr(),out);
    writeTable("Remote host:",request.getRemoteHost(),out);
    writeTable("Authorization scheme:", request.getAuthType(),out);
    writeTable("The browser you are using is",request.getHeader("User-Agent"),out);
 %>
 </table>
 </center>
 </div>


 <hr>
 <%
   //  HttpSession session = req.getSession(true);

     // Increment the hit count for this page.  The value is saved
     // in this client's session under the name "snoop.count".
     Integer count = (Integer)session.getAttribute("snoop.count");
     if (count == null)
       count = new Integer(1);
     else
       count = new Integer(count.intValue() + 1);
     session.setAttribute("snoop.count", count);

     out.println("<HTML><HEAD><TITLE>SessionSnoop</TITLE></HEAD>");
     out.println("<BODY><H1>Session Snoop</H1>");

     // Display the hit count for this page
     out.println("You've visited this page " + count +
       ((count.intValue() == 1) ? " time." : " times under this SessionID."));

     out.println("<P><center>");

     out.println("<H3>Here is your saved session data:</H3>");   %>
  <table border="1" cellpadding="4" cellspacing="0" bordercolor="#C0C0C0">
     <tr>
         <td bgcolor="#008080"><p align="center"><font
         color="#00FF00">Name</font></p>
         </td>
         <td bgcolor="#008080"><p align="center"><font
         color="#00FF00">Value</font></p>
         </td>
     </tr>
<%   Enumeration enume = session.getAttributeNames();
             while(enume.hasMoreElements()) {
             String nextAttr = (String)enume.nextElement();
            writeTable(nextAttr + ": " , (session.getAttribute(nextAttr).toString()),out);
        }
%>
 </table>

 <%
     out.println("<H3>Here are some vital stats on your session:</H3>");
     out.println("Session id: " + session.getId() + "<BR>");
     out.println("New session: " + session.isNew() + "<BR>");
     out.println("Creation time: " + session.getCreationTime());
     out.println("<I>(" + new Date(session.getCreationTime()) + ")</I><BR>");
     out.println("Last access time: " + session.getLastAccessedTime());
     out.println("<I>(" + new Date(session.getLastAccessedTime()) +
                 ")</I><BR>");

     out.println("Requested session ID from cookie: " +
                 request.isRequestedSessionIdFromCookie() + "<BR>");
     out.println("Requested session ID from URL: " +
                 request.isRequestedSessionIdFromURL() + "<BR>");
     out.println("Requested session ID valid: " +
                  request.isRequestedSessionIdValid() + "<BR>");
     out.println("<H3>Test URL Rewriting</H3>");
     out.println("Click <A HREF=\"" +
                 response.encodeURL(request.getRequestURI()) + "\">here</A>");
     out.println("to test that session tracking works via URL");
     out.println("rewriting even when cookies aren't supported.");

 %>
 </font>

</c:if>
</body>
</html>
