package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The rest interface {@link LanguagesResource}
 */
@Path("/language/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface LanguagesResource extends RestfulResource<Integer, Language>
{

	/**
	 * Find all {@link Language} objects.
	 *
	 * @return the list of {@link Language} objects.
	 */
	@GET
	@Path("/find/all")
	Response findAll();

	/**
	 * Find the {@link Language} object by name.
	 *
	 * @param name
	 *            the name
	 * @return the language
	 */
	@GET
	@Path("/find/by/name/{name}")
	Response findByName(@PathParam("name")String name);

	/**
	 * Find the {@link Language} object by code.
	 *
	 * @param code
	 *            the code
	 * @return the language
	 */
	@GET
	@Path("/find/by/code/{code}")
	Response findByCode(@PathParam("code")String code);


}
