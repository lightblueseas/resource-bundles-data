package de.alpharogroup.db.resource.bundles.rest.client;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;

/**
 * The class {@link ResourcebundlesRestClientTest}.
 */
public class ResourcebundlesRestClientTest
{

	/**
	 * Test get resourcebundles resource. 
	 * Note: you have to start a rest server to test this or you have to mock it.
	 */
	@Test(enabled=true)
	public void testGetResourcebundlesResource()
	{
		ResourcebundlesRestClient restClient = new ResourcebundlesRestClient("http://localhost:8080");
		ResourcebundlesResource resourcebundlesResource = restClient.getResourcebundlesResource();
		AssertJUnit.assertNotNull(resourcebundlesResource);
		// http://localhost:8080/resourcebundle/get/1
		Resourcebundle resourcebundle1 = resourcebundlesResource.get(Integer.valueOf(1).toString());
		AssertJUnit.assertNotNull(resourcebundle1);
		AssertJUnit.assertEquals("Erstes label", resourcebundle1.getValue());
		// http://localhost:8080/resourcebundle/find/base-resource-bundles/de_DE/resource.bundles.test.label
		Resourcebundle resourcebundle2 = resourcebundlesResource.find("base-resource-bundles", "de_DE", "resource.bundles.test.label");
		AssertJUnit.assertNotNull(resourcebundle2);
		AssertJUnit.assertEquals("Erstes label", resourcebundle2.getValue());
	}

}
