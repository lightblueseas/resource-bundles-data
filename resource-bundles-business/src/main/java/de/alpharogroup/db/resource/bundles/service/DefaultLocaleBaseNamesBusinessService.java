package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.DefaultLocaleBaseNamesDao;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
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
	@SuppressWarnings("unchecked")
	@Override
	public DefaultLocaleBaseNames find(String baseName, String defaultLocale) {
		final String hqlString = HqlStringCreator.forDefaultLocaleBaseNames(baseName, defaultLocale);
		final Query query = getQuery(hqlString);
		if(baseName != null && !baseName.isEmpty()){
			query.setParameter("baseName", baseName);
		}
		if(defaultLocale != null && !defaultLocale.isEmpty()){
			query.setParameter("locale", defaultLocale);
		}
		
		final List<DefaultLocaleBaseNames> defaultLocaleBaseNames = query.getResultList();
		return ListExtensions.getFirst(defaultLocaleBaseNames);
	}

}
