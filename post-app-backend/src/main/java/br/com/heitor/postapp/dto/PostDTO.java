package br.com.heitor.postapp.dto;

import java.io.Serializable;

public class PostDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String title;
    private String description;
    private Integer upvotes;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getUpvotes() {
		return upvotes;
	}
	
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}


}
