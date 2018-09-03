package de.alpharogroup.db.resource.bundles.rest.client;

import de.alpharogroup.cxf.rest.client.AbstractRestClient;

import de.alpharogroup.db.resource.bundles.rest.api.BundleApplicationsResource;
import lombok.Getter;

/**
 * The class {@link BundleApplicationsRestClient}
 */
public class BundleApplicationsRestClient extends AbstractRestClient
{
	/**
	 * The {@link BundleApplicationsResource}
	 */
	@Getter
	private final BundleApplicationsResource resource;

	public BundleApplicationsRestClient()
	{
		this(DEFAULT_BASE_HTTP_URL);
	}

	/**
	 * Instantiates a new {@link BundleApplicationsRestClient}
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public BundleApplicationsRestClient(final String baseUrl)
	{
		super(baseUrl);
		resource = newResource(BundleApplicationsResource.class);
	}
}
