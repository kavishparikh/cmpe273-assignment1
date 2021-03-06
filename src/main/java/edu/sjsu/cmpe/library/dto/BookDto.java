package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private Book book;
	
    /**
     * @param book
     */
    public BookDto(Book book) {
	super();
	this.book = book;
    }

   	
	/**
     * @return the book
     */
    public Book getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book book) {
	this.book = book;
    }
    
    public BookDto(Reviews reviews) {
	super();
	}
    
    public BookDto(ArrayList<Reviews> review) {
    	super();
	}

    /**
     * @return the book
     */
    public Book getReviews() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setReviews(Reviews reviews) {
    }
}
