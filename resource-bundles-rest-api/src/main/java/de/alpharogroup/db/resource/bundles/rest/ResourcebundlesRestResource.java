package de.alpharogroup.db.resource.bundles.rest;

import java.util.Properties;

import javax.ws.rs.core.Response;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundleService;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link ResourcebundlesRestResource} provides an implementation of the inteface {@link ResourcebundlesResource}.
 */
public class ResourcebundlesRestResource
	extends AbstractRestfulResource<Integer, Resourcebundle, ResourcebundleService>
	implements ResourcebundlesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle get(final String id)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final Resourcebundle resourcebundle = resourcebundleService.read(Integer.valueOf(id));
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(final String baseName, final String locale, final String key)
	{
		final Resourcebundle resourcebundle = getDomainService().find(baseName, locale, key);
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getString(final String baseName, final String locale, final String key) {
		final ResourcebundleService resourcebundleService = getDomainService();
		final String result = resourcebundleService.getString(baseName, locale, key);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getString(final String baseName, final String locale, final String key, final String[] params) {
		final ResourcebundleService resourcebundleService = getDomainService();
		final String result = resourcebundleService.getString(baseName, locale, key, params);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getString(final BundleKey key) {
		final ResourcebundleService resourcebundleService = getDomainService();
		final String result = resourcebundleService.getString(key);
		return Response.ok(KeyValuePair.builder().key(key.getResourceBundleKey().getKey()).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getResponseString(final String baseName, final String locale, final String key) {
		final ResourcebundleService resourcebundleService = getDomainService();
		final String result = resourcebundleService.getString(baseName, locale, key);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getProperties(final String baseName, final String locale)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final Properties properties = resourcebundleService.getProperties(baseName, locale);
		return Response.ok(properties).build();
	}

}
