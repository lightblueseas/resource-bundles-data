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
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.mapper.BundleApplicationsMapper;
import de.alpharogroup.db.resource.bundles.repositories.BundleApplicationsRepository;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The service class {@link BundleApplicationsDomainService}
 */
@Transactional
@Service("bundleApplicationDomainService")
public class BundleApplicationDomainService 
	extends 
		AbstractDomainService<java.lang.Integer, BundleApplication, BundleApplications, BundleApplicationsRepository, BundleApplicationsMapper>	
	implements 
		BundleApplicationService
{

	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	/**
	 * Sets the specific {@link BundleApplicationsMapper}.
	 *
	 * @param mapper
	 *            the new {@link BundleApplicationsMapper}.
	 */
	@Autowired
	public void setBundleApplicationsMapper(final BundleApplicationsMapper mapper)
	{
		setMapper(mapper);
	}

	@Autowired
	public void setBundleApplicationsRepository(final BundleApplicationsRepository repository)
	{
		setRepository(repository);
	}

	@Override
	public Set<BundleName> find(BundleApplication owner)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleApplication find(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleApplication get(BundleName bundleName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleApplication getOrCreateNewBundleApplications(String name,
		LanguageLocale defaultLocale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleApplication getOrCreateNewBundleApplications(String name,
		LanguageLocale defaultLocale, Set<LanguageLocale> supportedLocales)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
