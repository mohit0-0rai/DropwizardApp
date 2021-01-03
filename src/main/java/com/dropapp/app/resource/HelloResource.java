package com.dropapp.app.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hello")
public class HelloResource {

    @Path("{name}")
    @GET
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
