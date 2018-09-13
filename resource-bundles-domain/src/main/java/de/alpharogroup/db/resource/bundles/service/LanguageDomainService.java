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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.mapper.LanguagesMapper;
import de.alpharogroup.db.resource.bundles.repositories.LanguagesRepository;
import de.alpharogroup.db.resource.bundles.service.api.LanguageService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The service class {@link LanguageDomainService}
 */
@Transactional
@Service("languageDomainService")
public class LanguageDomainService
	extends
		AbstractDomainService<java.lang.Integer, Language, Languages, LanguagesRepository, LanguagesMapper>
	implements
		LanguageService
{

	/** The {@link LanguagesService} object */
	@Autowired
	private LanguagesService languagesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language find(final String name, final String iso639Dash1)
	{
		Languages entity = languagesService.find(name, iso639Dash1);
		Language domainObject = getMapper().toDomainObject(entity);
		return domainObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language findByIso639Dash1(String iso639Dash1)
	{
		return find(null, iso639Dash1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language findByName(String name)
	{
		return find(name, null);
	}

	/**
	 * Sets the specific {@link LanguagesMapper}
	 *
	 * @param mapper
	 *            the new {@link LanguagesMapper}
	 */
	@Autowired
	public void setLanguagesMapper(final LanguagesMapper mapper)
	{
		setMapper(mapper);
	}

	/**
	 * Sets the specific repository
	 *
	 * @param repository
	 *            the repository
	 */
	@Autowired
	public void setLanguagesRepository(LanguagesRepository repository)
	{
		setRepository(repository);
	}

}