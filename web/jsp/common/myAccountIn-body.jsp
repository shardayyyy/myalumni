<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="jcaptcha" prefix="jcaptcha"%>

<script type="text/javascript">
var req;
var target;
var isIE;

function initRequest(url) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function validateUserId() {
    if (!target) target = document.getElementById("memberUserName");
    var url = "<%=request.getContextPath()%>/action/validateMemberUserName?action=validateMemberUserName&memberUserName=" + escape(target.value);    
    initRequest(url);
    req.onreadystatechange = processRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function processRequest() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;
            setMessageUsingDOM(message);
            var submitBtn = document.getElementById("submit_btn");
            if (message == "false") {
              submitBtn.disabled = true;
            } else {
              submitBtn.disabled = false;
            }
        }
    }
}

function setMessageUsingInline(message) {
    mdiv = document.getElementById("userIdMessage");
    if (message == "false") {
       mdiv.innerHTML = "<div style=\"color:red\">Invalid Member UserName</div>";
    } else {
       mdiv.innerHTML = "<div style=\"color:green\">Valid Member UserName</div>";
    }  
}

 function setMessageUsingDOM(message) {
     var userMessageElement = document.getElementById("userIdMessage");
     var messageText;
     if (message == "false") {
         userMessageElement.style.color = "red";
         messageText = "Invalid User Id";
     } else {
         userMessageElement.style.color = "green";
         messageText = "Valid User Id";
     }
     var messageBody = document.createTextNode(messageText);
     // if the messageBody element has been created simple replace it otherwise
     // append the new element
     if (userMessageElement.childNodes[0]) {
         userMessageElement.replaceChild(messageBody, userMessageElement.childNodes[0]);
     } else {
         userMessageElement.appendChild(messageBody);
     }
 }

function disableSubmitBtn() {
    var submitBtn = document.getElementById("submit_btn");
    submitBtn.disabled = true;
}
</script>


<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
              <tr>
                <td height="30" colspan="2" class="bg0"><bean:message key="table.title.myalumniaccount"/></td>
              </tr>
              <tr class="portlet-section-body">
                <td width="30%"><span class="fieldlabel"><bean:message key="label.username"/>:<font color="#cc0000">*</font></span></td>
                <td width="70%">
                	<html:text property="memberUserName" styleId="memberUserName" size="25" maxlength="25"  titleKey="label.username" onkeyup="validateUserId()"/>
                	<div id="userIdMessage"></div>
                </td>
              </tr>
              <tr class="portlet-section-body">
                <td><span class="fieldlabel"><bean:message key="label.password"/>:<font color="#cc0000">*</font></span></td>
                <td><html:password property="memberPassword" size="25" maxlength="25" titleKey="label.password" /></td>
              </tr>
              <tr class="portlet-section-body">
                <td><span class="fieldlabel"><bean:message key="label.retypepassword"/>:<font color="#cc0000">* </font></span></td>
                <td><html:password property="memberPasswordConfirm" size="25" maxlength="25" titleKey="label.passwordconfirm"/></td>
              </tr>
              
              <tr class="portlet-section-body">
                <td>
	                <span class="fieldlabel"><jcaptcha:question/>:</span><font color="#cc0000">*</font>
	      	       <%-- Add the image--%>
	               <br><html:img page="/action/jcaptcha" align="absmiddle"/>
	               <br>
               
                </td>
                <td>
                       <%-- Add the input tag--%>
		               <input type="text"  name="jcaptcha_response" />
						<br>
		                <%-- Add the message tag--%>
		               <font color="#cc0000"><strong><jcaptcha:message/></strong></font><br>
                </td>
              </tr>
              
              
              <tr class="portlet-section-body">
                <td colspan="2" class="bg0"><div align="center">IP: Logged <strong><%=request.getRemoteAddr()%>&nbsp; (<%=request.getRemoteHost()%>)</strong></div></td>
              </tr>
</table>
