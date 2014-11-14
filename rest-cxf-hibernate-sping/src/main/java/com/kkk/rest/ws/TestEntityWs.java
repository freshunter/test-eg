package com.kkk.rest.ws;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kkk.rest.model.TestEntity;

@Path("/testentity")
public interface TestEntityWs{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TestEntity> get();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public TestEntity get(@PathParam("id") Long id);
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TestEntity create(TestEntity profile);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public TestEntity update(TestEntity profile);

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id);
}
