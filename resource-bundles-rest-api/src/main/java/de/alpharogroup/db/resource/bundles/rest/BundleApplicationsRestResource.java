package de.alpharogroup.db.resource.bundles.rest;

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
	public Response findAllBundleNames(String owner)
	{
		BundleApplication bundleApplication = getDomainService().find(owner);
		Set<BundleName> set = getDomainService().find(bundleApplication);
		return Response.ok(set).build();
	}

	@Override
	public Response find(String name)
	{
		BundleApplication bundleApplication = getDomainService().find(name);
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response get(BundleName bundleName)
	{
		BundleApplication bundleApplication = getDomainService().get(bundleName);
		return Response.ok(bundleApplication).build();
	}

	@Override
	public Response getOrCreateNewBundleApplications(String name, String defaultlocale)
	{
		BundleApplication bundleApplication = getDomainService()
			.getOrCreateNewBundleApplications(name, defaultlocale);
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

}