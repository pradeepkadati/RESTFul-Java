/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kadati.rest.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.kadati.rest.messenger.database.MessengerDB;
import org.kadati.rest.messenger.exception.DataNotFoundException;
import org.kadati.rest.messenger.model.Message;

/**
 *
 * @author kadat
 */
public class MessengerService {
    
    private Map<Long,Message> messages = MessengerDB.getMessages();
    
    public MessengerService(){
        
     messages.put(1L, new Message(1, "Hello World", "kadati"));
     messages.put(2L, new Message(2, "Hello Jersey", "kadati"));
    }
    
    public List<Message> getAllMessages(){
       List<Message> message = new ArrayList<>();
       message.addAll(messages.values());
       return message;
    }
    
    public List<Message> getAllMessagesByYear(int year){
        List<Message> message = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message msg:messages.values()) {
        	cal.setTime(msg.getCreated());
        	if (cal.get(Calendar.YEAR) == year) {
        		message.add(msg);
        	}
        }
        return message;
     }
    
    public List<Message> getFilteredMessages(int start,int size){
    	  List<Message> message = new ArrayList<>(messages.values());
    	  
    	  if (start+size > message.size()) {
    		  return new ArrayList<>();
    	  }
        return message.subList(start, start+size);
     }
    
    
	public Message getMessages(long id){
    	
      Message messsage = messages.get(id);
      if (messsage == null) {
    	  throw new DataNotFoundException("Message with id "+ id+" not found ");
      }
      
      return messages.get(id);
    }
    
    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }
    
     public Message updateMessage(Message message){
        
         if (message.getId() <=0){
             return null;
         }
         messages.put(message.getId(), message);
        return message;
    } 
     
     
    public Message removeMessage(Long id){
        return messages.remove(id);
    }
}
