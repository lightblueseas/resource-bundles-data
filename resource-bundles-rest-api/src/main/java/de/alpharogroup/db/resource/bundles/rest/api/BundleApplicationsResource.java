/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
import de.alpharogroup.service.rs.api.RestfulResource;
import lombok.NonNull;

/**
 * The interface {@link BundleApplicationsResource} provides rest methods
 */
@Path("/bundle/applications/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BundleApplicationsResource extends RestfulResource<Integer, BundleApplication>
{

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
	 * Find all {@link BundleApplication} objects
	 *
	 * @return the {@link Response} object with all {@link BundleApplication} objects
	 */
	@GET
	@Path("/find/all")
	Response findAll();

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
}