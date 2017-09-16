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
package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class {@link DatabaseListResourceBundle}.
 */
@Getter
@Setter
@NoArgsConstructor
public class DatabaseListResourceBundle extends ListResourceBundle
{

	/** The base name. */
	private String baseName;

	/** The locale. */
	private Locale locale;

	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService;

	/**
	 * Instantiates a new {@link DatabaseListResourceBundle} object from the given parameters.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 */
	public DatabaseListResourceBundle(final String baseName, final Locale locale)
	{
		this.locale = locale;
		this.baseName = baseName;
	}

	/**
	 * Instantiates a new {@link DatabaseListResourceBundle} object from the given parameters.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param resourcebundlesService
	 *            the resourcebundles service
	 */
	public DatabaseListResourceBundle(final String baseName, final Locale locale,
		final ResourcebundlesService resourcebundlesService)
	{
		setResourcebundlesService(resourcebundlesService);
		this.locale = locale;
		this.baseName = baseName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object[][] getContents()
	{
		final List<Resourcebundles> resourcebundles = resourcebundlesService
			.findResourceBundles(baseName, locale);
		final Object[][] all = new Object[resourcebundles.size()][2];
		int i = 0;
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			all[i] = new Object[] { resourcebundle.getKey().getName(), resourcebundle.getValue() };
			i++;
		}
		return all;
	}

}