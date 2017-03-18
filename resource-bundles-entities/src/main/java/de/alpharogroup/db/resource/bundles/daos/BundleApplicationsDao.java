package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;

@Repository("bundleApplicationsDao")
public class BundleApplicationsDao extends JpaEntityManagerDao<BundleApplications, Integer>{

	private static final long serialVersionUID = 1L;

}
