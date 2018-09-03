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

import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.repositories.BundleApplicationsRepository;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;
import lombok.NonNull;

/**
 * The class {@link BundleApplicationsBusinessService}.
 */
@Transactional
@Service("bundleApplicationsService")
public class BundleApplicationsBusinessService
	extends
		AbstractBusinessService<BundleApplications, Integer, BundleApplicationsRepository>
	implements
		BundleApplicationsService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Bundle names service. */
	@Autowired
	private BundleNamesService bundleNamesService;

	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService;

	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(BundleApplications bundleApplications)
	{
		List<BundleNames> bundleNames = bundleNamesService.find(bundleApplications);
		for (BundleNames bundleName : bundleNames)
		{
			resourcebundlesService.delete(bundleName);
		}
		bundleApplications.setDefaultLocale(null);
		BundleApplications merged = merge(bundleApplications);
		super.delete(merged);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<BundleNames> find(final BundleApplications owner)
	{
		final TypedQuery<BundleNames> typedQuery = getEntityManager()
			.createNamedQuery(BundleNames.NQ_FIND_BY_OWNER, BundleNames.class)
			.setParameter("owner", owner);

		final List<BundleNames> bundleNames = typedQuery.getResultList();
		return SetFactory.newHashSet(bundleNames);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BundleApplications find(final String name)
	{
		final String hqlString = HqlStringCreator.forBundleApplications(name);
		final Query query = getQuery(hqlString);
		if (name != null && !name.isEmpty())
		{
			query.setParameter("name", name);
		}
		final List<BundleApplications> applications = query.getResultList();
		return ListExtensions.getFirst(applications);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplications get(final BundleNames bundleName)
	{
		return bundleName.getOwner();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name, @NonNull final String defaultLocale)
	{
		LanguageLocales languageLocales = languageLocalesService.getOrCreateNewLanguageLocales(defaultLocale);
		return getOrCreateNewBundleApplications(name, languageLocales);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocales defaultLocale)
	{
		return getOrCreateNewBundleApplications(name, defaultLocale, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocales defaultLocale, Set<LanguageLocales> supportedLocales)
	{
		BundleApplications baseBundleApplication = find(name);
		if (baseBundleApplication == null)
		{
			baseBundleApplication = ResourceBundlesDomainObjectFactory.getInstance()
				.newBundleApplications(name, defaultLocale, supportedLocales);

			baseBundleApplication = merge(baseBundleApplication);
		}
		return baseBundleApplication;
	}

	/**
	 * Sets the bundle applications repository.
	 *
	 * @param repository
	 *            the new bundle applications repository
	 */
	@Autowired
	public void setBundleApplicationsRepository(final BundleApplicationsRepository repository)
	{
		setRepository(repository);
	}

}
