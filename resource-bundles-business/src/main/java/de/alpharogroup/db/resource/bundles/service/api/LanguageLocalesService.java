package de.alpharogroup.db.resource.bundles.service.api;

import java.util.Locale;

import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface LanguageLocalesService  extends BusinessService<LanguageLocales, Integer> {

	/**
	 * Find the {@link LanguageLocales} object from the given locale.
	 *
	 * @param locale
	 *            the locale
	 *            
	 * @return the found {@link LanguageLocales} object or null if not.
	 */
	LanguageLocales find(final String locale);

	/**
	 * Find the {@link LanguageLocales} object from the given locale.
	 *
	 * @param locale
	 *            the locale
	 *            
	 * @return the found {@link LanguageLocales} object or null if not.
	 */
	LanguageLocales find(Locale locale);
	
}
