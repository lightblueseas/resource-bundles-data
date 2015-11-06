package de.alpharogroup.db.resource.bundles.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import lombok.Getter;

/**
 * The class {@link ResourcebundlesRestClient} is a rest client for the resource-bundles that are persists in the database.
 */
public class ResourcebundlesRestClient
{
	
	/**
	 * Gets the resourcebundles resource.
	 *
	 * @return the resourcebundles resource
	 */
	@Getter
	private final ResourcebundlesResource resourcebundlesResource;

	/**
	 * Instantiates a new {@link ResourcebundlesRestClient}.
	 *
	 * @param baseUrl the base url
	 */
	public ResourcebundlesRestClient(String baseUrl)
	{
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJsonProvider());
        
        resourcebundlesResource  = JAXRSClientFactory.create(baseUrl, ResourcebundlesResource.class, providers);
        WebClient.client(resourcebundlesResource).accept(MediaType.APPLICATION_JSON);
        WebClient.client(resourcebundlesResource).type(MediaType.APPLICATION_JSON);
	}

}
