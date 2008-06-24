<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>


<c:choose>
    <c:when test="${MODULE == 'ADMIN'}">
            <c:url var="inbox" value="/action/admin/viewFolderMail?action=displayMailFolder"/>
            <c:url var="draft" value="/action/admin/viewFolderMail?action=displayMailFolder"/>
            <c:url var="sent" value="/action/admin/viewFolderMail?action=displayMailFolder"/>
            <c:url var="trash" value="/action/admin/viewFolderMail?action=displayMailFolder"/>
            <c:url var="storage" value="/action/admin/viewFolderMail?action=displayMailFolder"/>        
    </c:when>
    <c:otherwise>
            <c:url var="inbox" value="/action/member/viewFolderMail?action=displayMailFolder"/>
            <c:url var="draft" value="/action/member/viewFolderMail?action=displayMailFolder"/>
            <c:url var="sent" value="/action/member/viewFolderMail?action=displayMailFolder"/>
            <c:url var="trash" value="/action/member/viewFolderMail?action=displayMailFolder"/>
            <c:url var="storage" value="/action/member/viewFolderMail?action=displayMailFolder"/>    
    </c:otherwise>
</c:choose>
    

<table border="0" class="tborder" width="150" cellspacing="1" cellpadding="3" align="center">
      <tr class="portlet-section-header">
        <td colspan="3" align="left" class="bg0">Unread / Total Mails</td>
      </tr>


<c:set var="unread_count" scope="page" value="0"/>
<c:set var="mail_count" scope="page" value="0"/>
<c:set var="mail_count_total" scope="page" value="0"/>
<c:set var="unreadmail_count_total" scope="page" value="0"/>


<logic:iterate id="folder" name="MESSAGE_CENTER"  indexId="pIdx" property="messageFolders">

      <tr class="portlet-section-body" onmouseover="ChangeColor(this, true);" onmouseout="ChangeColor(this, false);">
        <td width="100%" nowrap="nowrap">
          <c:choose>
            <c:when test="${folder.folderName == 'Inbox'}">
              <html:img page="/images/icon/folder_inbox.gif" alt="Inbox" title="Inbox" align="middle" border="0" height="19" width="19"/> <a href="<c:out value="${inbox}"/>&type=inbox" title="Inbox">Inbox</a>
            </c:when>
            <c:when test="${folder.folderName == 'Sent'}">
              <html:img page="/images/icon/folder_sent.gif" alt="Sent" title="Sent" align="middle" border="0" height="19" width="19"/> <a href="<c:out value="${sent}"/>&type=sent" title="Sent">Sent</a>
            </c:when>
            <c:when test="${folder.folderName == 'Trash'}">
              <html:img page="/images/icon/folder_trash.gif" alt="Trash" title="Trash" align="middle" border="0" height="19" width="19"/> <a href="<c:out value="${trash}"/>&type=trash" title="Trash">Trash</a>
            </c:when>
            <c:when test="${folder.folderName == 'Storage'}">
              <html:img page="/images/icon/folder_default.gif" alt="Storage" title="Storage" align="middle" border="0" height="19" width="19"/> <a href="<c:out value="${storage}"/>&type=storage" title="Storage">Storage</a>
            </c:when>
          </c:choose>
        </td>
        <td align="right" nowrap>
          <c:choose>
            <c:when test="${folder.unreadMessageCount > 0}">
              <b><c:out value="${folder.unreadMessageCount}"/></b>
              <c:set var="unread_count" scope="page" value="${folder.unreadMessageCount}"/>
            </c:when>
            <c:otherwise>
              <c:out value="${folder.unreadMessageCount}"/>
              <c:set var="unread_count" scope="page" value="${folder.unreadMessageCount}"/>
            </c:otherwise>
          </c:choose>
          /
          <c:out value="${folder.messageCount}"/>
          <c:set var="mail_count" scope="page" value="${folder.messageCount}"/>
          &nbsp;
        </td>
      </tr>
	  <c:set var="unreadmail_count_total" scope="page" value="${unreadmail_count_total+unread_count}"/>
      <c:set var="mail_count_total" scope="page" value="${mail_count_total+mail_count}"/>
</logic:iterate>

      <tr class="bg0" >
        <td width="100%" align="right"> Total: </td>
        <td align="right" nowrap>
          <c:choose>
          <c:when test="${unreadmail_count_total > 0}">
              <b><c:out value="${unreadmail_count_total}"/></b>
            </c:when>
            <c:otherwise>
              <c:out value="${unreadmail_count_total}"/>
            </c:otherwise>
          </c:choose>
          /
         <c:out value="${mail_count_total}"/>
          &nbsp; </td>
      </tr>
    </table>
