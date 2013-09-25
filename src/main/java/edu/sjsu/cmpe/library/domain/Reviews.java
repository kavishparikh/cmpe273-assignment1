package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kavish.parikh
 *
 */

public class Reviews 
{
	@JsonProperty("id")
	static int reviewId = 1;
	private int rating;
	private String comment;
	
	//REVIEW ID
	public int getReviewId() {
    	return reviewId;
    }

    public void setReviewId(int reviewId) {
    	this.reviewId = reviewId;
    	reviewId = reviewId+1;
    }

    //REVIEW RATING
    public int getRating() {
    	return rating;
    }

    public void setRating(int rating) {
    	this.rating = rating;
    }
    
    //REVIEW COMMENT
    public String getComment() {
    	return comment;
    }

    public void setComment(String comment) {
    	this.comment = comment;
    }
}

