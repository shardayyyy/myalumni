<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert definition="myalumni.iphone" flush="true">
  <tiles:put name="title" value="Dormitory" />
  <tiles:put name="body" value="/iphone/body/dorm-body.jsp" />
</tiles:insert>

