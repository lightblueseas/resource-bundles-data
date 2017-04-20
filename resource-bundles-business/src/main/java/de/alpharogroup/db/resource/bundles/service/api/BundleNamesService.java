/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface BundleNamesService extends BusinessService<BundleNames, Integer>
{

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
	 * Find all {@link BundleNames} object from the given baseName and locale.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 *
	 * @return the found {@link BaseNames} object or null if not.
	 */
	BundleNames find(final String baseName, final Locale locale);

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
	 * Gets the default locale from the given {@link BaseNames} object.
	 *
	 * @param bundleNames
	 *            the bundle names
	 * @return the default locale from the given {@link BaseNames} object or null if not set.
	 */
	LanguageLocales getDefaultLocale(BundleNames bundleNames);

	/**
	 * Gets the default locale from the given base name as {@link String} object.
	 *
	 * @param baseName
	 *            the base name
	 * @return the default locale from the given base name as {@link String} object or null if not
	 *         set.
	 */
	LanguageLocales getDefaultLocale(String baseName);

	/**
	 * Gets the or creates a new {@link BundleNames} object.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the {@link BundleNames} object
	 */
	BundleNames getOrCreateNewBundleNames(final String baseName, final Locale locale);
}
