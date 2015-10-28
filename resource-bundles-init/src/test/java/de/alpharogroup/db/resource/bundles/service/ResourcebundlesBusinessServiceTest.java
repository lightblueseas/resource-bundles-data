package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.application.DatabaseResourceBundle;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesBusinessServiceTest extends AbstractTestNGSpringContextTests {

	
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
     * @param resourcebundlesService the new resourcebundles service
     */
    public void setResourcebundlesService(
			ResourcebundlesService resourcebundlesService) {
		this.resourcebundlesService = resourcebundlesService;
	}
    
	@Test(enabled=false)
	public void testFindResourceBundles() {
		initResourcebundles();
		DatabaseResourceBundle databaseResourceBundle = new DatabaseResourceBundle("resource.bundles", Locale.UK, resourcebundlesService);
		String actual = databaseResourceBundle.getString("resource.bundles.test.label");
		String expected = "First label";
		AssertJUnit.assertEquals(expected, actual);
		databaseResourceBundle = new DatabaseResourceBundle("resource.bundles", Locale.GERMAN, resourcebundlesService);
		actual = databaseResourceBundle.getString("resource.bundles.test.label");
		expected = "Erstes label";
		AssertJUnit.assertEquals(expected, actual);
		List<Resourcebundles> rb = resourcebundlesService.findAll();
		resourcebundlesService.delete(rb);
	}
	
	private void initResourcebundles() {
		Resourcebundles resourcebundles = ResourceBundlesDomainObjectFactory
				.getInstance().newResourcebundles("resource.bundles",
						"de", "resource.bundles.test.label", "Erstes label");
		resourcebundlesService.merge(resourcebundles);

		resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles("resource.bundles", "en",
						"resource.bundles.test.label", "First label");
		resourcebundlesService.merge(resourcebundles);

	}

}
