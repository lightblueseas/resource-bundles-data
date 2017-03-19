package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.DefaultLocaleBaseNamesDao;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.DefaultLocaleBaseNamesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link DefaultLocaleBaseNamesBusinessService}.
 */
@Transactional
@Service("defaultLocaleBaseNamesService")
public class DefaultLocaleBaseNamesBusinessService extends AbstractBusinessService<DefaultLocaleBaseNames, Integer, DefaultLocaleBaseNamesDao> implements DefaultLocaleBaseNamesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
     * {@inheritDoc}
     */
	@Autowired
	public void setDefaultLocaleBaseNamesDao(final DefaultLocaleBaseNamesDao dao) {
		setDao(dao);
	}
	
	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public DefaultLocaleBaseNames find(String baseName) {
		final String hqlString = HqlStringCreator.forDefaultLocaleBaseNames(baseName, null);
		final Query query = getQuery(hqlString);
		if(baseName != null && !baseName.isEmpty()){
			query.setParameter("baseName", baseName);
		}		
		final List<DefaultLocaleBaseNames> defaultLocaleBaseNames = query.getResultList();
		return ListExtensions.getFirst(defaultLocaleBaseNames);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public LanguageLocales getDefaultLocale(BundleNames bundleNames) {
		if(bundleNames!=null) {
			String baseName = bundleNames.getBaseName().getName();
			return getDefaultLocale(baseName);
		}
		return null;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public LanguageLocales getDefaultLocale(String baseName) {
		DefaultLocaleBaseNames defaultLocaleBaseNames = find(baseName);
		if(defaultLocaleBaseNames !=null){
			return defaultLocaleBaseNames.getDefaultLocale();
		}
		return null;
	}

}
