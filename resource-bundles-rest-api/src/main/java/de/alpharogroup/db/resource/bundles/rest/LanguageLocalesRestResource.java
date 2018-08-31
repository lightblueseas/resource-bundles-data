package de.alpharogroup.db.resource.bundles.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.rest.api.LanguageLocalesResource;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocaleService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link LanguageLocalesRestResource} provides an implementation of the inteface
 * {@link LanguageLocalesResource}
 */
public class LanguageLocalesRestResource
	extends
		AbstractRestfulResource<java.lang.Integer, LanguageLocale, LanguageLocaleService>
	implements
		LanguageLocalesResource
{

	@Override
	public Response findAll()
	{
		List<LanguageLocale> all = getDomainService().findAll();
		return Response.ok(all).build();
	}

	@Override
	public Response find(String locale)
	{
		LanguageLocale languageLocale = getDomainService().find(locale);
		return Response.ok(languageLocale).build();
	}

}