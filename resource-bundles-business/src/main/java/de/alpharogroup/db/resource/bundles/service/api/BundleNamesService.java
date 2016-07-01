package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.service.api.BusinessService;

public interface BundleNamesService  extends BusinessService<BundleNames, Integer> {

	/**
	 * Find the {@link BundleNames} object from the given baseName and locale.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 *            
	 * @return the found {@link BaseNames} object or null if not.
	 */
	BundleNames find(final String baseName, final String locale);
}
