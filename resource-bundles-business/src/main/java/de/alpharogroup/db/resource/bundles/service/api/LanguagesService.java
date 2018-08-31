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

import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link LanguagesService}
 */
public interface LanguagesService extends BusinessService<Languages, Integer>
{

	/**
	 * Find the {@link Languages} object from the name baseName and iso639Dash1.
	 *
	 * @param name
	 *            the name
	 * @param iso639Dash1
	 *            the specific code for the representation for the name of language
	 * 
	 * @return the found {@link Languages} object or null if not.
	 */
	Languages find(final String name, final String iso639Dash1);

	/**
	 * Find the {@link Languages} object from the name of the language
	 *
	 * @param name
	 *            the name of the language
	 * @return the found {@link Languages} object or null if not.
	 */
	Languages findByName(final String name);

	/**
	 * Find the {@link Languages} object from the specific code of the language
	 *
	 * @param name
	 * @param iso639Dash1
	 *            the specific code for the representation for the name of language
	 * @return the found {@link Languages} object or null if not.
	 */
	Languages findByIso639Dash1(final String iso639Dash1);

}
