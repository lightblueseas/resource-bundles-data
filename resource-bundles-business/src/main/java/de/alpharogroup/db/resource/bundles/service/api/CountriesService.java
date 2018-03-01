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

import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link CountriesService}.
 */
public interface CountriesService extends BusinessService<Countries, Integer>
{

	/**
	 * Find the {@link Countries} object from the given ISO 3166-1 alpha-2 name.
	 *
	 * @param iso3166A2name
	 *            the iso 3166 A 2 name
	 * @return the found {@link Countries} object or null if not.
	 */
	Countries find(final String iso3166A2name);

	/**
	 * Find the {@link Countries} object from the given ISO 3166-1 alpha-2 name.
	 *
	 * @param name
	 *            the name
	 * @param iso3166A2name
	 *            the iso 3166 A 2 name
	 * @return the found {@link Countries} object or null if not.
	 */
	Countries find(final String name, final String iso3166A2name);

}
