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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;

/**
 * The class {@link AbstractResourcebundlesBusinessServiceTest}.
 */
public class AbstractResourcebundlesBusinessServiceTest extends AbstractTestNGSpringContextTests
{

	/** The resourcebundles service. */
	@Autowired
	protected ResourcebundlesService resourcebundlesService;

	@Autowired
	protected BundleApplicationsService bundleApplicationsService;

	@Autowired
	protected BundleNamesService bundleNamesService;

	@Autowired
	protected BaseNamesService baseNamesService;

	@Autowired
	protected LanguageLocalesService languageLocalesService;

	/** The properties keys service. */
	@Autowired
	protected PropertiesKeysService propertiesKeysService;

	private BundleApplications getOrCreateBundleApplication(final String applicationName,
		final LanguageLocales defaultLocale)
	{
		BundleApplications expected = bundleApplicationsService.find(applicationName);
		if (expected == null)
		{
			// and save to db...
			expected = ResourceBundlesDomainObjectFactory.getInstance()
				.newBundleApplications(applicationName, defaultLocale);
			expected = bundleApplicationsService.merge(expected);
		}
		return expected;
	}

	/**
	 * Gets the resourcebundles service.
	 *
	 * @return the resourcebundles service
	 */
	public ResourcebundlesService getResourcebundlesService()
	{
		return resourcebundlesService;
	}

	/**
	 * Inits the resourcebundles.
	 */
	protected void initResourcebundles()
	{
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);
		Resourcebundles resourcebundles = resourcebundlesService.contains("resource.bundles",
			Locale.GERMAN, "resource.bundles.test.label");
		if (resourcebundles == null)
		{
			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.GERMAN);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewPropertiesKeys("resource.bundles.test.label");
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, "Erstes label");
			resourcebundlesService.saveOrUpdate(resourcebundles);
		}

		resourcebundles = resourcebundlesService.contains("resource.bundles", Locale.UK,
			"resource.bundles.test.label");
		if (resourcebundles == null)
		{

			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.UK);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewPropertiesKeys("resource.bundles.test.label");
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, "First label");
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}
	}

	/**
	 * Sets the resourcebundles service.
	 *
	 * @param resourcebundlesService
	 *            the new resourcebundles service
	 */
	public void setResourcebundlesService(final ResourcebundlesService resourcebundlesService)
	{
		this.resourcebundlesService = resourcebundlesService;
	}

	public void testFindBaseNames()
	{
		// The base Name
		final String baseName = "ApplicationBasePage";
		// check if baseNames exists...
		BaseNames expected = baseNamesService.find(baseName);
		if (expected == null)
		{
			expected = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames(baseName);
			expected = baseNamesService.merge(expected);
		}
		final BaseNames actual = baseNamesService.find(baseName);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	public void testFindBundleApplications()
	{
		final String applicationName = "foo-dating.com";
		BundleApplications expected = bundleApplicationsService.find(applicationName);
		if (expected == null)
		{
			final LanguageLocales languageLocales = languageLocalesService
				.getOrCreateNewLanguageLocales(Locale.GERMANY);
			// and save to db...
			expected = ResourceBundlesDomainObjectFactory.getInstance()
				.newBundleApplications(applicationName, languageLocales);
			expected = bundleApplicationsService.merge(expected);
		}
		final BundleApplications actual = bundleApplicationsService.find(applicationName);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	public void testFindBundleNames()
	{
		// The base Name
		final String baseName = "ApplicationBasePage";

		final BaseNames actual = baseNamesService.getOrCreateNewBaseNames(baseName);
		assertNotNull(actual);
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);
		// Get all bundle names as list
		final List<BundleNames> bundleNames = bundleNamesService.find(bundleApplication, actual);
		assertNotNull(bundleNames);
	}

	public void testFindLanguageLocales()
	{
		final Locale germanLocale = Locale.GERMAN;
		LanguageLocales expected = languageLocalesService.find(germanLocale);
		if (expected == null)
		{
			expected = ResourceBundlesDomainObjectFactory.getInstance()
				.newLanguageLocales(germanLocale);
			expected = languageLocalesService.merge(expected);
		}
		final LanguageLocales actual = languageLocalesService.find(germanLocale);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test find resource bundles.
	 */
	public void testFindResourceBundles()
	{
		initResourcebundles();
		DatabaseListResourceBundle databaseResourceBundle = new DatabaseListResourceBundle(
			"resource.bundles", Locale.UK, resourcebundlesService);
		String actual = databaseResourceBundle.getString("resource.bundles.test.label");
		String expected = "First label";
		assertEquals(expected, actual);
		databaseResourceBundle = new DatabaseListResourceBundle("resource.bundles", Locale.GERMAN,
			resourcebundlesService);
		actual = databaseResourceBundle.getString("resource.bundles.test.label");
		expected = "Erstes label";
		assertEquals(expected, actual);
		truncate();
	}

	/**
	 * Test method for
	 * {@link ResourcebundlesService#updateProperties(java.util.Properties, String, Locale)}
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 */
	public void testUpdateProperties() throws URISyntaxException, IOException
	{
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);
		final String propertiesFilename = "test_de_DE.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final String baseName = LocaleResolver.resolveBundlename(propertiesFile);
		final Locale locale = LocaleResolver.resolveLocale(propertiesFile);
		final Properties properties = PropertiesFileExtensions.loadProperties(propertiesFile);
		resourcebundlesService.updateProperties(bundleApplication, properties, baseName, locale);
		final Set<Resourcebundles> rb = new HashSet<>(
			resourcebundlesService.findResourceBundles(baseName, locale));
		assertEquals(4, rb.size());
		truncate();
	}

	/**
	 * Test method for
	 * {@link ResourcebundlesService#updateProperties(java.util.Properties, String, Locale, boolean)}
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 */
	public void testUpdatePropertiesUpdate() throws URISyntaxException, IOException
	{
		final String bundlepackage = "";
		final String bundlename = "ApplicationBasePage";
		final Map<File, Locale> fileToLocaleMap = LocaleResolver.resolveLocales(bundlepackage,
			bundlename, false);
		final Locale defaultLocale = Locale.GERMANY;
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		BundleApplications bundleApplication = getOrCreateBundleApplication(applicationName,
			languageLocales);

		for (final Entry<File, Locale> entry : fileToLocaleMap.entrySet())
		{
			final File propertiesFile = entry.getKey();
			Locale locale = entry.getValue();
			if (locale == null)
			{
				final BundleNames bundleNames = bundleNamesService
					.getOrCreateNewBundleNames(bundleApplication, bundlename, defaultLocale);
				bundleApplication.getBundleNames().add(bundleNames);
				bundleApplication = bundleApplicationsService.merge(bundleApplication);
				final LanguageLocales loc = bundleNamesService.getDefaultLocale(bundleNames);
				if (loc != null)
				{
					locale = LocaleResolver.resolveLocale(loc.getLocale());
				}
				else
				{
					locale = defaultLocale;
				}
			}
			final Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			resourcebundlesService.updateProperties(bundleApplication, properties, bundlename,
				locale);
		}

		final Set<Resourcebundles> rb = new HashSet<>(resourcebundlesService.findAll());

		assertEquals(627, rb.size());
	}

	/**
	 * Truncate the table 'resourcebundles'.
	 */
	private void truncate()
	{
		final List<BundleApplications> ba = bundleApplicationsService.findAll();
		bundleApplicationsService.delete(ba);
		final List<Resourcebundles> rb = resourcebundlesService.findAll();
		resourcebundlesService.delete(rb);
	}

}
