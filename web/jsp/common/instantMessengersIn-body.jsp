<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:url var="jstxtBoxLib" value="/js/selectbox.js" />
<script language="JavaScript1.2" type="text/javascript" src=<c:out value="${jstxtBoxLib}" escapeXml="false"/>></script>
     
<table width="100%" border="0" cellpadding="2" cellspacing="0">
    <tbody>
      <tr align="center"  class="bg0">
        <td><bean:message key="label.availableim"/></td>
        <td><bean:message key="label.addremove"/></td>
        <td><bean:message key="label.selectedim"/><font color="#FF0000">*</font></td>
      </tr>

      <tr align="center">
        <td width="45%"><p>
            <!-- List of available Instant Messangers -->
            <html:select property="lstAvailableIMs" multiple="true" styleId="availableIMs" size="9" titleKey="label.availableim" ondblclick="moveSelectedOptions(this.form['lstAvailableIMs'],this.form['lstSelectedIMs'],false)">
            	<logic:notEmpty name="luAvailableIMs" scope="session">
                	<logic:iterate id="availIM" scope="session" name="luAvailableIMs" indexId="recIdx">
                    	<option value="<c:out value="${availIM.lookupCodeId}" escapeXml="false" />"><c:out value="${availIM.label}" escapeXml="false" /></option>
                	</logic:iterate>
                </logic:notEmpty>
            </html:select>
          <br>
          <br>
          <input type="button" class="button" onclick="sortSelect(this.form['lstAvailableIMs'])" name="sort1" id="sort1" value="ReSort List Alpabeltically">
        </p>
          <p>&nbsp; </p></td>
        <td width="10%" class="Smallbold"><p>
            <input TYPE="button" NAME="right" VALUE="&gt;&gt;" onclick="moveSelectedOptions(this.form['lstAvailableIMs'],this.form['lstSelectedIMs'],false)"><br><br>
            <input TYPE="button" NAME="left" VALUE="&lt;&lt;" onclick="moveSelectedOptions(this.form['lstSelectedIMs'],this.form['lstAvailableIMs'],false)"><br><br>
            <input TYPE="button" NAME="right" VALUE="All &gt;&gt;" onclick="moveAllOptions(this.form['lstAvailableIMs'],this.form['lstSelectedIMs'],false)"><br><br>
            <input TYPE="button" NAME="left" VALUE="All &lt;&lt;" onclick="moveAllOptions(this.form['lstSelectedIMs'],this.form['lstAvailableIMs'],false)">
          </p></td>
        <td width="45%"><p>
            <!-- List of selected skills -->
            <html:select property="lstSelectedIMs" multiple="true" styleId="selectedIMs" size="9" titleKey="label.selectedim" ondblclick="moveSelectedOptions(this.form['lstSelectedIMs'],this.form['lstAvailableIMs'],false)">
            	<logic:notEmpty name="luSelectedIMs" scope="session">
                	<logic:iterate id="selectedIM" scope="session" name="luSelectedIMs" indexId="recIdx">
                    	<option value="<c:out value="${selectedIM.lookupCodeId}" escapeXml="false" />"><c:out value="${selectedIM.label}" escapeXml="false" /></option>
                	</logic:iterate>
                </logic:notEmpty>
            </html:select>
          <br>
          <br>
          <input type="button" class="button" onclick="sortSelect(this.form['lstSelectedIMs'])" name="sort2" id="sort2" value="ReSort List Alpabeltically">
        </p>
          <p>&nbsp; </p>
          </td>
      </tr>
      <tr>
      	<td colspan="3" align="center"><html:checkbox property="hideIm" value="Y" titleKey="label.makethisprivate"/> <font class="graysmall"> <bean:message key="label.makethisprivate"/></font></td>
      </tr>   
     <tr>
      	<td colspan="3" class="fieldlabel">&nbsp;</td>
      </tr>   
    </tbody>
</table>
                       
 