package org.kadati.rest.messenger.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kadati.rest.messenger.model.Message;

public class MessengerClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		Response response = client.
				target("http://localhost:8080/messenger-app/webapi/messages/2").
				request(MediaType.APPLICATION_JSON).
				get();
		
		Message message = response.readEntity(Message.class);
		
		System.out.println(message.getMessage());
	}

}
