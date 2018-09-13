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
import java.util.Set;

import javax.ws.rs.core.Response;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.rest.api.BundleApplicationsResource;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link BundleApplicationsRestResource} provides an implementation of the inteface
 * {@link BundleApplicationsResource}
 */
public class BundleApplicationsRestResource
	extends
		AbstractRestfulResource<java.lang.Integer, BundleApplication, BundleApplicationService>
	implements
		BundleApplicationsResource
{

	@Override
	public Response find(String name)
	{
		BundleApplication bundleApplication = getDomainService().find(name);
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response findAll()
	{
		List<BundleApplication> allBundleApplications = getDomainService().findAll();
		return Response.ok(allBundleApplications).build();
	}

	@Override
	public Response findAllBundleNames(String owner)
	{
		BundleApplication bundleApplication = getDomainService().find(owner);
		Set<BundleName> set = getDomainService().find(bundleApplication);
		return Response.ok(set).build();
	}

	@Override
	public Response get(BundleName bundleName)
	{
		BundleApplication bundleApplication = getDomainService().get(bundleName);
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response getOrCreateNewBundleApplications(BundleApplication searchCriteria)
	{
		BundleApplication bundleApplication = getDomainService().getOrCreateNewBundleApplications(
			searchCriteria.getName(), searchCriteria.getDefaultLocale(),
			searchCriteria.getSupportedLocales());
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response getOrCreateNewBundleApplications(String name, String defaultlocale)
	{
		BundleApplication bundleApplication = getDomainService()
			.getOrCreateNewBundleApplications(name, defaultlocale);
		return Response.ok(bundleApplication).build();
	}

}