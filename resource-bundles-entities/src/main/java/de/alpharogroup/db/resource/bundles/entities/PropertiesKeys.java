package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.ExtraLargeNameBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database properties keys.
 */
@Entity
@Table(name="properties_keys")
@Getter
@Setter
@NoArgsConstructor
public class PropertiesKeys 
extends ExtraLargeNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	
}