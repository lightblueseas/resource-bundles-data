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

import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.service.domain.DomainService;

/**
 * The domain service interface {@link BundleNameService}.
 */
public interface BundleNameService extends DomainService<Integer, BundleName>
{

	/**
	 * Find all {@link BundleName} objects from the given {@link BundleApplication} object.
	 *
	 * @param owner
	 *            the owner
	 * @return the list of found {@link BundleName} objects or null if not.
	 */
	List<BundleName> find(final BundleApplication owner);

	/**
	 * Find all {@link BundleName} objects from the given {@link BaseName} object.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the {@link BaseName} object
	 * @return the list of found {@link BundleName} objects or null if not.
	 */
	List<BundleName> find(final BundleApplication owner, BaseName baseName);

	/**
	 * Find the {@link BundleName} object from the given baseName and languageLocales.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param languageLocales
	 *            the languageLocales
	 * @return the found {@link BaseName} object or null if not.
	 */
	BundleName find(final BundleApplication owner, BaseName baseName,
		LanguageLocale languageLocales);

	/**
	 * Find all {@link BundleName} objects from the given baseName string.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the {@link BaseName} object
	 * @return the list of found {@link BundleName} objects or null if not.
	 */
	List<BundleName> find(final BundleApplication owner, final String baseName);

	/**
	 * Find all {@link BundleName} object from the given baseName and locale.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the found {@link BaseName} object or null if not.
	 */
	BundleName find(final BundleApplication owner, final String baseName, final Locale locale);

	/**
	 * Find all {@link BundleName} objects from the given baseName and locale.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the list of found {@link BaseName} objects or null if not.
	 */
	List<BundleName> find(final BundleApplication owner, final String baseName,
		final String locale);

	/**
	 * Gets the default locale from the given base name as {@link String} object.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @return the default locale from the given base name as {@link String} object or null if not
	 *         set.
	 */
	LanguageLocale getDefaultLocale(final BundleApplication owner, String baseName);

	/**
	 * Gets the default locale from the given {@link BaseName} object.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @return the default locale from the given {@link BaseName} object or null if not set.
	 */
	LanguageLocale getDefaultLocale(BundleName bundleName);

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
}