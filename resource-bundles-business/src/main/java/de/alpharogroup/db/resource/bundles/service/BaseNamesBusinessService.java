package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.BaseNamesDao;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link BaseNamesBusinessService}.
 */
@Transactional
@Service("baseNamesService")
public class BaseNamesBusinessService extends AbstractBusinessService<BaseNames, Integer, BaseNamesDao> implements BaseNamesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public BaseNames find(String baseName) {
		final String hqlString = HqlStringCreator.forBaseNames(baseName);
		final Query query = getQuery(hqlString);
		if(baseName != null && !baseName.isEmpty()){
			query.setParameter("baseName", baseName);
		}
		final List<BaseNames> baseNames = query.getResultList();
		return ListExtensions.getFirst(baseNames);
	}

}
