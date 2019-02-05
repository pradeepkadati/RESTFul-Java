package org.kadati.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kadati.rest.messenger.database.MessengerDB;
import org.kadati.rest.messenger.model.Comment;
import org.kadati.rest.messenger.model.ErrorMessage;
import org.kadati.rest.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = MessengerDB.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
	    Message message = messages.get(messageId);
	    ErrorMessage errorMessage = new ErrorMessage("Message not Found",404,"www.google.com");
    	Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	    if (message == null) {
	    	
	    	throw new WebApplicationException(response);
	    }
		Map<Long, Comment> comments =message.getComments();
		
		 if (comments == null || comments.isEmpty()) {
			throw new NotFoundException(response);
		 }
		Comment comment = comments.get(commentId);
		
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
		
}
