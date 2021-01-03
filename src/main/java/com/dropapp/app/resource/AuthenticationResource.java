package com.dropapp.app.resource;

import com.dropapp.app.model.Login;
import com.dropapp.app.model.Response;
import com.dropapp.app.repository.UserRepository;
import com.dropapp.app.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("")
@Produces({MediaType.APPLICATION_JSON})
public class AuthenticationResource {

    private AuthenticationService authService;

    private UserRepository userRepository;

    public AuthenticationResource(AuthenticationService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
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

    @Path("test")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Login test(Login login, @Context HttpServletRequest httpServletRequest) {
        //String header = httpServletRequest.getHeader("Authorization");
        return login;
    }

}