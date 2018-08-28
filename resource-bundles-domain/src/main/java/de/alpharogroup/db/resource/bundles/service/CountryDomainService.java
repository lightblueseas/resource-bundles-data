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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.db.resource.bundles.mapper.CountriesMapper;
import de.alpharogroup.db.resource.bundles.repositories.CountriesRepository;
import de.alpharogroup.db.resource.bundles.service.api.CountriesService;
import de.alpharogroup.db.resource.bundles.service.api.CountryService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The class {@link CountyDomainService}.
 */
@Transactional
@Service("countryDomainService")
public class CountryDomainService
	extends
		AbstractDomainService<Integer, Country, Countries, CountriesRepository, CountriesMapper>
	implements
		CountryService
{

	@Autowired
	private CountriesService countriesService;

	/**
	 * Sets the specific {@link CountriesMapper}.
	 *
	 * @param mapper
	 *            the new {@link CountriesMapper}.
	 */
	@Autowired
	public void setCountriesMapper(final CountriesMapper mapper)
	{
		setMapper(mapper);
	}

	@Autowired
	public void setCountriesRepository(final CountriesRepository repository)
	{
		setRepository(repository);
	}

	@Override
	public Country find(String iso3166a2name)
	{
		Countries entity = countriesService.find(iso3166a2name);
		Country domainObject = getMapper().toDomainObject(entity);
		return domainObject;
	}

	@Override
	public Country find(String name, String iso3166a2name)
	{
		Countries entity = countriesService.find(name, iso3166a2name);
		Country domainObject = getMapper().toDomainObject(entity);
		return domainObject;
	}

}
