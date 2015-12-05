package de.alpharogroup.db.resource.bundles.service.api;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.service.jpa.BusinessService;

/**
 * The interface {@link ResourcebundlesService}.
 */
public interface ResourcebundlesService extends BusinessService<Resourcebundles, Integer>
{

	/**
	 * Find a list of {@link Resourcebundles} objects from the given baseName and the given
	 * {@link Locale} object.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the list of the found {@link Resourcebundles} objects.
	 */
	List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale);

	/**
	 * Find a list of {@link Resourcebundles} objects from the given baseName, the given
	 * {@link Locale} object and the given properties key.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the properties key
	 * @return the list of the found {@link Resourcebundles} objects.
	 */
	List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale,
		final String key);

	/**
	 * Gets the {@link Resourcebundles} object entry from the the given baseName, the given
	 * {@link Locale} object and the given properties key.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the properties key
	 * @return the found {@link Resourcebundles} object
	 */
	Resourcebundles getResourcebundle(final String baseName, final Locale locale, final String key);

	/**
	 * Checks if a {@link Resourcebundles} object exists from the given baseName, the given
	 * {@link Locale} object and the given properties key.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the properties key
	 * @return the found {@link Resourcebundles} object or null if it does not exists.
	 */
	Resourcebundles contains(final String baseName, final Locale locale, final String key);

	/**
	 * Update the given {@link Properties} object to the underlying database with the given baseName
	 * and the given {@link Locale} object.
	 *
	 * @param properties
	 *            the properties
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 */
	void updateProperties(final Properties properties, final String baseName, final Locale locale);

	/**
	 * Update the given {@link Properties} object to the underlying database with the given baseName
	 * and the given {@link Locale} object.
	 *
	 * @param properties
	 *            the properties
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param update
	 *            flag that indicates if an existing property shell be updated
	 */
	void updateProperties(final Properties properties, final String baseName, final Locale locale,
		final boolean update);

	/**
	 * Find a list of {@link Resourcebundles} objects from the given baseName, the given locale as
	 * String, the given properties key and the given value that can be null if it shell be ignored.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the list of the found {@link Resourcebundles} objects.
	 */
	List<Resourcebundles> find(final String baseName, final String locale, final String key,
		final String value);

	/**
	 * Get the {@link Properties} object from the given baseName and the given {@link Locale}
	 * object.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object.
	 */
	Properties getProperties(final String baseName, final Locale locale);

	/**
	 * Get the {@link Properties} object from the given baseName and the given locale code as
	 * {@link String} object.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object.
	 */
	Properties getProperties(final String baseName, final String locale);
}