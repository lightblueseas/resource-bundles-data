package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.LargeNameBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database base names of bundles.
 */
@Entity
@Table(name="basenames")
@Getter
@Setter
@NoArgsConstructor
public class BaseNames 
extends LargeNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	
}