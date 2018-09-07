/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
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
	public Response findByCode(String code)
	{
		Language language = getDomainService().findByIso639Dash1(code);
		return Response.ok(language).build();
	}

	@Override
	public Response findByName(String name)
	{
		Language language = getDomainService().findByName(name);
		return Response.ok(language).build();
	}

}