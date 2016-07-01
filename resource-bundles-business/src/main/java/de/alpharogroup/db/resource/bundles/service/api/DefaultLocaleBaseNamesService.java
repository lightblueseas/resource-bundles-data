package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.service.api.BusinessService;

public interface DefaultLocaleBaseNamesService  extends BusinessService<DefaultLocaleBaseNames, Integer> {

	/**
	 * Find the {@link DefaultLocaleBaseNames} object from the given baseName and default locale.
	 *
	 * @param baseName
	 *            the base name
	 * @param defaultLocale
	 *            the default locale
	 *            
	 * @return the found {@link DefaultLocaleBaseNames} object or null if not.
	 */
	DefaultLocaleBaseNames find(final String baseName, final String defaultLocale);
}