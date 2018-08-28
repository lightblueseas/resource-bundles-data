package de.alpharogroup.db.resource.bundles.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.rest.api.CountriesResource;
import de.alpharogroup.db.resource.bundles.service.CountryDomainService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link CountriesRestResource} provides an implementation of the inteface
 * {@link CountriesResource}.
 */
public class CountriesRestResource
	extends
		AbstractRestfulResource<Integer, Country, CountryDomainService>
	implements
		CountriesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response findAll()
	{
		List<Country> all = getDomainService().findAll();
		return Response.ok(all).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response find(String name)
	{
		Country country = getDomainService().find(name);
		return Response.ok(country).build();
	}

}
