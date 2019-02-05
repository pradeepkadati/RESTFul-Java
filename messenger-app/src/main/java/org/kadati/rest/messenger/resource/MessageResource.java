/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kadati.rest.messenger.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.kadati.rest.messenger.model.Message;
import org.kadati.rest.messenger.service.MessengerService;

/**
 *
 * @author kadati
 */
@Path("/messages")
public class MessageResource {
   
   private MessengerService service = new MessengerService();
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Message> getMessages(@BeanParam MessageFilterBean msgFilterBean  /*@QueryParam("year") int year,
		   							@QueryParam("start") int start,
		   							@QueryParam("size") int size*/){
       if(msgFilterBean.getYear() > 0) {
    	   return service.getAllMessagesByYear(msgFilterBean.getYear() );
       }
       if(msgFilterBean.getStart()>=0 && msgFilterBean.getSize()> 0) {
    	   return service.getFilteredMessages(msgFilterBean.getStart(),  msgFilterBean.getSize());
       }
      return  service.getAllMessages();
    }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/{messageId}")
   public Message getJsonMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
	   System.out.println("JSON message is called");
	   Message message = service.getMessages(messageId);
	   message.addLink(getUriforSelf(uriInfo, message), "self");
	   message.addLink(getUriforProfile(uriInfo, message), "profile");
	   message.addLink(getUriforComments(uriInfo, message), "comments");
	return  message;
    }
   
   @GET
   @Produces(MediaType.TEXT_XML)
   @Path("/{messageId}")
   public Message getXmlMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
	   System.out.println("XML message is called");
	   Message message = service.getMessages(messageId);
	   message.addLink(getUriforSelf(uriInfo, message), "self");
	   message.addLink(getUriforProfile(uriInfo, message), "profile");
	   message.addLink(getUriforComments(uriInfo, message), "comments");
	return  message;
    }

	private String getUriforComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class, "getCommentResource").resolveTemplate("messageId", message.getId()).build()
				.toString();
}

	private String getUriforSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId())).build()
				.toString();
	}
   
	private String getUriforProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(message.getAuthor())).build()
				.toString();
	}
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message, @Context UriInfo uriInfo){
    	Message newMessage = service.addMessage(message);
    	URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
    	return Response.created(uri).entity(newMessage).build();
    }


   @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/{messageId}")
   public Message updateMessage(@PathParam("messageId") long messageId,Message message){
      message.setId(messageId);
      return service.updateMessage(message);
   }

   @DELETE
   @Path("/{messageId}")
   public void removeMessage(@PathParam("messageId") long id){
      service.removeMessage(id);
   }
   
   @Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
