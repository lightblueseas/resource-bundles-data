package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The interface {@link LanguageLocalesResource} provides rest methods
 */
@Path("/language/locale/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface LanguageLocalesResource extends RestfulResource<Integer, LanguageLocale>
{

	/**
	 * Find all {@link LanguageLocale} objects.
	 *
	 * @return the list of {@link LanguageLocale} objects.
	 */
	@GET
	@Path("/find/all")
	Response findAll();
	
	/**
	 * Find the {@link LanguageLocale} object from the given locale.
	 *
	 * @param locale
	 *            the locale
	 *
	 * @return the found {@link LanguageLocale} object or null if not.
	 */
	@GET
	@Path("/find/by/locale/{locale}")
	Response find(final String locale);
	
}