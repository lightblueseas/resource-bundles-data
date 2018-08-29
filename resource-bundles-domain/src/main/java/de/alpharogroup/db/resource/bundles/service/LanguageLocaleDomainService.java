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

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.mapper.LanguageLocalesMapper;
import de.alpharogroup.db.resource.bundles.repositories.LanguageLocalesRepository;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocaleService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The class {@link LanguageLocaleDomainService}
 */
@Transactional
@Service("languageLocaleDomainService")
public class LanguageLocaleDomainService
	extends
		AbstractDomainService<Integer, LanguageLocale, LanguageLocales, LanguageLocalesRepository, LanguageLocalesMapper>
	implements
	LanguageLocaleService
{

	@Autowired
	private LanguageLocalesService languageLocalesService;

	/**
	 * Sets the specific {@link LanguageLocalesMapper}.
	 *
	 * @param mapper
	 *            the new {@link LanguageLocalesMapper}.
	 */
	@Autowired
	public void setLanguageLocalesMapper(final LanguageLocalesMapper mapper)
	{
		setMapper(mapper);
	}

	@Autowired
	public void setLanguageLocalesRepository(final LanguageLocalesRepository repository)
	{
		setRepository(repository);
	}

	@Override
	public LanguageLocale find(Locale locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageLocale find(String locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageLocale getOrCreateNewLanguageLocales(Locale locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale resolveLocale(LanguageLocale languageLocales)
	{
		// TODO Auto-generated method stub
		return null;
	}


}
