package net.naijatek.myalumni.modules.common.domain;

public class ReminisceVO extends MyAlumniBaseVO{

	private String reminisceId ;
	private String fromYear;
	private String toYear;
	private String slangYear ;    // for jsp display only
	private String slang;
	private String meaning;
	private String pronounce;
	private String status;
	private String authorId;
	private MemberVO author;
	
	public MemberVO getAuthor() {
		return author;
	}


	public void setAuthor(MemberVO author) {
		this.author = author;
	}


	public String getAuthorId() {
		return authorId;
	}


	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ReminisceVO() {
		super();
	}
	
	
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
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}


	public String getSlangYear() {
		StringBuffer  sb = new StringBuffer();
		if (getFromYear() != null && getFromYear().length() > 0 )
		sb.append(getFromYear());
		
		if (getToYear() != null && getToYear().length() > 0){
			sb.append("-");
			sb.append(getToYear());
		}
		slangYear = sb.toString();
		return slangYear;
	}


	public void setSlangYear(String slangYear) {
		this.slangYear = slangYear;
	}
	
}
