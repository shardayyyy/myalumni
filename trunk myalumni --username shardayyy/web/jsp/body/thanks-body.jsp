<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">Thank You </td>
  </tr>
  <tr class="portlet-section-body">
    <td><div align="center">
      <br><br>

	  <table width="80%"  border="0" cellspacing="1" cellpadding="1" >
	  <tr>
    	<td class="quote"><br><br><center><c:out value="${MESSAGE}"/></center><br><br></td>
	  </tr>
	</table>

    </div>
      <p align="center">Feel free to check out our Discussuion Forum</p>
      <p align="center"><br>
</p>
      <form name="form2" method="post" action="index.jsp">
        <div align="center">
          <input type="submit" class="button" name="Submit2" value="RETURN    HOME" onClick="disabled=true; submit();">
        </div>
      </form>
      <p align="center">&nbsp;</p>
      <table width="600" border="0" align="center">
        <tr>
          <td width="296"><a href="forum/index.jsp"><html:img page="/images/diss_forum.gif" width="284" height="82" border="0"/></a></td>
          <td width="294"><div align="center"><a href="forum/index.jsp"><html:img page="/images/yahoogroup.gif" width="146" height="73" border="0"/></a></div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
  </tr>
</table>
