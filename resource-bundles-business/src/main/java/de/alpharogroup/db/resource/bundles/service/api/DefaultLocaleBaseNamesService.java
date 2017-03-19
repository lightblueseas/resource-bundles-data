package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface DefaultLocaleBaseNamesService  extends BusinessService<DefaultLocaleBaseNames, Integer> {

	/**
	 * Find the {@link DefaultLocaleBaseNames} object from the given baseName.
	 *
	 * @param baseName
	 *            the base name
	 *            
	 * @return the found {@link DefaultLocaleBaseNames} object or null if not.
	 */
	DefaultLocaleBaseNames find(final String baseName);
	
	/**
	 * Gets the default locale from the given {@link BaseNames} object.
	 *
	 * @param bundleNames the bundle names
	 * @return the default locale from the given {@link BaseNames} object or null if not set.
	 */
	LanguageLocales getDefaultLocale(BundleNames bundleNames);

	/**
	 * Gets the default locale from the given base name as {@link String} object.
	 *
	 * @param baseName the base name
	 * @return the default locale from the given base name as {@link String} object or null if not set.
	 */
	LanguageLocales getDefaultLocale(String baseName);
}
