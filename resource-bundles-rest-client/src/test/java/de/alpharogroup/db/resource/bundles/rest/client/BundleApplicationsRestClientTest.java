package de.alpharogroup.db.resource.bundles.rest.client;

import de.alpharogroup.db.resource.bundles.rest.api.BundleApplicationsResource;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;

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

}
