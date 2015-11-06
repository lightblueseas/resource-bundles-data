package de.alpharogroup.db.resource.bundles.rest;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.mapper.api.ResourcebundleService;
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
	public Resourcebundle get(String id)
	{
		final ResourcebundleService resourcebundleService = getBusinessMapperService();
		Resourcebundle resourcebundle = resourcebundleService.read(Integer.valueOf(id));
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	public Resourcebundle find(String baseName, String locale, String key)
	{
		Resourcebundle resourcebundle = getBusinessMapperService().find(baseName, locale, key);
		return resourcebundle;
	}

}
