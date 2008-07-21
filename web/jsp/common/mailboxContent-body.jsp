<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<%@ page import="net.naijatek.myalumni.util.BaseConstants"%>


<c:set var="_guestId">
<%= BaseConstants.GUEST_USERNAME_ID %>
</c:set>


      <c:choose>
      <c:when test="${MODULE == 'ADMIN'}">
           <c:url var="readOneMailByUserName" value="/action/admin/adminReadOneMail?action=readOneMail&messageId="/>
      </c:when>
      <c:otherwise>
            <c:url var="readOneMailByUserName" value="/action/member/readOneMailByUserName?action=readOneMail&messageId="/>
      </c:otherwise>
    </c:choose>


<script language="javaScript" type="text/Javascript">
<!-- Begin
function deleteMail(button)
{
   if(confirm('Are you sure you want to delete the the selected private mail(s)?')){
        document.privateMessageForm.privMsgsAction.value = "delete";
        document.privateMessageForm.submit();
   }
}

function moveMail(button)
{

     if (document.privateMessageForm.folderName.value == ''){
       alert('Where would you like to move this Private Message to?');
     }
     else if(confirm('Are you sure you want to move the the selected private mail(s)?')){
          document.privateMessageForm.privMsgsAction.value = "move";
          document.privateMessageForm.submit();
     }
}

function emptyTrash(button)
{
   if(confirm('Are you sure you want to permanently delete the selected messages in this folder?')){
     document.privateMessageForm.privMsgsAction.value = "empty";
     document.privateMessageForm.submit();

   }
}


function checkAll(field)
{
  for (i = 0; i < field.length; i++){
  	field[i].checked = true ;
  }
}

function uncheckAll(field)
{
  for (i = 0; i < field.length; i++){
  	field[i].checked = false ;
  }
}


