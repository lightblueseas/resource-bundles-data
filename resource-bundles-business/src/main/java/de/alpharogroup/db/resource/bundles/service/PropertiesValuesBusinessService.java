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
import de.alpharogroup.db.resource.bundles.entities.PropertiesValues;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.repositories.PropertiesValuesRepository;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesValuesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;

/**
 * The class {@link PropertiesValuesBusinessService}.
 */
@Transactional
@Service("propertiesValuesService")
public class PropertiesValuesBusinessService
	extends
		AbstractBusinessService<PropertiesValues, Integer, PropertiesValuesRepository>
	implements
		PropertiesValuesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Autowired
	public void setPropertiesValuesRepository(final PropertiesValuesRepository repository)
	{
		setRepository(repository);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PropertiesValues find(String propertiesValue)
	{
		final String hqlString = HqlStringCreator.forPropertiesValues(propertiesValue);
		final Query query = getQuery(hqlString);
		if (propertiesValue != null && !propertiesValue.isEmpty())
		{
			query.setParameter("propertiesValue", propertiesValue);
		}
		final List<PropertiesValues> propertiesValues = query.getResultList();
		return ListExtensions.getFirst(propertiesValues);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertiesValues getOrCreateNewPropertiesValues(String value)
	{
		PropertiesValues pvalue = find(value);
		if (pvalue == null)
		{
			pvalue = ResourceBundlesDomainObjectFactory.getInstance().newPropertiesValues(value);
			pvalue = merge(pvalue);
		}
		return pvalue;
	}

}
