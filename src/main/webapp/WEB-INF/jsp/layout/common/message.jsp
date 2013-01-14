<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>

<logic:messagesPresent message="true">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td align="center" class="quote"><br>
                    <span class="infoMessage">
                    	<html:messages property="INFO_KEY" id="msg" message="true">
                    		<c:out value="${msg}" escapeXml="false"/><br/>
                    	</html:messages> 
                    </span>
                    <span class="warnMessage">
                    	<html:messages property="WARN_KEY" id="msg" message="true">
                    		<c:out value="${msg}" escapeXml="false"/><br/>
                    	</html:messages>                     	
                    </span>	                    
                    <span class="errorMessage">
                    	<html:messages property="ERROR_KEY" id="msg" message="true">
                    		<c:out value="${msg}" escapeXml="false"/><br/>
                    	</html:messages>                     	
                    </span>	                
                    <span class="fatalMessage">
                    	<html:messages property="FATAL_KEY" id="msg" message="true">
                    		<c:out value="${msg}" escapeXml="false"/><br/>
                    	</html:messages>  <br>                   	
                    </span>	                 
                </td>
            </tr>
        </table>
 </logic:messagesPresent>
<logic:messagesPresent message="false"> 
		<table border="0" cellspacing="5" cellpadding="5" width="100%">
            <tr>
                <td align="center" class="quote">	<br>		
                    <span class="errorMessage" >
                    	<html:errors/> 
                    </span>   <br>
                 </td>
              </tr>
           </table>
</logic:messagesPresent>                   
