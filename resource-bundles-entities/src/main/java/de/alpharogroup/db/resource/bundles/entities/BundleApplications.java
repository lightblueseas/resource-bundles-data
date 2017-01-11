package de.alpharogroup.db.resource.bundles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.alpharogroup.db.entity.name.unique.ExtraLargeUNameBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * Entity class for saving in database applications with the corresponding {@link BundleNames}.
 */
@Entity
@Table(name="bundle_applications")
@Getter
@Setter
@NoArgsConstructor
public class BundleApplications 
extends ExtraLargeUNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	
		/** The bundle names of this application. */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "bundle_application_bundlenames", joinColumns = { 
			@JoinColumn(name = "application_id", referencedColumnName = "id") }, inverseJoinColumns = { 
			@JoinColumn(name = "bundlenames_id", referencedColumnName = "id") })
	private Set<BundleNames> bundleNames = new HashSet<BundleNames>();
}