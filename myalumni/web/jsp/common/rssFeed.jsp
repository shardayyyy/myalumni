<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>


<c:url var="viewallrss" value="/action/home/viewAllRssFeeds?action=viewAllRssFeeds"/>
<c:url var="viewonerss" value="/action/home/viewOneAnnouncements?action=viewOneAnnouncements"/>


<myalumni:loadRSS maxNumberToDisplay="5"/>


<table width="100%" border="0" cellpadding="0" cellspacing="0" class="SearchBorderStyle">
              <tbody>
                <tr>
                  <td colspan="2" class="adminTableTrim"><html:img page="/images/pixel.gif" height="1" width="3"/></td>
                </tr>
                <tr>
                  <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/pixel.gif" border="0" height="1" width="15"/> <span class="Bold"><c:out value="${rssHeader}" default="RSS Feed"/></span></div></td>
                  <td align="right" class="bg0"><html:img page="/images/icon/rss1.png"/>&nbsp;&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="2" class="adminTableBot"><html:img page="/images/pixel.gif" height="1" width="1"/></td>
                </tr>
                <tr>
                  <td colspan="2" >
                  <table width="100%" border="0" cellpadding="2" cellspacing="0">
                    <tbody>
                      <tr>
                        <td width="15"><html:img page="/images/pixel.gif" height="1" width="15"/></td>
                        <td class="Smallbold">&nbsp;</td>
                      </tr>
					  
					  	
					  	
					  			<c:set var="rowcss" scope="page" value="even"/>
		                        <c:set var="num_rec" scope="page" value="-1"/>
	
		                        <logic:present name="readNews">
			                        <logic:iterate id="rss" name="readNews" indexId="pIdx"> 
			                        
			                        	<c:choose>
				                        	<c:when test="${pIdx % 2 == '0'}">
				                        		<c:set var="rowcss" scope="page" value="even"/>
				                        	</c:when>
				                        	<c:otherwise>
				                        		<c:set var="rowcss" scope="page" value="odd"/>
				                        	</c:otherwise>
			                        	</c:choose>
			                        
			                        	<tr class="<c:out value="${rowcss}"/>">
						  					<td>
			                        				&nbsp;&nbsp;<%-- &#8226; --%>
			                        		</td>		
						  					<td>
												<span style="font-family: 'lucida grande', tahoma, verdana, arial, sans-serif; font-size: 13px; font-weight: bold;"><a href="<c:out value="${rss.link}"/>" title="<c:out value="${rss.titleEx.value}" escapeXml="false"/>" target="_new"><c:out value="${rss.titleEx.value}" escapeXml="false"/></a></span>
				                        		&nbsp;
				                        		<b>-</b>&nbsp;<fmt:formatDate type="date" dateStyle="full" value="${rss.publishedDate}"/>
				                        		<br>
				                                <c:out value="${rss.description.value}" escapeXml="false"/>
				                                <br>		                        			
			                        		</td>			                        													
	                        			</tr>
	                                      <tr>
	                                        <td height="10">&nbsp;</td>
	                                      </tr>	                        			
			                        <c:set var="num_rec" scope="page" value="${pIdx+1}"/>
			                        </logic:iterate>
		                        </logic:present>
		                        
	                            <c:if test="${num_rec == '-1'}">
	                                      <tr  bgcolor="#ffffff" class="Smallbold">
	                                        <td height="25">&nbsp;<bean:message key="message.norecordsfound"/></td>
	                                      </tr>
	                            </c:if>                          
                                                  					  			
			           
                       
                        
                        
                    </tbody>
                  </table>
                  
                  </td>
                </tr>
              </tbody>
            </table>