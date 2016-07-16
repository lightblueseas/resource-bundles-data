package de.alpharogroup.db.resource.bundles.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import lombok.Getter;
import lombok.Setter;

@Repository("languagesDao")
public class LanguagesDao extends JpaEntityManagerDao<Languages, Integer>{

	private static final long serialVersionUID = 1L;

	/** The entity manager. */
	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;

}
