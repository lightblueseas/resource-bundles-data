package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.service.api.BusinessService;

public interface PropertiesKeysService  extends BusinessService<PropertiesKeys, Integer> {
	

	/**
	 * Find the {@link PropertiesKeys} object from the given properties key.
	 * 
	 * @param propertiesKey the properties key
	 * @return the found {@link PropertiesKeys} object or null if not.
	 */
	PropertiesKeys find(final String propertiesKey);
}
