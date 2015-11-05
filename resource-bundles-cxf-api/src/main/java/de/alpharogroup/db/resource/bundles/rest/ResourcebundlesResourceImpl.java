package de.alpharogroup.db.resource.bundles.rest;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.mapper.ResourcebundleService;
import de.alpharogroup.db.service.rs.AbstractRestfulResource;

public class ResourcebundlesResourceImpl
	extends AbstractRestfulResource<Integer, Resourcebundle, ResourcebundleService>
	implements ResourcebundlesResource
{

	public Resourcebundle get(String id)
	{
		Resourcebundle resourcebundle = getBusinessMapperService().read(Integer.valueOf(id));
		return resourcebundle;
	}

	public Resourcebundle find(String baseName, String locale, String key)
	{
		Resourcebundle resourcebundle = getBusinessMapperService().find(baseName, locale, key);
		return resourcebundle;
	}

}
