package de.alpharogroup.db.resource.bundles.service.mapper;

import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.mapper.ResourcebundlesMapper;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;
import de.alpharogroup.locale.LocaleUtils;

@Transactional
@Service("resourcebundleMapperService")
public class ResourcebundleMapperService
	extends
	AbstractBusinessMapperService<Integer, Resourcebundle, Resourcebundles, ResourcebundlesDao, ResourcebundlesMapper>
	implements ResourcebundleService
{

	@Autowired
	public void setResourcebundlesDao(ResourcebundlesDao resourcebundlesDao)
	{
		setDao(resourcebundlesDao);
	}

	@Autowired
	public void setResourcebundlesMapper(ResourcebundlesMapper resourcebundlesMapper)
	{
		setMapper(resourcebundlesMapper);
	}

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

	@Override
	public Resourcebundle find(String baseName, Locale locale, String key)
	{
		return find(baseName, LocaleUtils.getLocaleFilenameSuffix(locale), key);
	}

	@Override
	public Resourcebundle find(String baseName, String locale, String key)
	{
		return ListExtensions.getFirst(find(baseName, locale, key, null));
	}

}
