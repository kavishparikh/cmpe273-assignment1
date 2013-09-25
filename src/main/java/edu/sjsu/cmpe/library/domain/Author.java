package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kavish.parikh
 *
 */

public class Author 
{
	@JsonProperty("id")
	static int authorId = 1;
	@JsonProperty("name")
    private String authorName;
	
	//AUTHOR ID
    public int getAuthorId() {
    	return authorId;
    }

    public void setAuthorId(int authorId) {
    	this.authorId = authorId;
    	authorId = authorId+1;
    }
    
    //AUTHOR NAME 
    public String getAuthorName() {
    	return authorName;
    }

    public void setAuthorName(String authorName) {
    	this.authorName = authorName;
    }
    
}
