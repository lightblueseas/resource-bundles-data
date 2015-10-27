package de.alpharogroup.db.resource.bundles.service.api;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.service.jpa.BusinessService;

/**
 * The Interface ResourcebundlesService.
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
	Resourcebundles getKey(String baseName, Locale locale, String key);
	
	/**
	 * Update the given properties to the db.
	 *
	 * @param properties the properties
	 * @param baseName the base name
	 * @param locale the locale
	 */
	void updateProperties(Properties properties, String baseName, Locale locale);
	
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