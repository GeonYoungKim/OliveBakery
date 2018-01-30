package kr.ac.controller.board.dto;

public class UpdateBread {
	private String breadname;
	private int breadquantity;
	public String getBreadname() {
		return breadname;
	}
	public void setBreadname(String breadname) {
		this.breadname = breadname;
	}
	public int getBreadquantity() {
		return breadquantity;
	}
	public void setBreadquantity(int breadquantity) {
		this.breadquantity = breadquantity;
	}
	@Override
	public String toString() {
		return "UpdateBread [breadname=" + breadname + ", breadquantity=" + breadquantity + "]";
	}

}
