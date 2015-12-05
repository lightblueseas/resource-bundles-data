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
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The interface {@link ResourcebundlesResource} provides methods for resolving resource bundles.
 */
@Path("/resourcebundle/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ResourcebundlesResource extends RestfulResource<Integer, Resourcebundle>
{

    /**
     * Gets the {@link Resourcebundle} from the given id.
     *
     * @param id the id
     * @return the {@link Resourcebundle}
     */
    @GET
    @Path("/get/{id}")
	Resourcebundle get(@PathParam("id")String id);

    /**
     * Find the {@link Resourcebundle} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @return the {@link Resourcebundle}
     */
    @GET
    @Path("/find/{basename}/{locale}/{key}")
	Resourcebundle find(@PathParam("basename")String baseName, @PathParam("locale")String locale, @PathParam("key")String key);

    /**
     * Find the {@link String} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @return the {@link String}
     */
    @GET
    @Path("/get/string/{basename}/{locale}/{key}")
    Response getString(@PathParam("basename")String baseName, @PathParam("locale")String locale, @PathParam("key")String key);

    /**
	 * Find the {@link String} from the given arguments.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the {@link Response} object with the
	 */
    @GET
    @Path("/get/string/{basename}/{locale}/{key_and_parameters}/parameters")
    Response getString(@PathParam("basename")String baseName, @PathParam("locale")String locale, @PathParam("key_and_parameters")String key, @QueryParam("parameter")final String[] params);

    /**
	 * Gets the resource {@link String} from the given {@link BundleKey}.
	 *
	 * @param bundleKey
	 *            the bundle key
	 * @return the {@link Response} object
	 */
    @POST
    @Path("/get/resourcebundle/value")
    Response getString(BundleKey key);


    /**
	 * Find the {@link String} from the given arguments.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the found {@link KeyValuePair} object as a {@link Response} object.
	 */
    @GET
    @Path("/get/r/string/{basename}/{locale}/{key}")
    Response getResponseString(@PathParam("basename")String baseName, @PathParam("locale")String locale, @PathParam("key")String key);



	/**
	 * Get the {@link Properties} object from the given baseName and the given {@link Locale}
	 * object.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object as a {@link Response} object.
	 */
	@GET
	@Path("/get/properties/{basename}/{locale}")
	Response getProperties(@PathParam("basename") String baseName,
		@PathParam("locale") String locale);


}