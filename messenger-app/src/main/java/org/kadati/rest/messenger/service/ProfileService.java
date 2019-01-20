package org.kadati.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kadati.rest.messenger.database.MessengerDB;
import org.kadati.rest.messenger.model.Profile;

public class ProfileService {

    private Map<String, Profile> profiles = MessengerDB.getProfiles();

    public ProfileService(){

        profiles.put("kadati", new Profile(1,"kadati", "pradeep", "kadati"));
        profiles.put("pradeep", new Profile(2,"pradeep", "pradeep kumar", "kadati"));
    }

    public List<Profile> getAllProfiles(){
        List<Profile> profile = new ArrayList<>();
        profile.addAll(profiles.values());
        return profile;
    }

    public Profile getProfile(String profileName){
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile){

        if (profile.getProfileName() == null){
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }


    public Profile removeProfile(String profileName){
        return profiles.remove(profileName);
    }
}
