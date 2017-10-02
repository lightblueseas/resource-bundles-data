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
import de.alpharogroup.db.resource.bundles.daos.PropertiesKeysDao;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link PropertiesKeysBusinessService}.
 */
@Transactional
@Service("propertiesKeysService")
public class PropertiesKeysBusinessService
	extends
		AbstractBusinessService<PropertiesKeys, Integer, PropertiesKeysDao>
	implements
		PropertiesKeysService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PropertiesKeys find(String propertiesKey)
	{
		final String hqlString = HqlStringCreator.forPropertiesKeys(propertiesKey);
		final Query query = getQuery(hqlString);
		if (propertiesKey != null && !propertiesKey.isEmpty())
		{
			query.setParameter("propertiesKey", propertiesKey);
		}
		final List<PropertiesKeys> propertiesKeys = query.getResultList();
		return ListExtensions.getFirst(propertiesKeys);
	}

	@Override
	public PropertiesKeys getOrCreateNewPropertiesKeys(final String key)
	{
		PropertiesKeys pkey = find(key);
		if (pkey == null)
		{
			pkey = ResourceBundlesDomainObjectFactory.getInstance().newPropertiesKeys(key);
			pkey = merge(pkey);
		}
		return pkey;
	}


	/**
	 * {@inheritDoc}
	 */
	@Autowired
	public void setPropertiesKeysDao(final PropertiesKeysDao dao)
	{
		setDao(dao);
	}

}
