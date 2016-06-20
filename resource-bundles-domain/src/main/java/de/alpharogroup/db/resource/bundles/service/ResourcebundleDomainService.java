package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.CacheableMap;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.mapper.ResourcebundlesMapper;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundleService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
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
public class ResourcebundleDomainService extends
		AbstractDomainService<Integer, Resourcebundle, Resourcebundles, ResourcebundlesDao, ResourcebundlesMapper>
		implements ResourcebundleService {

	@Autowired
	private ResourcebundlesService resourcebundlesService;

	private final CacheableMap<String, String, DatabaseListResourceBundle> cache = new CacheableMap<String, String, DatabaseListResourceBundle>() {

		@Override
		public DatabaseListResourceBundle newValue(final String baseName, final String locale) {
			return new DatabaseListResourceBundle(baseName, LocaleResolver.resolveLocale(locale),
					resourcebundlesService);
		}
	};

	private DatabaseListResourceBundle getDatabaseListResourceBundle(final String baseName, final String locale) {
		return cache.getValue(baseName, locale);
	}

	/**
	 * Sets the resourcebundles dao.
	 *
	 * @param resourcebundlesDao
	 *            the new resourcebundles dao
	 */
	@Autowired
	public void setResourcebundlesDao(final ResourcebundlesDao resourcebundlesDao) {
		setDao(resourcebundlesDao);
	}

	/**
	 * Sets the specific {@link ResourcebundlesMapper}.
	 *
	 * @param mapper
	 *            the new {@link ResourcebundlesMapper}.
	 */
	@Autowired
	public void setResourcebundlesMapper(ResourcebundlesMapper mapper) {
		setMapper(mapper);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Resourcebundle> find(final String baseName, final String locale, final String key, final String value) {
		final String hqlString = HqlStringCreator.forResourcebundles(baseName, locale, key);
		final Query query = getDao().getQuery(hqlString);
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
		final List<Resourcebundles> entities = query.getResultList();
		final List<Resourcebundle> bos = getMapper().toDomainObjects(entities);
		return bos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(final String baseName, final Locale locale, final String key) {
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(final String baseName, final String locale, final String key) {
		return ListExtensions.getFirst(find(baseName, locale, key, null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundle> findResourceBundles(final String baseName, final Locale locale) {
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle contains(final String baseName, final Locale locale, final String key) {
		return find(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle getResourcebundle(final String baseName, final Locale locale, final String key) {
		return find(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(final Properties properties, final String baseName, final Locale locale) {
		resourcebundlesService.updateProperties(properties, baseName, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(final Properties properties, final String baseName, final Locale locale, final boolean update) {
		resourcebundlesService.updateProperties(properties, baseName, locale, update);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(final String baseName, final String locale, final String key) {
		return getString(baseName, locale, key, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(final String baseName, final String locale, final String key, final String defaultValue) {
		return getString(baseName, locale, key, defaultValue, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(final String baseName, final String locale, final String key, final Object[] params) {
		return getString(baseName, locale, key, null, params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(final String baseName, final String locale, final String key, final String defaultValue, final Object[] params) {
		final DatabaseListResourceBundle listResourceBundle = getDatabaseListResourceBundle(baseName, locale);
		final String value = ResourceBundleExtensions.getString(listResourceBundle, key, defaultValue, params);
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(final BundleKey bundleKey) {
		return getString(bundleKey.getBaseName(), LocaleExtensions.getLocaleFilenameSuffix(bundleKey.getLocale()),
				bundleKey.getResourceBundleKey().getKey(), bundleKey.getResourceBundleKey().getDefaultValue(),
				bundleKey.getResourceBundleKey().getParameters());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final Locale locale)
	{
		return resourcebundlesService.getProperties(baseName, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties(final String baseName, final String locale)
	{
		return resourcebundlesService.getProperties(baseName, locale);
	}

}
