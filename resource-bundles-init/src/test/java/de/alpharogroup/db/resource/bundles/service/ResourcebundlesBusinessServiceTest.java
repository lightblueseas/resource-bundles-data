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

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;

/**
 * The class {@link ResourcebundlesBusinessServiceTest}.
 */
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesBusinessServiceTest extends AbstractTestNGSpringContextTests {

	/** The base names service. */
	@Autowired
	private BaseNamesService baseNamesService;
	
	/** The Bundle names service. */
	@Autowired
	private BundleNamesService bundleNamesService;
	
	/** The default locale base names service. */
//	@Autowired
//	private DefaultLocaleBaseNamesService defaultLocaleBaseNamesService;
	
	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;
	
	/** The properties keys service. */
	@Autowired
	private PropertiesKeysService propertiesKeysService;
	
	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService;	

	/**
	 * Gets the resourcebundles service.
	 *
	 * @return the resourcebundles service
	 */
	public ResourcebundlesService getResourcebundlesService() {
		return resourcebundlesService;
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

	/**
	 * Test find resource bundles.
	 */
	@Test(enabled = true)
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
		// truncate();
	}

	/**
	 * Test method for
	 * {@link ResourcebundlesService#updateProperties(java.util.Properties, String, Locale)}
	 * 
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 */
	@Test(enabled = true)
	public void testUpdateProperties() throws URISyntaxException, IOException {
		String propertiesFilename = "test_de_DE.properties";
		File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		String baseName = LocaleResolver.resolveBundlename(propertiesFile);
		Locale locale = LocaleResolver.resolveLocale(propertiesFile);
		Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
		resourcebundlesService.updateProperties(properties, baseName, locale);
		List<Resourcebundles> rb = resourcebundlesService.findAll();
		AssertJUnit.assertEquals(10, rb.size());
	}

	/**
	 * Test method for
	 * {@link ResourcebundlesService#updateProperties(java.util.Properties, String, Locale, boolean)}
	 * 
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 */
	@Test(enabled = true)
	public void testUpdatePropertiesUpdate() throws URISyntaxException, IOException {
		final String bundlepackage = "";
		final String bundlename = "test";
		Map<File, Locale> fileToLocaleMap = LocaleResolver.resolveLocales(bundlepackage, bundlename);

		for(Entry<File, Locale> entry : fileToLocaleMap.entrySet()) {
			File propertiesFile = entry.getKey();
			Locale locale = entry.getValue();
			Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			resourcebundlesService.updateProperties(properties, bundlename, locale, false);			
		}
		
		Set<Resourcebundles> rb = new HashSet<>(resourcebundlesService.findAll());
		
		AssertJUnit.assertEquals(10, rb.size());	
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
			
			BaseNames baseName = baseNamesService.find("resource.bundles");			
			if(baseName == null) {
				baseName = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames("resource.bundles");
				baseName = baseNamesService.merge(baseName);
			}
			
			LanguageLocales languageLocales = languageLocalesService.find(Locale.UK);			

			if(languageLocales == null) {
				languageLocales = ResourceBundlesDomainObjectFactory.getInstance().newLanguageLocales(Locale.UK);
				languageLocales = languageLocalesService.merge(languageLocales);
			}
			
			BundleNames bundleNames = bundleNamesService.find(baseName, languageLocales);
			if(bundleNames == null) {
				bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName("resource.bundles", Locale.UK);
				bundleNames.setBaseName(baseName);
				bundleNames.setLocale(languageLocales);
				bundleNames = bundleNamesService.merge(bundleNames);
			}
			
			PropertiesKeys propertiesKeys = propertiesKeysService.find("resource.bundles.test.label");
			if(propertiesKeys == null) {
				propertiesKeys = ResourceBundlesDomainObjectFactory.getInstance().newPropertiesKeys("resource.bundles.test.label");
				propertiesKeys = propertiesKeysService.merge(propertiesKeys);
			}

			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles("resource.bundles",
					Locale.UK, "resource.bundles.test.label", "First label");
			resourcebundles.setBundleName(bundleNames);
			resourcebundles.setKey(propertiesKeys);
			
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}
	}

}
