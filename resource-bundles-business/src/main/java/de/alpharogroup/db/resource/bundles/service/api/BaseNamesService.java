package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.service.api.BusinessService;

public interface BaseNamesService  extends BusinessService<BaseNames, Integer> {
	

	/**
	 * Find the {@link BaseNames} object from the given baseName.
	 *
	 * @param baseName
	 *            the base name
	 *            
	 * @return the found {@link BaseNames} object or null if not.
	 */
	BaseNames find(final String baseName);
}
