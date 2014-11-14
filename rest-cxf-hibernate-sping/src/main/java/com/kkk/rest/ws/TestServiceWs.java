package com.kkk.rest.ws;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.kkk.rest.model.ServiceEntity;

@Path("/service")
public interface TestServiceWs {
      
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ServiceEntity> get(@QueryParam("id") Long serviceID);
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceEntity create(ServiceEntity service);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{service-id}")
    public ServiceEntity update(ServiceEntity service, @PathParam("service-id") Long serviceID);

    @DELETE
    @Path("/{service-id}")
    public void delete(@PathParam("service-id") Long serviceID);
}
