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
package de.alpharogroup.db.resource.bundles.daos;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The class {@link ResourcebundlesDaoTest}.
 */
@ContextConfiguration(locations = "classpath:test-h2-applicationContext.xml")
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
	@Test(enabled = true)
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
	protected void initResourcebundles() {
		Resourcebundles resourcebundles = resourcebundlesService.contains("resource.bundles", Locale.GERMANY,
				"resource.bundles.test.label");
		if (resourcebundles == null) {
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles("resource.bundles",
					Locale.GERMAN, "resource.bundles.test.label", "Erstes label");
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}

		resourcebundles = resourcebundlesService.contains("resource.bundles", Locale.UK, "resource.bundles.test.label");
		if (resourcebundles == null) {
			resourcebundles = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles("resource.bundles",
					Locale.UK, "resource.bundles.test.label", "First label");
			resourcebundles = resourcebundlesService.merge(resourcebundles);
		}

	}

}
