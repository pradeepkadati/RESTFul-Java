package org.kadati.rest.messenger.resource;

import org.kadati.rest.messenger.model.Profile;
import org.kadati.rest.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private ProfileService service = new ProfileService();

    @GET
    public List<Profile> getProfiles(){
         System.out.println(this.hashCode());
        return  service.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){

        return  service.getProfile(profileName);
    }
    @POST
    public Profile addProfile(Profile profile){
        return service.addProfile(profile);
    }


    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile){
        profile.setProfileName(profileName);
        return service.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public void removeProfile(@PathParam("profileName") String profileName){
        service.removeProfile(profileName);
    }
}
