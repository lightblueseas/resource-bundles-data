package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.rest.LanguagesRestResource;
import de.alpharogroup.db.resource.bundles.rest.api.LanguagesResource;

public class LanguagesRestClientTest
{

	private LanguagesResource languagesResource;

	/** The rest client. */
	private LanguagesRestClient restClient;

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
			restClient = new LanguagesRestClient();
			languagesResource = restClient.getLanguagesResource();
			AssertJUnit.assertNotNull(languagesResource);
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
	 * Test method for {@link LanguagesRestResource#findAll()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testFindAll() throws Exception
	{
		// http://localhost:8080/language/find/all
		Response response = languagesResource.findAll();
		final List<Language> list = response.readEntity(List.class);
		assertNotNull(list);
		assertEquals(185, list.size());
	}

	/**
	 * Test method for {@link LanguagesRestResource#findByName(String)}
	 */
	@Test(enabled = false)
	public void testFindByName()
	{
		// http://localhost:8080/language/find/by/name/German
		Response response = languagesResource.findByName("German");
		Language viewModel = response.readEntity(Language.class);
		assertNotNull(viewModel);
		assertEquals(viewModel.getName() ,"German");
		assertEquals(viewModel.getIso639Dash1() ,"de");
	}

	/**
	 * Test method for {@link LanguagesRestResource#findByCode(String)}
	 */
	@Test(enabled = false)
	public void testFindByCode()
	{
		// http://localhost:8080/language/find/by/code/de
		Response response = languagesResource.findByCode("de");
		Language viewModel = response.readEntity(Language.class);
		assertNotNull(viewModel);
		assertEquals(viewModel.getName() ,"German");
		assertEquals(viewModel.getIso639Dash1() ,"de");
	}
}
