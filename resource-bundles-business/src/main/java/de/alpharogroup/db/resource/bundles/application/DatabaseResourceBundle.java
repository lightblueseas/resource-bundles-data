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
package de.alpharogroup.db.resource.bundles.application;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import de.alpharogroup.db.resource.bundles.service.DatabaseListResourceBundle;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The Class DatabaseResourceBundle.
 */
public class DatabaseResourceBundle extends ResourceBundle {

	/**
	 * Instantiates a new database resource bundle.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 */
	public DatabaseResourceBundle(String baseName, Locale locale) {
		setParent(new DatabaseListResourceBundle(baseName, locale));
	}

	/**
	 * Instantiates a new database resource bundle.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param resourcebundlesService
	 *            the resourcebundles service
	 */
	public DatabaseResourceBundle(String baseName, Locale locale, ResourcebundlesService resourcebundlesService) {
		setParent(new DatabaseListResourceBundle(baseName, locale, resourcebundlesService));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object handleGetObject(String key) {
		return parent.getObject(key);
	}

}