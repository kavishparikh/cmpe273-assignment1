package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Book {
	@JsonProperty
	
    private static int isbn;
	@NotNull(message="Should not be nulls")
    private String title;
    @JsonProperty("publication-date") 
    private String publication;
    private String language;
    @JsonProperty("num-pages") 
    private int pages;
    public String status;

    
    ArrayList<Author> authors = new ArrayList<Author>();
    public ArrayList<Reviews> reviews=new ArrayList<Reviews>(); 
    
    //ISBN GETTER AND SETTER
    public int getIsbn() {
	return isbn;
    }

    public void setIsbn(int x) {
    isbn = x;
    }

    //TITLE GETTER AND SETTER
    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
    
    //PUBLICATION GETTER AND SETTER
    public String getPublication() {
	return publication;
    }

    public void setPublication(String publication) {
	this.publication = publication;
    }
        
    //LANGUAGE GETTER AND SETTER
    public String getLanguage() {
    	return language;
        }

    public void setLanguage(String language) {
    	this.language = language;
        }
    
    //PAGES GETTER AND SETTER
    public int getPages() {
    	return pages;
        }

    public void setPages(int pages) {
    	this.pages = pages;
        }

    //STATUS GETTER AND SETTER
    public String getStatus() {
    	return status;
        }

    public void setStatus(String status) {
    	this.status = status;
        }
    	
    //AUTHORS GETTER AND SETTER
    public void setAuthors(ArrayList<Author> authors){  
    	this.authors = authors;
    }
    
    public ArrayList<Author> getAuthors(){
    	return authors;
    }
    
    //REVIEWS GETTER AND SETTER
    public void setReviews(){
    	this.reviews = reviews;
    }
    
    public ArrayList<Reviews> getReviews(){
    	return reviews;	
    	
    }
    
    public ArrayList<Reviews> addReviews(Reviews review){
    	if(reviews.size()==0)
    	{
    		reviews=new ArrayList<Reviews>();
    	}
    	reviews.add(review);
    	return reviews;
    }
    

}
