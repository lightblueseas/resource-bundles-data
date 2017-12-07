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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.check.Check;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.repositories.ResourcebundlesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link ResourcebundlesBusinessService}.
 */
@Slf4j
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

	/** The base names service. */
	@Autowired
	private BaseNamesService baseNamesService;

	/** The Bundle names service. */
	@Autowired
	private BundleNamesService bundleNamesService;

	/** The language locales service. */
	@Autowired
	private LanguageLocalesService languageLocalesService;

	/** The properties keys service. */
	@Autowired
	private PropertiesKeysService propertiesKeysService;

	/** The bundle applications service. */
	@Autowired
	private BundleApplicationsService bundleApplicationsService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles contains(final String baseName, final Locale locale, final String key)
	{
		return getResourcebundle(baseName, locale, key);
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
		resourcebundles.setBundleName(null);
		resourcebundles = super.merge(resourcebundles);
		super.delete(resourcebundles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Resourcebundles> find(BundleApplications owner, String baseName, String locale,
		String key, String value)
	{
		final String hqlString = HqlStringCreator.forResourcebundles(owner.getName(), baseName, locale, key, value);
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
	@SuppressWarnings("unchecked")
	public List<Resourcebundles> find(final String baseName, final String locale, final String key,
		final String value)
	{
		final String hqlString = HqlStringCreator.forResourcebundles(baseName, locale, key, value);
		final Query query = getQuery(hqlString);
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
	public List<Resourcebundles> findResourceBundles(final BundleNames bundleName)
	{
		final String baseName = bundleName.getBaseName().getName();
		final Locale locale = LocaleResolver.resolveLocale(bundleName.getLocale().getLocale());
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale)
	{
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale,
		final String key)
	{
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key, null);
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
			properties.setProperty(resourcebundle.getKey().getName(), resourcebundle.getValue());
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final Locale locale)
	{
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(baseName, locale);
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			properties.setProperty(resourcebundle.getKey().getName(), resourcebundle.getValue());
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final String localeCode)
	{
		return getProperties(baseName, LocaleResolver.resolveLocale(localeCode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles getResourcebundle(final String baseName, final Locale locale,
		final String key)
	{
		return ListExtensions.getFirst(findResourceBundles(baseName, locale, key));
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles merge(final Resourcebundles resourcebundles)
	{
		try
		{
			return super.merge(resourcebundles);
		}
		catch (final Exception e)
		{
			log.error("merge fail with super.merge(resourcebundles)", e);
			BaseNames baseName = baseNamesService
				.find(resourcebundles.getBundleName().getBaseName().getName());
			if (baseName == null)
			{
				baseName = ResourceBundlesDomainObjectFactory.getInstance()
					.newBaseNames(resourcebundles.getBundleName().getBaseName().getName());
				baseName = baseNamesService.merge(baseName);
			}

			LanguageLocales languageLocales = languageLocalesService
				.find(resourcebundles.getBundleName().getLocale().getLocale());

			if (languageLocales == null)
			{
				languageLocales = ResourceBundlesDomainObjectFactory.getInstance()
					.newLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());
				languageLocales = languageLocalesService.merge(languageLocales);
			}

			BundleNames bundleNames = bundleNamesService
				.find(resourcebundles.getBundleName().getOwner(), baseName, languageLocales);
			if (bundleNames == null)
			{
				bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(
					resourcebundles.getBundleName().getBaseName(),
					resourcebundles.getBundleName().getLocale());
				bundleNames.setBaseName(baseName);
				bundleNames.setLocale(languageLocales);
				bundleNames = bundleNamesService.merge(bundleNames);
			}

			PropertiesKeys propertiesKeys = propertiesKeysService
				.find(resourcebundles.getKey().getName());
			if (propertiesKeys == null)
			{
				propertiesKeys = ResourceBundlesDomainObjectFactory.getInstance()
					.newPropertiesKeys(resourcebundles.getKey().getName());
				propertiesKeys = propertiesKeysService.merge(propertiesKeys);
			}
			resourcebundles.setBundleName(bundleNames);
			resourcebundles.setKey(propertiesKeys);
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
			BaseNames baseName = baseNamesService
				.find(resourcebundles.getBundleName().getBaseName().getName());
			if (baseName == null)
			{
				baseName = ResourceBundlesDomainObjectFactory.getInstance()
					.newBaseNames(resourcebundles.getBundleName().getBaseName().getName());
				baseName = baseNamesService.merge(baseName);
			}

			LanguageLocales languageLocales = languageLocalesService
				.find(resourcebundles.getBundleName().getLocale().getLocale());

			if (languageLocales == null)
			{
				languageLocales = ResourceBundlesDomainObjectFactory.getInstance()
					.newLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());
				languageLocales = languageLocalesService.merge(languageLocales);
			}

			BundleNames bundleNames = bundleNamesService
				.find(resourcebundles.getBundleName().getOwner(), baseName, languageLocales);
			if (bundleNames == null)
			{
				bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(
					resourcebundles.getBundleName().getBaseName(),
					resourcebundles.getBundleName().getLocale());
				bundleNames.setBaseName(baseName);
				bundleNames.setLocale(languageLocales);
				bundleNames = bundleNamesService.merge(bundleNames);
			}

			PropertiesKeys propertiesKeys = propertiesKeysService
				.find(resourcebundles.getKey().getName());
			if (propertiesKeys == null)
			{
				propertiesKeys = ResourceBundlesDomainObjectFactory.getInstance()
					.newPropertiesKeys(resourcebundles.getKey().getName());
				propertiesKeys = propertiesKeysService.merge(propertiesKeys);
			}
			resourcebundles.setBundleName(bundleNames);
			resourcebundles.setKey(propertiesKeys);
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
		Resourcebundles resourcebundle = getResourcebundle(baseName, locale, key);
		if (resourcebundle != null)
		{
			if (update)
			{
				resourcebundle.setValue(value);
			}
		}
		else
		{
			final PropertiesKeys pkey = propertiesKeysService.getOrCreateNewPropertiesKeys(key);
			resourcebundle = Resourcebundles.builder().bundleName(bundleName).key(pkey).value(value)
				.build();
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
	public BundleNames updateProperties(final BundleApplications owner, final Properties properties,
		final String baseName, final Locale locale)
	{
		return updateProperties(owner, properties, baseName, locale, true);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public BundleNames updateProperties(final BundleApplications owner, final Properties properties,
		final String baseName, final Locale locale, final boolean update)
	{
		Check.get().notEmpty(baseName, "baseName").notNull(locale, "locale");
		final BundleNames bundleName = bundleNamesService.getOrCreateNewBundleNames(owner, baseName,
			locale);
		final Properties dbProperties = getProperties(bundleName);
		final String bundName = bundleName.getBaseName().getName();
		log.info("===============================================================");
		log.info("Processing bundle: " + bundName);
		log.info("===============================================================");
		for (final Map.Entry<Object, Object> element : properties.entrySet())
		{
			final String key = element.getKey().toString().trim();
			final String value = element.getValue().toString().trim();
			if (dbProperties.containsKey(key))
			{
				final String dbValue = dbProperties.getProperty(key);
				if (value.equals(dbValue))
				{
					continue;
				}
			}
			log.info("===============================================================");
			log.info("Processing bundle: " + bundName);
			log.info("===============================================================");
			log.info("===============================================================");
			log.info("Processing key: " + key + "");
			log.info("===============================================================");
			log.info("===============================================================");
			log.info("Processing value: " + value + "");
			log.info("===============================================================");
			saveOrUpdateEntry(bundleName, baseName, locale, key, value, update);
		}
		log.info("===============================================================");
		log.info("Finish of processing: " + bundleName.getBaseName().getName());
		log.info("===============================================================");
		return bundleName;
	}

}