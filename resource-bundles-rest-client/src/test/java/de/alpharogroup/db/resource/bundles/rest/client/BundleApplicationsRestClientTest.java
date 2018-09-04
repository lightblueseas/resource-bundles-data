package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

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

/**
 * The class {@link BundleApplicationsRestClientTest}
 */
public class BundleApplicationsRestClientTest
{

	/** The rest client */
	private BundleApplicationsRestClient restClient;

	/** The BundleApplications rest resource. */
	private BundleApplicationsResource resource;

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
	 * Test method for {@link BundleApplicationsRestResource#findAllBundleNames(String)}
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAllBundleNames()
	{
		// http://localhost:8080/bundle/applications/find/all/bundlenames/base-bundle-application
		String owner = "base-bundle-application";
		Response response = resource.findAllBundleNames(owner);
		final Set<BundleName> set = response.readEntity(Set.class);
		assertNotNull(set);
		assertEquals(4, set.size());
	}

	/**
	 * Test method for {@link BundleApplicationsRestResource#find(String)}
	 */
	@Test
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
	 * Test method for {@link BundleApplicationsRestResource#get(BundleName)}
	 */
	@Test
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
		
		BundleName bundleName = BundleName.builder().baseName(baseName).locale(languageLocale).build();
		bundleName.setId(4);
		bundleName.setVersion(1);		
		bundleName.setOwner(owner);
		
		response = resource.get(bundleName);
		BundleApplication entity = response.readEntity(BundleApplication.class);
		assertNotNull(entity);
		assertEquals(owner, entity);
		
	}

	/**
	 * Test method for {@link BundleApplicationsRestResource#getOrCreateNewBundleApplications(String, String)}
	 */
	@Test
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

	/**
	 * Test method for {@link BundleApplicationsRestResource#getOrCreateNewBundleApplications(BundleApplication)}
	 */
	@Test
	public void testGetOrCreateNewBundleApplicationsBundleApplication()
	{
		// TODO implement unit test...
		LanguageLocale languageLocale = LanguageLocale.builder().locale("en_US").build();
		BundleApplication searchCriteria = BundleApplication.builder().defaultLocale(languageLocale).build();
		resource.getOrCreateNewBundleApplications(searchCriteria);
	}

}
