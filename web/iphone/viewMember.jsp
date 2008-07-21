<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert definition="myalumni.iphone" flush="true">
  <tiles:put name="title" value="Member Details" />
  <tiles:put name="body" value="/iphone/body/viewMember-body.jsp" />
</tiles:insert>




