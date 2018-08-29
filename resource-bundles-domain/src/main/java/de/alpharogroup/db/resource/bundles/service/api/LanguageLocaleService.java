package de.alpharogroup.db.resource.bundles.service.api;

import java.util.Locale;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.service.domain.DomainService;

/**
 * The interface {@link LanguageLocaleService}
 */
public interface LanguageLocaleService extends DomainService<Integer, LanguageLocale>
{

	/**
	 * Find the {@link LanguageLocale} object from the given locale.
	 *
	 * @param locale
	 *            the locale
	 *
	 * @return the found {@link LanguageLocale} object or null if not.
	 */
	LanguageLocale find(Locale locale);

	/**
	 * Find the {@link LanguageLocale} object from the given locale.
	 *
	 * @param locale
	 *            the locale
	 *
	 * @return the found {@link LanguageLocale} object or null if not.
	 */
	LanguageLocale find(final String locale);

	/**
	 * Gets the or creates a new {@link LanguageLocale} object.
	 *
	 * @param locale
	 *            the locale
	 * @return the {@link LanguageLocale} object
	 */
	LanguageLocale getOrCreateNewLanguageLocales(final Locale locale);

	/**
	 * Resolves the {@link Locale} object from the given {@link LanguageLocale}.
	 *
	 * @param languageLocales
	 *            the language locales
	 * @return the {@link Locale} object.
	 */
	Locale resolveLocale(LanguageLocale languageLocales);
}
