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

import java.util.Locale;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.service.rs.api.RestfulResource;

/**
 * The interface {@link ResourcebundlesResource} provides methods for resolving resource bundles.
 */
@Path(ResourcebundlesResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ResourcebundlesResource extends RestfulResource<Integer, Resourcebundle>
{

	public static final String PATH = "/resourcebundle/";

	/**
	 * Find the {@link Resourcebundle} from the given arguments.
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the {@link Resourcebundle}
	 */
	@GET
	@Path("/find/{bundleappname}/{basename}/{locale}/{key}")
	Resourcebundle find(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale,
		@PathParam("key") String key);

	/**
	 * Find resource bundles from the given parameters.
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the {@link Response} object
	 */
	@GET
	@Path("/find/resourcebundles/{bundleappname}/{basename}/{locale}")
	Response findResourceBundles(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale);

	/**
	 * Gets the {@link Resourcebundle} from the given id.
	 *
	 * @param id
	 *            the id
	 * @return the {@link Resourcebundle}
	 */
	@GET
	@Path("/get/{id}")
	Resourcebundle get(@PathParam("id") String id);

	/**
	 * Gets the bundle application over the given name
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @return the bundle app
	 */
	@GET
	@Path("/get/r/app/{bundleappname}")
	Response getBundleApp(@PathParam("bundleappname") String bundleappname);

	/**
	 * Gets the or creates a new {@link BundleName} object
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the or create new bundle names
	 */
	@GET
	@Path("/get/or/create/bundlename/{bundleappname}/{basename}/{locale}")
	Response getOrCreateNewBundleName(@PathParam("bundleappname") String bundleappname,
		final @PathParam("basename") String baseName, final @PathParam("locale") String locale);

	/**
	 * Get the {@link Properties} object from the given baseName and the given {@link Locale} object
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object as a {@link Response} object
	 */
	@GET
	@Path("/get/properties/{bundleappname}/{basename}/{locale}")
	Response getProperties(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale);

	/**
	 * Find the {@link String} from the given arguments.
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the found {@link KeyValuePair} object as a {@link Response} object.
	 */
	@GET
	@Path("/get/r/string/{bundleappname}/{basename}/{locale}/{key}")
	Response getResponseString(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale,
		@PathParam("key") String key);

	/**
	 * Find the {@link String} from the given arguments.
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the {@link String}
	 */
	@GET
	@Path("/get/string/{bundleappname}/{basename}/{locale}/{key}")
	Response getString(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale,
		@PathParam("key") String key);

	/**
	 * Find the {@link String} from the given arguments.
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param params
	 *            the parameters
	 * @return the {@link Response} object with the {@link String}
	 */
	@GET
	@Path("/get/string/{bundleappname}/{basename}/{locale}/{key_and_parameters}/parameters")
	Response getString(@PathParam("bundleappname") String bundleappname,
		@PathParam("basename") String baseName, @PathParam("locale") String locale,
		@PathParam("key_and_parameters") String key,
		@QueryParam("parameter") final String[] params);

	/**
	 * Save or update the given resource bundle entry
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the {@link Response} object
	 */
	@GET
	@Path("/save/or/update/resourcebundle/{bundleappname}/{basename}/{locale}/{key}/{value}")
	Response saveOrUpdateEntry(@PathParam("bundleappname") String bundleappname,
		final @PathParam("basename") String baseName, final @PathParam("locale") String locale,
		final @PathParam("key") String key, final @PathParam("value") String value);

	/**
	 * Update the given {@link Properties} object to the underlying database with the given owner
	 * and the given baseName and the given {@link Locale} object that is encapsulated in the given
	 * {@link Quattro} object
	 *
	 * @param quattro
	 *            the quattro
	 * @return the {@link Response} object with the updated {@link BundleName} object
	 */
	@POST
	@Path("/update/bundlename")
	Response updateProperties(Quattro<Properties, String, String, Locale> quattro);

}