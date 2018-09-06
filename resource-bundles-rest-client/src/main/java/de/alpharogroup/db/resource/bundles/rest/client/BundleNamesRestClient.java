package de.alpharogroup.db.resource.bundles.rest.client;

import de.alpharogroup.cxf.rest.client.AbstractRestClient;

import de.alpharogroup.db.resource.bundles.rest.api.BundleNamesResource;
import lombok.Getter;

/**
 * The class {@link BundleNamesRestClient}
 */
public class BundleNamesRestClient extends AbstractRestClient
{
	/**
	 * The {@link BundleNamesResource}
	 */
	@Getter
	private final BundleNamesResource resource;

	public BundleNamesRestClient()
	{
		this(DEFAULT_BASE_HTTP_URL);
	}

	/**
	 * Instantiates a new {@link BundleNamesRestClient}
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public BundleNamesRestClient(final String baseUrl)
	{
		super(baseUrl);
		resource = newResource(BundleNamesResource.class);
	}
}
