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
package de.alpharogroup.db.resource.bundles.rest;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.ws.rs.core.Response;

import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundleService;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link ResourcebundlesRestResource} provides an implementation of the inteface
 * {@link ResourcebundlesResource}.
 */
public class ResourcebundlesRestResource
	extends
		AbstractRestfulResource<Integer, Resourcebundle, ResourcebundleService>
	implements
		ResourcebundlesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(String bundleappname, String baseName, String locale, String key)
	{
		final BundleApplication bundleApplication = getDomainService().find(bundleappname);
		final Locale loc = LocaleResolver.resolveLocale(locale);
		return getDomainService().find(bundleApplication, baseName, loc, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle get(String id)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final Resourcebundle resourcebundle = resourcebundleService.read(Integer.valueOf(id));
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getProperties(String bundleappname, String baseName, String locale)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final BundleApplication bundleApplication = resourcebundleService.find(bundleappname);
		Properties properties = getDomainService().getProperties(bundleApplication, baseName,
			locale);
		if (properties == null)
		{
			properties = new Properties();
		}
		return Response.ok(properties).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getResponseString(String bundleappname, String baseName, String locale,
		String key)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final BundleApplication bundleApplication = getDomainService().find(bundleappname);
		final String result = resourcebundleService.getString(bundleApplication, baseName, locale,
			key);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getString(String bundleappname, String baseName, String locale, String key)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final BundleApplication bundleApplication = getDomainService().find(bundleappname);
		final String result = resourcebundleService.getString(bundleApplication, baseName, locale,
			key);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response getString(String bundleappname, String baseName, String locale, String key,
		String[] params)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final BundleApplication bundleApplication = getDomainService().find(bundleappname);
		final String result = resourcebundleService.getString(bundleApplication, baseName, locale,
			key, params);
		return Response.ok(KeyValuePair.builder().key(key).value(result).build()).build();
	}

	@Override
	public Response getBundleApp(String name)
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		final BundleApplication bundleApplication = resourcebundleService.find(name);
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response findAllBundleApplications()
	{
		final ResourcebundleService resourcebundleService = getDomainService();
		List<BundleApplication> allBundleApplications = resourcebundleService.findAllBundleApplications();
		return Response.ok(allBundleApplications).build();
	}

}
