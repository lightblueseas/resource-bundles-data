/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
package de.alpharogroup.db.resource.bundles.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;


/**
 * The class {@link ResourcebundlesBusinessServiceH2Test}.
 */
@ContextConfiguration(locations = "classpath:test-h2-applicationContext.xml")
public class ResourcebundlesBusinessServiceH2Test extends AbstractResourcebundlesBusinessServiceTest
{

	@Override
	@Test(enabled = true)
	public void testFindBundleApplications()
	{
		super.testFindBundleApplications();
	}

	@Override
	@Test(enabled = true)
	public void testFindBundleNames()
	{
		super.testFindBundleNames();
	}

	@Override
	@Test(enabled = true)
	public void testFindLanguageLocales()
	{
		super.testFindLanguageLocales();
	}

	@Override
	@Test(enabled = true)
	public void testFindResourceBundles()
	{
		super.testFindResourceBundles();
	}

	@Override
	@Test(enabled = true)
	public void testUpdateProperties() throws URISyntaxException, IOException
	{
		super.testUpdateProperties();
	}

	@Override
	@Test(enabled = true)
	public void testUpdatePropertiesUpdate() throws URISyntaxException, IOException
	{
		super.testUpdatePropertiesUpdate();
	}

}
