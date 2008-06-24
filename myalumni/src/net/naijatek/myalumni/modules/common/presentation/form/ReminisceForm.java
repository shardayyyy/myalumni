package net.naijatek.myalumni.modules.common.presentation.form;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseForm;

public class ReminisceForm extends MyAlumniBaseForm {

	private String reminisceId ;
	private String fromYear;
	private String toYear;
	private String slang;
	private String meaning;
	private String pronounce;
	private String status;
	private String authorId;
	
	
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getPronounce() {
		return pronounce;
	}
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}
	public String getReminisceId() {
		return reminisceId;
	}
	public void setReminisceId(String reminisceId) {
		this.reminisceId = reminisceId;
	}
	public String getSlang() {
		return slang;
	}
	public void setSlang(String slang) {
		this.slang = slang;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	

	
}
