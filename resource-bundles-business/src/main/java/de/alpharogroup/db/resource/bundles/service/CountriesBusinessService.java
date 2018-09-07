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
import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.db.resource.bundles.repositories.CountriesRepository;
import de.alpharogroup.db.resource.bundles.service.api.CountriesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;

/**
 * The class {@link CountriesBusinessService}.
 */
@Transactional
@Service("countriesService")
public class CountriesBusinessService
	extends
		AbstractBusinessService<Countries, Integer, CountriesRepository>
	implements
		CountriesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	@Override
	public Countries find(String iso3166a2name)
	{
		return find(null, iso3166a2name);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Countries find(String name, String iso3166a2name)
	{
		final String hqlString = HqlStringCreator.forCountries(name, iso3166a2name);
		final Query query = getQuery(hqlString);
		if (name != null && !name.isEmpty())
		{
			query.setParameter("name", name);
		}
		if (iso3166a2name != null && !iso3166a2name.isEmpty())
		{
			query.setParameter("iso3166A2name", iso3166a2name);
		}

		final List<Countries> countries = query.getResultList();
		return ListExtensions.getFirst(countries);
	}

	@Autowired
	public void setCountriesRepository(final CountriesRepository repository)
	{
		setRepository(repository);
	}

}
