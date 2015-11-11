package de.alpharogroup.db.resource.bundles.service.api;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.service.jpa.BusinessService;

/**
 * The interface {@link ResourcebundlesService}.
 */
public interface ResourcebundlesService extends BusinessService<Resourcebundles, Integer> {
	
	/**
	 * Find resource bundles from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @return the list
	 */
	List<Resourcebundles> findResourceBundles(String baseName, Locale locale);
	
	/**
	 * Find a list from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the list
	 */
	List<Resourcebundles> findResourceBundles(String baseName, Locale locale, String key);
	
	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the key
	 */
	Resourcebundles getResourcebundle(String baseName, Locale locale, String key);

	/**
	 * Checks if a resource exists with the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the {@link Resourcebundles} or null if it does not exists.
	 */
	Resourcebundles contains(String baseName, Locale locale, String key);
	
	/**
	 * Update the given properties to the db.
	 *
	 * @param properties the properties
	 * @param baseName the base name
	 * @param locale the locale
	 */
	void updateProperties(Properties properties, String baseName, Locale locale);
	
	/**
	 * Update the given properties to the db.
	 *
	 * @param properties the properties
	 * @param baseName the base name
	 * @param locale the locale
	 * @param update flag that indicates if an existing property shell be updated
	 */
	void updateProperties(Properties properties, String baseName, Locale locale, boolean update);
	
	/**
	 * Finds all entries from the given parameters that can be null if it shell be ignored.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @param value the value
	 * @return the list
	 */
	List<Resourcebundles> find(String baseName, String locale,String key, String value);
}