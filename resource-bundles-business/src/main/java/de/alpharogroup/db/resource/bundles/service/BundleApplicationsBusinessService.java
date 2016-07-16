package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.BundleApplicationsDao;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link BundleApplicationsBusinessService}.
 */
@Transactional
@Service("bundleApplicationsService")
public class BundleApplicationsBusinessService extends AbstractBusinessService<BundleApplications, Integer, BundleApplicationsDao> implements BundleApplicationsService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
     * {@inheritDoc}
     */
	@Autowired
	public void setBundleApplicationsDao(final BundleApplicationsDao dao) {
		setDao(dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BundleApplications find(String name) {
		final String hqlString = HqlStringCreator.forBundleApplications(name);
		final Query query = getQuery(hqlString);
		if(name != null && !name.isEmpty()){
			query.setParameter("name", name);
		}
		final List<BundleApplications> applications = query.getResultList();
		return ListExtensions.getFirst(applications);
	}
	
}
