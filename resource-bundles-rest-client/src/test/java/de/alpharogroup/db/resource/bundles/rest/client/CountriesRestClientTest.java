package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.rest.CountriesRestResource;
import de.alpharogroup.db.resource.bundles.rest.api.CountriesResource;

public class CountriesRestClientTest
{

	private CountriesResource countriesResource;

	/** The rest client. */
	private CountriesRestClient restClient;

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
			restClient = new CountriesRestClient();
			countriesResource = restClient.getCountriesResource();
			AssertJUnit.assertNotNull(countriesResource);
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
	 * Test method for {@link CountriesRestResource#findAll()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testFindAll() throws Exception
	{
		// http://localhost:8080/country/find/all
		Response response = countriesResource.findAll();
		final List<Country> list = response.readEntity(List.class);
		assertNotNull(list);
		assertEquals(252, list.size());
	}

	/**
	 * Test method for {@link CountriesRestResource#find(String)}
	 */
	@Test(enabled = false)
	public void testFind() throws Exception
	{
		throw new RuntimeException("not yet implemented");
	}
}
