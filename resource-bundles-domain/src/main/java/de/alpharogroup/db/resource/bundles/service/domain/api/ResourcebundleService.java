package de.alpharogroup.db.resource.bundles.service.domain.api;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.service.domain.DomainService;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.resourcebundle.locale.BundleKey;

/**
 * The interface {@link ResourcebundleService}.
 */
public interface ResourcebundleService extends DomainService<Integer, Resourcebundle>
{
	
	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the key
	 */
	Resourcebundle find(String baseName, Locale locale, String key);
	
	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the key
	 */
	Resourcebundle find(String baseName, String locale, String key);
	
	/**
	 * Finds all entries from the given parameters that can be null if it shell be ignored.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @param value the value
	 * @return the list
	 */
	List<Resourcebundle> find(String baseName, String locale, String key, String value);
	
	
	/**
	 * Find resource bundles from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @return the list
	 */
	List<Resourcebundle> findResourceBundles(String baseName, Locale locale);
	

	/**
	 * Checks if a resource exists with the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the {@link Resourcebundle} or null if it does not exists.
	 */
	Resourcebundle contains(String baseName, Locale locale, String key);
			
	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the key
	 */
	Resourcebundle getResourcebundle(String baseName, Locale locale, String key);
	
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
     * Gets the resource {@link String} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @return the {@link String}
     */
    String getString(String baseName, String locale, String key);

    /**
     * Gets the resource {@link String} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @param params the parameters
     * @return the {@link String}
     */
    String getString(String baseName, String locale, String key, Object[] params);

    /**
     * Gets the resource {@link String} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @param defaultValue the default value
     * @return the {@link String}
     */
    String getString(String baseName, String locale, String key, final String defaultValue);

    /**
     * Gets the resource {@link String} from the given arguments.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param key the key
     * @param defaultValue the default value
     * @param params the parameters
     * @return the {@link String}
     */
    String getString(String baseName, String locale, String key, final String defaultValue, Object[] params);
    
    /**
     * Gets the resource {@link String} from the given {@link BundleKey}.
     *
     * @param bundleKey the bundle key
     * @return the {@link String}
     */
    String getString(final BundleKey bundleKey);

}
