/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kadati.rest.messenger.database;

import java.util.HashMap;
import java.util.Map;
import org.kadati.rest.messenger.model.Message;
import org.kadati.rest.messenger.model.Profile;

/**
 *
 * @author kadat
 */
public class MessengerDB {
    
    private static final Map<Long,Message> messages = new HashMap<>();
    private static final Map<Long,Profile> profiles = new HashMap<>();
    
    public static Map<Long,Message> getMessages(){
        return messages;
    }
    
    public static Map<Long,Profile> getProfiles(){
        return profiles;
    }
}
