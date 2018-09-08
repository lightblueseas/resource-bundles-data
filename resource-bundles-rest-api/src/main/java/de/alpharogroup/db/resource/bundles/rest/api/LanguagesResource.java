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
package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.service.rs.api.RestfulResource;

/**
 * The rest interface {@link LanguagesResource}
 */
@Path("/language/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface LanguagesResource extends RestfulResource<Integer, Language>
{

	/**
	 * Find all {@link Language} objects.
	 *
	 * @return the list of {@link Language} objects.
	 */
	@GET
	@Path("/find/all")
	Response findAll();

	/**
	 * Find the {@link Language} object by code.
	 *
	 * @param code
	 *            the code
	 * @return the language
	 */
	@GET
	@Path("/find/by/code/{code}")
	Response findByCode(@PathParam("code") String code);

	/**
	 * Find the {@link Language} object by name.
	 *
	 * @param name
	 *            the name
	 * @return the language
	 */
	@GET
	@Path("/find/by/name/{name}")
	Response findByName(@PathParam("name") String name);


}
