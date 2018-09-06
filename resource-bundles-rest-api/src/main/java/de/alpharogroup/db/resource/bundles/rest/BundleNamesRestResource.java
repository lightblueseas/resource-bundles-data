package de.alpharogroup.db.resource.bundles.rest;

import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.rest.api.BundleNamesResource;
import de.alpharogroup.db.resource.bundles.service.api.BundleNameService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link BundleNamesRestResource} provides an implementation of the inteface
 * {@link BundleNamesResource}
 */
public class BundleNamesRestResource
	extends
		AbstractRestfulResource<java.lang.Integer, BundleName, BundleNameService>
	implements
		BundleNamesResource
{
}