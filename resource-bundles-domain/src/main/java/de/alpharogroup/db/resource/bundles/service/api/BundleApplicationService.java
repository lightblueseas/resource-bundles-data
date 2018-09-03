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

import java.util.Set;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.service.domain.DomainService;
import lombok.NonNull;

/**
 * The interface {@link BundleApplicationService}
 */
public interface BundleApplicationService extends DomainService<Integer, BundleApplication>
{

	/**
	 * Find all {@link BundleName} objects from the given {@link BundleApplication} object.
	 *
	 * @param owner
	 *            the owner
	 * @return the list of found {@link BundleName} objects or null if not.
	 */
	Set<BundleName> find(final BundleApplication owner);

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
	 * Find the {@link BundleApplication} object from the given {@link BundleName} object.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @return the found {@link BundleApplication} object
	 */
	BundleApplication get(BundleName bundleName);

	/**
	 * Gets the {@link BundleApplication} object from the given name or creates a new
	 * {@link BundleApplication} object if not found.
	 *
	 *
	 * @param name
	 *            the name
	 * @param defaultLocale
	 *            the default locale
	 * @return the existing or a new {@link BundleApplication} object
	 */
	BundleApplication getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocale defaultLocale);

	/**
	 * Gets the {@link BundleApplication} object from the given name or creates a new
	 * {@link BundleApplication} object if not found.
	 *
	 *
	 * @param name
	 *            the name
	 * @param defaultLocale
	 *            the default locale
	 * @return the existing or a new {@link BundleApplication} object
	 */
	BundleApplication getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final String defaultLocale);

	/**
	 * Gets the {@link BundleApplication} object from the given name or creates a new
	 * {@link BundleApplication} object if not found.
	 *
	 * @param name
	 *            the name
	 * @param defaultLocale
	 *            the default locale
	 * @param supportedLocales
	 *            the supported locales
	 * @return the existing or a new {@link BundleApplication} object
	 */
	BundleApplication getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocale defaultLocale,
		@NonNull final Set<LanguageLocale> supportedLocales);

}
