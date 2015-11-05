package de.alpharogroup.db.resource.bundles.rest.client;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;

public class ResourcebundlesRestClientTest
{

	/**
	 * Test get resourcebundles resource. 
	 * Note: you have to start a rest server to test this or you have to mock it.
	 */
	@Test(enabled=false)
	public void testGetResourcebundlesResource()
	{
		ResourcebundlesRestClient restClient = new ResourcebundlesRestClient("http://localhost:8080");
		ResourcebundlesResource resourcebundlesResource = restClient.getResourcebundlesResource();
		AssertJUnit.assertNotNull(resourcebundlesResource);
		Resourcebundle resourcebundle = resourcebundlesResource.get(Integer.valueOf(1).toString());
		AssertJUnit.assertNotNull(resourcebundle);
		AssertJUnit.assertEquals("Erstes label", resourcebundle.getValue());
	}

}
