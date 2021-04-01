package com.muldel.dto;
import java.util.Date;


public class MDBoardDto {

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regdate;


  public MDBoardDto(){
  }	

  //글 작성할 때 사용할 :no는 필요없고, writer,title,content
//  원래는 new MDBoardDto(0,writer,title,content,null); 인데 , 
  //new MDBoardDto(writer,title,content); 이렇게 써줌. 
  public MDBoardDto(String writer,String title,String content){
	  this.writer=writer;
	  this.title=title;
	  this.content=content;
  }



//글 수정할 때 사용할
  //원래는 new MDBoardDto(seq,null,title,content,null);
  //인데, new MDBoardDto(seq,title,content); 이렇게 써줌. 
  public MDBoardDto(int seq,String title,String content){
       this.seq = seq;
       this.title=title;
       this.content=content;
  }
  
  
  
  //전체 파라미터 들어간 생성자.
public MDBoardDto(int seq, String writer, String title, String content, Date regdate) {
	this.seq = seq;
	this.writer = writer;
	this.title = title;
	this.content = content;
	this.regdate = regdate;
}


//게터세터.
public int getSeq() {
	return seq;
}

public void setSeq(int seq) {
	this.seq = seq;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public Date getRegdate() {
	return regdate;
}

public void setRegdate(Date regdate) {
	this.regdate = regdate;
}




  
  
  
  //글작성할 때 사용할 애랑 글 수정할 때 사용할 애들 다 들어가잇는데 왜 따로 써줘?
  /*
   * 
   * 
   * */
  
  
  
  
}