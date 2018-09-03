package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.service.rs.RestfulResource;
import lombok.NonNull;

/**
 * The interface {@link BundleApplicationsResource} provides rest methods
 */
@Path("/bundle/applications/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BundleApplicationsResource
	extends
		RestfulResource<Integer, BundleApplication>
{

	/**
	 * Find all {@link BundleName} objects from the given name of the owner(BundleApplication).
	 *
	 * @param owner
	 *            the owner
	 * @return the list of found {@link BundleName} objects or an empty set if not.
	 */
	@GET
	@Path("/find/all/bundlenames/{owner}")
	Response findAllBundleNames(final @PathParam("owner") String owner);

	/**
	 * Find the {@link BundleApplication} object from the given name.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the found {@link BundleApplication} object or null if not.
	 */
	@GET
	@Path("/find/by/name/{name}")
	Response find(final @PathParam("name") String name);

	/**
	 * Find the {@link BundleApplication} object from the given {@link BundleName} object.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @return the found {@link BundleApplication} object
	 */
	@POST
	@Path("/find/by/bundlename")
	Response get(BundleName bundleName);

	/**
	 * Gets the {@link BundleApplication} object from the given name or creates a new
	 * {@link BundleApplication} object if not found.
	 *
	 *
	 * @param name
	 *            the name
	 * @param defaultlocale
	 *            the default locale
	 * @return the existing or a new {@link BundleApplication} object
	 */
	@GET
	@Path("/get/or/create/{name}/{defaultlocale}")
	Response getOrCreateNewBundleApplications(@NonNull @PathParam("name") final String name,
		@NonNull @PathParam("defaultlocale") final String defaultlocale);

	/**
	 * Gets the {@link BundleApplication} object from the given search criteria
	 * {@link BundleApplication} object or create a new one if not found.
	 *
	 * @param searchCriteria
	 *            the search criteria
	 * @return the existing or a new {@link BundleApplication} object
	 */
	@POST
	@Path("/get/or/create")
	Response getOrCreateNewBundleApplications(@NonNull final BundleApplication searchCriteria);
}