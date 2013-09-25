package edu.sjsu.cmpe.library.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;
import java.util.Collection;
//import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;


//import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookResource {

	static int counter=0;
//	static int reviewIdCounter=0;
//	static int authorIdCounter=0;
    private static HashMap<Integer,Book> bookhashmap=new HashMap<Integer,Book>();
    
    public BookResource() {
    }

    //---------CREATING A BOOK----------
    @POST
    @Timed(name = "create-book")
    public Response createBook(@Valid Book book) {
		counter++;	
		book.setIsbn(counter);
		bookhashmap.put(counter, book);
		LinksDto bookResponse = new LinksDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("create-book","/books/" + book.getIsbn(), "POST"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		return Response.status(201).entity(bookResponse).build();	
    }
    
  //---------VIEWING A BOOK----------	
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto viewBook(@PathParam("isbn") int isbn) {
    	
    	Book book = bookhashmap.get(isbn);
		BookDto bookResponse=new BookDto(book);
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn(), "POST"));
		bookResponse.addLink(new LinkDto("view-all-review","/books/" + book.getIsbn(), "PUT"));
		return bookResponse;
    } 
       
  //---------DELETING A BOOK----------
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public BookDto deleteBook(@PathParam("isbn") int isbn) {
	    Book book = bookhashmap.remove(isbn);
		BookDto bookResponse=new BookDto(book);
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn(), "POST"));
		return bookResponse;
    }
    
	  //---------UPDATING A BOOK----------    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@QueryParam("status") String status, @PathParam("isbn") int isbn) {
		Book book=bookhashmap.get(isbn);
		book.setStatus(status);
		LinksDto bookResponse = new LinksDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn() +"/reviews", "GET"));
		System.out.println("-----------HashMap Size---------- :"+ bookhashmap.size());
		return Response.status(201).entity(bookResponse).build();
    }
    
  //---------CREATING A BOOK REVIEW----------
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "review-book")
    public Response createBookReview(@PathParam("isbn") int isbn) {
    	System.out.println(isbn);
    	System.out.println(bookhashmap.get(isbn));
    	Book book=bookhashmap.get(isbn);
    	Reviews review =new Reviews();
    	ArrayList<Reviews> rev=book.addReviews(review);
    	LinksDto bookResponse = new LinksDto();
		bookResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn() + "/reviews", "GET"));
		return Response.status(201).entity(rev).build();	
    }  
    
  //---------VIEWING A BOOK REVIEW----------	
  @GET
  @Path("/{isbn}/reviews/{id}")
  @Timed(name = "view-review")
  public BookDto viewBookReview(@PathParam("id") int id, @PathParam("isbn") int isbn) {
  		Book book=bookhashmap.get(isbn);
	    Reviews review = new Reviews();
	    BookDto bookResponse=new BookDto(review);
		bookResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn() + "/reviews/" + review.getReviewId(),"GET"));
		return bookResponse;
  }
  
//---------VIEWING BOOK REVIEW ALL----------	
  @GET
  @Path("/{isbn}/reviews")
  @Timed(name = "view-review")
  public BookDto viewBookReviewAll(@PathParam("isbn") int isbn) {
  		Book book=bookhashmap.get(isbn);
	    Reviews reviews = new Reviews();
	    ArrayList<Reviews> review=book.getReviews();
	    BookDto bookResponse=new BookDto(review);
		//bookResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn() + "/reviews/" + reviews.getReviewId(),"GET"));
		return bookResponse;
  }
    
//    //---------VIEW BOOK AUTHOR API--------
//  @GET
//  @Path("/{isbn}/authors/{id}")
//  @Timed(name = "view-author")
//  public BookDto viewBookAuthor(@PathParam("id") int id, @PathParam("isbn") int isbn) {
//  		Book book=bookhashmap.get(isbn);
//	    Author author = new Author();
//	    ArrayList<Author> author=book.getAuthors(id);
//	    BookDto bookResponse=new BookDto(author);
//		bookResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn() + "/reviews/" + author.getAuthorId(),"GET"));
//		return bookResponse;
//  }
  
  
   //--------VIEW ALL OF AUTHORS OF BOOK API--------
//  @GET
//  @Path("/{isbn}/author")
//  @Timed(name = "view-author")
//  public BookDto viewBookAuthorAll(@PathParam("isbn") int isbn) {
//  		Book book=bookhashmap.get(isbn);
//	    //Reviews reviews = new Reviews();
//	    ArrayList<Reviews> review=book.getReviews();
//	    BookDto bookResponse=new BookDto(review);
//		bookResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn() + "/reviews/" + review.getReviewId(),"GET"));
//		return bookResponse;
//  }
         
    
    	
}
