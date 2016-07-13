package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.ExtraLargeNameBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database applications with the corresponding {@link BundleNames}.
 */
@Entity
@Table(name="applications")
@Getter
@Setter
@NoArgsConstructor
public class Applications 
extends ExtraLargeNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	
		/** The bundle names of this application. */
	@ManyToMany(fetch=FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@JoinTable(name = "application_bundlenames", joinColumns = { 
			@JoinColumn(name = "application_id", referencedColumnName = "id") }, inverseJoinColumns = { 
			@JoinColumn(name = "bundlenames_id", referencedColumnName = "id") })
	private Set<BundleNames> bundleNames = new HashSet<BundleNames>();
}
