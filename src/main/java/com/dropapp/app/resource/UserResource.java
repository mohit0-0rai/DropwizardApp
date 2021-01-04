package com.dropapp.app.resource;

import com.dropapp.app.model.Response;
import com.dropapp.app.repository.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Path("")
    @GET
    public Response getAllUsers() {
        Response response = new Response();
        try {
            response.setCode(200);
            response.setMessage("Success");
            Map<String, Object> data = new HashMap<>();
            data.put("users", userRepository.getUsers());
            response.setData(data);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Server Error, Please try after some time.");
        }
        return response;
    }

    @Path("{id}")
    @GET
    public Response getUser(@PathParam("id") String id) {
        Response response = new Response();
        try {
            response.setCode(200);
            response.setMessage("Success");
            Map<String, Object> data = new HashMap<>();
            data.put("user", userRepository.getUser(Integer.parseInt(id)));
            response.setData(data);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Server Error, Please try after some time.");
        }
        return response;
    }

    @Path("update")
    @PUT
    public Response updateUser() {
        Response response = new Response();
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }
}
