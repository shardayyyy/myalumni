package net.naijatek.myalumni.modules.common.presentation.form;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseForm;

public class FrontPageForm extends MyAlumniBaseForm {

	private String linkId ;
	private String label;
	private String linkurl;
	private String important;
	
	
	
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
}
