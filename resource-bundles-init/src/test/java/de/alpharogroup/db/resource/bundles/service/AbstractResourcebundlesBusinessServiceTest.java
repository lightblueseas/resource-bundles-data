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
package de.alpharogroup.db.resource.bundles.service;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.db.init.AbstractDatabaseInitialization;
import de.alpharogroup.db.resource.bundles.application.DatabaseListResourceBundle;
import de.alpharogroup.db.resource.bundles.db.init.DatabaseInitialization;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.PropertiesValues;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.CountriesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesValuesService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.locale.Locales;
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;
import de.alpharogroup.string.StringExtensions;
import lombok.extern.java.Log;

/**
 * The class {@link AbstractResourcebundlesBusinessServiceTest}.
 */
@Log
public class AbstractResourcebundlesBusinessServiceTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	protected BaseNamesService baseNamesService;

	@Autowired
	protected BundleApplicationsService bundleApplicationsService;

	@Autowired
	protected BundleNamesService bundleNamesService;

	@Autowired
	protected CountriesService countriesService;

	private DatabaseInitialization databaseInitialization;

	@PersistenceUnit
	protected EntityManagerFactory entityManagerFactory;

	@Autowired
	protected LanguageLocalesService languageLocalesService;

	@Autowired
	protected LanguagesService languagesService;

	/** The properties keys service. */
	@Autowired
	protected PropertiesKeysService propertiesKeysService;

	/** The properties values service. */
	@Autowired
	private PropertiesValuesService propertiesValuesService;

	/** The resourcebundles service. */
	@Autowired
	protected ResourcebundlesService resourcebundlesService;

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
	 * Inits the bundle app 'bar-date.com' with two resourcebundles.
	 */
	protected void initBundleApplicationsBarDateDotCom()
	{
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);

		final LanguageLocales supportedEnglishLanguageLocale = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.ENGLISH);

		final LanguageLocales supportedHellenicLanguageLocale = languageLocalesService
			.getOrCreateNewLanguageLocales(Locales.HELLENIC);

		final String applicationName = "bar-date.com";
		BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);

		bundleApplication.addSupportedLanguageLocale(supportedEnglishLanguageLocale);
		bundleApplication.addSupportedLanguageLocale(supportedHellenicLanguageLocale);
		bundleApplication = bundleApplicationsService.merge(bundleApplication);

		Resourcebundles resourcebundles = resourcebundlesService.contains(bundleApplication,
			"resource.bundles", Locale.GERMAN, "resource.bundles.test.label");
		String value = null;
		if (resourcebundles == null)
		{
			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.GERMAN);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewNameEntity("resource.bundles.test.label");
			value = "Erstes label";
			PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, pvalue);
			resourcebundlesService.saveOrUpdate(resourcebundles);
		}

		resourcebundles = resourcebundlesService.contains(bundleApplication, "resource.bundles",
			Locale.UK, "resource.bundles.test.label");
		if (resourcebundles == null)
		{

			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.UK);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewNameEntity("resource.bundles.test.label");
			value = "First label";
			PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, pvalue);
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}
	}

	/**
	 * Inits the bundle app 'foo-dating.com' with two resourcebundles.
	 */
	protected void initBundleApplicationsFooDatingDotCom()
	{
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);
		Resourcebundles resourcebundles = resourcebundlesService.contains(bundleApplication,
			"resource.bundles", Locale.GERMAN, "resource.bundles.test.label");
		String value = null;
		if (resourcebundles == null)
		{
			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.GERMAN);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewNameEntity("resource.bundles.test.label");
			value = "Erstes label";
			PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, pvalue);
			resourcebundlesService.merge(resourcebundles);
		}

		resourcebundles = resourcebundlesService.contains(bundleApplication, "resource.bundles",
			Locale.UK, "resource.bundles.test.label");
		if (resourcebundles == null)
		{

			final BundleNames bundleName = bundleNamesService
				.getOrCreateNewBundleNames(bundleApplication, "resource.bundles", Locale.UK);
			final PropertiesKeys pkey = propertiesKeysService
				.getOrCreateNewNameEntity("resource.bundles.test.label");
			value = "First label";
			PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles(bundleName, pkey, pvalue);
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}
	}

	protected void initCountries()
	{
		List<Countries> availableCountries = DataObjectFactory.newCountries();
		for (Countries countries : availableCountries)
		{
			Countries foundCountry = countriesService.find(countries.getIso3166A2name());
			if (foundCountry == null)
			{
				countriesService.merge(countries);
			}
		}
	}

	/**
	 * Initialize the db.
	 */
	protected void initializeDatabase()
	{
		if (databaseInitialization == null)
		{
			Properties dbProperties;
			try
			{
				dbProperties = PropertiesFileExtensions.loadProperties("jdbc.properties");
				dbProperties.setProperty(AbstractDatabaseInitialization.JDBC_CREATE_DB_PROCESS_KEY,
					"create-empty");
				databaseInitialization = new DatabaseInitialization(dbProperties);
			}
			catch (final IOException e)
			{
				log.log(Level.SEVERE, "IOException", e);
			}
		}
		if (databaseInitialization != null)
		{
			try
			{
				databaseInitialization.initializeDatabase();
			}
			catch (ClassNotFoundException | SQLException | IOException e)
			{
				log.log(Level.SEVERE, StringExtensions.toString(databaseInitialization), e);
			}
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

	public void testBundleApplicationsWithSameNameResourceBundles()
	{
		initBundleApplicationsFooDatingDotCom();
		initBundleApplicationsBarDateDotCom();
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "bar-date.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);

		final Set<BundleNames> bundleNames = bundleApplicationsService.find(bundleApplication);
		assertNotNull(bundleNames);
		assertTrue(bundleNames.size() == 2);
		final String applicationName2 = "foo-dating.com";
		final BundleApplications bundleApplication2 = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName2, languageLocales);

		final Set<BundleNames> bundleNames2 = bundleApplicationsService.find(bundleApplication2);

		assertNotEquals(bundleNames, bundleNames2);
	}

	public void testDeleteBundleApplications()
	{
		initBundleApplicationsFooDatingDotCom();

		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);
		final String applicationName = "foo-dating.com";
		final BundleApplications bundleApplication = bundleApplicationsService
			.getOrCreateNewBundleApplications(applicationName, languageLocales);
		bundleApplicationsService.delete(bundleApplication);
		BundleApplications bundleApplications = bundleApplicationsService.find(applicationName);
		assertNull(bundleApplications);
	}

	public void testDeleteBundleName() throws URISyntaxException, IOException
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

		final BundleNames bundleNames = bundleNamesService
			.getOrCreateNewBundleNames(bundleApplication, bundlename, defaultLocale);

		resourcebundlesService.delete(bundleNames);
	}

	public void testFindBaseNames()
	{
		// The base Name
		final String baseName = "ApplicationBasePage";
		// check if baseNames exists...
		BaseNames expected = baseNamesService.findFirst(baseName);
		if (expected == null)
		{
			expected = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames(baseName);
			expected = baseNamesService.merge(expected);
		}
		final BaseNames actual = baseNamesService.findFirst(baseName);
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

		final BaseNames actual = baseNamesService.getOrCreateNewNameEntity(baseName);
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
		initBundleApplicationsFooDatingDotCom();
		final String bundleApplicationName = "foo-dating.com";
		DatabaseListResourceBundle databaseResourceBundle = new DatabaseListResourceBundle(
			bundleApplicationName, "resource.bundles", Locale.UK, resourcebundlesService);
		String actual = databaseResourceBundle.getString("resource.bundles.test.label");
		String expected = "First label";
		assertEquals(expected, actual);
		databaseResourceBundle = new DatabaseListResourceBundle(bundleApplicationName,
			"resource.bundles", Locale.GERMAN, resourcebundlesService);
		actual = databaseResourceBundle.getString("resource.bundles.test.label");
		expected = "Erstes label";
		assertEquals(expected, actual);
		initializeDatabase();
	}

	public void testSupportedLanguageLocales()
	{
		final LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.GERMANY);

		final LanguageLocales supportedEnglishLanguageLocale = languageLocalesService
			.getOrCreateNewLanguageLocales(Locale.ENGLISH);

		final LanguageLocales supportedHellenicLanguageLocale = languageLocalesService
			.getOrCreateNewLanguageLocales(Locales.HELLENIC);


		initBundleApplicationsBarDateDotCom();
		final String applicationName = "bar-date.com";

		BundleApplications bundleApplications = bundleApplicationsService.find(applicationName);

		assertTrue(bundleApplications.isSupported(languageLocales));
		assertTrue(bundleApplications.isSupported(supportedEnglishLanguageLocale));
		assertTrue(bundleApplications.isSupported(supportedHellenicLanguageLocale));
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
			resourcebundlesService.findResourceBundles(bundleApplication, baseName, locale));
		assertEquals(4, rb.size());
		initializeDatabase();
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
		Locale dl;
		final BundleNames bundleNames = bundleNamesService
			.getOrCreateNewBundleNames(bundleApplication, bundlename, defaultLocale);
		final LanguageLocales loc = bundleNamesService.getDefaultLocale(bundleNames);
		if (loc != null)
		{
			dl = LocaleResolver.resolveLocale(loc.getLocale());
		}
		else
		{
			dl = defaultLocale;
		}
		for (final Entry<File, Locale> entry : fileToLocaleMap.entrySet())
		{
			final File propertiesFile = entry.getKey();
			Locale locale = entry.getValue();
			if (locale == null)
			{
				locale = dl;
			}
			final Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			resourcebundlesService.updateProperties(bundleApplication, properties, bundlename,
				locale);
		}

		final Set<Resourcebundles> rb = new HashSet<>(resourcebundlesService.findAll());

		assertEquals(627, rb.size());
	}

}
