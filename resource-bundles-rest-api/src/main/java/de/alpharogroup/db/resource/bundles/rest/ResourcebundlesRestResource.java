package de.alpharogroup.db.resource.bundles.rest;

import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.rest.api.ResourcebundlesResource;
import de.alpharogroup.db.resource.bundles.service.DatabaseListResourceBundle;
import de.alpharogroup.db.resource.bundles.service.mapper.api.ResourcebundleService;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.locale.ResourceBundleExtensions;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link ResourcebundlesRestResource} provides an implementation of the inteface {@link ResourcebundlesResource}.
 */
public class ResourcebundlesRestResource
	extends AbstractRestfulResource<Integer, Resourcebundle, ResourcebundleService>
	implements ResourcebundlesResource
{	

	private CacheableMap<String, String, DatabaseListResourceBundle> cache = new CacheableMap<String, String, DatabaseListResourceBundle>() {
		
		@Override
		public DatabaseListResourceBundle newValue(String baseName, String locale) {
			return new DatabaseListResourceBundle(baseName, LocaleResolver.resolveLocale(locale));
		}
	};

	private DatabaseListResourceBundle getDatabaseListResourceBundle(String baseName, String locale) {
		return cache.getValue(baseName, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	public Resourcebundle get(String id)
	{
		final ResourcebundleService resourcebundleService = getBusinessMapperService();
		Resourcebundle resourcebundle = resourcebundleService.read(Integer.valueOf(id));
		return resourcebundle;
	}

	/**
	 * {@inheritDoc}
	 */
	public Resourcebundle find(String baseName, String locale, String key)
	{
		Resourcebundle resourcebundle = getBusinessMapperService().find(baseName, locale, key);
		return resourcebundle;
	}

	@Override
	public String getString(String baseName, String locale, String key) {
		final DatabaseListResourceBundle listResourceBundle = getDatabaseListResourceBundle(baseName, locale);
		final String value = ResourceBundleExtensions.getString(listResourceBundle, key);
		return value;
	}

	@Override
	public String getString(String baseName, String locale, String key, Object[] params) {
		final DatabaseListResourceBundle listResourceBundle = getDatabaseListResourceBundle(baseName, locale);
		final String value = ResourceBundleExtensions.getString(listResourceBundle, key, params);
		return value;
	}

}
