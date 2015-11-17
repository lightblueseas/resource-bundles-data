package de.alpharogroup.db.resource.bundles.rest;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.domain.api.ResourcebundleService;
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
	public Resourcebundle get(String id)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		Resourcebundle resourcebundle = resourcebundleService.read(Integer.valueOf(id));
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(String baseName, String locale, String key)
	{
		Resourcebundle resourcebundle = getDomainService().find(baseName, locale, key);
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(String baseName, String locale, String key) {
		final ResourcebundleService resourcebundleService = getDomainService();
		return resourcebundleService.getString(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(String baseName, String locale, String key, String[] params) {
		final ResourcebundleService resourcebundleService = getDomainService();
		return resourcebundleService.getString(baseName, locale, key, params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(BundleKey key) {
		final ResourcebundleService resourcebundleService = getDomainService();
		return resourcebundleService.getString(key);
	}

}
