/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
import java.util.Locale;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;

/**
 * The class {@link ResourcebundlesRestClientTest}.
 *
 * Note: you have to start a rest server to test this or you have to mock it.
 */
public class ResourcebundlesRestClientTest
{

	/** The resourcebundles resource. */
	private ResourcebundlesResource resource;

	/** The rest client. */
	private ResourcebundlesRestClient restClient;

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
			resource = restClient.getResource();
			assertNotNull(resource);
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
	 * Test method for {@link ResourcebundlesResource#find(String, String, String)}.
	 */
	@Test(enabled = false)
	public void testFind()
	{
		// http://localhost:8080/resourcebundle/find/base-bundle-application/base-resource-bundles/de_DE/resource.bundles.test.label
		final Resourcebundle resourcebundle2 = resource.find(
			"base-bundle-application", "base-resource-bundles", "de",
			"resource.bundles.test.label");
		assertNotNull(resourcebundle2);
		assertEquals("Erstes label", resourcebundle2.getValue().getName());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#findAllBundleApplications()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testfindAllBundleApplications()
	{
		// http://localhost:8080/resourcebundle/get/r/all/apps
		final Response response = resource.findAllBundleApplications();
		final List<BundleApplication> bundleApplications = response.readEntity(List.class);
		assertNotNull(bundleApplications);
		assertEquals(2, bundleApplications.size());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#get(String)}.
	 */
	@Test(enabled = false)
	public void testGet()
	{
		// http://localhost:8080/resourcebundle/get/1
		final Resourcebundle resourcebundle1 = resource
			.get(Integer.valueOf(1).toString());
		assertNotNull(resourcebundle1);
		assertEquals("Erstes label", resourcebundle1.getValue().getName());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getBundleApp(String)}
	 */
	@Test(enabled = false)
	public void testGetBundleApp()
	{
		// http://localhost:8080/resourcebundle/get/r/app/base-bundle-application
		final Response response = resource.getBundleApp("base-bundle-application");
		final BundleApplication bundleApplication = response.readEntity(BundleApplication.class);
		assertNotNull(bundleApplication);
		assertEquals("base-bundle-application", bundleApplication.getName());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getProperties(String, String, String)}.
	 */
	@Test(enabled = false)
	public void testGetProperties()
	{
		Response response;
		Properties properties;
		// http://localhost:8080/resourcebundle/get/properties/base-bundle-application/base-resource-bundles/de
		response = resource.getProperties("base-bundle-application",
			"base-resource-bundles", "de");

		properties = response.readEntity(Properties.class);
		assertNotNull(properties);
		assertEquals("Erstes label",
			properties.getProperty("resource.bundles.test.label"));

		// http://localhost:8080/resourcebundle/get/properties/base-bundle-application/test/de_DE
		response = resource.getProperties("base-bundle-application", "test",
			"en_US");
		properties = response.readEntity(Properties.class);
		assertNotNull(properties);
		assertTrue(properties.size() == 4);
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getResponseString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetResponseString()
	{
		// http://localhost:8080/resourcebundle/get/r/string/base-bundle-application/base-resource-bundles/de/resource.bundles.test.label
		final Response response = resource.getResponseString(
			"base-bundle-application", "base-resource-bundles", "de",
			"resource.bundles.test.label");
		final KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		assertNotNull(keyValuePair);
		assertEquals("Erstes label", keyValuePair.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetString()
	{
		// http://localhost:8080/resourcebundle/get/string/base-bundle-application/base-resource-bundles/de_DE/resource.bundles.test.label
		final Response response = resource.getString("base-bundle-application",
			"base-resource-bundles", "de", "resource.bundles.test.label");
		final KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		assertNotNull(keyValuePair);
		assertEquals("Erstes label", keyValuePair.getValue());
	}

	/**
	 * Test method for {@link ResourcebundlesResource#getString(String, String, String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testGetStringWithParameters()
	{
		// http://localhost:8080/resourcebundle/get/string/base-bundle-application/test/de_DE/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Deutschland
		final String[] paramsGerman = { "Fritz", "Deutschland" };
		final String baseName = "test";

		Response response = resource.getString("base-bundle-application", baseName,
			"de_DE", "com.example.gui.prop.with.params.label", paramsGerman);

		KeyValuePair<String, String> keyValuePair = response.readEntity(KeyValuePair.class);
		assertNotNull(keyValuePair);
		assertEquals("Hallo ich bin Fritz und komme aus Deutschland.",
			keyValuePair.getValue());

		// http://localhost:8080/resourcebundle/get/string/base-bundle-application/test/en_US/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Germany
		final String[] paramsBritain = { "Fritz", "Germany" };
		response = resource.getString("base-bundle-application", baseName, "en_US",
			"com.example.gui.prop.with.params.label", paramsBritain);
		keyValuePair = response.readEntity(KeyValuePair.class);
		assertNotNull(keyValuePair);
		assertEquals("Hello i am Fritz and i come from Germany.",
			keyValuePair.getValue());
	}
	
	/**
	 * Test method for {@link ResourcebundlesResource#updateProperties(Quattro)}
	 */
	@Test(enabled = false)
	public void testUpdateProperties() {
		Properties properties;
		String ownerName;
		String baseName;
		Locale locale;
		properties = new Properties();
		properties.setProperty("utest.one", "one val");
		properties.setProperty("utest.two", "two val");
		properties.setProperty("utest.three", "three val");
		ownerName = "base-bundle-application";
		baseName = "testbase";
		locale = Locale.GERMAN;
		Quattro<Properties, String, String, Locale> quattro =  Quattro.<Properties, String, String, Locale>builder()
			.topLeft(properties)
			.topRight(ownerName)
			.bottomLeft(baseName)
			.bottomRight(locale)
			.build();
		Response response = resource.updateProperties(quattro);
		BundleName bundleName = response.readEntity(BundleName.class);
		assertNotNull(bundleName);
		assertEquals(baseName, bundleName.getBaseName().getName());
	}

}
