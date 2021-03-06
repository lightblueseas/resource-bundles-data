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

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.repositories.LanguagesRepository;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;

/**
 * The class {@link LanguagesBusinessService}.
 */
@Transactional
@Service("languagesService")
public class LanguagesBusinessService
	extends
		AbstractBusinessService<Languages, Integer, LanguagesRepository>
	implements
		LanguagesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Languages find(final String name, final String iso639Dash1)
	{
		final String hqlString = HqlStringCreator.forLanguages(name, iso639Dash1);
		final Query query = getQuery(hqlString);
		if (name != null && !name.isEmpty())
		{
			query.setParameter("name", name);
		}
		if (iso639Dash1 != null && !iso639Dash1.isEmpty())
		{
			query.setParameter("iso639Dash1", iso639Dash1);
		}

		final List<Languages> languages = query.getResultList();
		return ListExtensions.getFirst(languages);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Languages findByIso639Dash1(String iso639Dash1)
	{
		return find(null, iso639Dash1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Languages findByName(final String name)
	{
		return find(name, null);
	}

	/**
	 * Sets the languages repository.
	 *
	 * @param repository
	 *            the new languages repository
	 */
	@Autowired
	public void setLanguagesRepository(final LanguagesRepository repository)
	{
		setRepository(repository);
	}

}
