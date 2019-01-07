/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kadati.rest.messenger;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.kadati.rest.messenger.model.Message;
import org.kadati.rest.messenger.service.MessengerService;

/**
 *
 * @author kadat
 */
@Path("/messages")
public class MessageResource {
   
   private MessengerService service = new MessengerService();
   
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public List<Message> getMessages(){
        
      return  service.getAllMessages();
    }
   
   @GET
   @Produces(MediaType.APPLICATION_XML)
   @Path("{messageId}")
   public Message getMessage(@PathParam("messageId") long messageId){
        
      return  service.getMessages(messageId);
    }
}
