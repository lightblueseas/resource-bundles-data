package de.alpharogroup.db.resource.bundles.service.mapper;

import java.util.List;
import java.util.Locale;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.service.entitymapper.BusinessMapperService;

/**
 * The interface {@link ResourcebundleService}.
 */
public interface ResourcebundleService extends BusinessMapperService<Integer, Resourcebundle>
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
}
