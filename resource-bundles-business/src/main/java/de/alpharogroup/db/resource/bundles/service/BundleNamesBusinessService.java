package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.BundleNamesDao;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link BundleNamesBusinessService}.
 */
@Transactional
@Service("bundleNamesService")
public class BundleNamesBusinessService extends AbstractBusinessService<BundleNames, Integer, BundleNamesDao> implements BundleNamesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
     * {@inheritDoc}
     */
	@Autowired
	public void setBundleNamesDao(final BundleNamesDao dao) {
		setDao(dao);
	}
	
	public List<BundleNames> find(BaseNames baseName) {
		if(baseName != null) {
			return find(baseName.getName(), null);
		}
		return null;		
	}
	
	public BundleNames find(BaseNames baseName, LanguageLocales languageLocales) {
		String bn = null;
		String ll = null;
		if(baseName != null) {
			bn = baseName.getName();
		}
		if(languageLocales != null) {
			ll = languageLocales.getLocale();
		}
		if(bn != null && ll != null) {
			return ListExtensions.getFirst(find(bn,ll));		
		}
		return null;
	}
	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BundleNames> find(String baseName, String locale) {
		final String hqlString = HqlStringCreator.forBundleNames(baseName, locale);
		final Query query = getQuery(hqlString);
		if(baseName != null && !baseName.isEmpty()){
			query.setParameter("baseName", baseName);
		}
		if(locale != null && !locale.isEmpty()){
			query.setParameter("locale", locale);
		}
		
		final List<BundleNames> bundleNames = query.getResultList();
		return bundleNames;
	}

}
