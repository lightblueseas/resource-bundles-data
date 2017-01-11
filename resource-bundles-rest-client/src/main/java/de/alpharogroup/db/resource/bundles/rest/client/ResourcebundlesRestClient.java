package de.alpharogroup.db.resource.bundles.rest.client;

import de.alpharogroup.cxf.rest.client.AbstractRestClient;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import lombok.Getter;

/**
 * The class {@link ResourcebundlesRestClient} is a rest client for the
 * resource-bundles that are persists in the database.
 */
public class ResourcebundlesRestClient extends AbstractRestClient {

	/**
	 * The {@link ResourcebundlesResource}.
	 */
	@Getter
	private final ResourcebundlesResource resourcebundlesResource;

	/**
	 * Instantiates a new {@link ResourcebundlesRestClient} with the default base url.
	 */
	public ResourcebundlesRestClient() {
		this(DEFAULT_BASE_HTTP_URL);
	}

	/**
	 * Instantiates a new {@link ResourcebundlesRestClient}.
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public ResourcebundlesRestClient(final String baseUrl) {
		super(baseUrl);
		resourcebundlesResource = newResource(ResourcebundlesResource.class);
	}

}