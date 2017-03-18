package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.name.LargeNameBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
 * Entity class for saving in database base names of bundles.
 */
@Entity
@Table(name="basenames")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseNames 
extends LargeNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	

	/**
	 * Instantiates a new {@link BaseNames} entity object.
	 *
	 * @param name the name
	 */
	@Builder
	BaseNames(String name) {
		super(name);
	}
}