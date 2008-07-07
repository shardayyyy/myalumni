<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>





    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.reminisce.reminisce"/> </span> </div></td>
                    <td align="right">&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfReminisce" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/member/listReminisce?action=listReminisce" excludedParams="action" id="row">
                                <display:column titleKey="label.reminisce.when"  class="top" style="width:10%;">
                                    <c:out value="${row.slangYear}"/>
                                </display:column>
                                <display:column titleKey="label.reminisce.slang" class="top" style="width:20%;">
                                   <c:out value="${row.slang}"/>
                                </display:column>
                                <display:column titleKey="label.reminisce.pronounciation"  class="top" style="width:20%;">
                                	<c:out value="${row.pronounce}"/>
                                </display:column>                                
                                <display:column titleKey="label.reminisce.meaning"  class="top" style="width:50%;">
                                   <c:out value="${row.meaning}"/>
                                </display:column>
                        </display:table>  
                    </fmt:bundle>
                                    
                    </td>
                  </tr>
                </tbody>
              </table>
                </td>
            </tr>
          </table>

