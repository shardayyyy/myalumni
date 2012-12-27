<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>


login
<ul id="login" title="Login">
    <html:form action="/member/login?action=login" styleClass="panel">
     
        <h2>User Login</h2>
        <fieldset>
            <div class="row">
                <label>Name</label>
          		 <html:text property="memberUserName" title="Member UserName"/>      
            </div>
            <div class="row">
                <label>Password</label>
                <html:password property="memberPassword" title="Member Password" redisplay="false"/>
            </div>
            <div class="row">		           
                <input type="submit" value="Login" class="button" />
            </div>		            
        </fieldset>
    </html:form>
</ul> 