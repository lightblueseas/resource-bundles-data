package de.alpharogroup.db.resource.bundles.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ResourcebundlesDao resourcebundlesDao;
	
	@Autowired
	private ResourcebundlesService resourcebundlesService;

	@Test(enabled=false)
	public void getAllResourcebundles() {
		initResourcebundles();
		List<Resourcebundles> list = resourcebundlesService.findAll();
		System.out.println(list.size());
		resourcebundlesService.delete(list);
	}
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
