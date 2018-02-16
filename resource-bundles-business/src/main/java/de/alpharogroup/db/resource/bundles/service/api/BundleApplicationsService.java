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

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface BundleApplicationsService extends BusinessService<BundleApplications, Integer>
{

	/**
	 * Find all {@link BundleNames} objects from the given {@link BundleApplications} object.
	 *
	 * @param owner
	 *            the owner
	 * @return the list of found {@link BundleNames} objects or null if not.
	 */
	Set<BundleNames> find(final BundleApplications owner);

	/**
	 * Find the {@link BundleApplications} object from the given name.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the found {@link BundleApplications} object or null if not.
	 */
	BundleApplications find(final String name);

	/**
	 * Find the {@link BundleApplications} object from the given {@link BundleNames} object.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @return the bundle applications
	 */
	BundleApplications get(BundleNames bundleName);

	/**
	 * Gets the {@link BundleApplications} object from the given name or creates a new
	 * {@link BundleApplications} object if not found.
	 *
	 * @param name
	 *            the name
	 * @return the existing or a new {@link BundleApplications} object
	 */
	@Deprecated
	BundleApplications getOrCreateNewBundleApplications(String name);

	/**
	 * Gets the {@link BundleApplications} object from the given name or creates a new
	 * {@link BundleApplications} object if not found.
	 *
	 *
	 * @param name
	 *            the name
	 * @param defaultLocale
	 *            the default locale
	 * @return the existing or a new {@link BundleApplications} object
	 */
	BundleApplications getOrCreateNewBundleApplications(final String name,
		final LanguageLocales defaultLocale);

}
