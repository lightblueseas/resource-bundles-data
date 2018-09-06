package de.alpharogroup.db.resource.bundles.rest.client;

import de.alpharogroup.db.resource.bundles.rest.api.BundleNamesResource;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;

/**
 * The class {@link BundleNamesRestClientTest}
 */
public class BundleNamesRestClientTest
{

	/** The rest client */
	private BundleNamesRestClient restClient;

	/** The BundleNames rest resource. */
	private BundleNamesResource resource;

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
			restClient = new BundleNamesRestClient();
			resource = restClient.getResource();
			AssertJUnit.assertNotNull(resource);
		}
	}

}
