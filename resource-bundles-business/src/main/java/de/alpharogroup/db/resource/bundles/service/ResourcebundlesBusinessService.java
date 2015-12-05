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
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.factories.ResourceBundlesDomainObjectFactory;
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
public class ResourcebundlesBusinessService extends AbstractBusinessService<Resourcebundles, Integer, ResourcebundlesDao> implements ResourcebundlesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
		final boolean update)
	{
		if(baseName == null || baseName.isEmpty()) {
			throw new IllegalArgumentException("Parameter baseName should not be null or empty.");
		}
		for (final Map.Entry<Object,Object> element : properties.entrySet()) {
			final String key = element.getKey().toString().trim();
			final String value = element.getValue().toString().trim();
			Resourcebundles resourcebundle = getResourcebundle(baseName, locale, key);
			if(resourcebundle != null) {
				if(update) {
					resourcebundle.setValue(value);
				}
			} else {
				resourcebundle = ResourceBundlesDomainObjectFactory
				.getInstance().newResourcebundles(baseName,
 LocaleExtensions.getLocaleFilenameSuffix(locale),
						key, value);
			}
			merge(resourcebundle);
		}
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
		if(baseName != null && !baseName.isEmpty()){
			query.setParameter("baseName", baseName);
		}
		if(locale != null && !locale.isEmpty()){
			query.setParameter("locale", locale);
		}
		if(key != null && !key.isEmpty()){
			query.setParameter("key", key);
		}
		if(value != null && !value.isEmpty()){
			query.setParameter("value", value);
		}
		final List<Resourcebundles> resourcebundles = query.getResultList();
		return resourcebundles;
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
			properties.setProperty(resourcebundle.getKey(), resourcebundle.getValue());
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

}