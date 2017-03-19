package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.service.api.BusinessService;

public interface BundleApplicationsService  extends BusinessService<BundleApplications, Integer> {
	
	/**
	 * Find the {@link BundleApplications} object from the given name.
	 *
	 * @param name
	 *            the name
	 *            
	 * @return the found {@link BundleApplications} object or null if not.
	 */
	BundleApplications find(final String name);
}
