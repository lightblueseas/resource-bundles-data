package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.service.rs.RestfulResource;

@Path("/resourcebundle/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ResourcebundlesResource extends RestfulResource<Integer, Resourcebundle>
{

    @GET
    @Path("/get/{id}")  
	Resourcebundle get(@PathParam("id")String id);

    @GET
    @Path("/find/{basename}/{locale}/{key}") 
	Resourcebundle find(@PathParam("basename")String baseName, @PathParam("locale")String locale, @PathParam("key")String key);

}