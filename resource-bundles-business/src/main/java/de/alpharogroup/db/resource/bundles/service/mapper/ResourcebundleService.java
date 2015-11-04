package de.alpharogroup.db.resource.bundles.service.mapper;

import java.util.List;
import java.util.Locale;

import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.db.service.entitymapper.BusinessMapperService;

public interface ResourcebundleService extends BusinessMapperService<Integer, Resourcebundle>{
	
	Resourcebundle find(String baseName, Locale locale, String key);
	
	Resourcebundle find(String baseName, String locale, String key);
	
	List<Resourcebundle> find(String baseName, String locale, String key, String value);
}
