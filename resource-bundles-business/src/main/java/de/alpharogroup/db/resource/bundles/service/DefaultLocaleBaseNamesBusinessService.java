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

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.DefaultLocaleBaseNamesDao;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.DefaultLocaleBaseNamesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link DefaultLocaleBaseNamesBusinessService}.
 */
@Transactional
@Service("defaultLocaleBaseNamesService")
public class DefaultLocaleBaseNamesBusinessService
	extends
		AbstractBusinessService<DefaultLocaleBaseNames, Integer, DefaultLocaleBaseNamesDao>
	implements
		DefaultLocaleBaseNamesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DefaultLocaleBaseNames find(String baseName)
	{
		final String hqlString = HqlStringCreator.forDefaultLocaleBaseNames(baseName, null);
		final Query query = getQuery(hqlString);
		if (baseName != null && !baseName.isEmpty())
		{
			query.setParameter("baseName", baseName);
		}
		final List<DefaultLocaleBaseNames> defaultLocaleBaseNames = query.getResultList();
		return ListExtensions.getFirst(defaultLocaleBaseNames);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(BundleNames bundleNames)
	{
		if (bundleNames != null)
		{
			String baseName = bundleNames.getBaseName().getName();
			return getDefaultLocale(baseName);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LanguageLocales getDefaultLocale(String baseName)
	{
		DefaultLocaleBaseNames defaultLocaleBaseNames = find(baseName);
		if (defaultLocaleBaseNames != null)
		{
			return defaultLocaleBaseNames.getDefaultLocale();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Autowired
	public void setDefaultLocaleBaseNamesDao(final DefaultLocaleBaseNamesDao dao)
	{
		setDao(dao);
	}

}
