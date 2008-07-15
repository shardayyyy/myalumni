<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Invalid Token !!!" />
  <tiles:put name="body" value="/jsp/body/tokenError-body.jsp" />
</tiles:insert>
