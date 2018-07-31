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
import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.mapper.ResourcebundlesMapper;
import de.alpharogroup.db.resource.bundles.repositories.ResourcebundlesRepository;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundleService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.resourcebundle.locale.BundleKey;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.locale.ResourceBundleExtensions;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The class {@link ResourcebundleDomainService}.
 */
@Transactional
@Service("resourcebundleDomainService")
public class ResourcebundleDomainService
	extends
		AbstractDomainService<Integer, Resourcebundle, Resourcebundles, ResourcebundlesRepository, ResourcebundlesMapper>
	implements
		ResourcebundleService
{

	@Autowired
	private ResourcebundlesService resourcebundlesService;

	@Override
	public Resourcebundle contains(final BundleApplication bundleApplication, final String baseName,
		final Locale locale, final String key)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.contains(owner, baseName,
			locale, key);
		if (resourcebundles != null)
		{
			return getMapper().toDomainObject(resourcebundles);
		}
		return null;
	}

	@Override
	public Resourcebundle find(final BundleApplication bundleApplication, final String baseName,
		final Locale locale, final String key)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());

		final List<Resourcebundles> list = resourcebundlesService.find(owner, baseName,
			LocaleExtensions.getLocaleFilenameSuffix(locale), key, null);
		final Resourcebundles first = ListExtensions.getFirst(list);
		if (first != null)
		{
			return getMapper().toDomainObject(first);
		}
		return null;
	}

	@Override
	public List<Resourcebundle> find(final BundleApplication bundleApplication,
		final String baseName, final String locale, final String key, final String value)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());

		final List<Resourcebundles> list = resourcebundlesService.find(owner, baseName, locale, key,
			value);
		if (CollectionExtensions.isNotEmpty(list))
		{
			return getMapper().toDomainObjects(list);
		}
		return ListFactory.newArrayList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplication find(final String name)
	{
		final BundleApplications bundleApplications = resourcebundlesService.find(name);
		if (bundleApplications != null)
		{
			final BundleApplication bundleApplication = getMapper().map(bundleApplications,
				BundleApplication.class);
			return bundleApplication;
		}
		return null;
	}

	@Override
	public List<Resourcebundle> findResourceBundles(final BundleApplication bundleApplication,
		final String baseName, final Locale locale)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final List<Resourcebundles> list = resourcebundlesService.findResourceBundles(owner,
			baseName, locale);
		if (CollectionExtensions.isNotEmpty(list))
		{
			return getMapper().toDomainObjects(list);
		}
		return ListFactory.newArrayList();
	}

	@Override
	public Properties getProperties(final BundleApplication bundleApplication,
		final String baseName, final Locale locale)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		return resourcebundlesService.getProperties(owner, baseName, locale);
	}

	@Override
	public Properties getProperties(final BundleApplication bundleApplication,
		final String baseName, final String localeCode)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		return resourcebundlesService.getProperties(owner, baseName, localeCode);
	}

	@Override
	public Resourcebundle getResourcebundle(final BundleApplication bundleApplication,
		final String baseName, final Locale locale, final String key)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			baseName, locale, key);
		if (resourcebundles != null)
		{
			return getMapper().toDomainObject(resourcebundles);
		}
		return null;
	}

	@Override
	public String getString(final BundleApplication bundleApplication, final BundleKey bundleKey)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			bundleKey.getBaseName(), bundleKey.getLocale(),
			bundleKey.getResourceBundleKey().getKey());
		if (resourcebundles != null)
		{
			return getMapper().toDomainObject(resourcebundles).getValue();
		}
		return "";
	}

	@Override
	public String getString(final BundleApplication bundleApplication, final String baseName,
		final String locale, final String key)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			baseName, LocaleResolver.resolveLocaleCode(locale), key);
		if (resourcebundles != null)
		{
			return getMapper().toDomainObject(resourcebundles).getValue();
		}
		return "";
	}

	@Override
	public String getString(final BundleApplication bundleApplication, final String baseName,
		final String locale, final String key, final Object[] parameters)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			baseName, LocaleResolver.resolveLocaleCode(locale), key);
		String value = "";
		if (resourcebundles != null)
		{
			value = getMapper().toDomainObject(resourcebundles).getValue();
			value = ResourceBundleExtensions.format(value, parameters);
		}
		return value;
	}

	@Override
	public String getString(final BundleApplication bundleApplication, final String baseName,
		final String locale, final String key, final String defaultValue)
	{

		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			baseName, LocaleResolver.resolveLocaleCode(locale), key);
		if (resourcebundles != null)
		{
			return getMapper().toDomainObject(resourcebundles).getValue();
		}
		return defaultValue;
	}

	@Override
	public String getString(final BundleApplication bundleApplication, final String baseName,
		final String locale, final String key, final String defaultValue, final Object[] parameters)
	{

		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		final Resourcebundles resourcebundles = resourcebundlesService.getResourcebundle(owner,
			baseName, LocaleResolver.resolveLocaleCode(locale), key);
		String value = defaultValue;
		if (resourcebundles != null)
		{
			value = getMapper().toDomainObject(resourcebundles).getValue();
			value = ResourceBundleExtensions.format(value, parameters);
		}
		return value;
	}

	/**
	 * Sets the specific {@link ResourcebundlesMapper}.
	 *
	 * @param mapper
	 *            the new {@link ResourcebundlesMapper}.
	 */
	@Autowired
	public void setResourcebundlesMapper(final ResourcebundlesMapper mapper)
	{
		setMapper(mapper);
	}

	@Autowired
	public void setResourcebundlesRepository(final ResourcebundlesRepository repository)
	{
		setRepository(repository);
	}

	@Override
	public void updateProperties(final BundleApplication bundleApplication,
		final Properties properties, final String baseName, final Locale locale)
	{
		final BundleApplications owner = resourcebundlesService.find(bundleApplication.getName());
		resourcebundlesService.updateProperties(owner, properties, baseName, locale);
	}

}
