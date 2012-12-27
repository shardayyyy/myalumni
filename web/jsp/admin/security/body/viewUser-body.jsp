<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                    </tr>
                    <tr>
                      <td class="bg0"><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.admin.users.viewuser"/></span></div></td>
                      <td align="right" class="bg0">&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                    </tr>
                    <tr>
                      <td colspan="2" valign="top" ><table width="100%" border="0" cellpadding="2" cellspacing="5">
                          <tbody>
                            <tr>
                              <td width="35%" align="right" class="Smallbold"><bean:message key="label.admin.users.name"/>:</td>
                              <td width="65%"><bean:write name="ObjectVO" property="firstName"/> <bean:write name="ObjectVO" property="lastName"/></td>
                            </tr>
                            <tr>
                              <td align="right" class="Smallbold"><bean:message key="label.admin.users.isadmin"/>:</td>
                              <td><bean:write name="ObjectVO" property="isAdmin"/></td>
                            </tr>
                            <tr>
                              <td align="right" class="Smallbold"><bean:message key="label.admin.users.email"/>:</td>
                              <td><bean:write name="ObjectVO" property="email"/></td>
                            </tr>
                            <tr>
                              <td align="right" valign="top" class="Smallbold"><bean:message key="label.admin.users.username"/>:</td>
                              <td valign="top"><bean:write name="ObjectVO" property="memberUserName"/></td>
                            </tr>
                            <tr>
                              <td align="right" class="Smallbold"><bean:message key="label.admin.status"/>: </td>
                              <td>
                              <c:choose>
				       					<c:when test="${ObjectVO.memberStatus == 'A'}">
											<span class="greensmall">Activated</span>
										</c:when>					
										<c:when test="${ObjectVO.memberStatus == 'D'}">
											<span class="graysmall">Deactivated</span>
										</c:when>
										<c:when test="${ObjectVO.memberStatus == 'U'}">
											<span class="greensmall">New</span>
										</c:when>
										<c:when test="${ObjectVO.memberStatus == 'L'}">
											<span class="redsmall">(Locked)</span>
										</c:when>
										<c:when test="${ObjectVO.memberStatus == 'X'}">
											<span class="redsmall">(Deleted)</span>
										</c:when>										
										<c:otherwise>
											<span class="redsmall">(Unknown Status)</span>
										</c:otherwise>	                                 
                                </c:choose> 
                              </td>
                            </tr>
                            <tr>
                              <td align="right" class="Smallbold"><bean:message key="label.admin.users.datecreated"/>: </td>
                              <td><fmt:formatDate value="${ObjectVO.creationDate}" type="both" timeStyle="short"/>
                              </td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                  </tbody>
                </table>
                <br>

                <%--  User Login History  --%>
                <%--  User Login History  --%>
                <%--  User Login History  --%>


                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                    </tr>
                    <tr class="bg0">
                      <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/><span class="Bold">Login History</span></div></td>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                    </tr>
                    <tr>
                      <td colspan="2" >
					  
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfUserLoginHistory" cellpadding="3" cellspacing="1" pagesize="20" id="row" requestURI="/action/admin/viewUser?action=viewUser" excludedParams="action">
                            <display:column titleKey="label.admin.security.logintime" sortable="true">
                                <fmt:formatDate value="${row.requestTime}" type="both" dateStyle="long" timeStyle="short"/>
                            </display:column>
                            <display:column property="sourceIP" titleKey="label.admin.security.sourceip" sortable="true"/>
                            <display:column property="userAgent" titleKey="label.admin.security.lang" sortable="true"/>
                            <display:column property="reasonCodeDesc" titleKey="label.admin.security.reasoncode" sortable="true"/>
                            <c:choose>
                                <c:when test="${row.loginStatus == 'P'}">
                                        <display:column titleKey="label.admin.status"  class="row-green" sortable="true">
                                            <bean:message key="label.admin.security.passed"/>
                                        </display:column>
                                </c:when>
                                <c:when test="${row.loginStatus == 'F'}">
                                        <display:column titleKey="label.admin.status"  class="row-red" sortable="true">
                                            <bean:message key="label.admin.security.failed"/>
                                        </display:column>
                                </c:when>
                            </c:choose>                                    
                        </display:table>  
                    </fmt:bundle> 					  
					
					  </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </table>
