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
import java.util.Locale;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.BundleNamesDao;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.DefaultLocaleBaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;

/**
 * The class {@link BundleNamesBusinessService}.
 */
@Transactional
@Service("bundleNamesService")
public class BundleNamesBusinessService extends AbstractBusinessService<BundleNames, Integer, BundleNamesDao>
		implements BundleNamesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DefaultLocaleBaseNamesService defaultLocaleBaseNamesService;	

	/** The base names service. */
	@Autowired
	private BaseNamesService baseNamesService;

	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> find(final BaseNames baseName) {
		if (baseName != null) {
			return find(baseName.getName(), (String)null);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleNames find(final BaseNames baseName, final LanguageLocales languageLocales) {
		String bn = null;
		String ll = null;
		if (baseName != null) {
			bn = baseName.getName();
		}
		if (languageLocales != null) {
			ll = languageLocales.getLocale();
		}
		if (bn != null && ll != null) {
			return ListExtensions.getFirst(find(bn, ll));
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BundleNames> find(final String baseName, final String locale) {
		final String hqlString = HqlStringCreator.forBundleNames(baseName, locale);
		final Query query = getQuery(hqlString);
		if (baseName != null && !baseName.isEmpty()) {
			query.setParameter("baseName", baseName);
		}
		if (locale != null && !locale.isEmpty()) {
			query.setParameter("locale", locale);
		}

		final List<BundleNames> bundleNames = query.getResultList();
		return bundleNames;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(final BundleNames bundleNames) {
		return defaultLocaleBaseNamesService.getDefaultLocale(bundleNames);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(final String baseName) {
		return defaultLocaleBaseNamesService.getDefaultLocale(baseName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Autowired
	public void setBundleNamesDao(final BundleNamesDao dao) {
		setDao(dao);
	}

	@Override
	public BundleNames find(final String baseName, final Locale locale)
	{
		return ListExtensions.getFirst(find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale)));
	}	

	@Override
	public BundleNames getOrCreateNewBundleNames(final String baseName, final Locale locale)
	{
		BundleNames bundleNames = find(baseName, locale);
		if (bundleNames == null)
		{
			LanguageLocales dbLocale = languageLocalesService.getOrCreateNewLanguageLocales(locale);
			BaseNames bn = baseNamesService.getOrCreateNewBaseNames(baseName);			
			bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(bn,
					dbLocale);
			bundleNames = merge(bundleNames);
		}
		return bundleNames;
	}

}
