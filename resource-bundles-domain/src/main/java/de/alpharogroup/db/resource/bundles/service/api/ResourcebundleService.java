/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.db.resource.bundles.service.api;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.service.domain.DomainService;

/**
 * The interface {@link ResourcebundleService}.
 */
public interface ResourcebundleService extends DomainService<Integer, Resourcebundle>
{
	/**
	 * Checks if a resource exists with the given parameters.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the {@link Resourcebundle} or null if it does not exists.
	 */
	Resourcebundle contains(BundleApplication bundleApplication, String baseName, Locale locale,
		String key);

	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the key
	 */
	Resourcebundle find(BundleApplication bundleApplication, String baseName, Locale locale,
		String key);

	/**
	 * Finds all entries from the given parameters that can be null if it shell be ignored.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<Resourcebundle> find(BundleApplication bundleApplication, String baseName, String locale,
		String key, String value);

	/**
	 * Find the {@link BundleApplication} object from the given name.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the found {@link BundleApplication} object or null if not.
	 */
	BundleApplication find(final String name);

	/**
	 * Finds all bundle applications.
	 *
	 * @return the list of the found {@link BundleApplication} objects.
	 */
	List<BundleApplication> findAllBundleApplications();

	/**
	 * Find resource bundles from the given parameters.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the list
	 */
	List<Resourcebundle> findResourceBundles(BundleApplication bundleApplication, String baseName,
		Locale locale);

	/**
	 * Gets the or creates a new {@link BundleName} object.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the or create new bundle names
	 */
	BundleName getOrCreateNewBundleName(BundleApplication owner, final String baseName,
		final Locale locale);

	/**
	 * Get the {@link Properties} object from the given baseName and the given {@link Locale}
	 * object.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object.
	 */
	Properties getProperties(BundleApplication bundleApplication, final String baseName,
		final Locale locale);

	/**
	 * Get the {@link Properties} object from the given baseName and the given locale code as
	 * {@link String} object.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link Properties} object.
	 */
	Properties getProperties(BundleApplication bundleApplication, final String baseName,
		final String locale);

	/**
	 * Gets the entry from the given parameters.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the key
	 */
	Resourcebundle getResourcebundle(BundleApplication bundleApplication, String baseName,
		Locale locale, String key);

	/**
	 * Gets the resource {@link String} from the given {@link BundleKey}.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param bundleKey
	 *            the bundle key
	 * @return the {@link String}
	 */
	String getString(BundleApplication bundleApplication, final BundleKey bundleKey);

	/**
	 * Gets the resource {@link String} from the given arguments.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the {@link String}
	 */
	String getString(BundleApplication bundleApplication, String baseName, String locale,
		String key);

	/**
	 * Gets the resource {@link String} from the given arguments.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String}
	 */
	String getString(BundleApplication bundleApplication, String baseName, String locale,
		String key, Object[] parameters);

	/**
	 * Gets the resource {@link String} from the given arguments.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String}
	 */
	String getString(BundleApplication bundleApplication, String baseName, String locale,
		String key, final String defaultValue);


	/**
	 * Gets the resource {@link String} from the given arguments.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String}
	 */
	String getString(BundleApplication bundleApplication, String baseName, String locale,
		String key, final String defaultValue, Object[] parameters);

	/**
	 * Save or update the given resource bundle entry
	 *
	 * @param bundleappname
	 *            the name of the bundle application
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the {@link Resourcebundle} object
	 */
	Resourcebundle saveOrUpdateEntry(final String bundleappname, final String baseName,
		final String locale, final String key, final String value);


	/**
	 * Update the given properties to the db.
	 *
	 * @param bundleApplication
	 *            the bundle application
	 * @param properties
	 *            the properties
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 */
	BundleName updateProperties(BundleApplication bundleApplication, Properties properties,
		String baseName, Locale locale);

}
