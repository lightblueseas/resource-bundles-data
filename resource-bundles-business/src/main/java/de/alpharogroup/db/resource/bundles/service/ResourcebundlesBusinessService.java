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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.PropertiesValues;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.repositories.ResourcebundlesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesValuesService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import lombok.NonNull;
import lombok.extern.java.Log;

/**
 * The class {@link ResourcebundlesBusinessService}.
 */
@Log
@Transactional
@Service("resourcebundlesService")
public class ResourcebundlesBusinessService
	extends
		AbstractBusinessService<Resourcebundles, Integer, ResourcebundlesRepository>
	implements
		ResourcebundlesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bundle applications service. */
	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	/** The Bundle names service. */
	@Autowired
	private BundleNamesService bundleNamesService;

	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;

	/** The properties keys service. */
	@Autowired
	private PropertiesKeysService propertiesKeysService;

	/** The properties values service. */
	@Autowired
	private PropertiesValuesService propertiesValuesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles contains(BundleApplications owner, String baseName, Locale locale,
		String key)
	{
		return getResourcebundle(owner, baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(BundleNames bundleName)
	{
		final List<Resourcebundles> list = find(bundleName);
		delete(list);
		bundleNamesService.delete(bundleName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final List<Resourcebundles> resourcebundles)
	{
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			delete(resourcebundle);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Resourcebundles resourcebundles)
	{
		PropertiesKeys key = resourcebundles.getKey();
		PropertiesValues value = resourcebundles.getValue();
		resourcebundles.setBundleName(null);
		resourcebundles.setKey(null);
		resourcebundles.setValue(null);
		resourcebundles = super.merge(resourcebundles);
		super.delete(resourcebundles);
		if (0 == find(key).size())
		{
			propertiesKeysService.delete(key);
		}
		if (0 == find(value).size())
		{
			propertiesValuesService.delete(value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Resourcebundles> find(BundleApplications owner, String baseName, String locale,
		String key, String value)
	{
		final String hqlString = HqlStringCreator.forResourcebundles(
			owner != null ? owner.getName() : null, baseName, locale, key, value);
		final Query query = getQuery(hqlString);
		if (owner != null)
		{
			query.setParameter("owner", owner);
		}
		if (baseName != null && !baseName.isEmpty())
		{
			query.setParameter("baseName", baseName);
		}
		if (locale != null && !locale.isEmpty())
		{
			query.setParameter("locale", locale);
		}
		if (key != null && !key.isEmpty())
		{
			query.setParameter("key", key);
		}
		if (value != null && !value.isEmpty())
		{
			query.setParameter("value", value);
		}
		final List<Resourcebundles> resourcebundles = query.getResultList();
		return resourcebundles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> find(BundleNames bundleName)
	{
		return find(bundleName.getOwner(), bundleName.getBaseName().getName(),
			bundleName.getLocale().getLocale(), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> find(PropertiesKeys key)
	{
		return find(null, null, null, key.getName(), null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> find(PropertiesValues value)
	{
		return find(null, null, null, null, value.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleApplications find(String name)
	{
		return bundleApplicationsService.find(name);
	}

	@Override
	public List<BundleApplications> findAllBundleApplications()
	{
		return bundleApplicationsService.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(BundleApplications owner, String baseName,
		Locale locale)
	{
		return find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(BundleApplications owner, String baseName,
		Locale locale, String key)
	{
		return find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(final BundleNames bundleName)
	{
		final String baseName = bundleName.getBaseName().getName();
		final Locale locale = LocaleResolver.resolveLocale(bundleName.getLocale().getLocale());
		final BundleApplications owner = bundleName.getOwner();
		return findResourceBundles(owner, baseName, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(BundleApplications owner, String baseName, Locale locale)
	{
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(owner, baseName, locale);
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			properties.setProperty(resourcebundle.getKey().getName(),
				resourcebundle.getValue().getName());
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(BundleApplications owner, String baseName, String localeCode)
	{
		return getProperties(owner, baseName, LocaleResolver.resolveLocale(localeCode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final BundleNames bundleName)
	{
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(bundleName);
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			properties.setProperty(resourcebundle.getKey().getName(),
				resourcebundle.getValue().getName());
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles getResourcebundle(BundleApplications owner, String baseName,
		Locale locale, String key)
	{
		return ListExtensions.getFirst(findResourceBundles(owner, baseName, locale, key));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BundleNames> importProperties(BundleApplications bundleApplication,
		List<KeyValuePair<File, Locale>> foundProperties) throws IOException
	{
		final List<BundleNames> list = new ArrayList<>();
		for (final KeyValuePair<File, Locale> entry : foundProperties)
		{
			final File propertiesFile = entry.getKey();
			final Locale locale = entry.getValue();
			final String bundlename = LocaleResolver.resolveBundlename(propertiesFile);
			final Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			final BundleNames bundleNames = updateProperties(bundleApplication, properties,
				bundlename, locale, false);
			list.add(bundleNames);
			bundleApplication = bundleApplicationsService.merge(bundleApplication);
		}
		return list;
	}

	private void initialize(final Resourcebundles resourcebundles)
	{
		LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());

		BundleNames bundleNames = bundleNamesService.getOrCreateNewBundleNames(
			resourcebundles.getBundleName().getOwner(),
			resourcebundles.getBundleName().getBaseName().getName(),
			languageLocalesService.resolveLocale(languageLocales));

		PropertiesKeys propertiesKeys = propertiesKeysService
			.getOrCreateNewNameEntity(resourcebundles.getKey().getName());

		resourcebundles.setBundleName(bundleNames);
		resourcebundles.setKey(propertiesKeys);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Resourcebundles merge(final Resourcebundles resourcebundles)
	{
		PropertiesKeys key;
		PropertiesValues value;
		Resourcebundles dbEntity = get(resourcebundles.getId());
		if (dbEntity != null)
		{
			key = dbEntity.getKey();
			value = dbEntity.getValue();
			if (!key.equals(resourcebundles.getKey()) && 1 < find(key).size())
			{
				key = PropertiesKeys.builder().name(resourcebundles.getKey().getName()).build();
				PropertiesKeys merged = propertiesKeysService.merge(key);
				resourcebundles.setKey(merged);
			}
			if (!value.equals(resourcebundles.getValue()) && 1 < find(value).size())
			{
				value = PropertiesValues.builder().name(resourcebundles.getValue().getName())
					.build();
				PropertiesValues merged = propertiesValuesService.merge(value);
				resourcebundles.setValue(merged);
			}
		}
		try
		{
			return super.merge(resourcebundles);
		}
		catch (final Exception e)
		{
			log.log(Level.SEVERE, "merge fail with super.merge(resourcebundles)", e);
			initialize(resourcebundles);
			return super.merge(resourcebundles);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(final Resourcebundles resourcebundles)
	{
		try
		{
			super.saveOrUpdate(resourcebundles);
		}
		catch (final Exception e)
		{
			log.log(Level.SEVERE, "save or update fail with super.saveOrUpdate(resourcebundles)", e);
			initialize(resourcebundles);
			super.saveOrUpdate(resourcebundles);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Resourcebundles saveOrUpdateEntry(final BundleNames bundleName, final String baseName,
		final Locale locale, final String key, final String value, final boolean update)
	{
		Resourcebundles resourcebundle = getResourcebundle(bundleName.getOwner(), baseName, locale,
			key);
		PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
		if (resourcebundle != null)
		{
			if (update)
			{
				resourcebundle.setValue(pvalue);
			}
		}
		else
		{
			final PropertiesKeys pkey = propertiesKeysService.getOrCreateNewNameEntity(key);
			resourcebundle = Resourcebundles.builder().bundleName(bundleName).key(pkey)
				.value(pvalue).build();
		}
		resourcebundle = merge(resourcebundle);
		return resourcebundle;
	}

	@Autowired
	public void setResourcebundlesRepository(final ResourcebundlesRepository repository)
	{
		setRepository(repository);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BundleNames updateProperties(final @NonNull BundleApplications owner,
		final @NonNull Properties properties, final @NonNull String baseName,
		final @NonNull Locale locale)
	{
		return updateProperties(owner, properties, baseName, locale, true);
	}

	@Transactional
	public BundleNames updateProperties(final @NonNull BundleApplications owner,
		final @NonNull Properties properties, final @NonNull String baseName,
		final @NonNull Locale locale, final boolean update)
	{
		final BundleNames bundleName = bundleNamesService.getOrCreateNewBundleNames(owner, baseName,
			locale);
		final Properties dbProperties = getProperties(bundleName);

		properties.entrySet().parallelStream().forEach(element -> {
			final String key = element.getKey().toString().trim();
			final String value = element.getValue().toString().trim();
			if (dbProperties.containsKey(key))
			{
				final String dbValue = dbProperties.getProperty(key);
				if (!value.equals(dbValue))
				{
					return;
				}
			}
			saveOrUpdateEntry(bundleName, baseName, locale, key, value, update);
		});
		return bundleName;
	}

	@Override
	public BundleNames updateProperties(final @NonNull Properties properties,
		final @NonNull String owner, final @NonNull String baseName,
		final @NonNull String localeCode)
	{
		BundleApplications bundleApplications = bundleApplicationsService.find(owner);
		Locale locale = LocaleResolver.resolveLocale(localeCode, false);
		return updateProperties(bundleApplications, properties, baseName, locale);
	}

}