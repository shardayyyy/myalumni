<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Change Log" />
  <tiles:put name="body" value="/jsp/body/changeLog-body.jsp" />
</tiles:insert>
