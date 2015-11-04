package de.alpharogroup.db.resource.bundles.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.daos.ResourcebundlesDao;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.mapper.ResourcebundlesMapper;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;

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

}
