package com.mvc.dto;
import java.util.Date;
public class MYDto {

	
	private int myseq;
	private String myname;
	private String mytitle;
	private String mycontent;
	private Date mydate;
	
	
	public MYDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MYDto(int myseq, String myname, String mytitle, String mycontent, Date mydate) {
		super();
		this.myseq = myseq;
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
		this.mydate = mydate;
	}
	public int getMyseq() {
		return myseq;
	}
	public void setMyseq(int myseq) {
		this.myseq = myseq;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getMytitle() {
		return mytitle;
	}
	public void setMytitle(String mytitle) {
		this.mytitle = mytitle;
	}
	public String getMycontent() {
		return mycontent;
	}
	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}
	public Date getMydate() {
		return mydate;
	}
	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}
	
	
	
	
	
}
