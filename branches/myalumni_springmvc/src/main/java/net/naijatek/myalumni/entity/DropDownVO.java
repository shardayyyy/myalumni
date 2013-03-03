package net.naijatek.myalumni.entity;

/**
 * @author folashade.adeyosoye
 *
 */
public class DropDownVO implements ValueLabelItem {

	private int id;
	private String label;
	private String value;
	private String status;
	

	
	public DropDownVO(String label, String value) {
		this.label = label;
		this.value = value;
    }
	
	
	public DropDownVO() {
		super();
    }



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getItemLabel() {
		return getLabel();
	}



	public String getItemValue() {
		return getValue();
	}
	


}