//  End -->
</script>








 <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
        <tr>
          <td height="30" colspan="6" class="bg0">
            <c:choose>
            <c:when test="${MESSAGE_CENTER.folderType == 'inbox'}">
              <html:img page="/images/icon/folder_inbox.gif" alt="Inbox" title="Inbox" align="absbottom" border="0" height="19" width="19"/>&nbsp;
              <bean:message key="label.myinbox"/>
            </c:when>
            <c:when test="${MESSAGE_CENTER.folderType == 'sent'}">
              <html:img page="/images/icon/folder_sent.gif" alt="Sent" title="Sent" align="absbottom" border="0" height="19" width="19"/>&nbsp;
              <bean:message key="label.mysent"/>
            </c:when>
            <c:when test="${MESSAGE_CENTER.folderType == 'trash'}">
              <html:img page="/images/icon/folder_trash.gif" alt="Trash" title="Trash" align="absbottom" border="0" height="19" width="19"/>&nbsp;
              <bean:message key="label.mytrash"/>
            </c:when>
            <c:when test="${MESSAGE_CENTER.folderType == 'storage'}">
              <html:img page="/images/icon/folder_default.gif" alt="demo" title="Storage" align="absbottom" border="0" height="19" width="19"/>&nbsp;
              <bean:message key="label.mystorage"/>
            </c:when>
            <c:when test="${MESSAGE_CENTER.folderType == 'draft'}">
              <html:img page="/images/icon/folder_default.gif" alt="demo" title="Storage" align="absbottom" border="0" height="19" width="19"/>&nbsp;
              <bean:message key="label.mydraft"/>
            </c:when>
            <c:otherwise>
              <bean:message key="label.myunknownfolder"/>
            </c:otherwise>
            </c:choose>
            : </td>
        </tr>
    <c:if test="${not empty MESSAGE_CENTER.listOfMessages}">
        <tr class="portlet-section-body">
          <td width="14">&nbsp;</td>
          <td width="4">&nbsp;</td>
          <td width="22%"><strong><bean:message key="label.from"/></strong></td>
          <td width="20%" align="center"><strong><bean:message key="label.date"/></strong></td>
          <td width="42%"><strong><bean:message key="label.subject"/></strong></td>
          <td width="11%" align="center"><strong><bean:message key="label.selectpm"/></strong><br />
            <input type="button" class="button" name="CheckAll" value=" + " onClick="javascript:checkAll(document.privateMessageForm.messageId)">
            <input type="button" class="button" name="UnCheckAll" value=" - " onClick="javascript:uncheckAll(document.privateMessageForm.messageId)">
         </td>
        </tr>
    </c:if>

        <c:set var="num_rec" scope="page" value="-1"/>
        <logic:iterate id="message" name="MESSAGE_CENTER"  indexId="pIdx" property="listOfMessages">


        <tr class="portlet-section-body" onmouseover="ChangeColor(this, true);" onmouseout="ChangeColor(this, false);"> 

          <td align="center">
            <c:choose>
              <c:when test="${message.messageStatus == 'N'}">
                <html:img page="/images/icon/i.p.new.gif" alt="New Mail !" width="14" height="11"/>
              </c:when>
              <c:otherwise>
                <html:img page="/images/icon/i.p.read.gif" alt="Read Email" width="15" height="12"/>
              </c:otherwise>
            </c:choose>
          </td>

          <td align="center">
            <c:choose>
              <c:when test="${message.priority == 'H'}">
                &nbsp;<html:img page="/images/icon/i.p.importance.h.gif" alt="High Importance" width="4" height="10"/>&nbsp;
              </c:when>
              <c:when test="${message.priority == 'L'}">
                <html:img page="/images/icon/i.p.importance.l.gif" alt="Low Importance" width="7" height="10"/>
              </c:when>
              <c:otherwise>
                &nbsp;&nbsp;&nbsp;
              </c:otherwise>
            </c:choose>
          </td>

          <td>
            <c:choose>
              <c:when test="${message.messageStatus eq 'N'}">
			     		<c:choose>
			     			<c:when test="${message.messageFromMember.memberId eq _guestId}">
			     				<strong><a href="<c:out value="${readOneMailByUserName}"/><c:out value="${message.messageId}"/>" title="Message from <c:out value="${message.guestName}"/> <c:out value="${message.guestEmail}"/>"><c:out value="${message.guestName}"/> <c:out value="${message.guestEmail}"/></a></strong>
			     			</c:when>
			     			<c:otherwise> 
			     				<strong><a href="<c:out value="${readOneMailByUserName}"/><c:out value="${message.messageId}"/>" title="Message from <c:out value="${message.messageFromMember.firstName}"/> <c:out value="${message.messageFromMember.lastName}"/>"><c:out value="${message.messageFromMember.firstName}"/> <c:out value="${message.messageFromMember.lastName}"/></a></strong>
			     			</c:otherwise>
			     		</c:choose>  
              </c:when>
              <c:otherwise>
			     		<c:choose>
			     			<c:when test="${message.messageFromMember.memberId eq _guestId}">
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${message.messageId}"/>" title="Message from <c:out value="${message.guestName}"/> <c:out value="${message.guestEmail}"/>"><c:out value="${message.guestName}"/> <c:out value="${message.guestEmail}"/></a>
			     			</c:when>
			     			<c:otherwise>
			     				<a href="<c:out value="${readOneMailByUserName}"/><c:out value="${message.messageId}"/>" title="Message from <c:out value="${message.messageFromMember.firstName}"/> <c:out value="${message.messageFromMember.lastName}"/>"><c:out value="${message.messageFromMember.firstName}"/> <c:out value="${message.messageFromMember.lastName}"/></a>
			     			</c:otherwise>
			     		</c:choose>                
              </c:otherwise>
            </c:choose>                        
          </td>

          <td>
            
            <c:choose>
              <c:when test="${message.messageStatus == 'N'}">
                    <strong><fmt:formatDate value="${message.messageDate}" dateStyle="long" type="both" timeStyle="short" /></strong>
              </c:when>
              <c:otherwise>
                <fmt:formatDate value="${message.messageDate}" dateStyle="long" type="both" timeStyle="short" />
              </c:otherwise>
            </c:choose>            
          </td>

          <td>
            <c:choose>
              <c:when test="${message.messageStatus == 'N'}">
                <c:choose>
                  <c:when test="${message.priority == 'H'}">
                    <font color="red"><strong><c:out value="${message.subject}"/> </strong></font>
                  </c:when>
                  <c:otherwise>
                    <strong><c:out value="${message.subject}"/> </strong>
                  </c:otherwise>
                </c:choose>
              </c:when>
              <c:otherwise>
                <c:out value="${message.subject}"/>
              </c:otherwise>
            </c:choose>
          </td>

          <td align="center">
            <input type="checkbox" name="messageId" value="<c:out value="${message.messageId}"/>" title="Delete This Private Message">
          </td>
          </tr>
          <c:set var="num_rec" scope="page" value="${pIdx+1}"/>

        </logic:iterate>

        <c:if test="${num_rec == '-1'}">
          <tr class="portlet-section-body">
            <td colspan="6"><strong><bean:message key="message.nomessages"/></strong></td>
          </tr>
        </c:if>

          <tr class="portlet-section-body">
            <td colspan="6" align="right">
              <c:if test="${num_rec != '-1'}">
                        <select name="folderName">
                          <c:choose>
                            <c:when test="${MESSAGE_CENTER.folderType == 'inbox'}">
                              <option value="">-- Move To --</option>
                              <option value="Storage">Storage</option>
                              <option value="Trash">Trash</option>
                            </c:when>
                            <c:when test="${MESSAGE_CENTER.folderType == 'sent'}">
                              <option value="Trash">Trash</option>
                            </c:when>
                            <c:when test="${MESSAGE_CENTER.folderType == 'trash'}">
                              <option value="">-- Move To --</option>
                              <option value="Inbox">Inbox</option>
                              <option value="Storage">Storage</option>
                            </c:when>
                            <c:when test="${MESSAGE_CENTER.folderType == 'storage'}">
                              <option value="">-- Move To --</option>
                              <option value="Inbox">Inbox</option>
                              <option value="Trash">Trash</option>
                            </c:when>
                            <c:when test="${MESSAGE_CENTER.folderType == 'draft'}">
                              <option value="Trash">Trash</option>
                            </c:when>
                          </c:choose>
                        </select>

                 <html:button  styleClass="button" property="buttonAction" value="Move Private Message" onclick="moveMail(this);"/>
            </c:if>

                <c:if test="${MESSAGE_CENTER.folderType == 'trash'}">
                     <c:if test="${not empty MESSAGE_CENTER.listOfMessages}">
                        <html:button  styleClass="button" property="buttonAction" value="Delete Selected Message(s)" onclick="emptyTrash(this);"/>
                    </c:if>
                </c:if>

&nbsp;

            </td>
          </tr>

      </table>