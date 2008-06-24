package net.naijatek.myalumni.modules.common.domain;

public class MessengerVO extends MyAlumniBaseVO{

	private String memberMessengerId;
	
	private String memberId;
	private MemberVO member;
	
	private String lookupCodeId;
	private XlatDetailVO messenger;

	
	
	
	public String getLookupCodeId() {
		return lookupCodeId;
	}
	public void setLookupCodeId(String lookupCodeId) {
		this.lookupCodeId = lookupCodeId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberMessengerId() {
		return memberMessengerId;
	}
	public void setMemberMessengerId(String memberMessengerId) {
		this.memberMessengerId = memberMessengerId;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	public XlatDetailVO getMessenger() {
		return messenger;
	}
	public void setMessenger(XlatDetailVO messenger) {
		this.messenger = messenger;
	}
	
}
