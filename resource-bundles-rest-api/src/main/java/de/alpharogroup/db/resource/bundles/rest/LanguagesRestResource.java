package de.alpharogroup.db.resource.bundles.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.rest.api.LanguagesResource;
import de.alpharogroup.db.resource.bundles.service.api.LanguageService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link LanguagesRestResource} provides an implementation of the inteface
 * {@link LanguagesResource}
 */
public class LanguagesRestResource
	extends
		AbstractRestfulResource<java.lang.Integer, Language, LanguageService>
	implements
		LanguagesResource
{

	@Override
	public Response findAll()
	{
		List<Language> all = getDomainService().findAll();
		return Response.ok(all).build();
	}

	@Override
	public Response findByName(String name)
	{
		Language language = getDomainService().findByName(name);
		return Response.ok(language).build();
	}

	@Override
	public Response findByCode(String code)
	{
		Language language = getDomainService().findByIso639Dash1(code);
		return Response.ok(language).build();
	}

}