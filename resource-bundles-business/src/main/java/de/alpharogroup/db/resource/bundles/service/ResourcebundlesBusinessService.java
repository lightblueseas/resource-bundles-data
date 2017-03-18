package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;

/**
 * The class {@link ResourcebundlesBusinessService}.
 */
@Transactional
@Service("resourcebundlesService")
public class ResourcebundlesBusinessService extends
		AbstractBusinessService<Resourcebundles, Integer, ResourcebundlesDao> implements ResourcebundlesService {

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

	@Autowired
	public void setResourcebundlesDao(final ResourcebundlesDao resourcebundlesDao) {
		setDao(resourcebundlesDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale) {
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundles> findResourceBundles(final String baseName, final Locale locale, final String key) {
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles getResourcebundle(final String baseName, final Locale locale, final String key) {
		return ListExtensions.getFirst(findResourceBundles(baseName, locale, key));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles contains(final String baseName, final Locale locale, final String key) {
		return getResourcebundle(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(final Properties properties, final String baseName, final Locale locale) {
		updateProperties(properties, baseName, locale, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(final Properties properties, final String baseName, final Locale locale,
			final boolean update) {
		if (baseName == null || baseName.isEmpty()) {
			throw new IllegalArgumentException("Parameter baseName should not be null or empty.");
		}
		for (final Map.Entry<Object, Object> element : properties.entrySet()) {
			final String key = element.getKey().toString().trim();
			final String value = element.getValue().toString().trim();
			Resourcebundles resourcebundle = getResourcebundle(baseName, locale, key);
			if (resourcebundle != null) {
				if (update) {
					resourcebundle.setValue(value);
				}
			} else {
				resourcebundle = ResourceBundlesDomainObjectFactory.getInstance().newResourcebundles(baseName,
						LocaleExtensions.getLocaleFilenameSuffix(locale), key, value);
			}
			merge(resourcebundle);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundles merge(Resourcebundles resourcebundles) {

		BaseNames baseName = baseNamesService.find(resourcebundles.getBundleName().getBaseName().getName());
		if (baseName == null) {
			baseName = ResourceBundlesDomainObjectFactory.getInstance()
					.newBaseNames(resourcebundles.getBundleName().getBaseName().getName());
			baseName = baseNamesService.merge(baseName);
		}

		LanguageLocales languageLocales = languageLocalesService
				.find(resourcebundles.getBundleName().getLocale().getLocale());

		if (languageLocales == null) {
			languageLocales = ResourceBundlesDomainObjectFactory.getInstance()
					.newLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());
			languageLocales = languageLocalesService.merge(languageLocales);
		}

		BundleNames bundleNames = bundleNamesService.find(baseName, languageLocales);
		if (bundleNames == null) {
			bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(
					resourcebundles.getBundleName().getBaseName().getName(),
					resourcebundles.getBundleName().getLocale().getLocale());
			bundleNames.setBaseName(baseName);
			bundleNames.setLocale(languageLocales);
			bundleNames = bundleNamesService.merge(bundleNames);
		}

		PropertiesKeys propertiesKeys = propertiesKeysService.find(resourcebundles.getKey().getName());
		if (propertiesKeys == null) {
			propertiesKeys = ResourceBundlesDomainObjectFactory.getInstance()
					.newPropertiesKeys(resourcebundles.getKey().getName());
			propertiesKeys = propertiesKeysService.merge(propertiesKeys);
		}
		resourcebundles.setBundleName(bundleNames);
		resourcebundles.setKey(propertiesKeys);
		return super.merge(resourcebundles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(Resourcebundles resourcebundles) {
		BaseNames baseName = baseNamesService.find(resourcebundles.getBundleName().getBaseName().getName());
		if (baseName == null) {
			baseName = ResourceBundlesDomainObjectFactory.getInstance()
					.newBaseNames(resourcebundles.getBundleName().getBaseName().getName());
			baseName = baseNamesService.merge(baseName);
		}

		LanguageLocales languageLocales = languageLocalesService
				.find(resourcebundles.getBundleName().getLocale().getLocale());

		if (languageLocales == null) {
			languageLocales = ResourceBundlesDomainObjectFactory.getInstance()
					.newLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());
			languageLocales = languageLocalesService.merge(languageLocales);
		}

		BundleNames bundleNames = bundleNamesService.find(baseName, languageLocales);
		if (bundleNames == null) {
			bundleNames = ResourceBundlesDomainObjectFactory.getInstance().newBundleName(
					resourcebundles.getBundleName().getBaseName().getName(),
					resourcebundles.getBundleName().getLocale().getLocale());
			bundleNames.setBaseName(baseName);
			bundleNames.setLocale(languageLocales);
			bundleNames = bundleNamesService.merge(bundleNames);
		}

		PropertiesKeys propertiesKeys = propertiesKeysService.find(resourcebundles.getKey().getName());
		if (propertiesKeys == null) {
			propertiesKeys = ResourceBundlesDomainObjectFactory.getInstance()
					.newPropertiesKeys(resourcebundles.getKey().getName());
			propertiesKeys = propertiesKeysService.merge(propertiesKeys);
		}
		resourcebundles.setBundleName(bundleNames);
		resourcebundles.setKey(propertiesKeys);
		super.saveOrUpdate(resourcebundles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Resourcebundles> find(final String baseName, final String locale, final String key,
			final String value) {
		final String hqlString = HqlStringCreator.forResourcebundles(baseName, locale, key, value);
		final Query query = getQuery(hqlString);
		if (baseName != null && !baseName.isEmpty()) {
			query.setParameter("baseName", baseName);
		}
		if (locale != null && !locale.isEmpty()) {
			query.setParameter("locale", locale);
		}
		if (key != null && !key.isEmpty()) {
			query.setParameter("key", key);
		}
		if (value != null && !value.isEmpty()) {
			query.setParameter("value", value);
		}
		final List<Resourcebundles> resourcebundles = query.getResultList();
		return resourcebundles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final Locale locale) {
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(baseName, locale);
		for (final Resourcebundles resourcebundle : resourcebundles) {
			properties.setProperty(resourcebundle.getKey().getName(), resourcebundle.getValue());
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final String localeCode) {
		return getProperties(baseName, LocaleResolver.resolveLocale(localeCode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(List<Resourcebundles> resourcebundles) {
		for (Resourcebundles resourcebundle : resourcebundles) {
			delete(resourcebundle);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Resourcebundles resourcebundles) {
		resourcebundles.setBundleName(null);
		resourcebundles = super.merge(resourcebundles);
		super.delete(resourcebundles);
	}

}