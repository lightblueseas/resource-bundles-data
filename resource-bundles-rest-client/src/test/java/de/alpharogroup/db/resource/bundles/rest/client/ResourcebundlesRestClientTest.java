package de.alpharogroup.db.resource.bundles.rest.client;

import java.util.Locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;

/**
 * The class {@link ResourcebundlesRestClientTest}.
 */
public class ResourcebundlesRestClientTest {

	/**
	 * Test get resourcebundles resource. Note: you have to start a rest server
	 * to test this or you have to mock it.
	 */
	@Test(enabled = true)
	public void testGetResourcebundlesResource() {
		ResourcebundlesRestClient restClient = new ResourcebundlesRestClient("http://localhost:8080");
		ResourcebundlesResource resourcebundlesResource = restClient.getResourcebundlesResource();
		AssertJUnit.assertNotNull(resourcebundlesResource);
		// http://localhost:8080/resourcebundle/get/1
		Resourcebundle resourcebundle1 = resourcebundlesResource.get(Integer.valueOf(1).toString());
		AssertJUnit.assertNotNull(resourcebundle1);
		AssertJUnit.assertEquals("Erstes label", resourcebundle1.getValue());
		// http://localhost:8080/resourcebundle/find/base-resource-bundles/de_DE/resource.bundles.test.label
		Resourcebundle resourcebundle2 = resourcebundlesResource.find("base-resource-bundles", "de_DE",
				"resource.bundles.test.label");
		AssertJUnit.assertNotNull(resourcebundle2);
		AssertJUnit.assertEquals("Erstes label", resourcebundle2.getValue());
		// http://localhost:8080/resourcebundle/get/string/base-resource-bundles/de_DE/resource.bundles.test.label
		String value = resourcebundlesResource.getString("base-resource-bundles", "de_DE",
				"resource.bundles.test.label");
		AssertJUnit.assertNotNull(value);
		AssertJUnit.assertEquals("Erstes label", value);
		// http://localhost:8080/resourcebundle/get/string/test/de_DE/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Deutschland
		String[] paramsGerman = { "Fritz", "Deutschland" };
		String baseName = "test";
		value = resourcebundlesResource.getString(baseName, "de_DE", "com.example.gui.prop.with.params.label",
				paramsGerman);
		AssertJUnit.assertNotNull(value);
		AssertJUnit.assertEquals("Hallo ich bin Fritz und komme aus Deutschland.", value);
		// http://localhost:8080/resourcebundle/get/string/test/en_GB/com.example.gui.prop.with.params.label/parameters?parameter=Fritz&parameter=Germany
		String[] paramsBritain = { "Fritz", "Germany" };
		value = resourcebundlesResource.getString(baseName, "en_GB", "com.example.gui.prop.with.params.label",
				paramsBritain);
		AssertJUnit.assertNotNull(value);
		AssertJUnit.assertEquals("Hello i am Fritz and i come from Germany.", value);
		// url can be invoked with a post request, so dont try this to invoke in a browser...
		value = resourcebundlesResource
				.getString(BundleKey.builder().baseName(baseName)
						.locale(Locale.UK).resourceBundleKey(ResourceBundleKey.builder()
								.key("com.example.gui.prop.with.params.label").parameters(paramsBritain).build())
				.build());
		AssertJUnit.assertNotNull(value);
	}

}
