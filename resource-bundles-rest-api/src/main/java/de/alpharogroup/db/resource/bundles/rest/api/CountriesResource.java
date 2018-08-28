package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/country/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountriesResource extends RestfulResource<Integer, Country>
{

	/**
	 * Find all {@link Country} objects.
	 *
	 * @return the list of {@link Country} objects.
	 */
	@GET
	@Path("/find/all")
	Response findAll();

	/**
	 * Find the {@link Country} object by name.
	 *
	 * @param name
	 *            the name
	 * @return the countries
	 */
	@GET
	@Path("/find/by/name/{iso3166A2name}")
	Response find(@PathParam("iso3166A2name")String name);


}
