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
package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.repositories.BundleNamesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;

/**
 * The class {@link BundleNamesBusinessService}.
 */
@Transactional
@Service("bundleNamesService")
public class BundleNamesBusinessService
	extends
		AbstractBusinessService<BundleNames, Integer, BundleNamesRepository>
	implements
		BundleNamesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base names service. */
	@Autowired
	private BaseNamesService baseNamesService;

	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;

	@Autowired
	private ResourcebundlesService resourcebundlesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(BundleNames bundleNames)
	{
		List<Resourcebundles> list = resourcebundlesService.find(bundleNames);
		resourcebundlesService.delete(list);
		BaseNames baseName = bundleNames.getBaseName();
		bundleNames.setBaseName(null);
		bundleNames.setLocale(null);
		bundleNames.setOwner(null);
		final BundleNames merged = super.merge(bundleNames);
		super.delete(merged);
		if (0 == find(baseName).size())
		{
			baseNamesService.delete(baseName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> find(BaseNames baseName)
	{
		return find(null, baseName != null ? baseName.getName() : null, (String)null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> find(BundleApplications owner)
	{
		return find(owner, null, (String)null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> find(final BundleApplications owner, final BaseNames baseName)
	{
		if (baseName != null)
		{
			return find(owner, baseName.getName(), (String)null);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleNames find(final BundleApplications owner, final BaseNames baseName,
		final LanguageLocales languageLocales)
	{
		String bn = null;
		String ll = null;
		if (baseName != null)
		{
			bn = baseName.getName();
		}
		if (languageLocales != null)
		{
			ll = languageLocales.getLocale();
		}
		if (bn != null && ll != null)
		{
			return ListExtensions.getFirst(find(owner, bn, ll));
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> find(final BundleApplications owner, final String baseName)
	{
		if (baseName != null)
		{
			return find(owner, baseName, (String)null);
		}
		return null;
	}

	@Override
	public BundleNames find(final BundleApplications owner, final String baseName,
		final Locale locale)
	{
		return ListExtensions
			.getFirst(find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale)));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BundleNames> find(final BundleApplications owner, final String baseName,
		final String locale)
	{
		final String hqlString = HqlStringCreator
			.forBundleNames(owner != null ? owner.getName() : null, baseName, locale);
		final Query query = getQuery(hqlString);
		if (owner != null)
		{
			query.setParameter("owner", owner);
		}
		if (baseName != null && !baseName.isEmpty())
		{
			query.setParameter("baseName", baseName);
		}
		if (locale != null && !locale.isEmpty())
		{
			query.setParameter("locale", locale);
		}

		final List<BundleNames> bundleNames = query.getResultList();
		return bundleNames;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(final BundleApplications owner, final String baseName)
	{
		final List<BundleNames> list = find(owner, baseName);
		if (CollectionExtensions.isNotEmpty(list))
		{
			return getDefaultLocale(ListExtensions.getFirst(list));
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(final BundleNames bundleNames)
	{
		final BundleApplications bundleApplications = bundleApplicationsService.get(bundleNames);
		if (bundleApplications != null)
		{
			return bundleApplications.getDefaultLocale();
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public BundleNames getOrCreateNewBundleNames(BundleApplications owner, final String baseName,
		final Locale locale)
	{
		BundleNames bundleNames = find(owner, baseName, locale);
		if (bundleNames == null)
		{
			final LanguageLocales dbLocale = languageLocalesService
				.getOrCreateNewLanguageLocales(locale);
			final BaseNames bn = baseNamesService.getOrCreateNewNameEntity(baseName);
			bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(owner, bn,
				dbLocale);
			bundleNames = merge(bundleNames);

			if (!owner.isSupported(dbLocale))
			{
				owner = bundleApplicationsService.get(owner.getId());
				owner.addSupportedLanguageLocale(dbLocale);
				bundleApplicationsService.merge(owner);
			}
		}
		return bundleNames;
	}

	@Override
	public BundleNames merge(BundleNames object)
	{
		BaseNames dbBaseName;
		dbBaseName = get(object.getId()).getBaseName();
		if (dbBaseName != null)
		{
			// check if basename have changed
			if (!dbBaseName.equals(object.getBaseName()))
			{
				// find all bundlenames with the same basename
				List<BundleNames> applicationBundleNames = find(object.getOwner(), dbBaseName);
				// get or create new name entity
				BaseNames newBaseName = baseNamesService
					.getOrCreateNewNameEntity(object.getBaseName().getName());
				// update this bundlenames object with the new basename
				object.setBaseName(newBaseName);
				// update all other bundlenames object with the same basename
				for (BundleNames bn : applicationBundleNames)
				{
					bn.setBaseName(newBaseName);
					if (!bn.equals(object))
					{
						super.merge(bn);
					}
				}
			}
		}
		return super.merge(object);

	}

	@Autowired
	public void setBundleNamesRepository(final BundleNamesRepository repository)
	{
		setRepository(repository);
	};

}
