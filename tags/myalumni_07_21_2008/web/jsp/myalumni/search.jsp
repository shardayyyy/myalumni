<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<c:set var="menuTab" scope="session" value="myfegocoid"/>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Search for an Alumni" />
  <tiles:put name="body" value="/jsp/common/search-body.jsp" />
</tiles:insert>