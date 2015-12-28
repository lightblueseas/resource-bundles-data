package de.alpharogroup.db.resource.bundles.rest.client;

import java.util.Locale;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;

/**
 * The class {@link ResourcebundlesRestClientTest}.
 *
 * Note: you have to start a rest server to test this or you have to mock it.
 */
public class ResourcebundlesRestClientTest
{

	/** The rest client. */
	private ResourcebundlesRestClient restClient;

	/** The resourcebundles resource. */
	private ResourcebundlesResource resourcebundlesResource;

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
			restClient = new ResourcebundlesRestClient();
			resourcebundlesResource = restClient.getResourcebundlesResource();
			AssertJUnit.assertNotNull(resourcebundlesResource);
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
	 * Test method for {@link ResourcebundlesResource#getResponseString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetResponseString()
	{
		// http://localhost:8080/resourcebundle/get/r/string/base-resource-bundles/de_DE/resource.bundles.test.label
		final Response response = resourcebundlesResource.getResponseString("base-resource-bundles",
			"de_DE", "resource.bundles.test.label");
		final KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		AssertJUnit.assertNotNull(keyValuePair);
		AssertJUnit.assertEquals("Erstes label", keyValuePair.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getString(BundleKey)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetStringWithBundleKey()
	{
		final String baseName = "test";
		final String[] paramsBritain = { "Fritz", "Germany" };
		// url can be invoked with a post request, so dont try this to invoke in a browser...
		final Response response = resourcebundlesResource.getString(BundleKey.builder()
			.baseName(baseName).locale(Locale.UK)
			.resourceBundleKey(ResourceBundleKey.builder()
				.key("com.example.gui.prop.with.params.label").parameters(paramsBritain).build())
			.build());
		AssertJUnit.assertNotNull(response);

		final KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		System.out.println(keyValuePair);
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetStringWithParameters()
	{
		// http://localhost:8080/resourcebundle/get/string/test/de_DE/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Deutschland
		final String[] paramsGerman = { "Fritz", "Deutschland" };
		final String baseName = "test";

		Response response = resourcebundlesResource.getString(baseName, "de_DE",
			"com.example.gui.prop.with.params.label", paramsGerman);

		KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		AssertJUnit.assertNotNull(keyValuePair);
		AssertJUnit.assertEquals("Hallo ich bin Fritz und komme aus Deutschland.",
			keyValuePair.getValue());

		// http://localhost:8080/resourcebundle/get/string/test/en_GB/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Germany
		final String[] paramsBritain = { "Fritz", "Germany" };
		response = resourcebundlesResource.getString(baseName, "en_GB",
			"com.example.gui.prop.with.params.label", paramsBritain);
		keyValuePair = response.readEntity(KeyValuePair.class);
		AssertJUnit.assertNotNull(keyValuePair);
		AssertJUnit.assertEquals("Hello i am Fritz and i come from Germany.", keyValuePair.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetString()
	{
		// http://localhost:8080/resourcebundle/get/string/base-resource-bundles/de_DE/resource.bundles.test.label
		final Response response = resourcebundlesResource.getString("base-resource-bundles", "de_DE",
			"resource.bundles.test.label");
		final KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		AssertJUnit.assertNotNull(keyValuePair);
		AssertJUnit.assertEquals("Erstes label",
			keyValuePair.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getProperties(String, String)}.
	 */
	@Test(enabled = false)
	public void testGetProperties()
	{
		// http://localhost:8080/resourcebundle/get/properties/base-resource-bundles/de_DE
		Response response = resourcebundlesResource.getProperties("base-resource-bundles",
			"de_DE");

		Properties properties = (Properties)response.readEntity(Properties.class);
		AssertJUnit.assertNotNull(properties);
		AssertJUnit.assertEquals("Erstes label",
			properties.getProperty("resource.bundles.test.label"));

		// http://localhost:8080/resourcebundle/get/properties/test/de_DE
		response = resourcebundlesResource.getProperties("test", "de_DE");
		properties = (Properties)response.readEntity(Properties.class);
		AssertJUnit.assertNotNull(properties);
		AssertJUnit.assertTrue(properties.size() == 4);
	}

	/**
	 * Test method for {@link ResourcebundlesResource#find(String, String, String)}.
	 */
	@Test(enabled = false)
	public void testFind()
	{
		// http://localhost:8080/resourcebundle/find/base-resource-bundles/de_DE/resource.bundles.test.label
		final Resourcebundle resourcebundle2 = resourcebundlesResource.find("base-resource-bundles",
			"de_DE", "resource.bundles.test.label");
		AssertJUnit.assertNotNull(resourcebundle2);
		AssertJUnit.assertEquals("Erstes label", resourcebundle2.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#get(String)}.
	 */
	@Test(enabled = false)
	public void testGet()
	{
		// http://localhost:8080/resourcebundle/get/1
		final Resourcebundle resourcebundle1 = resourcebundlesResource
			.get(Integer.valueOf(1).toString());
		AssertJUnit.assertNotNull(resourcebundle1);
		AssertJUnit.assertEquals("Erstes label", resourcebundle1.getValue());
	}


}
