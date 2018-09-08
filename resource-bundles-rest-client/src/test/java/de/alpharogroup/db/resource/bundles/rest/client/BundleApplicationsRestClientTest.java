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
package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.rest.BundleApplicationsRestResource;
import de.alpharogroup.db.resource.bundles.rest.api.BundleApplicationsResource;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;

/**
 * The class {@link BundleApplicationsRestClientTest}
 */
public class BundleApplicationsRestClientTest
{

	/** The BundleApplications rest resource. */
	private BundleApplicationsResource resource;

	/** The rest client */
	private BundleApplicationsRestClient restClient;

	/**
	 * Sets the up method
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUpMethod() throws Exception
	{
		if (restClient == null)
		{
			restClient = new BundleApplicationsRestClient();
			resource = restClient.getResource();
			AssertJUnit.assertNotNull(resource);
		}
	}

	/**
	 * Test method for {@link ResourcebundlesResource#delete(BundleApplication)}
	 */
	@Test(enabled = true)
	public void testDelete()
	{
		BundleApplication entity;
		// DELETE
		// http://localhost:8080/bundle/applications/delete/
		String owner = "foo-bar.com";
		Response response = resource.find(owner);

		entity = response.readEntity(BundleApplication.class);
		assertNotNull(entity);
		assertEquals(entity.getName(), owner);

		resource.delete(entity);
		response = resource.find(owner);
		assertTrue(response.getLength() == 0);
	}

	/**
	 * Test method for {@link BundleApplicationsRestResource#find(String)}
	 */
	@Test(enabled = false)
	public void testFind()
	{
		// http://localhost:8080/bundle/applications/find/by/name/base-bundle-application
		String owner = "base-bundle-application";
		Response response = resource.find(owner);
		final BundleApplication entity = response.readEntity(BundleApplication.class);
		assertNotNull(entity);
		assertEquals(entity.getName(), owner);
	}

	/**
	 * Test method for {@link ResourcebundlesResource#findAllBundleApplications()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testfindAllBundleApplications()
	{
		// http://localhost:8080/bundle/applications/find/all
		final Response response = resource.findAll();
		final List<BundleApplication> bundleApplications = response.readEntity(List.class);
		assertNotNull(bundleApplications);
		assertEquals(2, bundleApplications.size());
	}

	/**
	 * Test method for {@link BundleApplicationsRestResource#findAllBundleNames(String)}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testFindAllBundleNames()
	{
		// http://localhost:8080/bundle/applications/find/all/bundlenames/base-bundle-application
		String owner = "base-bundle-application";
		Response response = resource.findAllBundleNames(owner);
		final Set<BundleName> set = response.readEntity(Set.class);
		assertNotNull(set);
		assertEquals(5, set.size());
	}

	/**
	 * Test method for {@link BundleApplicationsRestResource#get(BundleName)}
	 */
	@Test(enabled = false)
	public void testGet()
	{
		// POST
		// http://localhost:8080/bundle/applications/find/by/bundlename
		BaseName baseName = BaseName.builder().name("test").build();
		baseName.setId(2);
		baseName.setVersion(1);
		LanguageLocale languageLocale = LanguageLocale.builder().locale("en_US").build();
		languageLocale.setId(3);
		languageLocale.setVersion(1);

		String ownerName = "base-bundle-application";
		Response response = resource.find(ownerName);
		BundleApplication owner = response.readEntity(BundleApplication.class);

		BundleName bundleName = BundleName.builder().baseName(baseName).locale(languageLocale)
			.build();
		bundleName.setId(4);
		bundleName.setVersion(1);
		bundleName.setOwner(owner);

		response = resource.get(bundleName);
		BundleApplication entity = response.readEntity(BundleApplication.class);
		assertNotNull(entity);
		assertEquals(owner, entity);

	}

	/**
	 * Test method for
	 * {@link BundleApplicationsRestResource#getOrCreateNewBundleApplications(BundleApplication)}
	 */
	@Test(enabled = false)
	public void testGetOrCreateNewBundleApplicationsBundleApplication()
	{
		// POST
		// http://localhost:8080/bundle/applications/get/or/create
		LanguageLocale languageLocale = LanguageLocale.builder().locale("en_US").build();
		BundleApplication searchCriteria = BundleApplication.builder().defaultLocale(languageLocale)
			.build();
		resource.getOrCreateNewBundleApplications(searchCriteria);
	}

	/**
	 * Test method for
	 * {@link BundleApplicationsRestResource#getOrCreateNewBundleApplications(String, String)}
	 */
	@Test(enabled = false)
	public void testGetOrCreateNewBundleApplicationsStringString()
	{
		// http://localhost:8080/bundle/applications/get/or/create/base-bundle-application/en_US
		String ownerName = "base-bundle-application";
		Response response = resource.find(ownerName);
		BundleApplication owner = response.readEntity(BundleApplication.class);
		assertNotNull(owner);

		response = resource.getOrCreateNewBundleApplications(ownerName, "en_US");
		BundleApplication entity = response.readEntity(BundleApplication.class);
		assertNotNull(entity);
		assertEquals(owner, entity);
	}

}
