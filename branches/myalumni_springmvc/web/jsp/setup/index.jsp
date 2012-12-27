<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<tiles:insert definition="myalumni.setup" flush="true">
  <tiles:put name="title" value="Setup" />
  <tiles:put name="body" value="/jsp/setup/body/index-body.jsp" />
</tiles:insert>	