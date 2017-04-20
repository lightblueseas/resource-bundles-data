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

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.service.api.BusinessService;

public interface DefaultLocaleBaseNamesService
	extends
		BusinessService<DefaultLocaleBaseNames, Integer>
{

	/**
	 * Find the {@link DefaultLocaleBaseNames} object from the given baseName.
	 *
	 * @param baseName
	 *            the base name
	 * 
	 * @return the found {@link DefaultLocaleBaseNames} object or null if not.
	 */
	DefaultLocaleBaseNames find(final String baseName);

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
}
