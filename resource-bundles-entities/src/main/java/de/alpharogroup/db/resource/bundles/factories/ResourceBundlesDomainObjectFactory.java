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
package de.alpharogroup.db.resource.bundles.factories;

import java.io.Serializable;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.PropertiesValues;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;

/**
 * A factory for creating Domain objects for the resource bundles.
 */
public class ResourceBundlesDomainObjectFactory implements Serializable
{

	/** The Constant instance. */
	private static final ResourceBundlesDomainObjectFactory instance = new ResourceBundlesDomainObjectFactory();

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the single instance of MessageSystemFactory.
	 *
	 * @return single instance of MessageSystemFactory
	 */
	public static ResourceBundlesDomainObjectFactory getInstance()
	{
		return instance;
	}

	/**
	 * Instantiates a new MessageSystemFactory object.
	 */
	private ResourceBundlesDomainObjectFactory()
	{
	}

	/**
	 * Factory method for create a new {@link BaseNames}.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the new {@link BaseNames}
	 */
	public BaseNames newBaseNames(final String name)
	{
		final BaseNames baseName = BaseNames.builder().name(name).build();
		return baseName;
	}

	/**
	 * Factory method for create a new {@link BundleNames}.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the new {@link BundleNames}
	 */
	public BundleNames newBundleName(final BundleApplications owner, final BaseNames baseName,
		final LanguageLocales locale)
	{
		final BundleNames bundleNames = BundleNames.builder().owner(owner).baseName(baseName)
			.locale(locale).build();
		return bundleNames;
	}

	/**
	 * Factory method for create a new {@link LanguageLocales}.
	 *
	 * @param locale
	 *            the locale
	 *
	 * @return the new {@link LanguageLocales}
	 */
	public LanguageLocales newLanguageLocales(final String locale)
	{
		final LanguageLocales languageLocales = LanguageLocales.builder().locale(locale).build();
		return languageLocales;
	}

	/**
	 * Factory method for create a new {@link Languages}.
	 *
	 * @param name
	 *            the name
	 * @param iso639Dash1
	 *            the iso 639 dash 1
	 * @return the new {@link Languages}
	 */
	public Languages newLanguages(final String name, final String iso639Dash1)
	{
		return Languages.builder().name(name).iso639Dash1(iso639Dash1).build();
	}

	/**
	 * Factory method for create a new {@link PropertiesKeys}.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the new {@link PropertiesKeys}
	 */
	public PropertiesKeys newPropertiesKeys(final String name)
	{
		return PropertiesKeys.builder().name(name).build();
	}

	/**
	 * Factory method for create a new {@link PropertiesValues}.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the new {@link PropertiesValues}
	 */
	public PropertiesValues newPropertiesValues(final String name)
	{
		return PropertiesValues.builder().name(name).build();
	}

	/**
	 * Factory method for create a new {@link Resourcebundles}.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the new {@link Resourcebundles}
	 */
	public Resourcebundles newResourcebundles(final BundleNames bundleName,
		final PropertiesKeys key, final PropertiesValues value)
	{
		final Resourcebundles resourcebundles = Resourcebundles.builder().bundleName(bundleName)
			.key(key).value(value).build();
		return resourcebundles;
	}

}
