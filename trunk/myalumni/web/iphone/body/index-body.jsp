<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
	

			<ul id="welcome" title="<c:out value="${ORGANIZATION_SHORT_NAME}"/>" selected="true">
			  <li><a href="#firstname"><div class="list-link"><p>By First Names</p></div></a></li>
			  <li><a href="#lastname"><div class="list-link"><p>By Last Names</p></div></a></li>
			   <c:if test="${applicationScope.hasDorm == 'Y'}">
			   		<li><html:link action="/iphone/getDorms?action=getDorms"><div class="list-link"><p>By Dormitory</p></div></html:link></li>
			   </c:if>
			  <li><a href="#gender"><div class="list-link"><p>By Gender</p></div></a></li>
			  <li><html:link page="/iphone/login.jsp"><div class="list-link"><p>Login</p></div></html:link></li>
			  <li><a href="#about"><div class="list-link"><p>About</p></div></a></li>
			</ul>

		

		<ul id="firstname" title="First Name...">
		  
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=a"><div class="list-link"><p>First Name by A</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=b"><div class="list-link"><p>First Name by B</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=c"><div class="list-link"><p>First Name by C</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=d"><div class="list-link"><p>First Name by D</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=e"><div class="list-link"><p>First Name by E</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=f"><div class="list-link"><p>First Name by F</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=g"><div class="list-link"><p>First Name by G</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=h"><div class="list-link"><p>First Name by H</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=i"><div class="list-link"><p>First Name by I</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=j"><div class="list-link"><p>First Name by J</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=k"><div class="list-link"><p>First Name by K</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=l"><div class="list-link"><p>First Name by L</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=m"><div class="list-link"><p>First Name by M</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=n"><div class="list-link"><p>First Name by N</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=o"><div class="list-link"><p>First Name by O</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=p"><div class="list-link"><p>First Name by P</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=q"><div class="list-link"><p>First Name by Q</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=r"><div class="list-link"><p>First Name by R</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=s"><div class="list-link"><p>First Name by S</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=t"><div class="list-link"><p>First Name by T</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=u"><div class="list-link"><p>First Name by U</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=v"><div class="list-link"><p>First Name by V</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=w"><div class="list-link"><p>First Name by W</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=x"><div class="list-link"><p>First Name by X</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=y"><div class="list-link"><p>First Name by Y</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterfn&alpha=z"><div class="list-link"><p>First Name by Z</p></div></html:link></li>
		</ul>  
		
		
		<ul id="lastname" title="Last Name...">
		  <li><html:link action="/iphone/filter?action=filterln&alpha=a"><div class="list-link"><p>Last Name by A</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=b"><div class="list-link"><p>Last Name by B</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=c"><div class="list-link"><p>Last Name by C</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=d"><div class="list-link"><p>Last Name by D</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=e"><div class="list-link"><p>Last Name by E</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=f"><div class="list-link"><p>Last Name by F</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=g"><div class="list-link"><p>Last Name by G</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=h"><div class="list-link"><p>Last Name by H</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=i"><div class="list-link"><p>Last Name by I</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=j"><div class="list-link"><p>Last Name by J</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=k"><div class="list-link"><p>Last Name by K</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=l"><div class="list-link"><p>Last Name by L</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=m"><div class="list-link"><p>Last Name by M</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=n"><div class="list-link"><p>Last Name by N</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=o"><div class="list-link"><p>Last Name by O</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=p"><div class="list-link"><p>Last Name by P</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=q"><div class="list-link"><p>Last Name by Q</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=r"><div class="list-link"><p>Last Name by R</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=s"><div class="list-link"><p>Last Name by S</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=t"><div class="list-link"><p>Last Name by T</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=u"><div class="list-link"><p>Last Name by U</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=v"><div class="list-link"><p>Last Name by V</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=w"><div class="list-link"><p>Last Name by W</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=x"><div class="list-link"><p>Last Name by X</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=y"><div class="list-link"><p>Last Name by Y</p></div></html:link></li>
		  <li><html:link action="/iphone/filter?action=filterln&alpha=z"><div class="list-link"><p>Last Name by Z</p></div></html:link></li>
		</ul>
	

		<ul id="gender" title="By Gender Name ...">
		   <li><html:link action="/iphone/filter?action=filterGender&alpha=m"><div class="list-link"><p>All Male's</p></div></html:link></li>
		   <li><html:link action="/iphone/filter?action=filterGender&alpha=f"><div class="list-link"><p>All Female's</p></div></html:link></li>
		</ul> 
		
				
		
		<ul id="about" title="About">
		  <br>
		  <center>&nbsp;&nbsp;This is iphone ported version of the <c:out value="${ORGANIZATION_SHORT_NAME}"/> <bean:message key="application.name"/> application.</center>
		  
		  <li><a href="http://www.naijatek.com/"  target="_self"><div class="list-link"><p>Powered by Naijatek Solutions&nbsp;<img src="img/naijateklogo-150x15.gif"></p></div></a></li>

		</ul> 					
		
		
	<%--	<div id="spinner" style="display:block"><p><html:img page="/iphone/img/spinner.gif" align="absmiddle" /><br />Loading...</p></div>
	--%>	
		