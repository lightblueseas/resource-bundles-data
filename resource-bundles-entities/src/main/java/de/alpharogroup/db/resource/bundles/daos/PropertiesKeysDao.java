package de.alpharogroup.db.resource.bundles.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link PropertiesKeysDao}.
 */
@Repository("propertiesKeysDao")
public class PropertiesKeysDao extends JpaEntityManagerDao<PropertiesKeys, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The entity manager. */
	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;
}
