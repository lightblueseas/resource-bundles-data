package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.rest.LanguageLocalesRestResource;
import de.alpharogroup.db.resource.bundles.rest.api.LanguageLocalesResource;

public class LanguageLocalesRestClientTest
{

	private LanguageLocalesResource resource;

	/** The rest client. */
	private LanguageLocalesRestClient restClient;

	/**
	 * Sets the up method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUpMethod() throws Exception
	{
		if (restClient == null)
		{
			restClient = new LanguageLocalesRestClient();
			resource = restClient.getResource();
			AssertJUnit.assertNotNull(resource);
		}
	}

	/**
	 * Tear down method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDownMethod() throws Exception
	{
	}
	

	/**
	 * Test method for {@link LanguageLocalesRestResource#findAll()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testFindAll() throws Exception
	{
		// http://localhost:8080/language/locale/find/all
		Response response = resource.findAll();
		final List<LanguageLocale> list = response.readEntity(List.class);
		assertNotNull(list);
		assertEquals(18, list.size());
	}

	/**
	 * Test method for {@link LanguageLocalesRestResource#find(String)}
	 */
	@Test(enabled = true)
	public void testFindByName()
	{
		// http://localhost:8080/language/locale/find/by/locale/de
		Response response = resource.find("de");
		LanguageLocale viewModel = response.readEntity(LanguageLocale.class);
		assertNotNull(viewModel);
		assertEquals(viewModel.getLocale() ,"de");
	}
}
