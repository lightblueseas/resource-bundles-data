package de.alpharogroup.db.resource.bundles.service.api;

import java.util.List;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface BundleNamesService  extends BusinessService<BundleNames, Integer> {

	/**
	 * Find all {@link BundleNames} objects from the given baseName and locale.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 *            
	 * @return the list of found {@link BaseNames} objects or null if not.
	 */
	List<BundleNames> find(final String baseName, final String locale);

	/**
	 * Find all {@link BundleNames} objects from the given {@link BaseNames} object.
	 *
	 * @param baseName
	 *            the {@link BaseNames} object
	 *            
	 * @return the list of found {@link BundleNames} objects or null if not.
	 */
	List<BundleNames> find(BaseNames baseName);

	/**
	 * Find the {@link BundleNames} object from the given baseName and languageLocales.
	 *
	 * @param baseName
	 *            the base name
	 * @param languageLocales
	 *            the languageLocales
	 *            
	 * @return the found {@link BaseNames} object or null if not.
	 */
	BundleNames find(BaseNames baseName, LanguageLocales languageLocales);
	
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
