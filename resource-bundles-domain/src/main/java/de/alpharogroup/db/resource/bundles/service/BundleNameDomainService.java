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
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.mapper.BundleNamesMapper;
import de.alpharogroup.db.resource.bundles.mapper.LanguageLocalesMapper;
import de.alpharogroup.db.resource.bundles.repositories.BundleNamesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BundleNameService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The service class {@link BundleNameDomainService}
 */
@Transactional
@Service("bundleNameDomainService")
public class BundleNameDomainService
	extends
		AbstractDomainService<java.lang.Integer, BundleName, BundleNames, BundleNamesRepository, BundleNamesMapper>
	implements
		BundleNameService
{

	/** The {@link BundleNamesService} object */
	@Autowired
	private BundleNamesService bundleNamesService;

	@Override
	public List<BundleName> find(BundleApplication owner)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		List<BundleNames> set = bundleNamesService.find(entity);
		List<BundleName> list = getMapper().map(set, BundleName.class);
		return list;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, BaseName baseName)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		BaseNames baseNames = getMapper().map(baseName, BaseNames.class);
		List<BundleNames> bundleNames = bundleNamesService.find(entity, baseNames);
		List<BundleName> list = getMapper().map(bundleNames, BundleName.class);
		return list;
	}

	@Override
	public BundleName find(BundleApplication owner, BaseName baseName,
		LanguageLocale languageLocales)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		BaseNames baseNamesEntity = getMapper().map(baseName, BaseNames.class);
		LanguageLocales languageLocalesEntity = getMapper().map(languageLocales,
			LanguageLocales.class);
		BundleNames bundleNames = bundleNamesService.find(entity, baseNamesEntity,
			languageLocalesEntity);
		BundleName domainObject = getMapper().toDomainObject(bundleNames);
		return domainObject;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, String baseName)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		List<BundleNames> bundleNames = bundleNamesService.find(entity, baseName);
		List<BundleName> list = getMapper().map(bundleNames, BundleName.class);
		return list;
	}

	@Override
	public BundleName find(BundleApplication owner, String baseName, Locale locale)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		BundleNames bundleNames = bundleNamesService.find(entity, baseName, locale);
		BundleName domainObject = getMapper().toDomainObject(bundleNames);
		return domainObject;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, String baseName, String locale)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		List<BundleNames> bundleNames = bundleNamesService.find(entity, baseName, locale);
		List<BundleName> list = getMapper().map(bundleNames, BundleName.class);
		return list;
	}

	@Override
	public LanguageLocale getDefaultLocale(BundleApplication owner, String baseName)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		LanguageLocales languageLocales = bundleNamesService.getDefaultLocale(entity, baseName);
		LanguageLocale languageLocale = getMapper().map(languageLocales, LanguageLocale.class);
		return languageLocale;
	}

	@Override
	public LanguageLocale getDefaultLocale(BundleName bundleName)
	{
		BundleNames bundleNames = getMapper().toEntity(bundleName);
		LanguageLocales defaultLocale = bundleNamesService.getDefaultLocale(bundleNames);
		LanguageLocale languageLocale = getMapper().map(defaultLocale, LanguageLocale.class);
		return languageLocale;
	}

	@Override
	public BundleName getOrCreateNewBundleName(BundleApplication owner, String baseName,
		Locale locale)
	{
		BundleApplications entity = getMapper().map(owner, BundleApplications.class);
		BundleNames newBundleNames = bundleNamesService.getOrCreateNewBundleNames(entity, baseName,
			locale);
		BundleName domainObject = getMapper().toDomainObject(newBundleNames);
		return domainObject;
	}

	/**
	 * Sets the specific {@link LanguageLocalesMapper}.
	 *
	 * @param mapper
	 *            the new {@link LanguageLocalesMapper}.
	 */
	@Autowired
	public void setBundleNamesMapper(final BundleNamesMapper mapper)
	{
		setMapper(mapper);
	}

	/**
	 * Sets the specific repository.
	 *
	 * @param repository
	 *            the repository
	 */
	@Autowired
	public void setBundleNamesRepository(BundleNamesRepository repository)
	{
		setRepository(repository);
	}

}