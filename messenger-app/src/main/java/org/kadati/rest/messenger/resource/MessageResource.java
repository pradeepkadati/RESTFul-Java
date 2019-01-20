/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kadati.rest.messenger.resource;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
   public Message getMessage(@PathParam("messageId") long messageId){
        
      return  service.getMessages(messageId);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message addMessage(Message message){
      return service.addMessage(message);
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

}
