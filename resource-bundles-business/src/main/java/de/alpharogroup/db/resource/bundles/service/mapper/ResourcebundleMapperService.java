package de.alpharogroup.db.resource.bundles.service.mapper;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.mapper.ResourcebundlesMapper;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;
import de.alpharogroup.db.resource.bundles.service.mapper.api.ResourcebundleService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;

/**
 * The class {@link ResourcebundleMapperService}.
 */
@Transactional
@Service("resourcebundleMapperService")
public class ResourcebundleMapperService
	extends
	AbstractBusinessMapperService<Integer, Resourcebundle, Resourcebundles, ResourcebundlesDao, ResourcebundlesMapper>
	implements ResourcebundleService
{

	@Autowired
	private ResourcebundlesService resourcebundlesService;

	/**
	 * Sets the resourcebundles dao.
	 *
	 * @param resourcebundlesDao the new resourcebundles dao
	 */
	@Autowired
	public void setResourcebundlesDao(ResourcebundlesDao resourcebundlesDao)
	{
		setDao(resourcebundlesDao);
	}

	/**
	 * Sets the resourcebundles mapper.
	 *
	 * @param resourcebundlesMapper the new resourcebundles mapper
	 */
	@Autowired
	public void setResourcebundlesMapper(ResourcebundlesMapper resourcebundlesMapper)
	{
		setMapper(resourcebundlesMapper);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Resourcebundle> find(String baseName, String locale, String key, String value)
	{
		String hqlString = HqlStringCreator.forResourcebundles(baseName, locale, key);
		final Query query = getDao().getQuery(hqlString);
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
		final List<Resourcebundles> entities = query.getResultList();
		final List<Resourcebundle> bos = getMapper().toBusinessObjects(entities);		
		return bos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(String baseName, Locale locale, String key)
	{
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle find(String baseName, String locale, String key)
	{
		return ListExtensions.getFirst(find(baseName, locale, key, null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Resourcebundle> findResourceBundles(String baseName, Locale locale)
	{
		return find(baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle contains(String baseName, Locale locale, String key)
	{
		return find(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resourcebundle getResourcebundle(String baseName, Locale locale, String key) {
		return find(baseName, locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(Properties properties, String baseName, Locale locale) {
		resourcebundlesService.updateProperties(properties, baseName, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperties(Properties properties, String baseName, Locale locale, boolean update) {
		resourcebundlesService.updateProperties(properties, baseName, locale, update);
	}

}
