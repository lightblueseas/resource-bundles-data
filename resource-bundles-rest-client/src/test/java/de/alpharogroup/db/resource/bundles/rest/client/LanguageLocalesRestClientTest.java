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
package de.alpharogroup.db.resource.bundles.rest.client;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.rest.LanguageLocalesRestResource;
import de.alpharogroup.db.resource.bundles.rest.api.LanguageLocalesResource;

public class LanguageLocalesRestClientTest
{

	private LanguageLocalesResource resource;

	/** The rest client. */
	private LanguageLocalesRestClient restClient;

	/**
	 * Sets the up method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUpMethod() throws Exception
	{
		if (restClient == null)
		{
			restClient = new LanguageLocalesRestClient();
			resource = restClient.getResource();
			AssertJUnit.assertNotNull(resource);
		}
	}

	/**
	 * Tear down method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDownMethod() throws Exception
	{
	}


	/**
	 * Test method for {@link LanguageLocalesRestResource#findAll()}
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void testFindAll() throws Exception
	{
		// http://localhost:8080/language/locale/find/all
		Response response = resource.findAll();
		final List<LanguageLocale> list = response.readEntity(List.class);
		assertNotNull(list);
		assertEquals(18, list.size());
	}

	/**
	 * Test method for {@link LanguageLocalesRestResource#find(String)}
	 */
	@Test(enabled = false)
	public void testFindByName()
	{
		// http://localhost:8080/language/locale/find/by/locale/de
		Response response = resource.find("de");
		LanguageLocale viewModel = response.readEntity(LanguageLocale.class);
		assertNotNull(viewModel);
		assertEquals(viewModel.getLocale(), "de");
	}
}
