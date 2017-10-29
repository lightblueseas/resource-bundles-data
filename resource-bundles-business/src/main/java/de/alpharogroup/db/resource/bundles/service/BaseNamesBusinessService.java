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

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.repositories.BaseNamesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.repository.AbstractBusinessService;

/**
 * The class {@link BaseNamesBusinessService}.
 */
@Transactional
@Service("baseNamesService")
public class BaseNamesBusinessService
	extends
		AbstractBusinessService<BaseNames, Integer, BaseNamesRepository>
	implements
		BaseNamesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseNames find(String baseName)
	{
		final String hqlString = HqlStringCreator.forBaseNames(baseName);
		final Query query = getQuery(hqlString);
		if (baseName != null && !baseName.isEmpty())
		{
			query.setParameter("baseName", baseName);
		}
		final List<BaseNames> baseNames = query.getResultList();
		return ListExtensions.getFirst(baseNames);
	}

	@Override
	public BaseNames getOrCreateNewBaseNames(final String baseName)
	{
		// check if baseNames exists...
		BaseNames foundBaseName = find(baseName);
		if (foundBaseName == null)
		{
			foundBaseName = ResourceBundlesDomainObjectFactory.getInstance().newBaseNames(baseName);
			foundBaseName = merge(foundBaseName);
		}
		return foundBaseName;
	}

	@Autowired
	public void setBaseNamesRepository(final BaseNamesRepository repository)
	{
		setRepository(repository);
	}

}
