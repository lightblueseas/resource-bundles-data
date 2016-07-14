package de.alpharogroup.db.resource.bundles.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.resource.bundles.entities.Applications;
import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;

import lombok.Getter;
import lombok.Setter;

@Repository("applicationsDao")
public class ApplicationsDao extends JpaEntityManagerDao<Applications, Integer>{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;

}
