package de.alpharogroup.db.resource.bundles.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;

/**
 * The class {@link ResourcebundlesDaoTest}.
 */
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesDaoTest extends AbstractTestNGSpringContextTests {

	/** The resourcebundles dao. */
	@Autowired
	private ResourcebundlesDao resourcebundlesDao;
	
	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService;

	/**
	 * Gets the all resourcebundles.
	 *
	 * @return the all resourcebundles
	 */
	@Test(enabled=false)
	public void getAllResourcebundles() {
		initResourcebundles();
		List<Resourcebundles> list = resourcebundlesService.findAll();
		List<Resourcebundles> list2 = resourcebundlesDao.findAll();
		AssertJUnit.assertEquals(list.size(), list2.size());
		resourcebundlesService.delete(list);
	}
	
	/**
	 * Inits the resourcebundles.
	 */
	@Transactional
	private void initResourcebundles() {
		Resourcebundles resourcebundles = ResourceBundlesDomainObjectFactory
				.getInstance().newResourcebundles("resource.bundles",
						"de_DE", "resource.bundles.test.label", "Erstes label");
		resourcebundles = (Resourcebundles) 
				resourcebundlesService.merge(resourcebundles);

		resourcebundles = ResourceBundlesDomainObjectFactory.getInstance()
				.newResourcebundles("resource.bundles", "UK",
						"resource.bundles.test.label", "First label");
		resourcebundlesService.merge(resourcebundles);
	}

}
