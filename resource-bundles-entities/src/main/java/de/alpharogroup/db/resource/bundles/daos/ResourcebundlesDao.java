package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;

/**
 * The class {@link ResourcebundlesDao}.
 */
@Repository("resourcebundlesDao")
public class ResourcebundlesDao extends JpaEntityManagerDao<Resourcebundles, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
