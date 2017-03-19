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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.file.FileExtension;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;

/**
 * The class {@link ResourcebundlesBusinessServiceTest}.
 */
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesBusinessServiceTest extends AbstractTestNGSpringContextTests {

	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService;

	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	@Autowired
	private BundleNamesService bundleNamesService;

	@Autowired
	private BaseNamesService baseNamesService;

	@Autowired
	private LanguageLocalesService languageLocalesService;

	/**
	 * Gets the resourcebundles service.
	 *
	 * @return the resourcebundles service
	 */
	public ResourcebundlesService getResourcebundlesService() {
		return resourcebundlesService;
	}

	/**
	 * Inits the resourcebundles.
	 */
	protected void initResourcebundles() {
		Resourcebundles resourcebundles = resourcebundlesService.contains("resource.bundles", Locale.GERMAN,
				"resource.bundles.test.label");
		if (resourcebundles == null) {
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles("resource.bundles",
					Locale.GERMAN, "resource.bundles.test.label", "Erstes label");
			resourcebundlesService.saveOrUpdate(resourcebundles);
		}

		resourcebundles = resourcebundlesService.contains("resource.bundles", Locale.UK, "resource.bundles.test.label");
		if (resourcebundles == null) {
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles("resource.bundles",
					Locale.UK, "resource.bundles.test.label", "First label");
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}
	}

	/**
	 * Sets the resourcebundles service.
	 *
	 * @param resourcebundlesService
	 *            the new resourcebundles service
	 */
	public void setResourcebundlesService(ResourcebundlesService resourcebundlesService) {
		this.resourcebundlesService = resourcebundlesService;
	}

	@Test(enabled = true)
	public void testFindBaseNames() {
		// The base Name
		String baseName = "ApplicationBasePage";
		// check if baseNames exists...
		BaseNames expected = baseNamesService.find(baseName);
		if (expected == null) {
			expected = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames(baseName);
			expected = baseNamesService.merge(expected);
		}
		BaseNames actual = baseNamesService.find(baseName);
		AssertJUnit.assertNotNull(actual);
		AssertJUnit.assertEquals(expected, actual);
	}

	@Test(enabled = false)
	public void testFindBundleApplications() {
		String applicationName = "foo-dating.com";
		BundleApplications expected = bundleApplicationsService.find(applicationName);
		if (expected == null) {
			// and save to db...
			expected = ResourceBundlesDomainObjectFactory.getInstance().newBundleApplications(applicationName);
			expected = bundleApplicationsService.merge(expected);
		}
		BundleApplications actual = bundleApplicationsService.find(applicationName);
		AssertJUnit.assertNotNull(actual);
		AssertJUnit.assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testFindBundleNames() {
		// The base Name
		String baseName = "ApplicationBasePage";
		// check if baseNames exists...
		BaseNames expected = baseNamesService.find(baseName);
		if (expected == null) {
			expected = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames(baseName);
			expected = baseNamesService.merge(expected);
		}
		BaseNames actual = baseNamesService.find(baseName);
		AssertJUnit.assertNotNull(actual);
		AssertJUnit.assertEquals(expected, actual);

		// Get all bundle names as list
		List<BundleNames> bundleNames = bundleNamesService.find(actual);
		boolean newBundleName = false;
		// check if bundle names are empty...
		if (ListExtensions.isEmpty(bundleNames)) {
			newBundleName = true;
		}
	}

	@Test(enabled = true)
	public void testFindLanguageLocales() {
		Locale germanLocale = Locale.GERMAN;
		LanguageLocales expected = languageLocalesService.find(germanLocale);
		if (expected == null) {
			expected = ResourceBundlesDomainObjectFactory.getInstance().newLanguageLocales(germanLocale);
			expected = languageLocalesService.merge(expected);
		}
		LanguageLocales actual = languageLocalesService.find(germanLocale);
		AssertJUnit.assertNotNull(actual);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test find resource bundles.
	 */
	@Test(enabled = false)
	public void testFindResourceBundles() {
		initResourcebundles();
		DatabaseListResourceBundle databaseResourceBundle = new DatabaseListResourceBundle("resource.bundles",
				Locale.UK, resourcebundlesService);
		String actual = databaseResourceBundle.getString("resource.bundles.test.label");
		String expected = "First label";
		AssertJUnit.assertEquals(expected, actual);
		databaseResourceBundle = new DatabaseListResourceBundle("resource.bundles", Locale.GERMAN,
				resourcebundlesService);
		actual = databaseResourceBundle.getString("resource.bundles.test.label");
		expected = "Erstes label";
		AssertJUnit.assertEquals(expected, actual);
		truncate();
	}

	@Test(enabled = true)
	public void testInsertApplicationProperties() throws URISyntaxException, IOException {

		// The base Name
		String baseName = "ApplicationBasePage";
		LanguageLocales languageLocales = bundleNamesService.getDefaultLocale(baseName);
		boolean newDefaultLocale = false;
		if (languageLocales == null) {
			newDefaultLocale = true;
		}
		String propertiesFileExtension = FileExtension.PROPERTIES.getExtension();
		String germanLocale = "_de";
		String germanPropertiesFilename = baseName + germanLocale + propertiesFileExtension;
		File germanPropertiesFile = ClassExtensions.getResourceAsFile(germanPropertiesFilename);

		String germanBaseName = LocaleResolver.resolveBundlename(germanPropertiesFile);
		Locale germanLocaleObj = LocaleResolver.resolveLocale(germanPropertiesFile);

		Properties germanProperties = PropertiesExtensions.loadProperties(germanPropertiesFile);
		// resourcebundlesService.updateProperties(germanProperties,
		// germanBaseName, germanLocaleObj);

		// String defaultPropertiesFilename = baseName +
		// propertiesFileExtension;
		// File defaultPropertiesFile =
		// ClassExtensions.getResourceAsFile(defaultPropertiesFilename);
		//
		// String defaultBaseName =
		// LocaleResolver.resolveBundlename(defaultPropertiesFile);
		// Locale defaultLocaleObj =
		// LocaleResolver.resolveLocale(defaultPropertiesFile);

	}

	/**
	 * Test method for
	 * {@link ResourcebundlesService#updateProperties(java.util.Properties, String, Locale)}
	 * 
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 */
	@Test(enabled = false)
	public void testUpdateProperties() throws URISyntaxException, IOException {
		String propertiesFilename = "test_de_DE.properties";
		File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		String baseName = LocaleResolver.resolveBundlename(propertiesFile);
		Locale locale = LocaleResolver.resolveLocale(propertiesFile);
		Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
		resourcebundlesService.updateProperties(properties, baseName, locale);
		Set<Resourcebundles> rb = new HashSet<>(resourcebundlesService.findAll());
		AssertJUnit.assertEquals(4, rb.size());
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
	@Test(enabled = false)
	public void testUpdatePropertiesUpdate() throws URISyntaxException, IOException {
		final String bundlepackage = "";
		final String bundlename = "test";
		Map<File, Locale> fileToLocaleMap = LocaleResolver.resolveLocales(bundlepackage, bundlename);

		for (Entry<File, Locale> entry : fileToLocaleMap.entrySet()) {
			File propertiesFile = entry.getKey();
			Locale locale = entry.getValue();
			Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			resourcebundlesService.updateProperties(properties, bundlename, locale, false);
		}

		Set<Resourcebundles> rb = new HashSet<>(resourcebundlesService.findAll());

		AssertJUnit.assertEquals(8, rb.size());
		truncate();
	}

	/**
	 * Truncate the table 'resourcebundles'.
	 */
	private void truncate() {
		List<BundleApplications> ba = bundleApplicationsService.findAll();
		bundleApplicationsService.delete(ba);
		List<Resourcebundles> rb = resourcebundlesService.findAll();
		resourcebundlesService.delete(rb);
	}

}
