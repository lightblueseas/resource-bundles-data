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

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import lombok.Getter;
import lombok.NonNull;

/**
 * The class {@link DatabaseControl} can be used to load ResourceBundle from the database.
 */
public class DatabaseControl extends Control
{

	@Getter
	private final String bundleApplicationName;

	/** The resourcebundles service. */
	@Getter
	private final ResourcebundlesService resourcebundlesService;

	/**
	 * Instantiates a new {@link DatabaseControl}.
	 */
	public DatabaseControl(@NonNull final ResourcebundlesService resourcebundlesService,
		@NonNull final String bundleApplicationName)
	{
		this.resourcebundlesService = resourcebundlesService;
		this.bundleApplicationName = bundleApplicationName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format,
		ClassLoader loader, boolean reload)
		throws IllegalAccessException, InstantiationException, IOException
	{
		final DatabaseResourceBundle databaseResourceBundle = new DatabaseResourceBundle(
			this.bundleApplicationName, baseName, locale, resourcebundlesService);
		return databaseResourceBundle;
	}

}