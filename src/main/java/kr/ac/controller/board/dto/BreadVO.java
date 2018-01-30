package kr.ac.controller.board.dto;

public class BreadVO {
	private String breadname;
	private int breadtotal;
	private String breadprice;
	
	public String getBreadname() {
		return breadname;
	}
	public void setBreadname(String breadname) {
		this.breadname = breadname;
	}
	@Override
	public String toString() {
		return "BreadVO [breadname=" + breadname + ", breadtotal=" + breadtotal + ", breadprice=" + breadprice + "]";
	}
	public int getBreadtotal() {
		return breadtotal;
	}
	public void setBreadtotal(int breadtotal) {
		this.breadtotal = breadtotal;
	}
	
	public String getBreadprice() {
		return breadprice;
	}
	public void setBreadprice(String breadprice) {
		this.breadprice = breadprice;
	}

}
