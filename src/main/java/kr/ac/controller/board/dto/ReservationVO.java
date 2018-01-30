package kr.ac.controller.board.dto;

public class ReservationVO {
	private String id;
	private String breadname;
	private String address;
	private int breadquantity;
	private String requirement;
	private Long time;
	
	
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBreadname() {
		return breadname;
	}
	public void setBreadname(String breadname) {
		this.breadname = breadname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getBreadquantity() {
		return breadquantity;
	}
	public void setBreadquantity(int breadquantity) {
		this.breadquantity = breadquantity;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	@Override
	public String toString() {
		return "ReservationVO [id=" + id + ", breadname=" + breadname + ", address=" + address + ", breadquantity="
				+ breadquantity + ", requirement=" + requirement + ", time=" + time + "]";
	}
}
